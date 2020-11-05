package fr.minemobs.puffermod.utils;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.client.entity.render.RedAmongusRenderer;
import fr.minemobs.puffermod.client.entity.render.TestEntityRender;
import fr.minemobs.puffermod.client.gui.ElectricFurnaceScreen;
import fr.minemobs.puffermod.client.gui.SawScreen;
import fr.minemobs.puffermod.init.BlockInit;
import fr.minemobs.puffermod.init.ModContainerTypes;
import fr.minemobs.puffermod.init.ModEntityTypes;
import fr.minemobs.puffermod.object.blocks.ClearGlass;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Main.modId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {

    public static final Map<ClearGlass, TextureAtlasSprite> TEXTURES = new HashMap<>();

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        OBJLoader.INSTANCE.loadModel(new OBJModel.ModelSettings(new ResourceLocation("puffermod:models/obj/test.obj"),
                false, false,true, false, "puffermod:models/obj/test.mtl"));
		ScreenManager.registerFactory(ModContainerTypes.EXAMPLE_FURNACE.get(), SawScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TEST_ENTITY.get(), TestEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RED_AMONGUS_ENTITY.get(), RedAmongusRenderer::new);
        RenderTypeLookup.setRenderLayer(BlockInit.clear_glass.get(), RenderType.getCutoutMipped());
    }
}
