package fr.minemobs.les3soleils.object.blocks;

import fr.minemobs.les3soleils.Main;
import fr.minemobs.les3soleils.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BankBlock extends Block {
    public BankBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
                                             BlockRayTraceResult hit) {
        if(handIn != null && worldIn != null && state != null && player != null && pos != null &&
                player.getHeldItemMainhand().getItem() == ItemInit.refined_iron_ingot.get()){
            player.inventory.addItemStackToInventory(new ItemStack(ItemInit.puffer_coins.get(), 1));
            player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);
            return ActionResultType.SUCCESS;
        }else{
            player.sendMessage(new TranslationTextComponent("machine." + Main.modId + ".error"));
            return ActionResultType.PASS;
        }
    }
}