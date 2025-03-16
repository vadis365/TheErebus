package erebus.entity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AnimatedBlock extends PathfinderMob {

	private static final EntityDataAccessor<BlockState> BLOCK_TYPE = SynchedEntityData.defineId(AnimatedBlock.class, EntityDataSerializers.BLOCK_STATE);

	public AnimatedBlock (EntityType<? extends AnimatedBlock> type, Level level) { 
		super(type, level);
		xpReward = 0;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BLOCK_TYPE, Blocks.STONE.defaultBlockState());
	}

	public void setBlockType(BlockState state) {
		entityData.set(BLOCK_TYPE, state);
	}

	public BlockState getBlockType() {
		return entityData.get(BLOCK_TYPE);
	}
	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.5D, 1));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 1D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.ATTACK_DAMAGE, 0);
	}

	@Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	public void kill() {
		super.kill();
		level().setBlock(blockPosition(), getBlockType(), 3);
	}
	
	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		if (spawnType == MobSpawnType.COMMAND || spawnType == MobSpawnType.SPAWN_EGG || spawnType == MobSpawnType.SPAWNER || spawnType == MobSpawnType.DISPENSER)
			setBlockType(level.getBlockState(blockPosition().below()));
		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}

	@Override
	  public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		if(!getBlockType().isEmpty())
			nbt.put("tempBlockTypes", NbtUtils.writeBlockState(getBlockType()));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		if (nbt.contains("tempBlockTypes", 10)) {
			BlockState item = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), nbt.getCompound("tempBlockTypes"));
			setBlockType(item);
		}
	}

}