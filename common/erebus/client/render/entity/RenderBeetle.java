package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBeetle;
import erebus.entity.EntityBeetle;

@SideOnly(Side.CLIENT)
public class RenderBeetle extends RenderLiving {
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/Beetlerarespawn.png"), new ResourceLocation("erebus:textures/entity/Beetleblue.png"), new ResourceLocation("erebus:textures/entity/Beetlebrown.png"),
	new ResourceLocation("erebus:textures/entity/Beetlegreen.png"), new ResourceLocation("erebus:textures/entity/Beetlered.png"), new ResourceLocation("erebus:textures/entity/Beetletan.png") };

	public RenderBeetle() {
		super(new ModelBeetle(), 0.5F);
	}

	public void renderBeetle(EntityBeetle entityBeetle, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityBeetle, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBeetle((EntityBeetle) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBeetle((EntityBeetle) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityBeetle beetle = (EntityBeetle) entity;
		if (beetle.getSkin() > 0 && beetle.getSkin() <= 10)
			return textures[1];
		else if (beetle.getSkin() > 10 && beetle.getSkin() <= 20)
			return textures[2];
		else if (beetle.getSkin() > 20 && beetle.getSkin() <= 30)
			return textures[3];
		else if (beetle.getSkin() > 30 && beetle.getSkin() <= 40)
			return textures[4];
		else if (beetle.getSkin() > 40 && beetle.getSkin() <= 50)
			return textures[5];
		else if (beetle.getSkin() == 0)
			return textures[0];
		else
			return null;
	}
}
