package erebus.client.render.tile;

import erebus.ModBlocks;
import erebus.block.bamboo.BlockExtenderThingy;
import erebus.blocks.BlockPlanksErebus;
import erebus.blocks.EnumWood;
import erebus.client.model.block.ModelExtenderThingy;
import erebus.tileentity.TileEntityExtenderThingy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityExtenderThingyRenderer extends TileEntitySpecialRenderer <TileEntityExtenderThingy> {

	private final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/special/tiles/extender_thingy.png");
	private final ModelExtenderThingy EXTENDER_MODEL = new ModelExtenderThingy();

	public void renderTile(TileEntityExtenderThingy tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {
		IBlockState state = tile.getWorld().getBlockState(tile.getPos());
		if(state == null || state.getBlock() != ModBlocks.BAMBOO_EXTENDER)
			return;

		bindTexture(TEXTURE);
		EnumFacing facing = state.getValue(BlockExtenderThingy.FACING);

		switch (facing) {
			case DOWN:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y - 0.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(180F, 0.0F, 0F, 1F);
				GlStateManager.rotate(-180F, 0.0F, 1F, 0F);
				EXTENDER_MODEL.render2();
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
				GlStateManager.translate(x, y + 0.125, z + 1D);
				GlStateManager.scale(1.0D, 0.875D, 1.0D);
				bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(ModBlocks.PLANKS.getDefaultState().withProperty(BlockPlanksErebus.TYPE, EnumWood.BAMBOO), 1.0F);
				GlStateManager.popMatrix();
				break;
			case UP:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(180F, 0.0F, 1F, 0F);
				EXTENDER_MODEL.render2();
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
				GlStateManager.translate(x, y , z + 1F);
				GlStateManager.scale(1.0D, 0.875D, 1.0D);
				bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(ModBlocks.PLANKS.getDefaultState().withProperty(BlockPlanksErebus.TYPE, EnumWood.BAMBOO), 1.0F);
				GlStateManager.popMatrix();
				break;
			case NORTH:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(180F, 0.0F, 1F, 0F);
				EXTENDER_MODEL.render();
				GlStateManager.popMatrix();
				break;
			case SOUTH:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(0F, 0.0F, 1F, 0F);
				EXTENDER_MODEL.render();
				GlStateManager.popMatrix();
				break;
			case WEST:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(90F, 0.0F, 1F, 0F);
				EXTENDER_MODEL.render();
				GlStateManager.popMatrix();
				break;
			case EAST:
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5D, y + 1.5F, z + 0.5D);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.rotate(-90F, 0.0F, 1F, 0F);
				EXTENDER_MODEL.render();
				GlStateManager.popMatrix();
				break;
		}
	}

	@Override
	public void render(TileEntityExtenderThingy tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
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
		EXTENDER_MODEL.render();
		GlStateManager.popMatrix();
	}
}