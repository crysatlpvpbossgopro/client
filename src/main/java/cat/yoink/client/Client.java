package cat.yoink.client;

import cat.yoink.client.feature.command.CommandRegistry;
import cat.yoink.client.feature.module.ModuleRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Client
{
    INSTANCE;

    public static final Logger logger = LogManager.getLogger("Client");

    public void initialize()
    {
        logger.info("Loading...");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ModuleRegistry.INSTANCE.save();
            CommandRegistry.INSTANCE.save();
        }));

        ModuleRegistry.INSTANCE.load();
        CommandRegistry.INSTANCE.load();

        logger.info("Finished");
    }
}
