package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import erebus.client.model.entity.ModelScytodes;
import erebus.entity.EntityScytodes;

public class RenderScytodes extends RenderLiving {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/scytodes1.png"), new ResourceLocation("erebus:textures/entity/scytodes2.png"), new ResourceLocation("erebus:textures/entity/scytodes3.png"), new ResourceLocation("erebus:textures/entity/scytodes4.png") };

	public RenderScytodes() {
		super(new ModelScytodes(), 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURES[Math.min(TEXTURES.length - 1, ((EntityScytodes) entity).getSkin())];
	}
}