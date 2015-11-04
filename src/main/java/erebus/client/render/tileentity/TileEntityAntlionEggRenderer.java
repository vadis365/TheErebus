package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAntlionEgg;
import erebus.tileentity.TileEntityAntlionEgg;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityAntlionEggRenderer extends TileEntitySpecialRenderer {

	private final ModelAntlionEgg model = new ModelAntlionEgg();
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/antlionEgg.png");

	public void renderEgg(TileEntityAntlionEgg tile, float x, float y, float z) {
		bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glScaled(-1, -1, 1);
		model.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float rotation) {
		renderEgg((TileEntityAntlionEgg) tile, (float) x, (float) y, (float) z);
	}
}