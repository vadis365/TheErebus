package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelBambooBridge;
import erebus.tileentity.TileEntityBambooBridge;

@SideOnly(Side.CLIENT)
public class TileEntityBambooBridgeRenderer extends TileEntitySpecialRenderer {

	private final ModelBambooBridge ModelBambooBridge = new ModelBambooBridge();

	public void renderAModelAt(TileEntityBambooBridge tile, double x, double y, double z, float f) {
		bindTexture(new ResourceLocation("erebus:textures/special/tiles/bambooLadder.png"));
		int meta = tile.getBlockMetadata();
		switch (meta) {
			case 2:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				ModelBambooBridge.render(tile);
				GL11.glPopMatrix();
				break;
			case 3:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				ModelBambooBridge.render(tile);
				GL11.glPopMatrix();
				break;
			case 4:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				ModelBambooBridge.render(tile);
				GL11.glPopMatrix();
				break;
			case 5:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				ModelBambooBridge.render(tile);
				GL11.glPopMatrix();
				break;
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float rotation) {
		{
			renderAModelAt((TileEntityBambooBridge) tile, x, y, z, rotation);
		}
	}
}