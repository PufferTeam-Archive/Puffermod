package fr.minemobs.puffermod.utils;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.client.gui.SawScreen;
import fr.minemobs.puffermod.init.ItemInit;
import fr.minemobs.puffermod.init.ModContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Main.modId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e){

		ScreenManager.registerFactory(ModContainerTypes.EXAMPLE_FURNACE.get(), SawScreen::new);

        ItemInit.puffer_coins.get().addPropertyOverride(new ResourceLocation(Main.modId, "count"), new IItemPropertyGetter() {
			
			@Override
			public float call(ItemStack stack, World worldIn, LivingEntity entityIn) {
				switch (stack.getCount()) {
				case 1:
					return 0.25f;
					
				case 2:
					return 0.5f;
					
				case 3:
					return 0.75f;
					
				case 4:
					return 1.0f;

				default:
					return 0.0f;
				}
			}
		});

		ItemInit.golden_coins.get().addPropertyOverride(new ResourceLocation(Main.modId, "count"), new IItemPropertyGetter() {

			@Override
			public float call(ItemStack stack, World worldIn, LivingEntity entityIn) {
				switch (stack.getCount()) {
					case 1:
						return 0.25f;

					case 2:
						return 0.5f;

					case 3:
						return 0.75f;

					case 4:
						return 1.0f;

					default:
						return 0.0f;
				}
			}
		});
    }
}
