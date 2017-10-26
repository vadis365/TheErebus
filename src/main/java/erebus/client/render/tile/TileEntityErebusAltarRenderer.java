package erebus.client.render.tile;

import erebus.block.altars.AltarAbstract;
import erebus.client.model.block.ModelErebusAltar;
import erebus.tileentity.TileEntityErebusAltar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarRenderer extends TileEntitySpecialRenderer<TileEntityErebusAltar> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/special/tiles/altar_base.png");

	private final ModelErebusAltar model = new ModelErebusAltar();

	public void renderTile(TileEntityErebusAltar tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {
		IBlockState state = tile.getWorld().getBlockState(tile.getPos());
		if(tile == null || !tile.hasWorld())
			return;
		EnumFacing facing = state.getValue(AltarAbstract.FACING);

		bindTexture(getAltarTexture(tile));
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
		GlStateManager.scale(0.5F, -0.5F, -0.5F);

		switch (facing) {
		case UP:
		case DOWN:
		case NORTH:
			GlStateManager.rotate(180F, 0F, 1F, 0F);
			break;
		case SOUTH:
			GlStateManager.rotate(0F, 0.0F, 1F, 0F);
			break;
		case WEST:
			GlStateManager.rotate(90F, 0.0F, 1F, 0F);
			break;
		case EAST:
			GlStateManager.rotate(-90F, 0.0F, 1F, 0F);
			break;
		}

		renderModel(tile);
		GlStateManager.popMatrix();
	}

	@Override
	public void render(TileEntityErebusAltar tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if(tile == null || !tile.hasWorld()) {
			renderTileAsItem(x, y, z);
			return;
		}
		renderTile(tile, x, y, z, partialTicks, destroyStage, alpha);
	}

	private void renderTileAsItem(double x, double y, double z) {
		GlStateManager.pushMatrix();
		bindTexture(TEXTURE);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
		GlStateManager.scale(-1, -1, 1);
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		model.render();
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}

	protected void renderModel(TileEntityErebusAltar altar) {
		model.render();
	}

	protected ResourceLocation getAltarTexture(TileEntityErebusAltar altar) {
		return TEXTURE;
	}
}