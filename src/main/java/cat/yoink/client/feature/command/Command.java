package cat.yoink.client.feature.command;

import cat.yoink.client.feature.Feature;

public interface Command extends Feature
{
    String getName();
    String[] getNames();

    String getUsage();

    void execute(String... args);
}
