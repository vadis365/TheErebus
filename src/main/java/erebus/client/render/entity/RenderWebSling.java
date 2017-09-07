package erebus.client.render.entity;

import erebus.ModBlocks;
import erebus.entity.EntityWebSling;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWebSling extends Render<EntityWebSling> {
	
	public RenderWebSling(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityWebSling entity, double x, double y, double z, float yaw, float tick) {
		renderWebSling(entity, x, y, z, yaw, tick);
	}

	public void renderWebSling(EntityWebSling entityWebSling, double x, double y, double z, float yaw, float tick) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate(x, y + 0.5D, z);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.rotate(-90F, 0F, 1F, 0F);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		if (entityWebSling.getType() == 1)
			Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(ModBlocks.WITHER_WEB.getDefaultState(), 1.0F);
		else if (entityWebSling.getType() == 0)
			Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(Blocks.WEB.getDefaultState(), 1.0F);
		else
			Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(Blocks.FIRE.getDefaultState(), 1.0F);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWebSling entity) {
		return null;
	}
}
