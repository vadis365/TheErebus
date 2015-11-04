package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelUmberGolemStatue;
import erebus.tileentity.TileEntityUmberGolemStatue;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityUmberGolemStatueRenderer extends TileEntitySpecialRenderer {

	private final ModelUmberGolemStatue ModelUmberGolemStatue = new ModelUmberGolemStatue();

	public void renderAModelAt(TileEntityUmberGolemStatue tile, double x, double y, double z, float f) {
		bindTexture(new ResourceLocation("erebus:textures/special/tiles/umberGolemStatue.png"));
		int meta = tile.getBlockMetadata();
		switch (meta) {
			case 2:
				GL11.glPushMatrix();
				GL11.glTranslatef((float) x + 0.5F, (float) y + 1.1F, (float) z + 0.75F);
				GL11.glScalef(0.75F, -0.75F, -0.75F);
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				ModelUmberGolemStatue.render();
				GL11.glPopMatrix();
				break;
			case 3:
				GL11.glPushMatrix();
				GL11.glTranslatef((float) x + 0.5F, (float) y + 1.1F, (float) z + 0.25F);
				GL11.glScalef(0.75F, -0.75F, -0.75F);
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				ModelUmberGolemStatue.render();
				GL11.glPopMatrix();
				break;
			case 4:
				GL11.glPushMatrix();
				GL11.glTranslatef((float) x + 0.75F, (float) y + 1.1F, (float) z + 0.5F);
				GL11.glScalef(0.75F, -0.75F, -0.75F);
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				ModelUmberGolemStatue.render();
				GL11.glPopMatrix();
				break;
			case 5:
				GL11.glPushMatrix();
				GL11.glTranslatef((float) x + 0.25F, (float) y + 1.1F, (float) z + 0.5F);
				GL11.glScalef(0.75F, -0.75F, -0.75F);
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				ModelUmberGolemStatue.render();
				GL11.glPopMatrix();
				break;
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		renderAModelAt((TileEntityUmberGolemStatue) tile, x, y, z, partialTickTime);
	}
}