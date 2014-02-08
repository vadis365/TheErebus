package erebus.client.render.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityExtractedBlock;

public class RenderExtractedBlock extends Render{
	private final RenderBlocks blockRenderer = new RenderBlocks();

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
		renderExtractedBlock((EntityExtractedBlock) entity, x, y, z, yaw, tick);
	}
	
	public void renderExtractedBlock(EntityExtractedBlock entity, double x, double y, double z, float yaw, float tick) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glTranslatef(0.0F, 0.5F, 0.0F);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		renderBlocks.renderBlockAsItem(Block.blocksList[entity.blockID], entity.blockMeta, 1.0F);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}