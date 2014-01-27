package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelGlowingJar;
import erebus.tileentity.TileEntityGlowingJar;

@SideOnly(Side.CLIENT)
public class TileEntityGlowingJarRenderer extends TileEntitySpecialRenderer {

	private final ModelGlowingJar ModelGlowingJar = new ModelGlowingJar();

	public void renderAModelAt(TileEntityGlowingJar tile, double d, double d1, double d2, float f) {
		TileEntityGlowingJar te = tile;
		if (te.animationTicks <= 6)
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/ModelGlowingJar1.png"));
		else if (te.animationTicks > 6 && te.animationTicks <= 12)
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/ModelGlowingJar2.png"));
		else if (te.animationTicks > 12 && te.animationTicks <= 18)
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/ModelGlowingJar3.png"));
		else if (te.animationTicks > 18 && te.animationTicks <= 24)
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/ModelGlowingJar4.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
		GL11.glScalef(0.7F, -1F, -0.7F);
		ModelGlowingJar.render();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		renderAModelAt((TileEntityGlowingJar) tile, x, y, z, partialTickTime);
	}
}