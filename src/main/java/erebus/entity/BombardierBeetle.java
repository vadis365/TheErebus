package erebus.entity;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import org.jspecify.annotations.NonNull;

public class BombardierBeetle extends Monster {
	private final float explosionRadius = 2;
	private int collideTick;

	public BombardierBeetle(EntityType<? extends BombardierBeetle> type, Level level) { 
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6D, true));
		goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.5D));
		goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.6D));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, false, false));
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.ATTACK_DAMAGE, 3D).
				add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
	}

	public static boolean canSpawnHere(EntityType<BombardierBeetle> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}
	
	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 2;
	}

    @Override
    public void tick() {
        super.tick();
		collideTick++;
		if (collideTick > 20 || getTarget() == null)
			collideTick = 0;
		if (getTarget() != null)
			if (!level().isClientSide() && horizontalCollision)
				if (collideTick == 20)
					clearpath();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BOMBARDIER_BEETLE_SOUND.get();
	}

	@Override
	protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
		return ModSounds.BOMBARDIER_BEETLE_HURT.get();
	}

	@Override
	protected @NonNull SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity entity) {
		if (hasLineOfSight(entity)) {
			if (super.doHurtTarget(level, entity)) {
				if (level.getGameRules().get(GameRules.MOB_GRIEFING))
					level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 1.0F, Level.ExplosionInteraction.NONE);
			}
		}
		return false;
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel level, DamageSource source, float damage) {
		if (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION))
			return false;
		return super.hurtServer(level, source, damage);
	}

	private void clearpath() {
		BlockPos infront = blockPosition().relative(this.getDirection(), 1);
		//boolean rule = level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
		//if (ConfigHandler.INSTANCE.bombardierBlockDestroy == true)
		level().explode(this, infront.getX(), infront.getY() + 1, infront.getZ(), explosionRadius, Level.ExplosionInteraction.BLOCK);
	}

}
