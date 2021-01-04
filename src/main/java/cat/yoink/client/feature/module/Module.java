package cat.yoink.client.feature.module;

import cat.yoink.client.feature.Feature;
import cat.yoink.client.feature.property.Setting;

import java.util.List;

public interface Module extends Feature
{
    String getName();

    int getBind();
    void setBind(int bind);

    boolean isEnabled();
    void setEnabled(boolean enabled);

    List<Setting<?>> getProperties();
    void addProperty(Setting<?> property);
}
