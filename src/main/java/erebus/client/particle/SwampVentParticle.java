package erebus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;

public class SwampVentParticle extends SingleQuadParticle {

    public SwampVentParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
        setSize(0.02F, 0.02F);
        quadSize = quadSize * (random.nextFloat() * 0.6F + 0.2F);
        xd = xSpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        yd = ySpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        zd = zSpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        lifetime = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
        setColor((float) 0 / 255, (float) 80 / 255, (float) 0 / 255);
    }

    @Override
    protected @NonNull Layer getLayer() {
        return Layer.OPAQUE;
    }

    @Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;

        if (lifetime-- <= 0) remove();

        yd += 0.002;
        move(xd, yd, zd);
        xd *= 0.8500000238418579D;
        yd *= 0.8500000238418579D;
        zd *= 0.8500000238418579D;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, @NonNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, @NonNull RandomSource random) {
            return new SwampVentParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite.first());
        }
    }
}
