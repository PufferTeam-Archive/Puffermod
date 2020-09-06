package fr.minemobs.puffermod.object.item;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.init.ItemInit;
import fr.minemobs.puffermod.utils.helper.KeyboardHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class KarlsonItem extends Item {

    public KarlsonItem(Properties properties) {
        super(properties.food(new Food.Builder()
                .hunger(4)
                .saturation(4)
                .fastToEat()
                .setAlwaysEdible()
                .build()));
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        if (entityLiving instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.addStat(Stats.ITEM_USED.get(this));
            serverplayerentity.sendMessage(new TranslationTextComponent("msg." + Main.modId + ".thic"));
        }

        if (!worldIn.isRemote) {
            entityLiving.removePotionEffect(Effects.POISON);
            entityLiving.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 1));
            entityLiving.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1200, 4));
            //entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 120, 1));
        }

        if (stack.isEmpty()) {
            return new ItemStack(ItemInit.empty_brick.get());
        } else {
            if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
                ItemStack itemstack = new ItemStack(ItemInit.empty_brick.get());
                PlayerEntity playerentity = (PlayerEntity)entityLiving;
                if (!playerentity.inventory.addItemStackToInventory(itemstack)) {
                    playerentity.dropItem(itemstack, false);
                }
            }

            return stack;
        }
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.setActiveHand(handIn);
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(KeyboardHelper.isHoldingShift()){
            tooltip.add(new TranslationTextComponent("tooltip." + Main.modId + ".karlson.milk"));
        }else{
            tooltip.add(new TranslationTextComponent("tooltip." + Main.modId + ".shift"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
