package cat.yoink.client.feature.command.impl;

import cat.yoink.client.feature.command.BasicCommand;
import cat.yoink.client.feature.command.CommandData;
import cat.yoink.client.feature.module.ModuleRegistry;
import cat.yoink.client.feature.module.impl.persistent.Commands;

@CommandData(names = {"prefix", "p"}, usage = "prefix <char>")
public final class Prefix extends BasicCommand
{
    @Override
    public void execute(String... args)
    {
        if (args.length == 1)
        {
            ((Commands) ModuleRegistry.INSTANCE.getModule(Commands.class)).prefix = args[0];
            printChatMessage(String.format("Set prefix to %s.", args[0]));
        }
        else printUsage();
    }
}
