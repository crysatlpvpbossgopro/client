package cat.yoink.client.feature.property.type;

import cat.yoink.client.feature.module.Module;
import cat.yoink.client.feature.property.BasicProperty;
import cat.yoink.client.feature.property.annotation.Number;

import java.lang.reflect.Field;

public final class NumberProperty extends BasicProperty<Double>
{
    private final double minimum;
    private final double maximum;
    private final double increment;
    private double value;

    public NumberProperty(Module module, Field field, double value, Number number)
    {
        super(module, field);
        this.value = value;
        minimum = number.min();
        maximum = number.max();
        increment = number.increment();
    }

    @Override
    public Double getValue()
    {
        return value;
    }

    @Override
    public void setValue(Double value)
    {
        double precision = 1 / this.increment;
        this.value = Math.round(Math.max(this.minimum, Math.min(this.maximum, value)) * precision) / precision;
        super.setValue(value);
    }

    public double getMinimum()
    {
        return minimum;
    }

    public double getMaximum()
    {
        return maximum;
    }

    public double getIncrement()
    {
        return increment;
    }
}
