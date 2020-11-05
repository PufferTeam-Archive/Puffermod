package fr.minemobs.puffermod.event;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.entity.RedAmongusEntity;
import fr.minemobs.puffermod.init.ModEntityTypes;
import fr.minemobs.puffermod.init.ParticleInit;
import fr.minemobs.puffermod.particle.ColouredParticle;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.modId, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AmongUsBuild {

    @SubscribeEvent
    public static void onEntityBuilt(BlockEvent.EntityPlaceEvent event){
        World worldIn = (World) event.getWorld();
        BlockPos pos = event.getPos();
        Block block = event.getState().getBlock();

        BlockPos pumpkin;
        BlockPos redConcrete;

        if(block == Blocks.CARVED_PUMPKIN){
            pumpkin = pos;
            redConcrete = pos.down();
        }else if(block == Blocks.RED_CONCRETE){
            pumpkin = pos.up();
            redConcrete = pos;
        }else return;
        spawnEntity(worldIn, redConcrete, pumpkin);
    }

    private static void spawnEntity(World worldIn, BlockPos redConcrete, BlockPos pumpkin) {
        PlayerEntity player = Minecraft.getInstance().player;
        if(worldIn.getBlockState(redConcrete).getBlock() == Blocks.RED_CONCRETE &&
        worldIn.getBlockState(pumpkin).getBlock() == Blocks.CARVED_PUMPKIN){
            worldIn.setBlockState(pumpkin, Blocks.AIR.getDefaultState());
            worldIn.setBlockState(redConcrete, Blocks.AIR.getDefaultState());
            worldIn.createExplosion(new TNTEntity(EntityType.TNT, worldIn),
                    redConcrete.getX(), redConcrete.getY(), redConcrete.getZ(),3, Explosion.Mode.NONE);
            RedAmongusEntity rae = new RedAmongusEntity(ModEntityTypes.RED_AMONGUS_ENTITY.get(), worldIn);
            rae.setPosition(redConcrete.getX() + 0.5D, redConcrete.getY(), redConcrete.getZ() + 0.5D);
            worldIn.addEntity(rae);
        }
    }
}
