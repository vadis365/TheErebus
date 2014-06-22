package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelPunchshroom;
import erebus.entity.EntityPunchshroom;

@SideOnly(Side.CLIENT)
public class RenderPunchshroom extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/punchshroom.png");

	public RenderPunchshroom() {
		super(new ModelPunchshroom(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		EntityPunchshroom punchshroom = (EntityPunchshroom) entityliving;
        int i = 1;
        float f1 = (punchshroom.prevSquishFactor + (punchshroom.squishFactor - punchshroom.prevSquishFactor) * partialTickTime) / ((float)i * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        float f3 = (float)i;
        GL11.glScalef(f2 * f3, 1.0F / f2 * f3, f2 * f3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
