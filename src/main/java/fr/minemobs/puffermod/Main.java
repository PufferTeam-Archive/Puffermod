package fr.minemobs.puffermod;

import fr.minemobs.puffermod.command.SetFireCommand;
import fr.minemobs.puffermod.init.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod("puffermod")
@Mod.EventBusSubscriber(modid = "puffermod", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Main {

    public static final String modId = "puffermod";

    public Main() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(this::serverStartingEvent);
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(ModItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties.group(ModItemGroup.instance));
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    @SubscribeEvent
    public void serverStartingEvent(FMLServerStartingEvent event)
    {
        SetFireCommand.register(event.getCommandDispatcher());
    }


    public static class ModItemGroup extends ItemGroup {

        public static final ModItemGroup instance = new ModItemGroup(ItemGroup.GROUPS.length, "Puffermod Items");

        public ModItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.refined_iron_ingot.get());
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }

    }

}
