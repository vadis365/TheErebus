package erebus.entity;

import erebus.block.entity.HoneyCombBlockEntity;
import erebus.entity.ai.BeePollinateGoal;
import erebus.registries.ModSounds;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Optional;

public class WorkerBee extends Animal {
	public boolean beeFlying;
	public boolean beePollinating = false;
	public boolean beeCollecting = false;
	private static final EntityDataAccessor<BlockPos> DROP_POINT= SynchedEntityData.defineId(WorkerBee.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Integer> NECTAR_POINTS = SynchedEntityData.defineId(WorkerBee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> TAME_STATE = SynchedEntityData.defineId(WorkerBee.class, EntityDataSerializers.BOOLEAN);
	private EntityAIFlyingWander aiFlyingWander;

	public WorkerBee(EntityType<? extends WorkerBee> type, Level level) {
		super(type, level);
		this.moveControl = new FlyingMoveControl(this, 10, false);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
        builder.define(DROP_POINT, this.blockPosition());
        builder.define(NECTAR_POINTS, 0);
        builder.define(TAME_STATE, false);
	}

	@Override
	protected void registerGoals() {
		aiFlyingWander = new EntityAIFlyingWander(this, 0.5D, 0.02F);
		goalSelector.addGoal(0, new BeePollinateGoal(this, 10));
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.5D, true));
		//tasks.addTask(3, new EntityAITempt(this, 0.5D, Items.SUGAR, false));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers(WorkerBee.class));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Wasp>(this, Wasp.class, true, false));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30D)
				.add(Attributes.FOLLOW_RANGE, 64D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.FLYING_SPEED, 1D)
				.add(Attributes.ATTACK_DAMAGE, 4D);
	}

	public static boolean canSpawnHere(EntityType<WorkerBee> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	@Override
	public boolean isPersistenceRequired() {
		return isTamedBee();
	}

	public boolean isFlying() {
		return !onGround();
	}

	@Override
	public void tick() {
		super.tick();
	
		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround() && vec3.y < 0.0D)
			this.setDeltaMovement(vec3.multiply(1.0D, 0.7D, 1.0D));

		if (!level().isClientSide()) {
			if(tickCount == 1)
				if(!isTamedBee())
					goalSelector.addGoal(3, aiFlyingWander);

			if (beeCollecting && !beePollinating) {
				getNavigation().moveTo(getDropPoint().getX() + 0.5D, getDropPoint().getY() + 1D, getDropPoint().getZ() + 0.5D, 1D);
			
				if (distanceToSqr(getDropPoint().getX() + 0.5D, getDropPoint().getY() + 0.5D, getDropPoint().getZ() + 1D) <= 1D) {
					if(getNectarPoints() > 0)
						addHoneyToInventory(getDropPoint().getX(), getDropPoint().getY(), getDropPoint().getZ());
					setBeeCollecting(false);
					//getNavigation().stop();
				}
			}

			if(isInWater())
				getNavigation().moveTo(getX(), getY() + 1D, getZ(), 0.5D);
		}
	}

	private void addHoneyToInventory(int x, int y, int z) {
		BlockEntity tile = level().getBlockEntity(new BlockPos(x, y, z));
		if (tile instanceof HoneyCombBlockEntity honeycomb) {
			Optional<IItemHandler> handlerOptional = CapHelper.getItemHandler(level(), new BlockPos(x, y, z), null);
			if (handlerOptional.isPresent()) {
				handlerOptional.ifPresent((handler) -> {
					ItemStack stack = new ItemStack(ModItems.NECTAR.get(), getNectarPoints());
					ItemStack stack1 = ItemHandlerHelper.insertItem(handler, stack, true);
					if (stack1.isEmpty()) {
						ItemHandlerHelper.insertItem(handler, stack, false);
						honeycomb.setChanged();
						setNectarPoints(0);
					} else {
						spawnAtLocation(new ItemStack(ModItems.NECTAR.get(), getNectarPoints()), 0.0F);
						setNectarPoints(0);
					}
				});
			} else {
				spawnAtLocation(new ItemStack(ModItems.NECTAR.get(), getNectarPoints()), 0.0F); // just in case
				setNectarPoints(0);
			}
		}
	}

	public void setBeeFlying(boolean state) {
		beeFlying = state;
	}

	public void setBeePollinating(boolean state) {
		beePollinating = state;
	}

	public void setBeeCollecting(boolean state) {
		beeCollecting = state;
	}

	@Override
    protected PathNavigation createNavigation(Level level){
		return new FlyingPathNavigation(this, level);
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}
	
    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.WASP_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.WASP_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}
/*
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (recentlyHit) {
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.NECTAR.createStack(2), 0.0F);
		}
	}
*/
	@Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!level().isClientSide() && !stack.isEmpty() && stack.getItem() ==  ModItems.NECTAR_COLLECTOR.get())
			if (getNectarPoints() > 0) {
				spawnAtLocation(new ItemStack(ModItems.NECTAR.get(), 2), 0.0F);
				stack.getItem().damageItem(stack, 1, player, null);
				stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
				setNectarPoints(getNectarPoints() - 2);
				return InteractionResult.SUCCESS;
			}

		if (!stack.isEmpty() && stack.getItem() == ModItems.BEE_TAMING_AMULET.get() && stack.has(ModDataComponents.BEE_TAMING_AMULET)) {
			if (!level().isClientSide()) {
				BlockPos dataBlockPos = stack.getComponents().get(ModDataComponents.BEE_TAMING_AMULET.get());
				setDropPoint(dataBlockPos);
				setTameState(true);
				goalSelector.removeGoal(aiFlyingWander);
				setTarget(null);
			}
			level().broadcastEntityEvent(this, (byte)18);
			player.swing(hand);
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}

	public void setDropPoint(BlockPos pos) {
		entityData.set(DROP_POINT, pos);
	}

	public BlockPos getDropPoint() {
		return entityData.get(DROP_POINT);
	}

	public void setTameState(boolean state) {
		entityData.set(TAME_STATE, state);
	}

	public boolean isTamedBee() {
		return entityData.get(TAME_STATE);
	}

	public void setNectarPoints(int count) {
		entityData.set(NECTAR_POINTS, count);
	}

	public int getNectarPoints() {
		return entityData.get(NECTAR_POINTS);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return null;
	}

	@Override
	  public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putInt("nectarPoints", getNectarPoints());
		nbt.putBoolean("tameState", isTamedBee());
		nbt.put("dropPoint", NbtUtils.writeBlockPos(getDropPoint()));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		setNectarPoints(nbt.getInt("nectarPoints"));
		setTameState(nbt.getBoolean("tameState"));
		Optional<BlockPos> optional = NbtUtils.readBlockPos(nbt, "dropPoint");
		if(!optional.isEmpty())
			setDropPoint(optional.get());
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return false;
	}

	class EntityAIFlyingWander extends WaterAvoidingRandomStrollGoal {
		public EntityAIFlyingWander(WorkerBee creatureIn, double speedIn, float chance) {
			super(creatureIn, speedIn, chance);
		}

		@Nullable
		protected Vec3 getPosition() {
			Vec3 vec3 = this.mob.getViewVector(0.0F);
			Vec3 vec31 = HoverRandomPos.getPos(this.mob, 8, 7, vec3.x, vec3.z, ((float) Math.PI / 2F), 2, 1);
			return vec31 != null ? vec31 : AirAndWaterRandomPos.getPos(this.mob, 8, 4, -2, vec3.x, vec3.z, (float) Math.PI / 2F);
		}
	}
}
