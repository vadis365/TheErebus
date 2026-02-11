package erebus.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BotFlyLarva extends Mob {
	private static final EntityDataAccessor<Byte> PARASITE_COUNT = SynchedEntityData.defineId(BotFlyLarva.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> INFESTED_PLAYER = SynchedEntityData.defineId(BotFlyLarva.class, EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
	private static final Codec<List<EntityReference<LivingEntity>>> INFESTED_PLAYER_CODEC = EntityReference.<LivingEntity>codec().listOf();

	public BotFlyLarva(EntityType<? extends BotFlyLarva> type, Level level) {
		super(type, level);
		//tasks.addTask(0, new EntityAIWander(this, 0.3D));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
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
	public boolean canBeCollidedWith(Entity other) {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SILVERFISH_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(@NonNull DamageSource p_184601_1_) {
		return SoundEvents.SILVERFISH_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SILVERFISH_DEATH;
	}

	@Override
	protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
		this.playSound(SoundEvents.SILVERFISH_STEP, 0.15F, 1.0F);
	}

	@Override
	public void playerTouch(@NonNull Player player) {
		super.playerTouch(player);
		if (!level().isClientSide())
			if (player.getPassengers().isEmpty()) {
				startRiding(player, true, true);
				infectPlayer(EntityReference.of(player));
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
					((LivingEntity) getVehicle()).addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, duration * 20, 0));
					((LivingEntity) getVehicle()).addEffect(new MobEffectInstance(MobEffects.HUNGER, duration * 20, 0));
				}
				if (getParasiteCount() == 0)
					kill((ServerLevel) level());
			}
		}

	}

	public void setABitDead() {
		level().playSound(null, blockPosition(), getDeathSound(), SoundSource.HOSTILE, 1.0F, 0.7F);
		if (level().isClientSide())
			level().addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
		if (!level().isClientSide())
			spawnAtLocation((ServerLevel) level(), new ItemStack(Items.SLIME_BALL), 0.0F);
		setParasiteCount((byte) (getParasiteCount() - 1));
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel level, DamageSource source, float damage) {
		if (source.is(DamageTypes.IN_WALL) || source.is(DamageTypes.DROWN))
			return false;
		return super.hurtServer(level, source, damage);
	}

	public void setParasiteCount(byte parasites) {
		entityData.set(PARASITE_COUNT, parasites);
	}

	public byte getParasiteCount() {
		return entityData.get(PARASITE_COUNT);
	}

	private void infectPlayer(EntityReference<LivingEntity> player) {
		entityData.set(INFESTED_PLAYER, Optional.of(player));
	}

	private Stream<EntityReference<LivingEntity>> getInfectedPlayerStream() {
		return entityData.get(INFESTED_PLAYER).stream();
	}

	@Nullable
	public Player getPlayer() {
		if(entityData.get(INFESTED_PLAYER).isEmpty()) return null;
		return level().getPlayerByUUID(entityData.get(INFESTED_PLAYER).get().getUUID());
	}

	@Override
	public void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		setParasiteCount(input.getByteOr("parasites", (byte) 0));
		input.read("playerName", INFESTED_PLAYER_CODEC).orElse(List.of()).forEach(this::infectPlayer);
		if (!level().isClientSide())
			if(getPlayer() != null) {
				if (!getPlayer().isVehicle()) {
					startRiding(getPlayer(), true, true);
				}
			}
	}

	@Override
	public void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.store("playerName", INFESTED_PLAYER_CODEC, getInfectedPlayerStream().toList());
		output.putByte("parasites", getParasiteCount());
	}
}
