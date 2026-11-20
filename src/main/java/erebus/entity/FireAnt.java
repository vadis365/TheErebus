package erebus.entity;

import erebus.entity.ai.FireAntFireballGoal;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import org.jspecify.annotations.NonNull;

public class FireAnt extends ClimbingMonster {
    public FireAnt(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.STEP_HEIGHT, 0);
    }

    protected boolean soldier() {
        return false;
    }

    @Override
    public boolean isWithinMeleeAttackRange(@NonNull LivingEntity target) {
        return distanceToSqr(target) <= 4 + target.getBbWidth();
    }

    @Override
    protected void registerGoals() {
        double speed = soldier() ? 0.5 : 0.6;
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new FireAntFireballGoal(this, soldier()));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, speed, true));
        goalSelector.addGoal(3, new RandomStrollGoal(this, speed));
        goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8));
        goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean onClimbable() {
        return horizontalCollision;
    }

    @Override
    public boolean checkSpawnRules(@NonNull LevelAccessor level, @NonNull EntitySpawnReason reason) {
        return true;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.FIRE_ANT_SOUND.get();
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.FIRE_ANT_HURT.get();
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState state) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1);
    }
}
