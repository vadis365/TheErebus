package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantulaBaby;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderTarantulaBaby extends RenderLiving {

	private final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/tarantula.png"), new ResourceLocation("erebus:textures/entity/tarantulaTurqoise.png") };

	public RenderTarantulaBaby() {
		super(new ModelTarantula(), 0.25F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		float size = 0.3F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURES[Math.min(TEXTURES.length - 1, ((EntityTarantulaBaby) entity).getSkin())];
	}
}