package erebus.client.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntitySonicFX extends EntityFX {
	/** the scale of the flame FX */
	private final float flameScale;

	@SideOnly(Side.CLIENT)
	public EntitySonicFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		motionX = motionX * 0.009999999776482582D + par8;
		motionY = motionY * 0.009999999776482582D + par10;
		motionZ = motionZ * 0.009999999776482582D + par12;
		flameScale = particleScale;
		particleRed = particleGreen = particleBlue = 1.0F;
		particleMaxAge = 20;
		noClip = true;
		particleTextureIndexX = 0;
		particleTextureIndexY = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
		par1Tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/particle/particles.png"));
		par1Tessellator.startDrawingQuads();
		par1Tessellator.setBrightness(200);
		float f6 = (particleAge + par2) / particleMaxAge;
		particleScale = flameScale * (1.0F - f6 * f6 * 0.5F);
		super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
		par1Tessellator.draw();
		par1Tessellator.startDrawingQuads();
	}

	@Override
	public int getBrightnessForRender(float par1) {
		float f1 = (particleAge + par1) / particleMaxAge;

		if (f1 < 0.0F)
			f1 = 0.0F;

		if (f1 > 1.0F)
			f1 = 1.0F;

		int i = super.getBrightnessForRender(par1);
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (f1 * 15.0F * 16.0F);

		if (j > 240)
			j = 240;

		return j | k << 16;
	}

	@Override
	public float getBrightness(float par1) {
		float f1 = (particleAge + par1) / particleMaxAge;

		if (f1 < 0.0F)
			f1 = 0.0F;

		if (f1 > 1.0F)
			f1 = 1.0F;

		float f2 = super.getBrightness(par1);
		return f2 * f1 + (1.0F - f1);
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge)
			setDead();

		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.9599999785423279D;
		motionY *= 0.9599999785423279D;
		motionZ *= 0.9599999785423279D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}
}
