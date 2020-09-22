package fr.minemobs.puffermod;

import fr.minemobs.puffermod.command.SetFireCommand;
import fr.minemobs.puffermod.init.*;
import fr.minemobs.puffermod.object.item.ModSpawnEggItem;
import fr.minemobs.puffermod.world.gen.StructureGen;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("puffermod")
@Mod.EventBusSubscriber(modid = "puffermod", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String modId = "puffermod";

    public Main() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::serverStartingEvent);
        modEventBus.addListener(this::setup);
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        FeatureInit.FEATURES.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
        modEventBus.register(this);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream().filter(block -> !(block.get() instanceof FlowingFluidBlock)).map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(ModItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties.group(ModItemGroup.instance));
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> entityTypeRegister){
        ModSpawnEggItem.initSpawnEggs();
    }

    private void setup(final FMLCommonSetupEvent event){
        DeferredWorkQueue.runLater(StructureGen::generateStructure);
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
