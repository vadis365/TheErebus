package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelSolifuge;
import erebus.entity.EntitySolifuge;

@SideOnly(Side.CLIENT)
public class RenderSolifuge extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/solifuge.png");

	public RenderSolifuge() {
		super(new ModelSolifuge(), 0.5F);

	}

	public void renderSolifuge(EntitySolifuge entitySolifuge, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entitySolifuge, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderSolifuge((EntitySolifuge) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderSolifuge((EntitySolifuge) entity, x, y, z, rotationYaw, partialTickTime);
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleSolifuge((EntitySolifuge) entityliving, f);
	}

	protected void scaleSolifuge(EntitySolifuge entitySolifuge, float f) {
		if (entitySolifuge.getIsAdult() == 0) {
			float f1 = 1F;
			shadowSize = f1;
			GL11.glScalef(f1, f1, f1);
		}
		if (entitySolifuge.getIsAdult() == 1) {
			float f1 = 0.5F;
			shadowSize = f1;
			GL11.glScalef(f1, f1, f1);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
