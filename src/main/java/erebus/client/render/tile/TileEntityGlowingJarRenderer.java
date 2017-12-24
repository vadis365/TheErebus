package erebus.client.render.tile;

import org.lwjgl.opengl.GL11;

import erebus.client.model.block.ModelGlowingJar;
import erebus.lib.Reference;
import erebus.tileentity.TileEntityGlowingJar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityGlowingJarRenderer extends TileEntitySpecialRenderer <TileEntityGlowingJar> {

	private final ModelGlowingJar glowingJar = new ModelGlowingJar();
	private static final ResourceLocation GLOWING_JAR_TEXTURE = new ResourceLocation("erebus:textures/special/tiles/glowing_jar.png");
	private static final ResourceLocation WISP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":entity/wisp");

	@Override
	public void render(TileEntityGlowingJar tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if(tile == null || !tile.hasWorld()) {
			renderTileAsItem(x, y, z);
			return;
		}
		renderTile(tile, x, y, z, partialTicks, destroyStage, alpha);
	}

	public void renderTile(TileEntityGlowingJar tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate(x + 0.5F, y - tile.particleSize / 4, z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		setGLColorFromInt(-1277682);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		float xMax, zMax, xMin, zMin, yMin = 0;
		xMax = 0.5F;
		zMax = 0F;
		xMin = -0.5F;
		zMin = 0F;
		yMin = 0.5F;
		GlStateManager.enableRescaleNormal();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.rotate(180F + Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, -1.0F, 0.0F);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(WISP_TEXTURE.toString());
		GlStateManager.scale(tile.particleSize / 3 + 0.5F, tile.particleSize / 3 + 0.5F, tile.particleSize / 3 + 0.5F);
		renderQuads(buffer, xMax, xMin, yMin, 1F, zMin, zMax, texture);
		tessellator.draw();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();

		bindTexture(GLOWING_JAR_TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.51F, (float) z + 0.5F);
		GlStateManager.scale(0.7F, -0.9999F, -0.7F);
		GlStateManager.disableCull();
		glowingJar.render();
		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

	private void setGLColorFromInt(int color) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;
		GlStateManager.color(red, green, blue, 1F);
	}

	private void renderQuads(BufferBuilder buffer, float xMax, float xMin, float yMin, float height, float zMin, float zMax, TextureAtlasSprite textureAtlasSprite) {
		double uMin = (double) textureAtlasSprite.getMinU();
		double uMax = (double) textureAtlasSprite.getMaxU();
		double vMin = (double) textureAtlasSprite.getMinV();
		double vMax = (double) textureAtlasSprite.getMaxV();
		final double vHeight = vMax - vMin;
		addVertexWithUV(buffer, xMax, yMin, zMax, uMin, vMin);
		addVertexWithUV(buffer, xMax, height, zMax, uMin, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMin, height, zMax, uMax, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMin, yMin, zMax, uMax, vMin);
	}

	private void addVertexWithUV(BufferBuilder buffer, float x, float y, float z, double u, double v) {
		buffer.pos(x / 2f, y, z / 2f).tex(u, v).endVertex();
	}

	public void renderTileAsItem(double x, double y, double z) {
		bindTexture(GLOWING_JAR_TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.51F, (float) z + 0.5F);
		GlStateManager.scale(0.7F, -1F, -0.7F);
		GlStateManager.disableCull();
		glowingJar.render();
		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

}