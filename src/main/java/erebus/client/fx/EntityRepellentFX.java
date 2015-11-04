package erebus.client.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityRepellentFX extends EntityFX {

	float repellentParticleScale;

	public EntityRepellentFX(World world, double x, double y, double z, float red, float green, float blue) {
		this(world, x, y, z, 1.0F, red, green, blue);
	}

	public EntityRepellentFX(World world, double x, double y, double z, float sizeMp, float red, float green, float blue) {
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
		noClip = false;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
		float f6 = (particleAge + par2) / particleMaxAge * 32.0F;

		if (f6 < 0.0F)
			f6 = 0.0F;

		if (f6 > 1.0F)
			f6 = 1.0F;

		particleScale = repellentParticleScale * f6;
		super.renderParticle(tessellator, par2, par3, par4, par5, par6, par7);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge)
			setDead();

		setParticleTextureIndex(7 - particleAge * 8 / particleMaxAge);
		moveEntity(motionX, motionY, motionZ);

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