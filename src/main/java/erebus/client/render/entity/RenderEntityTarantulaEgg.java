package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import erebus.client.model.block.ModelTarantulaEgg;
import erebus.entity.EntityTarantulaEgg;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityTarantulaEgg extends Render {

	private final ModelTarantulaEgg model = new ModelTarantulaEgg();
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/tarantulaEgg.png");

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
		renderEgg((EntityTarantulaEgg) entity, x, y, z, yaw, tick);
	}

	public void renderEgg(EntityTarantulaEgg entityEgg, double x, double y, double z, float yaw, float tick) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(entityEgg.prevRotationPitch + (entityEgg.rotationPitch - entityEgg.prevRotationPitch) * tick - entityEgg.rotationticks, 0.0F, 1.0F, 0.0F);
		GL11.glScaled(0.7D, 0.7D, 0.7D);
		model.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

}