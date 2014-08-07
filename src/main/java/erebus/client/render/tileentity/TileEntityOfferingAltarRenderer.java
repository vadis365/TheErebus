package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
<<<<<<< HEAD

import org.lwjgl.opengl.GL11;

import erebus.client.model.block.ModelOfferingAltar;

public class TileEntityOfferingAltarRenderer extends TileEntitySpecialRenderer {
	public ModelOfferingAltar model = new ModelOfferingAltar();

	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y, double z, float m) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
		GL11.glScalef(0.5F, -0.5F, -0.5F);
		model.render();
		GL11.glPopMatrix();
=======
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import erebus.client.model.block.ModelOfferingAltar;
import erebus.tileentity.TileEntityOfferingAltar;

@SideOnly(Side.CLIENT)
public class TileEntityOfferingAltarRenderer extends TileEntitySpecialRenderer {
    public ModelOfferingAltar model = new ModelOfferingAltar();
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/altarOfOffering.png");

	@Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
    	TileEntityOfferingAltar altar = (TileEntityOfferingAltar) tile;
		bindTexture(getAltarTexture(altar));
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1F, -1F, -1F);
        model.render();
        GL11.glPopMatrix();
    }
 
	protected ResourceLocation getAltarTexture(TileEntityOfferingAltar altar) {
		return texture;
>>>>>>> FETCH_HEAD
	}
}
