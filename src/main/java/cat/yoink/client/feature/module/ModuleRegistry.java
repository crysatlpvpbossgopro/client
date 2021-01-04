package cat.yoink.client.feature.module;

import cat.yoink.client.feature.property.Scanner;
import cat.yoink.client.feature.property.Setting;
import cat.yoink.client.feature.property.type.BooleanProperty;
import cat.yoink.client.feature.property.type.ColorProperty;
import cat.yoink.client.feature.property.type.ListProperty;
import cat.yoink.client.feature.property.type.NumberProperty;
import cat.yoink.client.feature.registry.BasicRegistry;
import com.google.common.reflect.ClassPath;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;

public final class ModuleRegistry extends BasicRegistry<Module>
{
    public static final ModuleRegistry INSTANCE = new ModuleRegistry();

    @SuppressWarnings("UnstableApiUsage")
    private ModuleRegistry()
    {
        super(new File(dir + File.separator + "modules.json"));

        try { ClassPath.from(Thread.currentThread().getContextClassLoader()).getTopLevelClassesRecursive("cat.yoink.client.feature.module.impl").stream()
                    .map(ClassPath.ClassInfo::load)
                    .filter(Module.class::isAssignableFrom)
                    .map(clazz -> { try { return (Module) clazz.newInstance(); } catch (Exception e) { e.printStackTrace(); return null; } } )
                    .forEach(this::register); } catch (Exception e) { e.printStackTrace(); }
        getRegistry().forEach(module -> Scanner.INSTANCE.scan(module).forEach(module::addProperty));
    }

    @Override
    public void save()
    {
        try {
            JsonArray array = new JsonArray();
            getRegistry().forEach(module -> {
                JsonObject json = new JsonObject();
                json.addProperty("name", module.getName());
                json.addProperty("bind", module.getBind());
                json.addProperty("enabled", module.isEnabled());
                JsonArray properties = new JsonArray();
                for (Setting<?> p : module.getProperties())
                {
                    JsonObject property = new JsonObject();
                    property.addProperty("name", p.getName());
                    if (p instanceof BooleanProperty) property.addProperty("value", ((BooleanProperty) p).getValue());
                    else if (p instanceof ListProperty) property.addProperty("value", ((ListProperty) p).getValue());
                    else if (p instanceof NumberProperty)property.addProperty("value", ((NumberProperty) p).getValue());
                    else if (p instanceof ColorProperty) property.addProperty("value", ((ColorProperty) p).getValue().getRGB());
                    properties.add(property);
                }
                json.add("properties", properties);
                array.add(json);
            });
            FileUtils.forceDelete(config);
            FileUtils.write(config, gson.toJson(array), StandardCharsets.UTF_8);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void load()
    {
        try {
            JsonArray array = new JsonParser().parse(FileUtils.readFileToString(config, StandardCharsets.UTF_8)).getAsJsonArray();
            array.forEach(element -> {
                JsonObject json = element.getAsJsonObject();
                Module module = ModuleRegistry.INSTANCE.getRegistry().stream().filter(m -> m.getName().equals(json.get("name").getAsString())).findAny().orElse(null);
                assert module != null;
                module.setBind(json.get("bind").getAsInt());
                module.setEnabled(json.get("enabled").getAsBoolean());
                json.get("properties").getAsJsonArray().forEach(ps -> {
                    JsonObject pJson = ps.getAsJsonObject();
                    Setting<?> property = module.getProperties().stream().filter(s -> s.getName().equals(pJson.get("name").getAsString())).findAny().orElse(null);
                    assert property != null;
                    if (property instanceof BooleanProperty) ((BooleanProperty) property).setValue(pJson.get("value").getAsBoolean());
                    else if (property instanceof ListProperty) ((ListProperty) property).setValue(pJson.get("value").getAsString());
                    else if (property instanceof NumberProperty) ((NumberProperty) property).setValue(pJson.get("value").getAsDouble());
                    else if (property instanceof ColorProperty) ((ColorProperty) property).setValue(new Color(pJson.get("value").getAsInt()));
                });
            });
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Module getModule(Class<? extends Module> klazz)
    {
        return getRegistry().stream().filter(module -> module.getClass().equals(klazz)).findAny().orElse(null);
    }
}
