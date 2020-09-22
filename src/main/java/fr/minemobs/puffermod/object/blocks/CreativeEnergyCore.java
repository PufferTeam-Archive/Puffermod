package fr.minemobs.puffermod.object.blocks;

import fr.minemobs.puffermod.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class CreativeEnergyCore extends Block {

    public CreativeEnergyCore(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        // Always use TileEntityType#create to allow registry overrides to work.
        return ModTileEntityTypes.CREATIVE_ENERGY_CORE.get().create();
    }
}
