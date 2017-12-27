package erebus.client.render.entity;

import erebus.entity.EntityThrownSand;
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
public class RenderThrownSand extends Render<EntityThrownSand> {

	public RenderThrownSand(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
	}

	@Override
	public void doRender(EntityThrownSand entityThrownSand, double x, double y, double z, float yaw, float tick) {
		renderThrownSand(entityThrownSand, x, y, z, yaw, tick);
	}

	public void renderThrownSand(EntityThrownSand entityThrownSand, double x, double y, double z, float yaw,
			float tick) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.5D, z);
		GlStateManager.rotate(entityThrownSand.prevRotationYaw + (entityThrownSand.rotationYaw - entityThrownSand.prevRotationYaw) * tick - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entityThrownSand.prevRotationPitch + (entityThrownSand.rotationPitch - entityThrownSand.prevRotationPitch) * tick - EntityThrownSand.rotationticks, 0.0F, 0.0F, 1.0F);
		GlStateManager.scale(1.0D, 1.0D, 1.0D);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(Blocks.SAND.getDefaultState(), 1.0F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityThrownSand entityThrownSand) {
		return null;
	}

}