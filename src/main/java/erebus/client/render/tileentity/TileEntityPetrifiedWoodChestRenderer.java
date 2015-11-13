package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.block.BlockPetrifiedChest;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityPetrifiedWoodChestRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation RES_NORMAL_DOUBLE = new ResourceLocation("erebus:textures/special/tiles/petrifiedLargechest.png");
	private static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation("erebus:textures/special/tiles/petrifiedChest.png");

	private final ModelChest chestModel = new ModelChest();
	private final ModelChest largeChestModel = new ModelLargeChest();

	public void renderTileEntityPetrifiedWoodChestAt(TileEntityPetrifiedWoodChest chest, double x, double y, double z, float partialTickTime) {
		int i;

		if (!chest.hasWorldObj())
			i = 0;
		else {
			Block block = chest.getBlockType();
			i = chest.getBlockMetadata();

			if (block instanceof BlockPetrifiedChest && i == 0) {
				try {
					((BlockPetrifiedChest) block).unifyAdjacentChests(chest.getWorldObj(), chest.xCoord, chest.yCoord, chest.zCoord);
				} catch (ClassCastException e) {
					FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", chest.xCoord, chest.yCoord, chest.zCoord);
				}
				i = chest.getBlockMetadata();
			}

			chest.checkForAdjacentChests();
		}

		if (chest.adjacentChestZNeg == null && chest.adjacentChestXNeg == null) {
			ModelChest modelchest;

			if (chest.adjacentChestXPos == null && chest.adjacentChestZPosition == null) {
				modelchest = chestModel;
				bindTexture(RES_NORMAL_SINGLE);
			} else {
				modelchest = largeChestModel;
				bindTexture(RES_NORMAL_DOUBLE);
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short short1 = 0;

			if (i == 2)
				short1 = 180;

			if (i == 3)
				short1 = 0;

			if (i == 4)
				short1 = 90;

			if (i == 5)
				short1 = -90;

			if (i == 2 && chest.adjacentChestXPos != null)
				GL11.glTranslatef(1.0F, 0.0F, 0.0F);

			if (i == 5 && chest.adjacentChestZPosition != null)
				GL11.glTranslatef(0.0F, 0.0F, -1.0F);

			GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float f1 = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTickTime;
			float f2;

			if (chest.adjacentChestZNeg != null) {
				f2 = chest.adjacentChestZNeg.prevLidAngle + (chest.adjacentChestZNeg.lidAngle - chest.adjacentChestZNeg.prevLidAngle) * partialTickTime;

				if (f2 > f1)
					f1 = f2;
			}

			if (chest.adjacentChestXNeg != null) {
				f2 = chest.adjacentChestXNeg.prevLidAngle + (chest.adjacentChestXNeg.lidAngle - chest.adjacentChestXNeg.prevLidAngle) * partialTickTime;

				if (f2 > f1)
					f1 = f2;
			}

			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
			modelchest.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		renderTileEntityPetrifiedWoodChestAt((TileEntityPetrifiedWoodChest) tile, x, y, z, partialTickTime);
	}
}