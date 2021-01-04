package cat.yoink.client.feature.module.impl.persistent;

import cat.yoink.client.feature.module.ModuleData;
import cat.yoink.client.feature.module.ModuleRegistry;
import cat.yoink.client.feature.module.type.Persistent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@ModuleData(names = {"Keybinds", "Binds", "Bind"})
public final class Binds extends Persistent
{
    @SubscribeEvent
    public void onInputKeyInput(InputEvent.KeyInputEvent event)
    {
        if (isSafe() && Keyboard.getEventKeyState() && Keyboard.getEventKey() != Keyboard.KEY_NONE)
        {
            ModuleRegistry.INSTANCE.getRegistry().stream()
                    .filter(module -> module.getBind() == Keyboard.getEventKey())
                    .forEach(module -> module.setEnabled(!module.isEnabled()));
        }
    }
}
