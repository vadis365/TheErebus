package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelGlowGem;
import erebus.tileentity.TileEntityGlowGem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityGlowGemRenderer extends TileEntitySpecialRenderer {

	private final ModelGlowGem ModelGlowGem = new ModelGlowGem();

	public void renderAModelAt(TileEntityGlowGem tile, double x, double y, double z, float f) {
		bindTexture(new ResourceLocation("erebus:textures/special/tiles/glowGem.png"));
		if (!tile.lightOn)
			GL11.glColor3f(1F, 0F, 0F);
		int meta = tile.getBlockMetadata();
		switch (meta) {
			case 0:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y - 0.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 0F, 1F);
				GL11.glRotatef(-180F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 1:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 2:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 0.5F, z - 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				GL11.glRotatef(90F, 1.0F, 0F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 3:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 0.5F, z + 1.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				GL11.glRotatef(90F, 1.0F, 0F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 4:
				GL11.glPushMatrix();
				GL11.glTranslated(x - 0.5D, y + 0.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				GL11.glRotatef(90F, 1.0F, 0F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 5:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 1.5D, y + 0.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				GL11.glRotatef(90F, 1.0F, 0F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 6:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y - 0.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 0F, 1F);
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 7:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y - 0.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 0F, 1F);
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 8:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y - 0.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 0F, 1F);
				GL11.glRotatef(-180F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 9:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y - 0.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 0F, 1F);
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 10:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 11:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 12:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
			case 13:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				ModelGlowGem.render(tile);
				GL11.glPopMatrix();
				break;
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float meta) {
		renderAModelAt((TileEntityGlowGem) tile, x, y, z, meta);
	}
}