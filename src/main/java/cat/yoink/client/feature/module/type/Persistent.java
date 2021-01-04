package cat.yoink.client.feature.module.type;

import cat.yoink.client.feature.module.Module;
import cat.yoink.client.feature.module.ModuleData;
import cat.yoink.client.feature.property.Setting;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public abstract class Persistent implements Module
{
    private final String[] names;
    private final List<Setting<?>> properties = new ArrayList<>();

    protected Persistent()
    {
        names = getClass().getAnnotation(ModuleData.class).names();
        if (!(this instanceof Toggleable)) MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public String getName()
    {
        return names[0];
    }

    @Override
    public int getBind()
    {
        return Keyboard.KEY_NONE;
    }

    @Override
    public void setBind(int bind) { }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) { }

    @Override
    public List<Setting<?>> getProperties()
    {
        return properties;
    }

    @Override
    public void addProperty(Setting<?> property)
    {
        properties.add(property);
    }
}
