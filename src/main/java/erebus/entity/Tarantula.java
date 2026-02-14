package erebus.entity;

import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class Tarantula extends Monster {

    private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(Tarantula.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(Tarantula.class, EntityDataSerializers.BYTE);

    public Tarantula(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30F)
                .add(Attributes.MOVEMENT_SPEED, 0.6F)
                .add(Attributes.ARMOR, 4)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
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
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState state) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean onClimbable() {
        return isBesideClimbableBlock();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        return !effect.is(MobEffects.POISON) && super.canBeAffected(effect);
    }

    public boolean isBesideClimbableBlock() {
        return (entityData.get(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte data = entityData.get(CLIMBING);
        if (climbing)
            data = (byte)(data | 1);
        else
        	data = (byte)(data & -2);
       entityData.set(CLIMBING, data);
    }

    public void setSkin(int skin) {
        entityData.set(SKIN_TYPE, skin);
    }

    public int getSkin() {
        return entityData.get(SKIN_TYPE);
    }

    @Override
    public void tick() {
        super.tick();
        if(level().isClientSide()) {
            setBesideClimbableBlock(horizontalCollision);
        }
    }

    @Override
    public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
        if (super.doHurtTarget(level, target)) {
            if (target instanceof LivingEntity living) {
                byte duration = 0;

                switch (level.getDifficulty()) {
                    case NORMAL -> duration = 5;
                    case HARD -> duration = 10;
                }

                if (duration > 0)
                    living.addEffect(new MobEffectInstance(MobEffects.POISON, duration * 20, 0));
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @Nullable SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
        RandomSource random = level.getRandom();
        if (random.nextInt(100) == 0) {
            MoneySpider spider = ModEntities.MONEY_SPIDER.get().create(this.level(), EntitySpawnReason.JOCKEY);
            if (spider != null) {
                spider.snapTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                spider.finalizeSpawn(level, difficulty, spawnReason, null);
                spider.startRiding(this, false, false);
            }
        }

        if (groupData == null) {
            groupData = new Spider.SpiderEffectsGroupData();
            if (level.getDifficulty() == Difficulty.HARD && random.nextFloat() < 0.1F * difficulty.getSpecialMultiplier()) {
                ((Spider.SpiderEffectsGroupData)groupData).setRandomEffect(random);
            }
        }

        if (groupData instanceof Spider.SpiderEffectsGroupData spiderEffectsGroupData) {
            Holder<MobEffect> effect = spiderEffectsGroupData.effect;
            if (effect != null) {
                this.addEffect(new MobEffectInstance(effect, -1));
            }
        }

        return groupData;
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("skin", getSkin());
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        setSkin(input.getIntOr("skin", 0));
    }
}
