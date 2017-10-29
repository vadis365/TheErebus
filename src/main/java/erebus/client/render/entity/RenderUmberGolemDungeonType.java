package erebus.client.render.entity;

import erebus.client.model.entity.ModelUmberGolem;
import erebus.entity.EntityUmberGolemDungeonTypes;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUmberGolemDungeonType extends RenderLiving<EntityUmberGolemDungeonTypes> {
	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/umber_golem_mud.png"), new ResourceLocation("erebus:textures/entity/umber_golem_iron.png"), new ResourceLocation("erebus:textures/entity/umber_golem_gold.png"), new ResourceLocation("erebus:textures/entity/umber_golem_jade.png") };

	public RenderUmberGolemDungeonType(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelUmberGolem(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityUmberGolemDungeonTypes golem, float partialTickTime) {
		GlStateManager.scale(1.4F, 1.4F, 1.4F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityUmberGolemDungeonTypes golem) {
		if (golem.getType() == 0)
			return TEXTURES[0];
		else if (golem.getType() == 1)
			return TEXTURES[1];
		else if (golem.getType() == 2)
			return TEXTURES[2];
		else if (golem.getType() == 3)
			return TEXTURES[3];
		else
			return null;
	}
}