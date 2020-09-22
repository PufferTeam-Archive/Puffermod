package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.tileentity.CreativeEnergyCoreTE;
import fr.minemobs.puffermod.tileentity.ElectricFurnaceTileEntity;
import fr.minemobs.puffermod.tileentity.SawTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, Main.modId);

	public static final RegistryObject<TileEntityType<SawTileEntity>> SAW = TILE_ENTITY_TYPES
			.register("saw", () -> TileEntityType.Builder
					.create(SawTileEntity::new, BlockInit.example_furnace.get()).build(null));

	public static final RegistryObject<TileEntityType<ElectricFurnaceTileEntity>> ELECTRIC_FURNACE = TILE_ENTITY_TYPES
			.register("electric_furnace", () -> TileEntityType.Builder
					.create(ElectricFurnaceTileEntity::new, BlockInit.ELECTRIC_FURNACE.get()).build(null));

	public static final RegistryObject<TileEntityType<CreativeEnergyCoreTE>> CREATIVE_ENERGY_CORE = TILE_ENTITY_TYPES
			.register("creative_energy_core", () -> TileEntityType.Builder
					.create(CreativeEnergyCoreTE::new, BlockInit.CREATIVE_ENERGY_CORE.get()).build(null));

}
