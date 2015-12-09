package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import erebus.client.model.entity.ModelFireAnt;

public class RenderZombieAnt extends RenderLiving {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/blackAntFungal.png");

	public RenderZombieAnt() {
		super(new ModelFireAnt(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
}