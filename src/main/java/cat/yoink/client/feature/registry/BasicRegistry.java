package cat.yoink.client.feature.registry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicRegistry<T> implements Registry<T>
{
    protected final File config;
    private final List<T> registry = new ArrayList<>();

    protected BasicRegistry(File config)
    {
        this.config = config;
    }

    protected void register(T t)
    {
        registry.add(t);
    }

    protected void unregister(T t)
    {
        registry.remove(t);
    }

    public List<T> getRegistry()
    {
        return registry;
    }
}
