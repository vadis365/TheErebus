package erebus.client.render.tile;

import erebus.block.altars.AltarAbstract;
import erebus.client.model.block.ModelAltarLightning;
import erebus.client.model.block.ModelErebusAltar;
import erebus.tileentity.TileEntityErebusAltarLightning;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarLightningRenderer extends TileEntitySpecialRenderer<TileEntityErebusAltarLightning> {
	private static final ResourceLocation[] TEXTURE = new ResourceLocation[] { new ResourceLocation("erebus:textures/special/tiles/altar_lightning_1.png"), new ResourceLocation("erebus:textures/special/tiles/altar_lightning_2.png"), new ResourceLocation("erebus:textures/special/tiles/altar_lightning_3.png"), new ResourceLocation("erebus:textures/special/tiles/altar_lightning_4.png"), new ResourceLocation("erebus:textures/special/tiles/altar_lightning_5.png"), new ResourceLocation("erebus:textures/special/tiles/altar_lightning_6.png"), new ResourceLocation("erebus:textures/special/tiles/altar_lightning_7.png") };

	private final ModelAltarLightning model = new ModelAltarLightning();
	private final ModelErebusAltar modelBasic = new ModelErebusAltar();

	public void renderTile(TileEntityErebusAltarLightning tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {
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

		model.render(tile);
		GlStateManager.popMatrix();
	}

	@Override
	public void render(TileEntityErebusAltarLightning tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if(tile == null || !tile.hasWorld()) {
			renderTileAsItem(x, y, z);
			return;
		}
		renderTile(tile, x, y, z, partialTicks, destroyStage, alpha);
	}

	private void renderTileAsItem(double x, double y, double z) {
		GlStateManager.pushMatrix();
		bindTexture(TEXTURE[0]);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
		GlStateManager.scale(-1, -1, 1);
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		modelBasic.render();
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}

	protected ResourceLocation getAltarTexture(TileEntityErebusAltarLightning tile) {
		if (tile.animationTicks <= 5)
			return TEXTURE[0];
		else if (tile.animationTicks > 5 && tile.animationTicks <= 10)
			return TEXTURE[1];
		else if (tile.animationTicks > 10 && tile.animationTicks <= 15)
			return TEXTURE[2];
		else if (tile.animationTicks > 15 && tile.animationTicks <= 20)
			return TEXTURE[3];
		else if (tile.animationTicks > 20 && tile.animationTicks < 25)
			return TEXTURE[4];
		else if (tile.animationTicks == 25 && tile.fuzz <= 5)
			return TEXTURE[4];
		else if (tile.animationTicks == 25 && tile.fuzz > 5 && tile.fuzz <= 10 || tile.animationTicks == 25 && tile.fuzz > 15 && tile.fuzz <= 20)
			return TEXTURE[5];
		else if (tile.animationTicks == 25 && tile.fuzz > 10 && tile.fuzz <= 15)
			return TEXTURE[6];
		else
			return null;
	}
}