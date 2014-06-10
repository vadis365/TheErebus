package erebus.client.render.entity;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityWebSling;

@SideOnly(Side.CLIENT)
public class RenderWebSling extends Render {
	private final RenderBlocks blockRenderer = new RenderBlocks();

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
		renderWebSling((EntityWebSling) entity, x, y, z, yaw, tick);
	}

	public void renderWebSling(EntityWebSling entityWebSling, double x, double y, double z, float yaw, float tick) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y + 0.5D, z);
		bindTexture(TextureMap.locationBlocksTexture);
		if (entityWebSling.getDataWatcher().getWatchableObjectByte(24) == 1)
			GL11.glColor3f(0F, 0F, 0F);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		if (entityWebSling.getDataWatcher().getWatchableObjectByte(24) < 2)
			blockRenderer.drawCrossedSquares(Blocks.web.getIcon(0, 0), -0.5D, -0.5D, -0.5D, 1.0F);
		else
			blockRenderer.drawCrossedSquares(Blocks.fire.getIcon(0, 0), -0.5D, -0.5D, -0.5D, 1.0F);
		tessellator.draw();

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}
