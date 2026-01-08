package erebus.entity.ai;

import erebus.entity.Grasshopper;
import erebus.entity.Locust;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GrasshopperEatPlantsGoal extends EatBlockGoal {

	private final double moveSpeed;
	private int plantsEaten = 0;
	private final Grasshopper grasshopper;

	public GrasshopperEatPlantsGoal(Grasshopper grasshopper, double moveSpeed, int eatSpeed, boolean doDropItem) {
		super(grasshopper, null, moveSpeed, eatSpeed, doDropItem);
		this.moveSpeed = moveSpeed;
		this.dropItem = doDropItem;
		this.grasshopper = grasshopper;
	}

	@Override
	protected boolean canEatBlock(BlockState state) {
		Block block = state.getBlock();
		if (state.isAir())
			return false;
		else return state.is(Blocks.SHORT_GRASS) || state.is(ModBlocks.FERN.get()) || block instanceof CropBlock && ((CropBlock) block).isMaxAge(state);
    }

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	public boolean canUse() {
		return !grasshopper.getMoveControl().hasWanted() && !grasshopper.isEating && super.canUse();
	}

	@Override
	protected void moveToLocation() {
		grasshopper.getMoveControl().setWantedPosition(targetX + 0.5D, targetY + 0.5D, targetZ + 0.5D, moveSpeed);
	}

	@Override
	protected void prepareToEat() {
		grasshopper.setIsEating(true);
	}

	@Override
	protected void eatingInterupted() {
		grasshopper.setIsEating(false);
	}

	@Override
	protected void afterEaten() {
		grasshopper.level().destroyBlock(new BlockPos(targetX, targetY, targetZ), dropItem, grasshopper);
		grasshopper.setIsEating(false);
		plantsEaten++;
		if (plantsEaten == 6)
			if (grasshopper.level().getEntitiesOfClass(Grasshopper.class, grasshopper.getBoundingBox().inflate(16)).size() < 10) {
				Grasshopper newGrasshopper = ModEntities.GRASSHOPPER.get().create(grasshopper.level(), EntitySpawnReason.BREEDING);
				if (newGrasshopper != null) {
					newGrasshopper.copyPosition(grasshopper);
					grasshopper.level().addFreshEntity(newGrasshopper);
				}
			}
		if (plantsEaten >= 12) {
			if (grasshopper.level().getEntitiesOfClass(Locust.class, grasshopper.getBoundingBox().inflate(16)).size() < 5) {
				Locust locust = ModEntities.LOCUST.get().create(grasshopper.level(), EntitySpawnReason.BREEDING);
				if (locust != null) {
					locust.copyPosition(grasshopper);
					grasshopper.remove(Entity.RemovalReason.DISCARDED);
					grasshopper.level().addFreshEntity(locust);
					grasshopper.level().playSound(null, locust.blockPosition(), ModSounds.LOCUST_SPAWN.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
				}
			}
		}
	}

	@Override
	protected void dropItem() {
		// TODO Auto-generated method stub
	}
}
