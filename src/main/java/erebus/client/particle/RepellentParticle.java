package erebus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jspecify.annotations.NonNull;

public class RepellentParticle extends SingleQuadParticle {

    public RepellentParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        setSize(0.02F, 0.02F);
        quadSize = quadSize * (random.nextFloat() * 0.6F + 0.2F);
        xd *= 0.10000000149011612D;
        yd *= 0.10000000149011612D;
        zd *= 0.10000000149011612D;

        float f = (float) (Math.random() * 0.4F + 0.6F);
        float red = (float) (((Math.random() * 0.20000000298023224D) + 0.8F) * f) / 255F;
        float green = 1.0f;
        float blue = (float) (((Math.random() * 0.20000000298023224D) + 0.8F) * f) / 255F;

        setColor(red, green, blue);
    }

    @Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;

        if (lifetime-- <= 0) remove();

        xd *= 0.9599999785423279D;
        yd *= 0.9599999785423279D;
        zd *= 0.9599999785423279D;

        if(onGround) {
            xd *= 0.699999988079071D;
            zd *= 0.699999988079071D;
        }
    }

    @Override
    protected @NonNull Layer getLayer() {
        return Layer.OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, @NonNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, @NonNull RandomSource random) {
            return new RepellentParticle(level, x, y, z, sprite.first());
        }
    }
}
