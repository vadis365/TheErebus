package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class ZombieAntSoldier extends Monster {

	public ZombieAntSoldier(EntityType<? extends ZombieAntSoldier> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.4D, false));
		goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
				.add(Attributes.ARMOR, 5D);
	}
	
	@Override
	public float getVoicePitch() {
		return 0.5F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.ANT_SOUND.get();
	}

	@Override
	protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
		return ModSounds.ANT_HURT.get();
	}

	@Override
	protected @NonNull SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

	@Override
	protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
		this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}

	public static boolean canSpawnHere(EntityType<ZombieAntSoldier> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 5;
	}

	@Override
	public void tick() {
		super.tick();
		if (level().isClientSide())
			ClientParticles.spawnParticles(ClientParticles.ParticleType.SPORES, getX() + (random.nextDouble() - 0.5D) * getBbWidth(), getBoundingBox().minY + random.nextDouble() * getBbHeight() - 0.25D, getZ() + (random.nextDouble() - 0.5D) * getBbWidth(), 1.0D + random.nextDouble(), 1.0D + random.nextDouble(), 1.0D + random.nextDouble());
	}
}
