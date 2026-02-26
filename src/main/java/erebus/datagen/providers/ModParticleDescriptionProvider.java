package erebus.datagen.providers;

import erebus.Erebus;
import erebus.registries.client.ModParticles;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;

public class ModParticleDescriptionProvider extends ParticleDescriptionProvider {
    /**
     * Creates an instance of the data provider.
     *
     * @param output the expected root directory the data generator outputs to
     */
    public ModParticleDescriptionProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(ModParticles.SONIC.get(), Erebus.prefix("particle_sonic"));
        spriteSet(ModParticles.WISP.get(), Erebus.prefix("wisp"));
        spriteSet(ModParticles.GNEISS_VENT.get(), Identifier.fromNamespaceAndPath("minecraft", "bubble"));
        spriteSet(ModParticles.SWAMP_VENT.get(), Identifier.fromNamespaceAndPath("minecraft", "bubble"));
        spriteSet(
                ModParticles.REPELLENT.get(),
                Identifier.fromNamespaceAndPath("minecraft", "generic_0"),
                Identifier.fromNamespaceAndPath("minecraft", "generic_1"),
                Identifier.fromNamespaceAndPath("minecraft", "generic_2"),
                Identifier.fromNamespaceAndPath("minecraft", "generic_3"),
                Identifier.fromNamespaceAndPath("minecraft", "generic_4"),
                Identifier.fromNamespaceAndPath("minecraft", "generic_5"),
                Identifier.fromNamespaceAndPath("minecraft", "generic_6"),
                Identifier.fromNamespaceAndPath("minecraft", "generic_7")
        );
    }
}
