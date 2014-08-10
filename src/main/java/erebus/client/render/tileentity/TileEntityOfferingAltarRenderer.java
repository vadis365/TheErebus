package erebus.client.render.tileentity;

import erebus.client.model.block.ModelOfferingAltar;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityOfferingAltarRenderer extends TileEntitySpecialRenderer {
	public ModelOfferingAltar model = new ModelOfferingAltar();
    public ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/offeringAltar.png");

	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y, double z, float m) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glRotatef(180f, 1f, 0f, 0f);
        bindTexture(texture);
		model.render();
		GL11.glPopMatrix();
	}
}
