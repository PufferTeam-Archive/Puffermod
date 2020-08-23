package fr.minemobs.puffermod.object.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RefinedIron extends Item {

    public RefinedIron(Properties properties) {
        super(properties);
    }

    /*/@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(!KeyboardHelper.isHoldingShift()){
            tooltip.add(new TranslationTextComponent("tooltip." + Main.modId + ".shift"));
        }else{
            tooltip.add(new TranslationTextComponent("tooltip." + Main.modId + ".refined"));
        }
        if(KeyboardHelper.isHoldingShift() && KeyboardHelper.isHoldingControl()){
            tooltip.add(new TranslationTextComponent("tooltip." + Main.modId + ".minemobs"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }/*/
}
