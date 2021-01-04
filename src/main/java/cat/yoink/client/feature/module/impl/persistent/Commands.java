package cat.yoink.client.feature.module.impl.persistent;

import cat.yoink.client.feature.command.Command;
import cat.yoink.client.feature.command.CommandRegistry;
import cat.yoink.client.feature.module.ModuleData;
import cat.yoink.client.feature.module.type.Persistent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.Optional;

@ModuleData(names = {"Commands", "Command"})
public final class Commands extends Persistent
{
    public String prefix = ".";

    @SubscribeEvent
    public void onClientChat(ClientChatEvent event)
    {
        if (isSafe() && event.getMessage().startsWith(prefix))
        {
            event.setCanceled(true);
            mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());

            String[] split = event.getMessage().split(" ");
            Optional<Command> command = CommandRegistry.INSTANCE.getRegistry().stream().filter(c -> Arrays.stream(c.getNames()).anyMatch(s -> s.equalsIgnoreCase(split[0].substring(prefix.length())))).findAny();
            if (command.isPresent()) command.get().execute(Arrays.copyOfRange(split, 1, split.length));
            else printChatMessage("Invalid command.");
        }
    }
}
