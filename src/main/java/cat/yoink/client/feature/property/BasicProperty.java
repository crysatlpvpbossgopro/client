package cat.yoink.client.feature.property;

import cat.yoink.client.feature.module.Module;
import cat.yoink.client.feature.property.annotation.Property;

import java.lang.reflect.Field;

public abstract class BasicProperty<T> implements Setting<T>
{
    private final Module module;
    private final Field field;
    private final String name;

    public BasicProperty(Module module, Field field)
    {
        this.module = module;
        this.field = field;
        this.name = field.getAnnotation(Property.class).name();
    }

    @Override
    public void setValue(T value)
    {
        try { field.set(module, getValue()); }
        catch (Exception ignored) { }
    }

    @Override
    public String getName()
    {
        return name;
    }
}
