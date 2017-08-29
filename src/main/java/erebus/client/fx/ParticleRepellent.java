package erebus.client.fx;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleRepellent extends Particle {

	float repellentParticleScale;

	public ParticleRepellent(World world, double x, double y, double z, float red, float green, float blue) {
		this(world, x, y, z, 1.0F, red, green, blue);
	}

	public ParticleRepellent(World world, double x, double y, double z, float sizeMp, float red, float green, float blue) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		motionX *= 0.10000000149011612D;
		motionY *= 0.10000000149011612D;
		motionZ *= 0.10000000149011612D;

		if (red == 0.0F)
			red = 1.0F;

		float f4 = (float) Math.random() * 0.4F + 0.6F;
		particleRed = ((float) (Math.random() * 0.20000000298023224D) + 0.8F) * red * f4;
		particleGreen = 255;
		particleBlue = ((float) (Math.random() * 0.20000000298023224D) + 0.8F) * blue * f4;
		particleScale *= 0.5F;
		particleScale *= sizeMp;
		repellentParticleScale = particleScale;
		particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
		particleMaxAge = (int) (particleMaxAge * sizeMp);
		canCollide = false;
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge * 32.0F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		this.particleScale = repellentParticleScale * f;
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge)
			setExpired();

		setParticleTextureIndex(7 - particleAge * 8 / particleMaxAge);
		move(motionX, motionY, motionZ);

		if (posY == prevPosY) {
			motionX *= 1.1D;
			motionZ *= 1.1D;
		}

		motionX *= 0.9599999785423279D;
		motionY *= 0.9599999785423279D;
		motionZ *= 0.9599999785423279D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}
}