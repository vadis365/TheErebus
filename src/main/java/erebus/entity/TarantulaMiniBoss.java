package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.entity.ai.TarantulaMiniBossAttackGoal;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class TarantulaMiniBoss extends Monster {

    private final ServerBossEvent bossEvent = Util.make(
            new ServerBossEvent(
                    Mth.createInsecureUUID(random),
                    getDisplayName(),
                    BossEvent.BossBarColor.RED,
                    BossEvent.BossBarOverlay.PROGRESS
            ),
            e -> e.setDarkenScreen(true)
    );

    private static final EntityDataAccessor<Byte> SKIN_TYPE = SynchedEntityData.defineId(TarantulaMiniBoss.class, EntityDataSerializers.BYTE);
    public int deathTicks;

    public TarantulaMiniBoss(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(SKIN_TYPE, (byte) 1);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new TarantulaMiniBossAttackGoal(this, 0.3D));
        goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 16.0F));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @Override
    public boolean removeWhenFarAway(double distSqr) {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 300F)
                .add(Attributes.MOVEMENT_SPEED, 0.9F)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
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
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState blockIn) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();

        if(isInDesperation() && getFancyRenderOverlay()) {
            entityData.set(SKIN_TYPE, (byte) 0);
        }

        if(isInDesperation() && !isDeadOrDying() && getTarget() != null) {

            forceCollideWithPlayer(getTarget(), distanceTo(getTarget()));
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        bossEvent.setProgress(getHealth() / getMaxHealth());
    }

    @Override
    protected void tickDeath() {
        ++deathTicks;
        int xpCount = 1000;

        move(MoverType.SELF, new Vec3(0.0D, 0.310000000149011612D, 0.0D));
        walkAnimation.setSpeed(0.5F);

        if(deathTicks % 25 == 1) {
            playSound(getDeathSound(), 1.0F, 0.1F);
            playSound(SoundEvents.SPIDER_HURT, 1.0F, 0.1F);
            playSound(SoundEvents.GHAST_HURT, 1.0F, 0.1F);
        }

        if(deathTicks >= 100 && deathTicks <= 200) {
            ClientParticles.spawnParticles(ClientParticles.ParticleType.BOSS_DEATH, getX(), getY(), getZ(), 0, 0, 0);
        }

        Level award = level();
        if(award instanceof ServerLevel level) {
            if(deathTicks > 150 && deathTicks % 5 == 0 && level.getGameRules().get(GameRules.MOB_DROPS)) {
                int amount = EventHooks.getExperienceDrop(this, EntityReference.get(lastHurtByPlayer, level, Player.class), Mth.floor(xpCount * 0.08));
                ExperienceOrb.award(level, position(), amount);
            }

            if(deathTicks == 200) {
                int amount = EventHooks.getExperienceDrop(this, EntityReference.get(lastHurtByPlayer, level, Player.class), Mth.floor(xpCount * 2 * 0.08));
                ExperienceOrb.award(level, position(), amount);
                level.setBlock(blockPosition(), ModBlocks.TARANTULA_EGG.get().defaultBlockState(), Block.UPDATE_ALL);
                drop(new ItemStack(ModItems.SPIDER_T_SHIRT.get()), true, false);
                remove(RemovalReason.KILLED);
                gameEvent(GameEvent.ENTITY_DIE);
            }
        }
    }

    @Override
    public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
        if(super.doHurtTarget(level, target)) {
            if(target instanceof LivingEntity living) {
                byte duration = 0;

                if(random.nextInt(19) == 0) {
                    switch (level.getDifficulty()) {
                        case NORMAL -> duration = 5;
                        case HARD -> duration = 10;
                        default -> duration = 0;
                    }
                }

                if(duration > 0) {
                    living.addEffect(new MobEffectInstance(MobEffects.POISON, duration * 20, 0, false, false));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
        if(isDeadOrDying()) {
            setHealth(1.0F);
            return true;
        }

        if(isInDesperation() && !source.isDirect()) return false;
        if(!isInDesperation() && source.isDirect()) return false;
        if(damage < 0.01F) return false;
        return super.hurtServer(level, source, damage);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
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
    public void addDeltaMovement(@NonNull Vec3 momentum) {
        if(!isInDesperation()) {
            momentum.multiply(1, 2, 1);
            setOnGround(true);
        } else {
            momentum.multiply(2, 2, 2);
            setOnGround(false);
        }

        super.addDeltaMovement(momentum);
    }

    public boolean getFancyRenderOverlay() {
        return entityData.get(SKIN_TYPE) == 1;
    }

    public void forceCollideWithPlayer(LivingEntity target, float distance) {
        if (distance > 1.0F && distance < 8.0F)
            if (onGround()) {
                getLookControl().setLookAt(target, 30.0F, 30.0F);
                double distanceX = target.getX() - getX();
                double distanceZ = target.getZ() - getZ();
                float sqrt = Mth.sqrt((float) (distanceX * distanceX + distanceZ * distanceZ));
                Vec3 movement = getDeltaMovement();
                setDeltaMovement(new Vec3(distanceX / sqrt * 0.5D * 0.300000011920929D + movement.x * 0.10000000298023224D, 0, distanceZ / sqrt * 0.5D * 0.300000011920929D + movement.z * 0.10000000298023224D));
            }
    }

    public boolean isInDesperation() {
        return getHealth() <= getMaxHealth() / 2;
    }

    public void spawnBlamParticles() {
        ClientParticles.spawnParticles(ClientParticles.ParticleType.TARANTULA_BLAM, getX(), getY(), getZ(), 0, 0, 0);
    }
}
