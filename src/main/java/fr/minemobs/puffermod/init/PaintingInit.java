package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PaintingInit {

    public static final DeferredRegister<PaintingType> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES,
            Main.modId);

    public static final RegistryObject<PaintingType> astrophotography01 = PAINTING_TYPES.register("astrophotography01_by_bklandscapes",
            () -> new PaintingType(64,64));

}
