package fr.minemobs.puffermod.object.blocks;

import fr.minemobs.puffermod.init.BlockInit;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.state.EnumProperty;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.ConnectionType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ClearGlass extends AbstractGlassBlock {
    public ClearGlass(Properties properties) {
        super(Properties.from(Blocks.GLASS));
        BlockState state = this.getDefaultState();
    }
}
