package erebus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SonicParticle extends SingleQuadParticle {
    private final float initialSize;

    public SonicParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
        xd = xd * 0.01 + xSpeed;
        yd = yd * 0.01 + ySpeed;
        zd = zd * 0.01 + zSpeed;
        initialSize = quadSize;
        lifetime = 20;
        hasPhysics = false;
        friction = 0.96F;
    }

    @Override
    public float getQuadSize(float partialTick) {
        float progress = (age + partialTick) / lifetime;
        return initialSize * (1 - progress * progress * 0.5F);
    }

    @Override
    protected int getLightCoords(float partialTick) {
        int light = super.getLightCoords(partialTick);
        int blockLight = Math.min(240, (light & 255) + (int) (Mth.clamp((age + partialTick) / lifetime, 0, 1) * 240));
        return blockLight | (light & 0xFFFF0000);
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
            SonicParticle particle = new SonicParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite.first());
            particle.setColor(option.getRed(), option.getGreen(), option.getBlue());
            return particle;
        }
    }
}
