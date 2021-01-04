package cat.yoink.client.feature.property;

import cat.yoink.client.feature.module.Module;
import cat.yoink.client.feature.property.annotation.Property;
import cat.yoink.client.feature.property.type.BooleanProperty;
import cat.yoink.client.feature.property.type.ColorProperty;
import cat.yoink.client.feature.property.type.ListProperty;
import cat.yoink.client.feature.property.type.NumberProperty;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Scanner
{
    INSTANCE;

    public List<Setting<?>> scan(Module module)
    {
        return Arrays.stream(module.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Property.class))
                .map(field -> { try {
                        Property data = field.getAnnotation(Property.class);
                        Object value = field.get(module);

                        if (value instanceof Boolean) return new BooleanProperty(module, field, (Boolean) value);
                        else if (value instanceof String) return new ListProperty(module, field, (String) value, data.list());
                        else if (value instanceof Color) return new ColorProperty(module, field, (Color) value);
                        else if (value instanceof Double) return new NumberProperty(module, field, (Double) value, data.number());
                        else return null;
                    } catch (Exception ignored) { return null; }
                }).collect(Collectors.toList());
    }
}
