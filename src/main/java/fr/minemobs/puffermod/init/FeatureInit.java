package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.world.feature.structures.TestStructure;
import fr.minemobs.puffermod.world.feature.structures.TestPieces;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Main.modId)
public class FeatureInit {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<Feature<?>>(ForgeRegistries.FEATURES, Main.modId);

    public static IStructurePieceType TEST_PIECE = TestPieces.Piece::new;

    public static final RegistryObject<TestStructure> TEST = FEATURES.register("test",
            () -> new TestStructure(NoFeatureConfig::deserialize));

    @SubscribeEvent
    public static void registerStructurePieces(RegistryEvent.Register<Feature<?>> event){
        Registry.register(Registry.STRUCTURE_PIECE, "RANDOM_STRUCTURE".toLowerCase(Locale.ROOT), TEST_PIECE);
    }
}

