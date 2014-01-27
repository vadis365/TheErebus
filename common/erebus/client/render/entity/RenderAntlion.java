package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelAntlion;
import erebus.entity.EntityAntlion;

@SideOnly(Side.CLIENT)
public class RenderAntlion extends RenderLiving {

	private static ResourceLocation texture;

	public RenderAntlion(ModelAntlion model, float shadowSize) {
		super(model, shadowSize);

	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving((EntityAntlion) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleAntlion((EntityAntlion) entityliving, f);

	}

	protected void scaleAntlion(EntityAntlion entityAntlion, float f) {
		if (!entityAntlion.isBoss()) {
			float f1 = 0.75F;
			shadowSize = f1;
			GL11.glScalef(f1, f1, f1);
		} else {
			float f1 = 1.0F;
			shadowSize = f1;
			GL11.glScalef(f1, f1, f1);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityAntlion antlion = (EntityAntlion) entity;
		if (!antlion.isBoss())
			texture = new ResourceLocation("erebus:textures/entity/ModelAntlion.png");
		if (antlion.isBoss())
			texture = new ResourceLocation("erebus:textures/entity/ModelAntlionSandstone.png");
		return texture;
	}
}
