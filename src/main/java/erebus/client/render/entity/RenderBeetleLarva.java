package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBeetleLarva;
import erebus.entity.EntityBeetleLarva;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBeetleLarva extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/beetleLarva.png");

	public RenderBeetleLarva() {
		super(new ModelBeetleLarva(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float larvaSize = ((EntityBeetleLarva) entityliving).getLarvaSize();
		GL11.glScalef(larvaSize, larvaSize, larvaSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}