package cat.yoink.client.feature;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public interface Feature
{
    Minecraft mc = Minecraft.getMinecraft();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    default boolean isSafe()
    {
        return mc.player != null && mc.world != null;
    }

    default void printChatMessage(String message)
    {
        mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(("&7[&cClient&7] " + message).replace("&", "\u00A7")));
    }
}
