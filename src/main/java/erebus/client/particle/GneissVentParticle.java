package erebus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GneissVentParticle extends TextureSheetParticle {

    public GneissVentParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public @Nullable Particle createParticle(@NotNull SimpleParticleType type,
                                                 @NotNull ClientLevel level,
                                                 double x,
                                                 double y,
                                                 double z,
                                                 double xSpeed,
                                                 double ySpeed,
                                                 double zSpeed) {
            GneissVentParticle particle = new GneissVentParticle(level, x, y, z);
            particle.pickSprite(sprite);
            return particle;
        }
    }
}
