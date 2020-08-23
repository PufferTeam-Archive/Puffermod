package fr.minemobs.puffermod.recipes;

import fr.minemobs.puffermod.Main;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IExempleRecipe extends IRecipe<RecipeWrapper> {

    ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(Main.modId, "exemple");

    @Nonnull
    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getValue(RECIPE_TYPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height){
        return false;
    }

    Ingredient getInput();
}
