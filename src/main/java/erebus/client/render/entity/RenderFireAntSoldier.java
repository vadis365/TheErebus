package erebus.client.render.entity;

import erebus.client.model.entity.ModelFireAntSoldier;
import erebus.entity.EntityFireAntSoldier;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFireAntSoldier extends RenderLiving<EntityFireAntSoldier> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/fire_ant_soldier.png");

	public RenderFireAntSoldier(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelFireAntSoldier(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityFireAntSoldier ant, float partialTickTime) {
		GlStateManager.scale(0.75F, 0.75F, 0.75F);
		if (ant.isClimbing())
			GlStateManager.rotate(90.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFireAntSoldier ant) {
		return TEXTURE;
	}
}