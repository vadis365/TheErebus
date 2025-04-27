package erebus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class SwampVentParticle extends TextureSheetParticle {

    public SwampVentParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z);
        setSize(0.02F, 0.02F);
        quadSize = quadSize * (random.nextFloat() * 0.6F + 0.2F);
        xd = xSpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        yd = ySpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        zd = zSpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        lifetime = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
        setColor((float) 0 / 255, (float) 80 / 255, (float) 0 / 255);
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

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
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
            SwampVentParticle particle = new SwampVentParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(sprite);
            return particle;
        }
    }
}
