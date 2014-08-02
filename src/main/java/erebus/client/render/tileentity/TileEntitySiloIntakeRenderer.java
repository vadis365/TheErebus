package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.block.silo.TileEntitySiloIntake;
import erebus.client.model.block.ModelSiloIntake;

@SideOnly(Side.CLIENT)
public class TileEntitySiloIntakeRenderer extends TileEntitySpecialRenderer {

	private final ModelSiloIntake ModelSiloIntake = new ModelSiloIntake();
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/siloIntake.png");

	public void renderSiloIntake(TileEntitySiloIntake pole, float x, float y, float z) {
		bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x + 0.5F, y + 0.435F, z + 0.5F);
		GL11.glScalef(1F, -1F, -1F);
		ModelSiloIntake.renderModel();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float rotation) {
		renderSiloIntake((TileEntitySiloIntake) tile, (float) x, (float) y, (float) z);
	}
}