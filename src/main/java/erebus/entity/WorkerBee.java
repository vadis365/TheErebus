package erebus.entity;

import erebus.block.entity.HoneyCombBlockEntity;
import erebus.entity.ai.BeePollinateGoal;
import erebus.entity.ai.EntityAIFlyingWander;
import erebus.registries.ModSounds;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.NonNull;

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
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
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

	public static boolean canSpawnHere(EntityType<WorkerBee> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
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
						addHoneyToInventory(getDropPoint());
					setBeeCollecting(false);
					//getNavigation().stop();
				}
			}

			if(isInWater())
				getNavigation().moveTo(getX(), getY() + 1D, getZ(), 0.5D);
		}
	}

	private void addHoneyToInventory(BlockPos pos) {
		BlockEntity tile = level().getBlockEntity(pos);
		ResourceHandler<ItemResource> target = level().getCapability(Capabilities.Item.BLOCK, pos, Direction.UP);
		ResourceHandler<ItemResource> source = getCapability(Capabilities.Item.ENTITY_AUTOMATION, Direction.DOWN);

		if(tile instanceof HoneyCombBlockEntity) {
			try (Transaction transaction = Transaction.openRoot()) {
				ItemStack nectar = new ItemStack(ModItems.NECTAR.get(), getNectarPoints());

				int nectarMoved = ResourceHandlerUtil.move(source, target, filter -> filter.is(ModItems.NECTAR.get()), getNectarPoints(), transaction);
				nectar.shrink(nectarMoved);
				setNectarPoints(nectar.getCount());
				transaction.commit();
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
    protected @NonNull PathNavigation createNavigation(@NonNull Level level){
		return new FlyingPathNavigation(this, level);
	}

	@Override
	public boolean causeFallDamage(double fallDistance, float damageModifier, @NonNull DamageSource damageSource) {
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
	protected SoundEvent getHurtSound(@NonNull DamageSource source) {
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
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState blockIn) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	public @NonNull InteractionResult mobInteract(Player player, @NonNull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!level().isClientSide() && !stack.isEmpty() && stack.getItem() ==  ModItems.NECTAR_COLLECTOR.get())
			if (getNectarPoints() > 0) {
				spawnAtLocation((ServerLevel) level(), new ItemStack(ModItems.NECTAR.get(), 2), 0.0F);
				stack.getItem().damageItem(stack, 1, player, null);
				stack.hurtAndBreak(1, player, hand.asEquipmentSlot());
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
	public AgeableMob getBreedOffspring(@NonNull ServerLevel level, @NonNull AgeableMob otherParent) {
		return null;
	}

	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putInt("nectarPoints", getNectarPoints());
		output.putBoolean("tameState", isTamedBee());
		output.store("dropPoint", BlockPos.CODEC, getDropPoint());
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		setNectarPoints(input.getIntOr("nectarPoints", 0));
		setTameState(input.getBooleanOr("tameState", false));
		Optional<BlockPos> dropPoint = input.	read("dropPoint", BlockPos.CODEC);
		setDropPoint(dropPoint.orElse(BlockPos.ZERO));
	}

	@Override
	public boolean isFood(@NonNull ItemStack stack) {
		return false;
	}
}
