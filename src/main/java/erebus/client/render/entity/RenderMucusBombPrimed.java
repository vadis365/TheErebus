package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.entity.EntityMucusBombPrimed;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMucusBombPrimed extends Render {
	private RenderBlocks blockRenderer = new RenderBlocks();
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/blocks/mucusBombSides.png");

	public RenderMucusBombPrimed() {
		shadowSize = 0.5F;
	}

	public void renderPrimedMucusBomb(EntityMucusBombPrimed entityMucusBombPrimed, double x, double y, double z, float rotationYaw, float partialTickTime) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		float f2;

		if (entityMucusBombPrimed.fuse - partialTickTime + 1.0F < 10.0F) {
			f2 = 1.0F - (entityMucusBombPrimed.fuse - partialTickTime + 1.0F) / 10.0F;

			if (f2 < 0.0F)
				f2 = 0.0F;

			if (f2 > 1.0F)
				f2 = 1.0F;

			f2 *= f2;
			f2 *= f2;
			float f3 = 1.0F + f2 * 0.3F;
			GL11.glScalef(f3, f3, f3);
		}

		f2 = (1.0F - (entityMucusBombPrimed.fuse - partialTickTime + 1.0F) / 100.0F) * 0.8F;
		bindTexture(TextureMap.locationBlocksTexture);
		blockRenderer.renderBlockAsItem(ModBlocks.mucusBomb, 0, entityMucusBombPrimed.getBrightness(partialTickTime));

		if (entityMucusBombPrimed.fuse / 5 % 2 == 0) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
			bindTexture(TextureMap.locationBlocksTexture);
			blockRenderer.renderBlockAsItem(ModBlocks.mucusBomb, 0, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderPrimedMucusBomb((EntityMucusBombPrimed) entity, x, y, z, rotationYaw, partialTickTime);
	}
}
