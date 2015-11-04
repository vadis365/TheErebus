package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelBambooPole;
import erebus.tileentity.TileEntityBambooPole;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityBambooPoleRenderer extends TileEntitySpecialRenderer {

	private final ModelBambooPole ModelBambooPole = new ModelBambooPole();
	public static ResourceLocation bambooCrateResource = new ResourceLocation("erebus:textures/special/tiles/bambooCrate.png");

	public void renderPole(TileEntityBambooPole pole, float x, float y, float z) {
		bindTexture(bambooCrateResource);
		GL11.glPushMatrix();
		GL11.glTranslatef(x + 0.5F, y + 0.5F, z + 0.5F);
		ModelBambooPole.renderModel();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float rotation) {
		renderPole((TileEntityBambooPole) tile, (float) x, (float) y, (float) z);
	}
}