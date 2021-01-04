package cat.yoink.client;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "client")
public final class EntryPoint
{
    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        Client.INSTANCE.initialize();
    }
}
