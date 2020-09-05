package fr.minemobs.puffermod.object.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class WoodenFrameBlock extends Block {
    public WoodenFrameBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Stream.of(
                Block.makeCuboidShape(2, 2, 2, 14, 14, 14),
                Block.makeCuboidShape(14, 14, 2, 15, 15, 14),
                Block.makeCuboidShape(2, 14, 1, 14, 15, 2),
                Block.makeCuboidShape(2, 14, 14, 14, 15, 15),
                Block.makeCuboidShape(1, 14, 2, 2, 15, 14),
                Block.makeCuboidShape(1, 15, 0, 15, 16, 16),
                Block.makeCuboidShape(0, 15, 1, 1, 16, 15),
                Block.makeCuboidShape(15, 15, 1, 16, 16, 15),
                Block.makeCuboidShape(1, 0, 15, 15, 1, 16),
                Block.makeCuboidShape(0, 0, 1, 16, 1, 15),
                Block.makeCuboidShape(1, 0, 0, 15, 1, 1),
                Block.makeCuboidShape(0, 0, 15, 1, 16, 16),
                Block.makeCuboidShape(0, 0, 0, 1, 16, 1),
                Block.makeCuboidShape(15, 0, 0, 16, 16, 1),
                Block.makeCuboidShape(15, 0, 15, 16, 16, 16),
                Block.makeCuboidShape(14, 1, 14, 15, 15, 15),
                Block.makeCuboidShape(14, 1, 1, 15, 15, 2),
                Block.makeCuboidShape(1, 1, 14, 2, 15, 15),
                Block.makeCuboidShape(1, 1, 1, 2, 15, 2),
                Block.makeCuboidShape(14, 1, 2, 15, 2, 14),
                Block.makeCuboidShape(2, 1, 1, 14, 2, 2),
                Block.makeCuboidShape(2, 1, 14, 14, 2, 15),
                Block.makeCuboidShape(1, 1, 2, 2, 2, 14)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    }



}
