package erebus.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class BotFlyLarva extends Mob {
	private static final EntityDataAccessor<Byte> PARASITE_COUNT = SynchedEntityData.defineId(BotFlyLarva.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Optional<UUID>> INFESTED_PLAYER = SynchedEntityData.defineId(BotFlyLarva.class, EntityDataSerializers.OPTIONAL_UUID);
	
	public BotFlyLarva(EntityType<? extends BotFlyLarva> type, Level level) {
		super(type, level);
		//tasks.addTask(0, new EntityAIWander(this, 0.3D));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(PARASITE_COUNT, (byte) 1);
		builder.define(INFESTED_PLAYER, Optional.empty());
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 8D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.ATTACK_DAMAGE, 0D);
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SILVERFISH_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return SoundEvents.SILVERFISH_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SILVERFISH_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {
		this.playSound(SoundEvents.SILVERFISH_STEP, 0.15F, 1.0F);
	}

	@Override
	public void playerTouch(Player player) {
		super.playerTouch(player);
		if (!level().isClientSide())
			if (player.getPassengers().isEmpty()) {
				startRiding(player, true);
				setPlayerUUID(player.getUUID()); // may not work
			}
	}
		
	@Override
	public void tick() {
		super.tick();
		if (getVehicle() != null && getVehicle() instanceof Player) {
			setRot(getVehicle().getYRot(), 0F);
			if (!level().isClientSide()) {
				//TODO
			//	if(getVehicle().isCrouching()) //remove after test
				//	setABitDead();
				//
				if (getParasiteCount() > 0 && random.nextInt(180 / getParasiteCount()) == 0) {
					byte duration = (byte) (getParasiteCount() * 5);
					((LivingEntity) getVehicle()).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration * 20, 0));
					((LivingEntity) getVehicle()).addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration * 20, 0));
					((LivingEntity) getVehicle()).addEffect(new MobEffectInstance(MobEffects.HUNGER, duration * 20, 0));
				}
				if (getParasiteCount() == 0)
					kill();
			}
		}
		
	}

	public void setABitDead() {
		level().playSound(null, blockPosition(), getDeathSound(), SoundSource.HOSTILE, 1.0F, 0.7F);
		if (level().isClientSide())
			level().addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
		if (!level().isClientSide())
			spawnAtLocation(new ItemStack(Items.SLIME_BALL), 0.0F);
		setParasiteCount((byte) (getParasiteCount() - 1));
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		if (source.is(DamageTypes.IN_WALL) || source.is(DamageTypes.DROWN))
			return false;
		return super.hurt(source, damage);
	}

	public void setParasiteCount(byte parasites) {
		entityData.set(PARASITE_COUNT, parasites);
	}

	public byte getParasiteCount() {
		return entityData.get(PARASITE_COUNT);
	}

	private void setPlayerUUID(@Nullable UUID uuidIn) {
		entityData.set(INFESTED_PLAYER, Optional.ofNullable(uuidIn));
	}
	
    @Nullable
	public UUID getPlayerUUID() {
		return entityData.get(INFESTED_PLAYER).orElse(null);
	}

 @Nullable
	public Player playerName() {
		return level().getPlayerByUUID(getPlayerUUID());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		setParasiteCount(nbt.getByte("parasites"));
		if (nbt.hasUUID("playerName")) {
			setPlayerUUID(nbt.getUUID("playerName"));
			if (!level().isClientSide())
				if (!playerName().isVehicle()) {
					startRiding(playerName(), true);
				}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putByte("parasites", getParasiteCount());
		 if (getPlayerUUID() != null)
			 nbt.putUUID("playerName", getPlayerUUID());
	}
}
