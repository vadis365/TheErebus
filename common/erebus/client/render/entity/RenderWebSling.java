package erebus.client.render.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.entity.EntityWebSling;

@SideOnly(Side.CLIENT)
public class RenderWebSling extends Render {
	private final RenderBlocks blockRenderer = new RenderBlocks();
	private static ResourceLocation texture;

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
		renderWebSling((EntityWebSling) entity, x, y, z, yaw, tick);
	}

	public void renderWebSling(EntityWebSling entityWebSling, double x, double y, double z, float yaw, float tick) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y + 0.5D, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(-90F, 0, 1F, 0);
		GL11.glScaled(1.0D, 1.0D, 1.0D);
		bindTexture(TextureMap.locationBlocksTexture);
		if (entityWebSling.getDataWatcher().getWatchableObjectByte(24) == 0)
			blockRenderer.renderBlockAsItem(Block.web, 3, 1.0F);
		if (entityWebSling.getDataWatcher().getWatchableObjectByte(24) == 1)
			blockRenderer.renderBlockAsItem(ModBlocks.blockWitherWeb, 3, 1.0F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityWebSling entityWebSling = (EntityWebSling) entity;
		if (entityWebSling.getDataWatcher().getWatchableObjectByte(24) == 0)
			texture = new ResourceLocation("minecraft", "textures/blocks/web.png");
		if (entityWebSling.getDataWatcher().getWatchableObjectByte(24) == 1)
			texture = new ResourceLocation("erebus:textures/blocks/witherWeb.png");
		return texture;
	}
}
