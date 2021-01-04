package cat.yoink.client.feature.property.type;

import cat.yoink.client.feature.module.Module;
import cat.yoink.client.feature.property.BasicProperty;

import java.lang.reflect.Field;

public final class BooleanProperty extends BasicProperty<Boolean>
{
    private boolean value;

    public BooleanProperty(Module module, Field field, boolean value)
    {
        super(module, field);
        this.value = value;
    }

    @Override
    public Boolean getValue()
    {
        return value;
    }

    @Override
    public void setValue(Boolean value)
    {
        super.setValue(value);
        this.value = value;
        super.setValue(value);
    }
}
