package cat.yoink.client.feature.property.type;

import cat.yoink.client.feature.module.Module;
import cat.yoink.client.feature.property.BasicProperty;
import cat.yoink.client.feature.property.annotation.Values;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public final class ListProperty extends BasicProperty<String>
{
    private final List<String> values;
    private int index;

    public ListProperty(Module module, Field field, String value, Values values)
    {
        super(module, field);
        this.values = Arrays.asList(values.value());
        if (!this.values.contains(value)) this.values.add(value);
        index = this.values.indexOf(value);
    }

    @Override
    public String getValue()
    {
        return values.get(index);
    }

    @Override
    public void setValue(String value)
    {
        index = values.indexOf(value);
        super.setValue(value);
    }

    public void cycleForward()
    {
        if (index < values.size() - 1) index++;
        else index = 0;
        super.setValue(getValue());
    }

    public void cycleBackward()
    {
        if (index > 0) index--;
        else index = values.size() - 1;
        super.setValue(getValue());
    }
}
