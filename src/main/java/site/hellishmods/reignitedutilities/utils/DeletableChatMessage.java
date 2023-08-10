package site.hellishmods.reignitedutilities.utils;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class DeletableChatMessage {
    ITextComponent component;
    NewChatGui chat;
    int id;

    public boolean checkForClient() {
        return FMLEnvironment.dist!=Dist.CLIENT;
    }

    public DeletableChatMessage(ITextComponent component) {
        if (checkForClient()) return;

        this.component = component;

        Minecraft mc = Minecraft.getInstance();
        chat = mc.gui.getChat();

        id = new Random().nextInt(Integer.MAX_VALUE);
        // chat.addMessage(component, id);
    }

    public void delete() {
        if (checkForClient()) return;
    }
}
