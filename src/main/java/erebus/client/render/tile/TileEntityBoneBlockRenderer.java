package erebus.client.render.tile;

import org.lwjgl.opengl.GL11;

import erebus.ModBlocks;
import erebus.blocks.BlockBones;
import erebus.client.model.block.ModelBoneBlock;
import erebus.tileentity.TileEntityBones;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityBoneBlockRenderer extends TileEntitySpecialRenderer<TileEntityBones> {
	
	private final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/special/tiles/bone_block.png");
	private final ModelBoneBlock MODEL = new ModelBoneBlock();
	
	public void renderTile(TileEntityBones tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {
		IBlockState state = tile.getWorld().getBlockState(tile.getPos());
		if(state == null || state.getBlock() != ModBlocks.BLOCK_OF_BONES)
			return;

		bindTexture(TEXTURE);
		EnumFacing facing = state.getValue(BlockBones.FACING);
		if(facing == null)
			return;

		switch (facing) {
			case DOWN:
				break;
			case UP:
				break;
			case NORTH:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(180F, 0.0F, 1F, 0F);
				MODEL.render();
				GlStateManager.popMatrix();
				break;
			case SOUTH:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(0F, 0.0F, 1F, 0F);
				MODEL.render();
				GlStateManager.popMatrix();
				break;
			case WEST:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(90F, 0.0F, 1F, 0F);
				MODEL.render();
				GlStateManager.popMatrix();
				break;
			case EAST:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(-90F, 0.0F, 1F, 0F);
				MODEL.render();
				GlStateManager.popMatrix();
				break;
		}
		if (!StringUtils.isNullOrEmpty(tile.getOwnerName()))
			renderNameTag(tile.getOwnerName(), x + 0.5D, y, z + 0.5D);
	}
	
	@Override
	public void render(TileEntityBones tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if(tile == null || !tile.hasWorld()) {
			renderTileAsItem(x, y, z);
			return;
		}
		renderTile(tile, x, y, z, partialTicks, destroyStage, alpha);
	}

	private void renderTileAsItem(double x, double y, double z) {
		GlStateManager.pushMatrix();
		bindTexture(TEXTURE);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(-1, -1, 1);
		MODEL.render();
		GlStateManager.popMatrix();
	}

	private void renderNameTag(String name, double x, double y, double z) {
		float scale = 0.02666667F;
		float height = 0.8F;
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + height + 0.5F, z);
		GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
		GlStateManager.scale(-scale, -scale, scale);
		GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.disableLighting();
		GlStateManager.depthMask(false);
		GlStateManager.disableDepth();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		GlStateManager.disableTexture2D();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
		int width = fontrenderer.getStringWidth(name) / 2;
		vertexbuffer.pos(x - width - 1, y - 1, z).color(0F, 0F, 0, 0.25F).endVertex();
		vertexbuffer.pos(x - width - 1, y + 8, z).color(0F, 0F, 0, 0.25F).endVertex();
		vertexbuffer.pos(x + width + 1, y + 8, z).color(0F, 0F, 0, 0.25F).endVertex();
		vertexbuffer.pos(x + width + 1, y - 1, z).color(0F, 0F, 0, 0.25F).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, 0, 553648127);
		GlStateManager.enableDepth();
		GlStateManager.depthMask(true);
		fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, 0, -1);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
	}
}