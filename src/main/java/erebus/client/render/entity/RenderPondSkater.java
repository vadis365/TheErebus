package erebus.client.render.entity;

import erebus.client.model.entity.ModelPondSkater;
import erebus.entity.EntityPondSkater;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPondSkater extends RenderLiving<EntityPondSkater> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/pond_skater.png");

	public RenderPondSkater(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelPondSkater(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityPondSkater skater, float partialTickTime) {
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.translate(0F, -0.5F, 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPondSkater skater) {
		return TEXTURE;
	}
}