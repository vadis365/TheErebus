package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelUmberGolem;
import erebus.entity.EntityUmberGolemDungeonTypes;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderUmberGolemDungeonType extends RenderLiving {
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/umberGolemMud.png"), new ResourceLocation("erebus:textures/entity/umberGolemIron.png"), new ResourceLocation("erebus:textures/entity/umberGolemGold.png"), new ResourceLocation("erebus:textures/entity/umberGolemJade.png") };

	public RenderUmberGolemDungeonType() {
		super(new ModelUmberGolem(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 1.4F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityUmberGolemDungeonTypes golem = (EntityUmberGolemDungeonTypes) entity;
		if (golem.getType() == 0)
			return textures[0];
		else if (golem.getType() == 1)
			return textures[1];
		else if (golem.getType() == 2)
			return textures[2];
		else if (golem.getType() == 3)
			return textures[3];
		else
			return null;
	}
}