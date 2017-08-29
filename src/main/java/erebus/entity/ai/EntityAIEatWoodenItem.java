package erebus.entity.ai;

import erebus.ModBlocks;
import erebus.block.BlockHollowLog;
import erebus.block.bamboo.BlockBambooCrop;
import erebus.block.bamboo.BlockBambooTorch;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.EntityBeetleLarva;
import erebus.lib.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;

public class EntityAIEatWoodenItem extends EntityAIEatBlock {

	private final double moveSpeed;

	public EntityAIEatWoodenItem(EntityAnimal entity, double moveSpeed, int eatSpeed) {
		super(entity, null, 0, moveSpeed, eatSpeed);
		this.moveSpeed = moveSpeed;
	}

	@Override
	protected boolean canEatBlock(Block block, int blockMeta) {
		if (block == null)
			return false;

		if (block == ModBlocks.scorchedWood)
			return false;

		if (block == ModBlocks.planks && blockMeta == EnumWood.Rotten.ordinal())
			return false;

		if (block == EnumWood.Bamboo.getSlab())
			return false;

		if (block == EnumWood.Bamboo.getStair())
			return false;

		if (block == ModBlocks.planks && blockMeta == EnumWood.Bamboo.ordinal())
			return false;

		if (ConfigHandler.INSTANCE.beetleLarvaEating == 2)
			return true;
		else if (block.getMaterial() != Material.wood || block instanceof BlockLog || block instanceof BlockBambooCrop || block instanceof BlockHollowLog || block == Blocks.brown_mushroom_block || block == Blocks.red_mushroom_block || block instanceof BlockBambooTorch)
			return false;
		else if (ConfigHandler.INSTANCE.beetleLarvaEating == 0 && block.hasTileEntity(blockMeta))
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
		beetleLarva.worldObj.setBlockToAir(cropX, cropY, cropZ);
		beetleLarva.setIsEating(false);
		beetleLarva.setMoveTasks(true);
		beetleLarva.setLarvaSize(beetleLarva.getLarvaSize() + 0.1F);
	}
}