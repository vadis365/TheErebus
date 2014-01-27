package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelScorpion;
import erebus.entity.EntityScorpion;

@SideOnly(Side.CLIENT)
public class RenderScorpion extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/ModelScorpion.png");

	public RenderScorpion() {
		super(new ModelScorpion(), 0.5F);

	}

	public void renderScorpion(EntityScorpion entityScorpion, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityScorpion, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderScorpion((EntityScorpion) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderScorpion((EntityScorpion) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
