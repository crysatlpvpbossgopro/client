package cat.yoink.client.feature.registry;

import cat.yoink.client.feature.Feature;

import java.io.File;
import java.util.List;

public interface Registry<T> extends Feature
{
    File dir = new File(mc.gameDir + File.separator + "client");

    List<T> getRegistry();

    void save();

    void load();
}
