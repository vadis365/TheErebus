package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAltar;
import erebus.client.model.block.ModelStone4;
import erebus.tileentity.TileEntityAltar;

@SideOnly(Side.CLIENT)
public class TileEntityAltarRenderer extends TileEntitySpecialRenderer {

	final ModelAltar model = new ModelAltar();
	final ModelStone4 stone4 = new ModelStone4();
	public static float rotation;
	public static final float rotaspeed = 0.01f;

	public void renderAModelAt(TileEntityAltar tileentity, double d, double d1, double d2, float f) {
		if (tileentity.blockMetadata == 1)
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/AltarActive.png"));
		else
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/Altar.png"));

		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5f, (float) d1 + 1.5f, (float) d2 + 0.5f);
		GL11.glRotatef(180F, 0.0f, 0.0F, 1.0F);
		model.renderModel(0.0625F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5f, (float) d1 + 1.5f, (float) d2 + 0.5f);
		bindTexture(new ResourceLocation("erebus:textures/special/tiles/Altar.png"));
		GL11.glRotatef(180F, 0.0f, 0.0f, 1.0f);
		GL11.glRotatef(rotation, 0.0f, 1.0f, 0.0f);
		stone4.renderModel(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
		renderAModelAt((TileEntityAltar) tileentity, d, d1, d2, f);
		if (rotation > 360)
			rotation = 0;
		else
			rotation = rotation + rotaspeed;
	}
}