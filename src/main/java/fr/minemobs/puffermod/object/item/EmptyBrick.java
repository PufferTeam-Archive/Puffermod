package fr.minemobs.puffermod.object.item;

import fr.minemobs.puffermod.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class EmptyBrick extends Item {
    public EmptyBrick(Properties properties) {
        super(properties);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if(target instanceof CowEntity){
            if(stack.getItem() == this){
                ItemStack newStack = new ItemStack(ItemInit.karlson_milk.get());

                ItemStack item = new ItemStack(playerIn.getHeldItemMainhand().getItem());

                if (!playerIn.inventory.addItemStackToInventory(newStack)) {
                    playerIn.dropItem(newStack, false);
                }

                int count = playerIn.getHeldItem(hand).getCount();

                playerIn.getHeldItem(hand).setCount(count - 1);
            }
            return true;
        }
        return false;
    }
}
