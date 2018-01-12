package erebus.client.render.entity;

import erebus.client.model.entity.ModelPunchroom;
import erebus.entity.EntityPunchroom;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPunchroom extends RenderLiving<EntityPunchroom> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/punchroom.png");
	private static final ResourceLocation TEXTURE_SPECIAL = new ResourceLocation("erebus:textures/entity/punchroom_rubby.png");

	public RenderPunchroom(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelPunchroom(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityPunchroom punchroom, float partialTickTime) {
		int i = 1;
		float f1 = (punchroom.prevSquishFactor + (punchroom.squishFactor - punchroom.prevSquishFactor) * partialTickTime) / (i * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
		float f3 = i;
		GlStateManager.scale(f2 * f3, 1.0F / f2 * f3, f2 * f3);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPunchroom punchroom) {
		if (punchroom.hasCustomName())
			if (punchroom.getCustomNameTag().equals("Bryuf"))
				return TEXTURE_SPECIAL;
		return TEXTURE;
	}
}
