package erebus.entity.ai;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.block.bamboo.BlockBambooTorch;
import erebus.blocks.BlockHollowLog;
import erebus.blocks.EnumWood;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.EntityBeetleLarva;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class EntityAIEatWoodenItem extends EntityAIEatBlock {

	private final double moveSpeed;

	public EntityAIEatWoodenItem(EntityAnimal entity, double moveSpeed, int eatSpeed) {
		super(entity, null, 0, moveSpeed, eatSpeed);
		setMutexBits(1);
		this.moveSpeed = moveSpeed;
	}

	@Override
	protected boolean canEatBlock(IBlockState state) {
		Block block = state.getBlock();
		if (block == null)
			return false;

		if (block == EnumWood.SCORCHED.getLog())
			return false;

		if (block == ModBlocks.PLANKS && block.getMetaFromState(state) == EnumWood.ROTTEN.ordinal())
			return false;

		if (block == EnumWood.BAMBOO.getSlab())
			return false;

		if (block == EnumWood.BAMBOO.getStairs())
			return false;

		if (block == ModBlocks.PLANKS && block.getMetaFromState(state) == EnumWood.BAMBOO.ordinal())
			return false;

		if (ConfigHandler.INSTANCE.beetleLarvaEating == 2)
			return true;
		else if (state.getMaterial() != Material.WOOD || block instanceof BlockLog || block == EnumWood.BAMBOO.getLog() || block == Blocks.BROWN_MUSHROOM_BLOCK || block == Blocks.RED_MUSHROOM_BLOCK || block instanceof BlockBambooTorch || block instanceof BlockHollowLog)
			return false;
		else if (ConfigHandler.INSTANCE.beetleLarvaEating == 0 && block.hasTileEntity(state))
			return false;

		return true;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}
	
	@Override
	public boolean shouldExecute() {
		return !entity.getMoveHelper().isUpdating() && super.shouldExecute();
		//return !entity.getNavigator().noPath() && super.shouldExecute();
	}

	@Override
	protected void moveToLocation() {
		EntityBeetleLarva beetleLarva = (EntityBeetleLarva) entity;
		if (!beetleLarva.isEating)
			entity.getNavigator().tryMoveToXYZ(cropX + 0.5D, cropY, cropZ + 0.5D, moveSpeed);
	}

	@Override
	public void prepareToEat() {
		EntityBeetleLarva beetleLarva = (EntityBeetleLarva) entity;
		if(eatTicks%100 == 0)
			beetleLarva.getEntityWorld().playSound((EntityPlayer)null, beetleLarva.getPosition(), ModSounds.BEETLE_LARVA_MUNCH, SoundCategory.NEUTRAL, 0.5F, 1F);
		beetleLarva.setIsEating(true);
	}

	@Override
	protected void eatingInterupted() {
		EntityBeetleLarva beetleLarva = (EntityBeetleLarva) entity;
		beetleLarva.setIsEating(false);
		entity.getNavigator().clearPath();
	}

	@Override
	protected void afterEaten() {
		EntityBeetleLarva beetleLarva = (EntityBeetleLarva) entity;
		beetleLarva.getEntityWorld().setBlockToAir(new BlockPos(cropX, cropY, cropZ));
		beetleLarva.setIsEating(false);
		beetleLarva.setLarvaSize(beetleLarva.getLarvaSize() + 0.1F);
		entity.getNavigator().clearPath();
	}
}