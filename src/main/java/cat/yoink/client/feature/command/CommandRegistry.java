package cat.yoink.client.feature.command;

import cat.yoink.client.feature.module.ModuleRegistry;
import cat.yoink.client.feature.module.impl.persistent.Commands;
import cat.yoink.client.feature.registry.BasicRegistry;
import com.google.common.reflect.ClassPath;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public final class CommandRegistry extends BasicRegistry<Command>
{
    public static final CommandRegistry INSTANCE = new CommandRegistry();

    @SuppressWarnings("UnstableApiUsage")
    public CommandRegistry()
    {
        super(new File(dir + File.separator + "prefix.json"));

        try { ClassPath.from(Thread.currentThread().getContextClassLoader()).getTopLevelClassesRecursive("cat.yoink.client.feature.command.impl").stream()
                .map(ClassPath.ClassInfo::load)
                .filter(Command.class::isAssignableFrom)
                .map(clazz -> { try { return (Command) clazz.newInstance(); } catch (Exception e) { e.printStackTrace(); return null; } } )
                .forEach(this::register); } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void save()
    {
        JsonObject json = new JsonObject();
        json.addProperty("prefix", ((Commands) ModuleRegistry.INSTANCE.getModule(Commands.class)).prefix);
        try { FileUtils.write(config, gson.toJson(json), StandardCharsets.UTF_8); } catch (Exception ignored) { }
    }

    @Override
    public void load()
    {
        JsonObject json = new JsonObject();
        json.addProperty("prefix", ((Commands) ModuleRegistry.INSTANCE.getModule(Commands.class)).prefix);
        try { ((Commands) ModuleRegistry.INSTANCE.getModule(Commands.class)).prefix = new JsonParser().parse(FileUtils.readFileToString(config, StandardCharsets.UTF_8)).getAsJsonObject().get("prefix").getAsString(); } catch (Exception ignored) { }
    }
}
