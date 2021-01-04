package cat.yoink.client.feature.command;

public abstract class BasicCommand implements Command
{
    private final String[] names;
    private final String usage;

    protected BasicCommand()
    {
        CommandData data = getClass().getAnnotation(CommandData.class);
        names = data.names();
        usage = data.usage();
    }

    protected void printUsage()
    {
        printChatMessage("Usage: " + usage);
    }

    @Override
    public String getName()
    {
        return names[0];
    }

    @Override
    public String[] getNames()
    {
        return names;
    }

    @Override
    public String getUsage()
    {
        return usage;
    }
}
