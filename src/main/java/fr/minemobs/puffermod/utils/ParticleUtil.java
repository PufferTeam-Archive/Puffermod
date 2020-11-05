package fr.minemobs.puffermod.utils;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.init.ParticleInit;
import fr.minemobs.puffermod.object.particle.TestParticle;
import fr.minemobs.puffermod.particle.ColouredParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.modId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleUtil {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticle(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particles.registerFactory(ParticleInit.COLOURED_PARTICLE.get(),
                ColouredParticle.Factory::new);

        Minecraft.getInstance().particles.registerFactory(ParticleInit.test_particle.get(),
                TestParticle.Factory::new);
    }

}
