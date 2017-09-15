package erebus.client.fx;


import erebus.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleSonic extends Particle {
	private final float scale;
	// TODO Stitch event for this
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":particle/particle_sonic");

	@SideOnly(Side.CLIENT)
	public ParticleSonic(World world, double x, double y, double z, double velX, double velY, double velZ) {
		super(world, x, y, z, velX, velY, velY);
		motionX = motionX * 0.009999999776482582D + velX;
		motionY = motionY * 0.009999999776482582D + velY;
		motionZ = motionZ * 0.009999999776482582D + velY;
		scale = particleScale;
		particleRed = particleGreen = particleBlue = 1.0F;
		particleMaxAge = 20;
		canCollide = false;
		setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEXTURE.toString()));
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge;
        this.particleScale = this.scale * (1.0F - f * f * 0.5F);
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

	@Override
	public int getFXLayer() {
		return 1;
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
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge)
			setExpired();

		move(motionX, motionY, motionZ);
		motionX *= 0.9599999785423279D;
		motionY *= 0.9599999785423279D;
		motionZ *= 0.9599999785423279D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}
}
