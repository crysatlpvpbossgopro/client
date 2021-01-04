package cat.yoink.client.feature.module.type;

import cat.yoink.client.feature.module.ModuleData;
import net.minecraftforge.common.MinecraftForge;

public abstract class Toggleable extends Persistent
{
    private int bind;
    private boolean enabled;

    protected Toggleable()
    {
        ModuleData data = getClass().getAnnotation(ModuleData.class);
        bind = data.bind();
        enabled = data.enabled();
    }

    protected void onEnable() { }
    protected void onDisable() { }

    @Override
    public int getBind()
    {
        return bind;
    }

    @Override
    public void setBind(int bind)
    {
        this.bind = bind;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
        if (enabled)
        {
            onEnable();
            MinecraftForge.EVENT_BUS.register(this);
        }
        else
        {
            onDisable();
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
