package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelBambooCrate;
import erebus.client.model.block.ModelColossalCrate;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityRenderBambooCrate extends TileEntitySpecialRenderer {

	private final ModelBambooCrate bambooCrateModel = new ModelBambooCrate();
	public static ResourceLocation bambooCrateResource = new ResourceLocation("erebus:textures/special/tiles/bambooCrate.png");

	private final ModelColossalCrate colossalCrateModel = new ModelColossalCrate();
	public static ResourceLocation colossalCrateResource = new ResourceLocation("erebus:textures/special/tiles/colossalCrate.png");

	public void renderCrate(TileEntityBambooCrate crate, float x, float y, float z) {

		if (crate.getBlockMetadata() != 0) {
			if (crate.getBlockMetadata() == 1) {
				bindTexture(colossalCrateResource);
				GL11.glPushMatrix();
				GL11.glTranslatef(x + 1.5F, y + 1.5F, z + 1.5F);
				GL11.glScalef(1.0F, -1F, -1F);
				colossalCrateModel.renderModel();
				GL11.glPopMatrix();
			}
		} else {
			bindTexture(bambooCrateResource);
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
			GL11.glScalef(1.0F, -1F, -1F);
			bambooCrateModel.renderModel();
			GL11.glPopMatrix();
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float rotation) {
		renderCrate((TileEntityBambooCrate) tile, (float) x, (float) y, (float) z);
	}
}