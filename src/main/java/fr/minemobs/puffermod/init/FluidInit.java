package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {

    public static final ResourceLocation MILK_STILL_RL = new ResourceLocation(Main.modId, "blocks/milk_still");

    public static final ResourceLocation MILK_FLOWING_RL = new ResourceLocation(Main.modId, "blocks/milk_still");

    public static final ResourceLocation MILK_OVERLAY_RL = new ResourceLocation(Main.modId, "blocks/milk_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Main.modId);

    public static final RegistryObject<FlowingFluid> MILK_FLUID = FLUIDS.register("milk_fluid",
            () -> new ForgeFlowingFluid.Source(FluidInit.MILK_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MILK_FLOWING = FLUIDS.register("milk_flowing",
            () -> new ForgeFlowingFluid.Flowing(FluidInit.MILK_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MILK_PROPERTIES = new ForgeFlowingFluid.Properties(() -> MILK_FLUID.get(),
            () -> MILK_FLOWING.get(), FluidAttributes.builder(MILK_STILL_RL, MILK_FLOWING_RL).viscosity(2).rarity(Rarity.UNCOMMON)
            .sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK).overlay(MILK_OVERLAY_RL).density(5).temperature(-2)).block(() -> FluidInit.MILK_BLOCK.get()).bucket(() ->
            ItemInit.KARLSON_CARTON.get());

    public static final RegistryObject<FlowingFluidBlock> MILK_BLOCK = BlockInit.BLOCKS.register("milk",
            () -> new FlowingFluidBlock(() -> FluidInit.MILK_FLUID.get(), Block.Properties.create(Material.WATER)
                    .doesNotBlockMovement().hardnessAndResistance(1000.0f).noDrops()));

}
