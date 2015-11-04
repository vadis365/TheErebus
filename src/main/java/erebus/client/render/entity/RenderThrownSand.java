package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityThrownSand;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public class RenderThrownSand extends Render {
	private final RenderBlocks blockRenderer = new RenderBlocks();

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
		renderThrownSand((EntityThrownSand) entity, x, y, z, yaw, tick);
	}

	public void renderThrownSand(EntityThrownSand entityThrownSand, double x, double y, double z, float yaw, float tick) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y + 0.5D, z);
		GL11.glRotatef(entityThrownSand.prevRotationYaw + (entityThrownSand.rotationYaw - entityThrownSand.prevRotationYaw) * tick - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entityThrownSand.prevRotationPitch + (entityThrownSand.rotationPitch - entityThrownSand.prevRotationPitch) * tick - EntityThrownSand.rotationticks, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(1.0D, 1.0D, 1.0D);
		bindTexture(TextureMap.locationBlocksTexture);
		blockRenderer.renderBlockAsItem(Blocks.sand, 3, 1.0F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}