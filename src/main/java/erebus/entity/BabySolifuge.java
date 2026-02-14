package erebus.entity;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class BabySolifuge extends Monster {

    private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(BabySolifuge.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> POTION_TYPE = SynchedEntityData.defineId(BabySolifuge.class, EntityDataSerializers.BYTE);

    public final String[] POTION_NAME = new String[] {
            "Move Slowdown", "Dig Slowdown", "Nausea", "Blindness", "Hunger", "Weakness", "Poison", "Wither", "Levitation"
    };

    public BabySolifuge(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        setPotionEffect((byte) random.nextInt(POTION_NAME.length));
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.3F, false));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5F));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10F)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    public void tick() {
        super.tick();
        if(!hasCustomName()) {
            setCustomName(Component.literal(POTION_NAME[getPotionEffect()] + " Solifuge"));
        }

        if(level().isClientSide()) {
            setBesideClimbableBlock(horizontalCollision);
        }
    }

    @Override
    public boolean onClimbable() {
        return isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return (entityData.get(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte data = entityData.get(CLIMBING);
        if(climbing) {
            data = (byte)(data | 1);
        } else {
            data = (byte)(data & -2);
        }
        entityData.set(CLIMBING, data);
    }

    @Override
    public void playAmbientSound() {
        playSound(SoundEvents.SPIDER_AMBIENT);
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    @Override
    public float getVoicePitch() {
        return super.getVoicePitch() * 2F;
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, DamageSource source, float damage) {
        if(source.is(DamageTypes.IN_WALL)) {
            return false;
        }
        return super.hurtServer(level, source, damage);
    }

    @Override
    public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
        if (super.doHurtTarget(level, target)) {
            if(target instanceof LivingEntity living) {
                byte duration;
                switch(level.getDifficulty()) {
                    case NORMAL-> duration = 5;
                    case HARD-> duration = 10;
                    default -> duration = 0;
                }

                if(duration > 0) {
                    living.addEffect(new MobEffectInstance(getMobEffectHolder(), duration * 20, 0, false, false));
                }
            }
        }
        return true;
    }

    private @NonNull Holder<MobEffect> getMobEffectHolder() {
        Holder<MobEffect> effect;

        switch(getPotionEffect()) {
            case 1 -> effect = MobEffects.MINING_FATIGUE;
            case 2 -> effect = MobEffects.NAUSEA;
            case 3 -> effect = MobEffects.BLINDNESS;
            case 4 -> effect = MobEffects.HUNGER;
            case 5 -> effect = MobEffects.WEAKNESS;
            case 6 -> effect = MobEffects.POISON;
            case 7 -> effect = MobEffects.WITHER;
            case 8 -> effect = MobEffects.LEVITATION;
            default -> effect = MobEffects.SLOWNESS;
        }
        return effect;
    }

    public byte getPotionEffect() {
        return entityData.get(POTION_TYPE);
    }

    public void setPotionEffect(byte effect) {
        entityData.set(POTION_TYPE, effect);
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        setPotionEffect(input.getByteOr("potion_effect", (byte) 0));
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putByte("potion_effect", getPotionEffect());
    }
}
