package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelErebusAltar;
import erebus.tileentity.TileEntityErebusAltar;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarRenderer extends TileEntitySpecialRenderer {
	private static final ResourceLocation tex = new ResourceLocation("erebus:textures/special/tiles/altarBase.png");

	private final ModelErebusAltar model = new ModelErebusAltar();

	@Override
	public final void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		TileEntityErebusAltar altar = (TileEntityErebusAltar) tile;
		int meta = tile.getBlockMetadata();

		bindTexture(getAltarTexture(altar));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
		GL11.glScalef(0.5F, -0.5F, -0.5F);

		switch (meta) {
			case 2:
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				break;
			case 3:
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				break;
			case 4:
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				break;
			case 5:
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				break;
		}

		renderModel(altar);
		GL11.glPopMatrix();
	}

	protected void renderModel(TileEntityErebusAltar altar) {
		model.render();
	}

	protected ResourceLocation getAltarTexture(TileEntityErebusAltar altar) {
		return tex;
	}
}