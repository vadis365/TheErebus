package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.block.ModelSmoothieMaker;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntitySmoothieMakerRenderer extends TileEntitySpecialRenderer {

	private final ModelSmoothieMaker model;
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/smoothieMaker.png");

	public TileEntitySmoothieMakerRenderer() {
		model = new ModelSmoothieMaker();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) (z + 0.5F));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(0.89F, 1.0F, 0.89F);
		model.renderAll();
		GL11.glPopMatrix();
	}

}
