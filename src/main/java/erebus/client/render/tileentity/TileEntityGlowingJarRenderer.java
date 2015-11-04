package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.client.model.block.ModelGlowingJar;
import erebus.tileentity.TileEntityGlowingJar;
import erebus.tileentity.TileEntityJarOHoney;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityGlowingJarRenderer extends TileEntitySpecialRenderer {

	private final ModelGlowingJar glowingJar = new ModelGlowingJar();
	private final RenderItem renderItem;
	private final RenderBlocks blockRenderer = new RenderBlocks();
	private static final ResourceLocation GLOWING_JAR = new ResourceLocation("erebus:textures/special/tiles/glowingJar.png");
	public static TileEntityGlowingJarRenderer instance;

	public TileEntityGlowingJarRenderer() {
		renderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return false;
			}
		};
		renderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void func_147497_a(TileEntityRendererDispatcher renderer) {
		super.func_147497_a(renderer);
		instance = this;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		if (tile instanceof TileEntityJarOHoney)
			renderJarOHoney((TileEntityJarOHoney) tile, x, y, z);
		else if (tile instanceof TileEntityGlowingJar)
			renderGlowJar(x, y, z, ((TileEntityGlowingJar) tile).getGhostItem());

		renderJar(x, y, z);
	}

	public void renderJarOHoney(TileEntityJarOHoney tile, double x, double y, double z) {
		int amount = tile.tank.getFluidAmount();
		int capacity = tile.tank.getCapacity();
		float size = 0.70F / capacity * amount;
		if (amount >= 100) {
			GL11.glPushMatrix();
			GL11.glEnable(3042);
			GL11.glBlendFunc(770, 771);
			GL11.glTranslated((float) x + 0.5F, (float) (y + 0.030F + size * 0.5F), (float) z + 0.5F);
			GL11.glScalef(0.55F, -size, -0.55F);
			bindTexture(TextureMap.locationBlocksTexture);
			blockRenderer.renderBlockAsItem(ModBlocks.amber, 0, 1.0F);
			GL11.glDisable(3042);
			GL11.glPopMatrix();
		}
		renderNameTag(tile.getOwnerName(), x, y, z);
	}

	public void renderGlowJar(double x, double y, double z, EntityItem ghostItem) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) (y + 0.2F), (float) z + 0.5F);
		GL11.glScalef(1.2F, 1.2F, 1.2F);
		renderItem.doRender(ghostItem, 0, 0, 0, 0, 0);
		GL11.glRotatef(90, 0, 1, 0);
		renderItem.doRender(ghostItem, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}

	public void renderJar(double x, double y, double z) {
		bindTexture(GLOWING_JAR);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.51F, (float) z + 0.5F);
		GL11.glScalef(0.7F, -1F, -0.7F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		glowingJar.render();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}

	private void renderNameTag(String name, double x, double y, double z) {
		float scale = 0.02666667F;
		float height = 0.8F;

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + height + 0.5F, z + 0.5F);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(-scale, -scale, scale);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		tessellator.startDrawingQuads();
		FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
		int width = fontrenderer.getStringWidth(name) / 2;
		tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
		tessellator.addVertex(-width - 1, -1, 0.0D);
		tessellator.addVertex(-width - 1, 8, 0.0D);
		tessellator.addVertex(width + 1, 8, 0.0D);
		tessellator.addVertex(width + 1, -1, 0.0D);
		tessellator.draw();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, 0, 553648127);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, 0, -1);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}
}