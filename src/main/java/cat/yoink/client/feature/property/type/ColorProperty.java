package cat.yoink.client.feature.property.type;

import cat.yoink.client.feature.module.Module;
import cat.yoink.client.feature.property.BasicProperty;

import java.awt.*;
import java.lang.reflect.Field;

public final class ColorProperty extends BasicProperty<Color>
{
    private Color value;

    public ColorProperty(Module module, Field field, Color value)
    {
        super(module, field);
        this.value = value;
    }

    @Override
    public Color getValue()
    {
        return value;
    }

    @Override
    public void setValue(Color value)
    {
        super.setValue(value);
        this.value = value;
        super.setValue(value);
    }
}
