package fr.minemobs.puffermod.object.item;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.utils.helper.KeyboardHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CheeseItem extends Item {
    public CheeseItem(Properties properties) {
        super(properties.food(new Food.Builder().setAlwaysEdible().build()));
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        if (entityLiving instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.addStat(Stats.ITEM_USED.get(this));
            serverplayerentity.sendMessage(new TranslationTextComponent("msg." + Main.modId + ".coulommiers"));
        }

        if (!worldIn.isRemote) {
            entityLiving.removePotionEffect(Effects.HUNGER);
            entityLiving.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 1));
            entityLiving.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 200, 1));
        }
        return stack;
    }

    public int getUseDuration(ItemStack stack) {
        return 80;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_EAT;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_EAT;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(KeyboardHelper.isHoldingShift()){
            tooltip.add(new TranslationTextComponent("tooltip." + Main.modId + ".coulommiers"));
        }else{
            tooltip.add(new TranslationTextComponent("tooltip." + Main.modId + ".shift"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
