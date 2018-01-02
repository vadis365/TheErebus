package erebus.client.render.entity;

import erebus.client.model.entity.ModelWorkerBee;
import erebus.entity.EntityWorkerBee;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWorkerBee extends RenderLiving<EntityWorkerBee> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/worker_bee.png");

	public RenderWorkerBee(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelWorkerBee(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityWorkerBee bee, float partialTickTime) {
		float size = 0.5F;
		shadowSize = size;
		GlStateManager.scale(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWorkerBee bee) {
		return TEXTURE;
	}
}