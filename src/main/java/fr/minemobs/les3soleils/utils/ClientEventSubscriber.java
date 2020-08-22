package fr.minemobs.les3soleils.utils;

import fr.minemobs.les3soleils.Main;
import fr.minemobs.les3soleils.init.ItemInit;
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
    }
}
