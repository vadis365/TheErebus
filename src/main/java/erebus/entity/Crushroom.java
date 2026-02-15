package erebus.entity;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class Crushroom extends Monster {

    private static final EntityDataAccessor<Integer> SMASH_COUNT = SynchedEntityData.defineId(Crushroom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> STANDING = SynchedEntityData.defineId(Crushroom.class, EntityDataSerializers.BYTE);

    public Crushroom(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 200F)
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(SMASH_COUNT, 0);
        entityData.define(STANDING, (byte) 0);
    }

    @Override
    public void playAmbientSound() {
        playSound(ModSounds.CRUSHLING_LIVING.get());
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.CRUSHLING_HURT.get();
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return ModSounds.CRUSHLING_DEATH.get();
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState blockState) {
        playSound(SoundEvents.IRON_GOLEM_STEP, 0.15F, 1.0F);
    }

    public int getSmashCount() {
        return entityData.get(SMASH_COUNT);
    }

    public void setSmashCount(int count) {
        entityData.set(SMASH_COUNT, count);
    }

    public byte isStanding() {
        return entityData.get(STANDING);
    }

    public void setStanding(boolean standing) {
        entityData.set(STANDING, (byte) (standing ? 1 : 0));
    }
}
