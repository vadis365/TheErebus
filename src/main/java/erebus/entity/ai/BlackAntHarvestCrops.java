package erebus.entity.ai;

import erebus.entity.BlackAnt;
import erebus.utils.FakePlayerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlackAntHarvestCrops extends BlackAntBlockHome {
	private final BlackAnt blackAnt;
	private final double moveSpeed;

	public BlackAntHarvestCrops(BlackAnt blackAnt, double moveSpeed, int eatSpeed, boolean shouldDropItem) {
		super(blackAnt, null, moveSpeed, eatSpeed, shouldDropItem);
		this.blackAnt = blackAnt;
		this.moveSpeed = moveSpeed;
	}

	@Override
	public boolean canUse() {
		return blackAnt.isTamedAnt() && blackAnt.getAntRole() == blackAnt.HARVESTER ? !blackAnt.getMoveControl().hasWanted() && super.canUse() : false;
	}

	@Override
	protected boolean canEatBlock(BlockState state) {
		Block block = state.getBlock();
		return block instanceof CropBlock && ((CropBlock) block).isMaxAge(state);
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		blackAnt.getMoveControl().setWantedPosition(targetX + 0.5D, targetY, targetZ + 0.5D, moveSpeed);
	}

	@Override
	protected void prepareToEat() {
	}

	@Override
	protected void eatingInterupted() {
		blackAnt.getNavigation().isDone();
	}

	@Override
	protected void dropItem() {
	}

	@Override
	protected void afterEaten() {
		BlockPos pos = new BlockPos(targetX, targetY, targetZ);
		if (!blackAnt.level().isClientSide()) {
			Player player = FakePlayerHandler.get((ServerLevel) blackAnt.level(), blackAnt.getPlayerOwner());
			blackAnt.level().destroyBlock(new BlockPos(targetX, targetY, targetZ), dropItem, player);
			blackAnt.level().setBlock(pos.below(), Blocks.DIRT.defaultBlockState(), 3);
			blackAnt.setPos(targetX + 0.5D, targetY, targetZ + 0.5D); // may stop ant glitching in to farmland once it changed
			blackAnt.getNavigation().isDone();
		}
	}

}
