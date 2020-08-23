package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.recipes.ExampleRecipe;
import fr.minemobs.puffermod.recipes.ExampleRecipeSerializer;
import fr.minemobs.puffermod.recipes.IExempleRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {

    public static final IRecipeSerializer<ExampleRecipe> EXEMPLE_RECIPE_SERIALIZER = new ExampleRecipeSerializer();
    public static final IRecipeType<ExampleRecipe> EXAMPLE_TYPE = registerType(IExempleRecipe.RECIPE_TYPE_ID);

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>
            (ForgeRegistries.RECIPE_SERIALIZERS, Main.modId);

    public static final RegistryObject<IRecipeSerializer<?>> EXAMPLE_SERIALIZER = RECIPE_SERIALIZERS.register("example",
            () -> EXEMPLE_RECIPE_SERIALIZER);

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T>{
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T)Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }

}
