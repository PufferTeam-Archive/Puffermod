package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.container.ElectricFurnaceContainer;
import fr.minemobs.puffermod.container.SawContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			Main.modId);

	public static final RegistryObject<ContainerType<SawContainer>> EXAMPLE_FURNACE = CONTAINER_TYPES
			.register("example_furnace", () -> IForgeContainerType.create(SawContainer::new));

	public static final RegistryObject<ContainerType<ElectricFurnaceContainer>> ELECTRIC_FURNACE = CONTAINER_TYPES
			.register("electric_furnace", () -> IForgeContainerType.create(ElectricFurnaceContainer::new));
}

