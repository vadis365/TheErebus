package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntitySlidingBlockPuzzle;
import erebus.tileentity.TileEntitySlidingBlockPuzzle.SlidingPiece;
import erebus.tileentity.TileEntitySlidingBlockPuzzle.SlidingPuzzle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

@SideOnly(Side.CLIENT)
public class TileEntitySlidingBlockPuzzleRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("erebus", "textures/blocks/anthillBlock.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		TileEntitySlidingBlockPuzzle puzzleTile = (TileEntitySlidingBlockPuzzle) tile;
		ForgeDirection facing = puzzleTile.getFacing();
		SlidingPuzzle puzzle = puzzleTile.getPuzzle();
		SlidingPiece[] pieces = puzzleTile.getPieces();
		if (puzzle == null)
			return;

		bindTexture(new ResourceLocation(puzzle.imagePath));

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		if (facing == ForgeDirection.WEST || facing == ForgeDirection.SOUTH)
			GL11.glTranslated(facing.offsetX, 0, facing.offsetZ);
		GL11.glScalef(-0.0625F, -0.0625F, 0.0625F);
		GL11.glTranslated(-16, -16, 0);

		for (int i = 0; i < pieces.length; i++) {
			SlidingPiece piece = pieces[i];
			if (piece.isEmpty())
				continue;

			int xx = i % 2 * 8;
			int yy = i / 2 * 8;

			int width = 8;
			int height = 8;
			double uvSize = 1 / 6F;
			double uu = piece.getU() / 6F;
			double vv = piece.getV() / 6F;
			drawFace(facing, xx, yy, width, height, uu, vv, uvSize);
		}

		bindTexture(BACKGROUND_TEXTURE);

		GL11.glPushMatrix();
		GL11.glTranslated(16 * facing.offsetX, 0, 16 * -facing.offsetZ);
		drawFace(facing.getOpposite(), 0, 0, 16, 16, 0, 0, 1);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(facing.offsetX, 0, -facing.offsetZ);
		drawFace(facing, 0, 0, 16, 16, 0, 0, 1);
		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void drawFace(ForgeDirection facing, float xx, float yy, float width, float height, double uu, double vv, double uvSize) {
		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawingQuads();
		switch (facing) {
			case WEST:
				tessellator.addVertexWithUV(0, yy + height, xx + 0, uu + 0, vv + uvSize);
				tessellator.addVertexWithUV(0, yy + height, xx + width, uu + uvSize, vv + uvSize);
				tessellator.addVertexWithUV(0, yy + 0, xx + width, uu + uvSize, vv + 0);
				tessellator.addVertexWithUV(0, yy + 0, xx + 0, uu + 0, vv + 0);
				break;
			case EAST:
				tessellator.addVertexWithUV(0, yy + 0, xx + 0, uu + uvSize, vv + 0);
				tessellator.addVertexWithUV(0, yy + 0, xx + width, uu + 0, vv + 0);
				tessellator.addVertexWithUV(0, yy + height, xx + width, uu + 0, vv + uvSize);
				tessellator.addVertexWithUV(0, yy + height, xx + 0, uu + uvSize, vv + uvSize);
				break;
			case SOUTH:
				tessellator.addVertexWithUV(xx + 0, yy + 0, 0, uu + uvSize, vv + 0);
				tessellator.addVertexWithUV(xx + width, yy + 0, 0, uu + 0, vv + 0);
				tessellator.addVertexWithUV(xx + width, yy + height, 0, uu + 0, vv + uvSize);
				tessellator.addVertexWithUV(xx + 0, yy + height, 0, uu + uvSize, vv + uvSize);
				break;
			case NORTH:
				tessellator.addVertexWithUV(xx + 0, yy + height, 0, uu + 0, vv + uvSize);
				tessellator.addVertexWithUV(xx + width, yy + height, 0, uu + uvSize, vv + uvSize);
				tessellator.addVertexWithUV(xx + width, yy + 0, 0, uu + uvSize, vv + 0);
				tessellator.addVertexWithUV(xx + 0, yy + 0, 0, uu + 0, vv + 0);
				break;
			default:
				break;
		}
		tessellator.draw();
	}
}