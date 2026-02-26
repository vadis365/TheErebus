package erebus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SonicParticle extends SingleQuadParticle {

    public SonicParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
    }

    @Override
    protected @NonNull Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public @Nullable Particle createParticle(ColorParticleOption option, @NonNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, @NonNull RandomSource random) {
            SonicParticle particle = new SonicParticle(level, x, y, z, sprite.first());
            particle.setColor(option.getRed(), option.getGreen(), option.getBlue());
            return particle;
        }
    }
}
