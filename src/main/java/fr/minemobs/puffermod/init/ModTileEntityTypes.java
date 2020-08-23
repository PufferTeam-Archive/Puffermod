package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
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

}
