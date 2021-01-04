package cat.yoink.client.feature.property;

import cat.yoink.client.feature.Feature;

public interface Setting<T> extends Feature
{
    String getName();

    T getValue();

    void setValue(T value);
}
