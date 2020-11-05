package fr.minemobs.puffermod.object.blocks;

import fr.minemobs.puffermod.utils.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.Entity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ILightReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class SkyBlock extends Block implements IBlockColor {
    public SkyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getColor(BlockState stateIn, @Nullable ILightReader lightIn, @Nullable BlockPos pos, int p_getColor_4_) {
        return RenderUtils.getFogColor().getColor();
    }

    @Override
    public Vec3d getFogColor(BlockState state, IWorldReader world, BlockPos pos, Entity entity, Vec3d originalColor, float partialTicks) {
        return null;
    }

    @Override
    public boolean recolorBlock(BlockState state, IWorld world, BlockPos pos, Direction facing, DyeColor color) {
        return true;
    }
}
