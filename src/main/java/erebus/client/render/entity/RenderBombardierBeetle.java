package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBombardierBeetle;
import erebus.entity.EntityBombardierBeetle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBombardierBeetle extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/beetleBombardier.png");

	public RenderBombardierBeetle() {
		super(new ModelBombardierBeetle(), 0.6F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
		scaleBombardierBeetle((EntityBombardierBeetle) entity, partialTickTime);
	}

	protected void scaleBombardierBeetle(EntityBombardierBeetle entity, float partialTickTime) {
		GL11.glScalef(1.5F, 1.5F, 1.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
