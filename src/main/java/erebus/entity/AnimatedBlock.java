package erebus.entity;

import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class AnimatedBlock extends PathfinderMob {

	private static final EntityDataAccessor<BlockState> BLOCK_TYPE = SynchedEntityData.defineId(AnimatedBlock.class, EntityDataSerializers.BLOCK_STATE);
	private int lastX = 0, lastY = 0, lastZ = 0;

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
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, false));
		goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5D, 1));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Monster>(this, Monster.class, true, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.ATTACK_DAMAGE, 2D);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		super.tick();
	/*	if (level().isClientSide() && isGlowingBlock(getBlockType()))
			if (getLightLevelDependentMagicValue() < 0.5F)
				lightUp(level(), blockPosition());
			else
				switchOff();
	*/
	}

	@SuppressWarnings("deprecation")
	private boolean isGlowingBlock(BlockState state) {
		return state.getLightEmission() > 0;
	}

	@OnlyIn(Dist.CLIENT)
	private void lightUp(Level level, BlockPos pos) {
	//TODO  work out wtf this is in the Lighting code!!!
		//level.setLightFor(LightLayer.BLOCK, pos, 9);
		for (int i = -2; i < 2; i++)
			for (int j = -2; j < 2; j++)
				for (int k = -2; k < 2; k++)
					if (pos.getX() + i != lastX || pos.getY() + j != lastY || pos.getZ() + k != lastZ || isRemoved()) {
						level.getChunkSource().getLightEngine().checkBlock(new BlockPos(lastX + i, lastY + j, lastZ + k));
						//level.checkLightFor(LightLayer.BLOCK, new BlockPos(lastX + i, lastY + j, lastZ + k));
						lastX = pos.getX();
						lastY = pos.getY();
						lastZ = pos.getZ();
					}
	}

	@OnlyIn(Dist.CLIENT)
	private void switchOff() {
		level().getChunkSource().getLightEngine().checkBlock(new BlockPos(lastX, lastY, lastZ));
		level().getChunkSource().getLightEngine().checkBlock(blockPosition());
	}

	@Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@SuppressWarnings("deprecation")
	@Override
	public void kill() {
		super.kill();
	//	spawnAtLocation(Item.byBlock(getBlockType().getBlock()), 1);
	/*	if (level().isClientSide() && isGlowingBlock(getBlockType()))
			switchOff();
	*/
	}
	
	@Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack is = player.getItemInHand(hand);
		if (!level().isClientSide && !is.isEmpty() && is.getItem() == ModItems.WAND_OF_ANIMATION.get()) {
			remove(RemovalReason.DISCARDED);
			level().setBlock(blockPosition(), getBlockType(), 3);
			level().playSound(null, blockPosition(), ModSounds.ALTAR_OFFERING.get(), SoundSource.NEUTRAL, 0.2F, 1.0F);
			return InteractionResult.SUCCESS;
		} else if (getBlockType() == OtherBlocks.PETRIFIED_CRAFTING_TABLE.get().defaultBlockState() && is.isEmpty()) {
			System.out.println("Open Petrified crafting table Gui Here");
			player.openMenu(getBlockType().getMenuProvider(level(), blockPosition()));
			return InteractionResult.SUCCESS;
		} else
			return super.mobInteract(player, hand);
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