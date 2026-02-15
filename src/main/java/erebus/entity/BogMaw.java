package erebus.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class BogMaw extends Monster {

    private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(BogMaw.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> JAW_ANGLE = SynchedEntityData.defineId(BogMaw.class, EntityDataSerializers.FLOAT);

    public BogMaw(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new LeapAtTargetGoal(this, 0.5F));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0, true));
        targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(ROTATION, 0.0F);
        entityData.define(JAW_ANGLE, 0.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25F)
                .add(Attributes.MOVEMENT_SPEED, 0.0F)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
        if(target instanceof LivingEntity entity) {
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 50, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 50, 0));
        }
        return super.doHurtTarget(level, target);
    }

    @Override
    public void tick() {
        if(level().isClientSide() && getTarget() != null) {
            double distance = distanceTo(getTarget());
            float rot = getTarget().getYRot();
            setRotation(rot);

            if(distance <= 4.0D && getJawAngle() < 1) {
                setJawAngle(getJawAngle()+ 0.1F);
            }

            if(distance > 4.0D && getJawAngle() > 0) {
                setJawAngle(getJawAngle()- 0.1F);
            }
        }

        super.tick();
    }

    public void setRotation(float rot) {
        entityData.set(ROTATION, rot);
    }

    public float getRotation() {
        return entityData.get(ROTATION);
    }

    public void setJawAngle(float angle) {
        entityData.set(JAW_ANGLE, angle);
    }

    public float getJawAngle() {
        return entityData.get(JAW_ANGLE);
    }
}
