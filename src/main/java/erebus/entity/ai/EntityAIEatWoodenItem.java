package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityAnimal;
import erebus.ModBlocks;
import erebus.block.BlockBambooCrop;
import erebus.block.BlockBambooTorch;
import erebus.block.BlockHollowLog;
import erebus.core.handler.ConfigHandler;
import erebus.entity.EntityBeetleLarva;

public class EntityAIEatWoodenItem extends EntityAIEatBlock {

	private final double moveSpeed;

	public EntityAIEatWoodenItem(EntityAnimal entity, double moveSpeed, int eatSpeed) {
		super(entity, null, 0, null, moveSpeed, eatSpeed);
		this.moveSpeed = moveSpeed;
	}

	@Override
	protected boolean canEatBlock(Block block, int blockMeta) {
		if (block == null)
			return false;

		if (block == ModBlocks.planksErebus && blockMeta == 9)
			return false;

		if (block == ModBlocks.plankSlabs[2] && blockMeta == 1)
			return false;

		if (block == ModBlocks.plankStairs[9])
			return false;

		if (ConfigHandler.beetleLarvaEating == 2)
			return true;
		else if (block.getMaterial() != Material.wood || block instanceof BlockLog || block instanceof BlockBambooCrop || block instanceof BlockHollowLog || block instanceof BlockMushroomCap || block instanceof BlockBambooTorch)
			return false;
		else if (ConfigHandler.beetleLarvaEating == 0 && block.hasTileEntity(blockMeta))
			return false;

		return true;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		EntityBeetleLarva beetleLarva = (EntityBeetleLarva) entity;
		if (!beetleLarva.isEating)
			entity.getMoveHelper().setMoveTo(cropX + 0.5D, cropY, cropZ + 0.5D, moveSpeed);
	}

	@Override
	protected void prepareToEat() {
		EntityBeetleLarva beetleLarva = (EntityBeetleLarva) entity;
		beetleLarva.setMoveTasks(false);
		beetleLarva.setIsEating(true);
	}

	@Override
	protected void eatingInterupted() {
		EntityBeetleLarva beetleLarva = (EntityBeetleLarva) entity;
		beetleLarva.setIsEating(false);
		beetleLarva.setMoveTasks(true);
	}

	@Override
	protected void afterEaten() {
		EntityBeetleLarva beetleLarva = (EntityBeetleLarva) entity;
		beetleLarva.setIsEating(false);
		beetleLarva.setMoveTasks(true);
		beetleLarva.setLarvaSize(beetleLarva.getLarvaSize() + 0.1F);
	}
}