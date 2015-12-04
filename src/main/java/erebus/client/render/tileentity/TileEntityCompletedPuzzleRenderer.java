package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityCompletedPuzzle;
import erebus.tileentity.TileEntitySlidingBlockPuzzle.SlidingPuzzle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityCompletedPuzzleRenderer extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		TileEntityCompletedPuzzle puzzleTile = (TileEntityCompletedPuzzle) tile;
		SlidingPuzzle puzzle = puzzleTile.getPuzzle();
		if (puzzle == null)
			puzzle = SlidingPuzzle.TEST;

		bindTexture(new ResourceLocation(puzzle.imagePath));

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glScalef(-0.0625F, -0.0625F, 0.0625F);
		GL11.glTranslated(-16, -16, 0);

		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawingQuads();

		// WEST
		tessellator.addVertexWithUV(16, 16, 0, 0, 1);
		tessellator.addVertexWithUV(16, 16, 16, 1, 1);
		tessellator.addVertexWithUV(16, 0, 16, 1, 0);
		tessellator.addVertexWithUV(16, 0, 0, 0, 0);

		// EAST
		tessellator.addVertexWithUV(0, 16, 0, 1, 1);
		tessellator.addVertexWithUV(0, 0, 0, 1, 0);
		tessellator.addVertexWithUV(0, 0, 16, 0, 0);
		tessellator.addVertexWithUV(0, 16, 16, 0, 1);

		// NORTH
		tessellator.addVertexWithUV(16, 0, 0, 1, 0);
		tessellator.addVertexWithUV(0, 0, 0, 0, 0);
		tessellator.addVertexWithUV(0, 16, 0, 0, 1);
		tessellator.addVertexWithUV(16, 16, 0, 1, 1);

		// SOUTH
		tessellator.addVertexWithUV(16, 16, 16, 0, 1);
		tessellator.addVertexWithUV(0, 16, 16, 1, 1);
		tessellator.addVertexWithUV(0, 0, 16, 1, 0);
		tessellator.addVertexWithUV(16, 0, 16, 0, 0);

		tessellator.draw();

		GL11.glPopMatrix();
	}
}