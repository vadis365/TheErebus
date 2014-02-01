package erebus.entity.ai;

import java.awt.Point;
import java.util.List;

import net.minecraft.block.BlockCrops;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import erebus.core.helper.Spiral;
import erebus.core.helper.Utils;

public class BetterEntityAIEatCrop extends EntityAIBase {

	private final EntityAnimal entity;
	private final int maxGrowthMetadata;
	private final BlockCrops crop;
	private final ItemStack seed;

	private boolean hasTarget;
	private int cropX, cropY, cropZ;
	private int spiralIndex;
	private static final List<Point> spiral = new Spiral(32, 32).spiral();

	public BetterEntityAIEatCrop(EntityAnimal entity, BlockCrops crop, int maxGrowthMetadata, ItemStack seed) {
		this.entity = entity;
		this.maxGrowthMetadata = maxGrowthMetadata;
		this.crop = crop;
		this.seed = seed;
		hasTarget = false;
		spiralIndex = 0;
	}

	@Override
	public boolean shouldExecute() {
		return entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
	}

	@Override
	public boolean continueExecuting() {
		return !entity.isInLove() && !entity.isChild();
	}

	@Override
	public void updateTask() {
		if (!continueExecuting())
			return;

		int xCoord = (int) entity.posX;
		int yCoord = (int) entity.posY;
		int zCoord = (int) entity.posZ;

		if (!hasTarget) {
			increment();

			Point p = getNextPoint();
			for (int y = -2; y < 2; y++)
				if (entity.worldObj.getBlockId(xCoord + p.x, yCoord + y, zCoord + p.y) == crop.blockID && entity.worldObj.getBlockMetadata(xCoord + p.x, yCoord + y, zCoord + p.y) >= maxGrowthMetadata) {
					cropX = xCoord + p.x;
					cropY = yCoord + y;
					cropZ = zCoord + p.y;
					hasTarget = true;
				}
		}

		// isReadyToMate() should make sure the breeding timer is zero
		if (entity.isReadyForLove() && hasTarget) {
			entity.getNavigator().tryMoveToXYZ(cropX, cropY, cropZ, 1.0D);
			entity.getLookHelper().setLookPosition(cropX + 0.5D, cropY + 0.5D, cropZ + 0.5D, 30.0F, 8.0F);

			boolean flag = entity.boundingBox.intersectsWith(AxisAlignedBB.getBoundingBox(cropX, cropY, cropZ, cropX + 1, cropY + 1, cropZ + 1));
			if (flag)
				if (entity.worldObj.getBlockId(cropX, cropY, cropZ) != crop.blockID)
					hasTarget = false;
				else {
					entity.worldObj.playAuxSFXAtEntity(null, 2001, cropX, cropY, cropZ, crop.blockID + (maxGrowthMetadata << 12));
					entity.worldObj.setBlockToAir(cropX, cropY, cropZ);
					Utils.dropStack(entity.worldObj, cropX, cropY, cropZ, seed.copy());
					hasTarget = false;
					entity.seduce(); // TODO Whatever is to be done (love
										// particles and all that)
				}
		}
	}

	private void increment() {
		spiralIndex++;
		if (spiralIndex >= spiral.size())
			spiralIndex = 0;
	}

	private Point getNextPoint() {
		return spiral.get(spiralIndex);
	}
}