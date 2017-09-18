package erebus.client.render.entity;

import erebus.client.model.entity.ModelAnimatedChest;
import erebus.entity.EntityAnimatedChest;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAnimatedChest extends RenderLiving<EntityAnimatedChest> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/animated_chest.png");

	public RenderAnimatedChest(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelAnimatedChest(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAnimatedChest chester) {
		return TEXTURE;
	}
}