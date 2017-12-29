package erebus.client.render.tile;

import erebus.ModBlocks;
import erebus.block.cooking.BlockSmoothieMaker;
import erebus.client.model.block.ModelSmoothieMaker;
import erebus.tileentity.TileEntitySmoothieMaker;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntitySmoothieMakerRenderer extends TileEntitySpecialRenderer <TileEntitySmoothieMaker> {

	private final ModelSmoothieMaker MODEL= new ModelSmoothieMaker();
	private final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/special/tiles/smoothie_maker.png");

	@Override
	public void render(TileEntitySmoothieMaker tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if(tile == null || !tile.hasWorld()) {
			renderTileAsItem(x, y, z);
			return;
		}
		renderTile(tile, x, y, z, partialTicks, destroyStage, alpha);
	}
	
	public void renderTile(TileEntitySmoothieMaker tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {

		IBlockState state = tile.getWorld().getBlockState(tile.getPos());
		if(state == null || state.getBlock() != ModBlocks.SMOOTHIE_MAKER)
			return;

		bindTexture(TEXTURE);
		EnumFacing facing = state.getValue(BlockSmoothieMaker.FACING);
		switch (facing) {
			case DOWN:
				break;
			case UP:
				break;
			case NORTH:
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.scale(0.89F, 1.0F, 0.89F);
				GlStateManager.rotate(90F, 0.0F, 1F, 0F);
				MODEL.renderAll();
				GlStateManager.popMatrix();
				break;
			case SOUTH:
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.scale(0.89F, 1.0F, 0.89F);
				GlStateManager.rotate(-90F, 0.0F, 1F, 0F);
				MODEL.renderAll();
				GlStateManager.popMatrix();
				break;
			case WEST:
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.scale(0.89F, 1.0F, 0.89F);
				GlStateManager.rotate(0F, 0.0F, 1F, 0F);
				MODEL.renderAll();
				GlStateManager.popMatrix();
				break;
			case EAST:
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
				GlStateManager.scale(1F, -1F, -1F);
				GlStateManager.scale(0.89F, 1.0F, 0.89F);
				GlStateManager.rotate(180F, 0.0F, 1F, 0F);
				MODEL.renderAll();
				GlStateManager.popMatrix();
				break;
		}
	}

	private void renderTileAsItem(double x, double y, double z) {
		GlStateManager.pushMatrix();
		bindTexture(TEXTURE);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.scale(0.89F, 1.0F, 0.89F);
		MODEL.renderAll();
		GlStateManager.popMatrix();
	}
}
