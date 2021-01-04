package cat.yoink.client.feature.module.impl;

import cat.yoink.client.Client;
import cat.yoink.client.feature.module.ModuleData;
import cat.yoink.client.feature.module.type.Persistent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleData(names = {"Example1", "Test1", "One"})
public final class ModuleOne extends Persistent
{
    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event)
    {
        if (isSafe())
            Client.logger.info(event.getEntity().getName() + " jumped");
    }
}
