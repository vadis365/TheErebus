package erebus.registries.client;

import erebus.Erebus;
import erebus.client.particle.GneissVentParticle;
import erebus.client.particle.SwampVentParticle;
import erebus.client.particle.WispParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Erebus.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SWAMP_VENT = register("swamp_vent");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GNEISS_VENT = register("gneiss_vent");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WISP = register("wisp");

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SWAMP_VENT.get(), SwampVentParticle.Provider::new);
        event.registerSpriteSet(GNEISS_VENT.get(), GneissVentParticle.Provider::new);
        event.registerSpriteSet(WISP.get(), WispParticle.Provider::new);
    }
}
