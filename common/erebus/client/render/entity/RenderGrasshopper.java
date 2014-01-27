package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelGrasshopper;
import erebus.entity.EntityGrasshopper;

@SideOnly(Side.CLIENT)
public class RenderGrasshopper extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/ModelGrasshopper.png");

	public RenderGrasshopper() {
		super(new ModelGrasshopper(), 0.5F);

	}

	public void renderGrasshopper(EntityGrasshopper entityGrasshopper, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityGrasshopper, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderGrasshopper((EntityGrasshopper) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderGrasshopper((EntityGrasshopper) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
