package fr.minemobs.puffermod.event;

import fr.minemobs.puffermod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Main.modId, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TestChatEvent {

    @SubscribeEvent
    public static void chatEvent(ClientChatReceivedEvent event){
        String a = "msg." + Main.modId + ".";

        String[] msgList = {a + "notwrite", a + "love"};

        Random rnd = new Random();

        String pseudo = Minecraft.getInstance().player.getName().getFormattedText();

        StringTextComponent stx = new StringTextComponent("<" + pseudo + "> ");

        TranslationTextComponent ttx = new TranslationTextComponent(msgList[rnd.nextInt(msgList.length)]);

        if(event.getMessage().getUnformattedComponentText().toLowerCase().substring(3).contains("ez")){
            event.setMessage(stx.appendSibling(ttx));
        }
    }
}
