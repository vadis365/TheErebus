package erebus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;

public class RepellentParticle extends SingleQuadParticle {
    private final SpriteSet sprites;
    private final float initialSize;

    public RepellentParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites.first());
        this.sprites = sprites;
        setSize(0.02F, 0.02F);
        initialSize = quadSize *= 0.5F;
        xd = xd * 0.1 + xSpeed;
        yd = yd * 0.1 + ySpeed;
        zd = zd * 0.1 + zSpeed;
        lifetime = (int) (8 / (random.nextDouble() * 0.8 + 0.2));
        hasPhysics = false;
        friction = 0.96F;
        speedUpWhenYMotionIsBlocked = true;
        setSprite(sprites.get(lifetime, lifetime));

        float f = random.nextFloat() * 0.4F + 0.6F;
        float red = (random.nextFloat() * 0.2F + 0.8F) * f / 255F;
        float green = 1.0f;
        float blue = (random.nextFloat() * 0.2F + 0.8F) * f / 255F;

        setColor(red, green, blue);
    }

    @Override
    public void tick() {
        super.tick();
        if (!removed) setSprite(sprites.get(Math.max(0, lifetime - age), lifetime));
    }

    @Override
    public float getQuadSize(float partialTick) {
        return initialSize * Mth.clamp((age + partialTick) / lifetime * 32, 0, 1);
    }

    @Override
    protected @NonNull Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, @NonNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, @NonNull RandomSource random) {
            return new RepellentParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
        }
    }
}
