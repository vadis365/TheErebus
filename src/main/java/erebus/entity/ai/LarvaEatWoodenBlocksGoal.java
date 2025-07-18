package erebus.entity.ai;

import erebus.entity.BeetleLarva;
import erebus.registries.ModSounds;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.SlabBlocks;
import erebus.registries.blocks.providers.StairBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class LarvaEatWoodenBlocksGoal extends EatBlockGoal {
	private final BeetleLarva beetleLarva;
	private final double moveSpeed;

	public LarvaEatWoodenBlocksGoal(BeetleLarva beetleLarva, double moveSpeed, int eatSpeed) {
		super(beetleLarva, null, moveSpeed, eatSpeed, false, 4);
		this.beetleLarva = beetleLarva;
		this.moveSpeed = moveSpeed;
	}

	@Override
	protected boolean canEatBlock(BlockState state) {
		Block block = state.getBlock();
		if (state.isAir() || block == null)
			return false;

		if (state.is(WoodBlocks.LOG_SCORCHED.get()))
			return false;

		if (state.is(WoodBlocks.LOG_ROTTEN.get()))
			return false;

		if (state.is(SlabBlocks.SLAB_PLANKS_BAMBOO.get()))
			return false;

		if (state.is(StairBlocks.STAIRS_BAMBOO.get()))
			return false;

		if (state.is(WoodBlocks.PLANKS_BAMBOO.get()))
			return false;

		//if (ConfigHandler.INSTANCE.beetleLarvaEating == 2)
		//	return true;
		else if (state.is(Blocks.BROWN_MUSHROOM_BLOCK) || state.is(Blocks.RED_MUSHROOM_BLOCK) || state.is(OtherBlocks.BAMBOO_TORCH.get()) || state.is(WoodBlocks.LOG_HOLLOW.get()))
			return false;
		//else if (ConfigHandler.INSTANCE.beetleLarvaEating == 0 && block.hasBlockEntity(state))
		//	return false;

		return state.is(BlockTags.PLANKS) || state.is(BlockTags.LOGS);
	}

	@Override
	protected boolean isEntityReady() {
		return hasTarget;
	}
	
	@Override
	public boolean canUse() {
	return !beetleLarva.getMoveControl().hasWanted() && !beetleLarva.isEating && super.canUse();
	}

	@Override
	protected void moveToLocation() {
		beetleLarva.getMoveControl().setWantedPosition(targetX + 0.5D, targetY + 0.5D, targetZ + 0.5D, moveSpeed);
	}

	@Override
	public void prepareToEat() {
		if(eatTicks%100 == 0)
			beetleLarva.level().playSound(null, beetleLarva.blockPosition(), ModSounds.BEETLE_LARVA_MUNCH.get(), SoundSource.NEUTRAL, 0.5F, 1F);
		beetleLarva.setIsEating(true);
	}

	@Override
	protected void eatingInterupted() {
		beetleLarva.setIsEating(false);
	}

	@Override
	protected void afterEaten() {
		beetleLarva.level().destroyBlock(new BlockPos(targetX, targetY, targetZ), dropItem, beetleLarva);
		beetleLarva.setIsEating(false);
		beetleLarva.setLarvaSize(beetleLarva.getLarvaSize() + 0.1F);
	}

	@Override
	protected void dropItem() {
		// TODO Auto-generated method stub
		
	}
	
}