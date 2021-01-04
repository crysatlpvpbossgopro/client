package cat.yoink.client.feature.module.impl;

import cat.yoink.client.Client;
import cat.yoink.client.feature.module.ModuleData;
import cat.yoink.client.feature.module.type.Toggleable;
import cat.yoink.client.feature.property.annotation.Number;
import cat.yoink.client.feature.property.annotation.Property;
import cat.yoink.client.feature.property.type.NumberProperty;
import org.lwjgl.input.Keyboard;

import java.awt.*;

@ModuleData(names = {"Example2", "Test2", "Two"}, bind = Keyboard.KEY_R)
public final class ModuleTwo extends Toggleable
{
    @Property(name = "Example Setting", number = @Number(max = 10000))
    public double value = 100;
    @Property(name = "Color test thing")
    public Color test = new Color(4, 2, 192);

    @Override
    protected void onEnable()
    {
        ((NumberProperty) getProperties().get(0)).setValue(value + 1);
        Client.logger.info(value);
        Client.logger.info(getName() + " enabled");
    }

    @Override
    protected void onDisable()
    {
        System.out.println(getName() + " disabled");
    }
}
