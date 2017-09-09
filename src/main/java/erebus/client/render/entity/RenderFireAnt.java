package erebus.client.render.entity;

import erebus.client.model.entity.ModelFireAnt;
import erebus.entity.EntityFireAnt;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFireAnt extends RenderLiving<EntityFireAnt> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/fire_ant.png");

	public RenderFireAnt(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelFireAnt(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityFireAnt ant, float partialTickTime) {
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		if (ant.isClimbing())
			GlStateManager.rotate(90.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFireAnt ant) {
		return TEXTURE;
	}
}