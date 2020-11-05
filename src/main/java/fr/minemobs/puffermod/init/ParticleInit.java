package fr.minemobs.puffermod.init;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.object.particle.TestParticle;
import fr.minemobs.puffermod.particle.ColouredParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleInit {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Main.modId);

    public static final RegistryObject<BasicParticleType> test_particle = PARTICLES.register("test_particle",
            () -> new BasicParticleType(true));

    public static final RegistryObject<ParticleType<ColouredParticle.ColouredParticleData>> COLOURED_PARTICLE = PARTICLES.register(
            "coloured_particle",
            () -> new ParticleType<ColouredParticle.ColouredParticleData>(false, ColouredParticle.ColouredParticleData.DESERIALIZER));
}
