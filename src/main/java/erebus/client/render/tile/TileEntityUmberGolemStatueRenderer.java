package erebus.client.render.tile;

import erebus.ModBlocks;
import erebus.blocks.BlockBones;
import erebus.blocks.BlockUmberGolemStatue;
import erebus.client.model.block.ModelUmberGolemStatue;
import erebus.tileentity.TileEntityUmberGolemStatue;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityUmberGolemStatueRenderer extends TileEntitySpecialRenderer<TileEntityUmberGolemStatue> {

	private final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/special/tiles/umber_golem_statue.png");
	private final ModelUmberGolemStatue MODEL = new ModelUmberGolemStatue();

	public void renderTile(TileEntityUmberGolemStatue tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {

		IBlockState state = tile.getWorld().getBlockState(tile.getPos());
		if(state == null || state.getBlock() != ModBlocks.UMBER_GOLEM_STATUE)
			return;

		bindTexture(TEXTURE);
		EnumFacing facing = state.getValue(BlockUmberGolemStatue.FACING);
		switch (facing) {
			case DOWN:
				break;
			case UP:
				break;
			case NORTH:
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.1F, (float) z + 0.75F);
				GlStateManager.scale(0.75F, -0.75F, -0.75F);
				GlStateManager.rotate(180F, 0.0F, 1F, 0F);
				MODEL.render();
				GlStateManager.popMatrix();
				break;
			case SOUTH:
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.1F, (float) z + 0.25F);
				GlStateManager.scale(0.75F, -0.75F, -0.75F);
				GlStateManager.rotate(0F, 0.0F, 1F, 0F);
				MODEL.render();
				GlStateManager.popMatrix();
				break;
			case WEST:
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.75F, (float) y + 1.1F, (float) z + 0.5F);
				GlStateManager.scale(0.75F, -0.75F, -0.75F);
				GlStateManager.rotate(90F, 0.0F, 1F, 0F);
				MODEL.render();
				GlStateManager.popMatrix();
				break;
			case EAST:
				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.25F, (float) y + 1.1F, (float) z + 0.5F);
				GlStateManager.scale(0.75F, -0.75F, -0.75F);
				GlStateManager.rotate(-90F, 0.0F, 1F, 0F);
				MODEL.render();
				GlStateManager.popMatrix();
				break;
		}
	}

	@Override
	public void render(TileEntityUmberGolemStatue tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
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
}