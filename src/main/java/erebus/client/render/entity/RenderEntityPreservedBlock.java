package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.ModBlocks;
import erebus.entity.EntityPreservedBlock;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityPreservedBlock extends Render {
	private final RenderBlocks blockRenderer = new RenderBlocks();
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/blocks/glassAmber.png");

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
		renderPreservedBlock((EntityPreservedBlock) entity, x, y, z, yaw, tick);
	}

	public void renderPreservedBlock(EntityPreservedBlock entityPreservedBlock, double x, double y, double z, float yaw, float tick) {
		float rotation = entityPreservedBlock.ticksExisted * 20 + tick;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(rotation, 0, 1F, 0);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		bindTexture(TextureMap.locationBlocksTexture);
		blockRenderer.renderBlockAsItem(ModBlocks.amber, 0, 0.6F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(45F + rotation, 1F, 1F, 0);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		bindTexture(TextureMap.locationBlocksTexture);
		blockRenderer.renderBlockAsItem(ModBlocks.amber, 0, 0.6F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(-45F + rotation, 1F, 1F, 0);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		bindTexture(TextureMap.locationBlocksTexture);
		blockRenderer.renderBlockAsItem(ModBlocks.amber, 0, 0.6F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

}