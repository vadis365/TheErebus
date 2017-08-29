package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelGlowWorm;
import erebus.entity.EntityGlowWorm;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderGlowWorm extends RenderLiving {

	private static final ResourceLocation TEXTURE_ON = new ResourceLocation("erebus:textures/entity/glowWormGlow.png");
	private static final ResourceLocation TEXTURE_OFF = new ResourceLocation("erebus:textures/entity/glowWorm.png");

	public RenderGlowWorm() {
		super(new ModelGlowWorm(), 0.0F);
		setRenderPassModel(new ModelGlowWorm());
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		GL11.glScalef(0.75F, 0.75F, 0.75F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntityGlowWorm) entity).isGlowing() ? TEXTURE_ON : TEXTURE_OFF;
	}
}