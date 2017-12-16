package erebus.client.render.tile;

import erebus.blocks.BlockPetrifiedChest;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityPetrifiedWoodChestRenderer extends TileEntitySpecialRenderer<TileEntityPetrifiedWoodChest> {

	private static final ResourceLocation RES_NORMAL_DOUBLE = new ResourceLocation("erebus:textures/special/tiles/petrified_large_chest.png");
	private static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation("erebus:textures/special/tiles/petrified_chest.png");

	private final ModelChest chestModel = new ModelChest();
	private final ModelChest largeChestModel = new ModelLargeChest();

	@Override
	public void render(TileEntityPetrifiedWoodChest chest, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		int i;
		
		if(chest == null) {
			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.translate(0.5F, 0.5F, 0.5F);
			GlStateManager.rotate(/*(float)j*/0.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			bindTexture(RES_NORMAL_SINGLE);
			this.chestModel.chestLid.rotateAngleX = 0;
			this.chestModel.renderAll();

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			return;
		}

		if (!chest.hasWorld())
			i = 0;
		else {
			Block block = chest.getBlockType();
			i = chest.getBlockMetadata();

			if (block instanceof BlockPetrifiedChest && i == 0) {
				try {
					((BlockPetrifiedChest) block).checkForSurroundingChests(chest.getWorld(), chest.getPos(), chest.getWorld().getBlockState(chest.getPos()));
				} catch (ClassCastException e) {
					FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", chest.getPos());
				}
				i = chest.getBlockMetadata();
			}

			chest.checkForAdjacentChests();
		}

		if (chest.adjacentChestZNeg == null && chest.adjacentChestXNeg == null) {
			ModelChest modelchest;

			if (chest.adjacentChestXPos == null && chest.adjacentChestZPos == null) {
				modelchest = chestModel;
				bindTexture(RES_NORMAL_SINGLE);
			} else {
				modelchest = largeChestModel;
				bindTexture(RES_NORMAL_DOUBLE);
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.translate(0.5F, 0.5F, 0.5F);
			float short1 = 0;

			if (i == 2)
				short1 = 180;

			if (i == 3)
				short1 = 0;

			if (i == 4)
				short1 = 90;

			if (i == 5)
				short1 = -90;

			if (i == 2 && chest.adjacentChestXPos != null)
				GlStateManager.translate(1.0F, 0.0F, 0.0F);

			if (i == 5 && chest.adjacentChestZPos != null)
				GlStateManager.translate(0.0F, 0.0F, -1.0F);

			GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			float f1 = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTicks;
			float f2;

			if (chest.adjacentChestZNeg != null) {
				f2 = chest.adjacentChestZNeg.prevLidAngle + (chest.adjacentChestZNeg.lidAngle - chest.adjacentChestZNeg.prevLidAngle) * partialTicks;

				if (f2 > f1)
					f1 = f2;
			}

			if (chest.adjacentChestXNeg != null) {
				f2 = chest.adjacentChestXNeg.prevLidAngle + (chest.adjacentChestXNeg.lidAngle - chest.adjacentChestXNeg.prevLidAngle) * partialTicks;

				if (f2 > f1)
					f1 = f2;
			}

			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
			modelchest.renderAll();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}