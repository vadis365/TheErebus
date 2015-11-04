package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelBambooBridge;
import erebus.tileentity.TileEntityBambooBridge;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityBambooBridgeRenderer extends TileEntitySpecialRenderer {

	private final ModelBambooBridge ModelBambooBridge = new ModelBambooBridge();
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/bambooLadder.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float rotation) {
		TileEntityBambooBridge bridge = (TileEntityBambooBridge) tile;
		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
		GL11.glScalef(1F, -1F, -1F);

		switch (bridge.getBlockMetadata()) {
			case 2:
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				break;
			case 3:
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				break;
			case 4:
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				break;
			case 5:
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				break;
		}

		ModelBambooBridge.render(bridge);
		GL11.glPopMatrix();
	}
}