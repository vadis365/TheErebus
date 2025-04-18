package erebus.entity.ai;
/*
import erebus.registries.ModBlocks;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GrasshopperEatPlantsGoal extends EatBlockGoal {

	private final double moveSpeed;
	private int plantsEaten = 0;
	private Grasshopper grasshopper;

	public GrasshopperEatPlantsGoal(Grasshopper grasshopper, double moveSpeed, int eatSpeed, boolean doDropItem) {
		super(grasshopper, null, moveSpeed, eatSpeed, doDropItem);
		this.moveSpeed = moveSpeed;
		this.dropItem = doDropItem;
		this.grasshopper = grasshopper;
	}

	@Override
	protected boolean canEatBlock(BlockState state) {
		Block block = state.getBlock();
		if (state.isAir() || block == null)
			return false;
		else if (state.is(Blocks.TALL_GRASS) || state.is(ModBlocks.FERN.get()) || block instanceof CropBlock && ((CropBlock)block).isMaxAge(state))
			return true;
		return false;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}
	
	@Override
	public boolean canUse() {
	return !grasshopper.getMoveControl().hasWanted() && super.canUse();
	}

	@Override
	protected void moveToLocation() {
		if (!grasshopper.isEating)
			grasshopper.getMoveControl().setWantedPosition(targetX + 0.5D, targetY + 0.5D, targetZ + 0.5D, moveSpeed);
	}

	@Override
	protected void prepareToEat() {
		grasshopper.setIsEating(true);
	}

	@Override
	protected void eatingInterupted() {
		grasshopper.setIsEating(false);
		grasshopper.getNavigation().stop();
	}

	@Override
	protected void afterEaten() {
		grasshopper.level().destroyBlock(new BlockPos(targetX, targetY, targetZ), dropItem, grasshopper);

		grasshopper.setIsEating(false);
		plantsEaten++;
		if (plantsEaten == 6)
			if (grasshopper.level().countEntities(Grasshopper.class) < 80) {
				Grasshopper newGrasshopper = ModEntities.GRASSHOPPER.get().create(grasshopper.level());
				if (newGrasshopper != null) {
					newGrasshopper.copyPosition(grasshopper);
					grasshopper.level().addFreshEntity(newGrasshopper);
					grasshopper.getNavigator().clearPath();
				}
			}
		if (plantsEaten >= 12) {
			if (grasshopper.level().countEntities(Locust.class) < 5) {
				Locust locust = ModEntities.LOCUST.get().create(grasshopper.level());
				if (locust != null) {
					locust.copyPosition(grasshopper);
					grasshopper.remove(Entity.RemovalReason.DISCARDED);
					grasshopper.level().addFreshEntity(locust);
					grasshopper.level().playSound(null, locust.getPosition(), ModSounds.LOCUST_SPAWN.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
				}

			}
		}
	}

	@Override
	protected void dropItem() {
		// TODO Auto-generated method stub
		
	}
}*/