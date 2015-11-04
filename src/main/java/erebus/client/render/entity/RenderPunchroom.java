package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelPunchroom;
import erebus.entity.EntityPunchroom;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderPunchroom extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/punchroom.png");
	private static final ResourceLocation texture1 = new ResourceLocation("erebus:textures/entity/punchroomRubby.png");

	public RenderPunchroom() {
		super(new ModelPunchroom(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		EntityPunchroom punchroom = (EntityPunchroom) entityliving;
		int i = 1;
		float f1 = (punchroom.prevSquishFactor + (punchroom.squishFactor - punchroom.prevSquishFactor) * partialTickTime) / (i * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
		float f3 = i;
		GL11.glScalef(f2 * f3, 1.0F / f2 * f3, f2 * f3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (((EntityLiving) entity).hasCustomNameTag())
			if (((EntityLiving) entity).getCustomNameTag() == "Bryuf")
				return texture1;
		return texture;
	}
}
