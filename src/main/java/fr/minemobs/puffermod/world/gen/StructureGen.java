package fr.minemobs.puffermod.world.gen;

import fr.minemobs.puffermod.init.FeatureInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class StructureGen {
    public static void generateStructure(){
        for(Biome biome : ForgeRegistries.BIOMES){
            if(biome == Biomes.PLAINS){
                biome.addStructure(FeatureInit.TEST.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            }
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
                    FeatureInit.TEST.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
    }
}
