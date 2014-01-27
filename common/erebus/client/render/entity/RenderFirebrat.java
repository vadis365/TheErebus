package erebus.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityFirebrat;

@SideOnly(Side.CLIENT)
public class RenderFirebrat extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/ModelFirebrat.png");

	public RenderFirebrat(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	public void renderFirebrat(EntityFirebrat entityFirebrat, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityFirebrat, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderFirebrat((EntityFirebrat) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase entityLivingBase) {
		return 180F;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entityLivingBase, int par2, float par3) {
		return -1;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderFirebrat((EntityFirebrat) entity, x, y, z, rotationYaw, partialTickTime);
	}
}
