package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.entity.RedAmongusEntity;
import fr.minemobs.puffermod.entity.TestEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.modId);

    public static final RegistryObject<EntityType<TestEntity>> TEST_ENTITY = ENTITY_TYPES
            .register("test_entity",
                    () -> EntityType.Builder.<TestEntity>create(TestEntity::new, EntityClassification.CREATURE)
                            .size(0.6F, 1.8F)
                            .build(new ResourceLocation(Main.modId, "test_entity").toString()));

    public static final RegistryObject<EntityType<RedAmongusEntity>> RED_AMONGUS_ENTITY = ENTITY_TYPES
            .register("red_amongus_entity",
                    () -> EntityType.Builder.<RedAmongusEntity>create(RedAmongusEntity::new, EntityClassification.CREATURE)
                            .size(0.6F, 1.0F)
                            .build(new ResourceLocation(Main.modId, "red_amongus_entity").toString()));
}
