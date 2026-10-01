package erebus.entity.ai;

import erebus.entity.TarantulaMiniBoss;
import erebus.entity.projectile.PoisonJet;
import erebus.entity.projectile.TarantulaEgg;
import erebus.registries.ModSounds;
import erebus.registries.data.tags.ModEntityTypeTags;
import erebus.registries.entity.ModEntities;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class TarantulaMiniBossAttackGoal extends Goal {

    private final TarantulaMiniBoss boss;
    private final double speedTowardsTarget;
    private int findAttemptCount;
    private int failedPathFindingPenalty;
    private int shouldDo;
    private int attackTick;
    private boolean jumpAttack;
    private Path path;

    public TarantulaMiniBossAttackGoal(TarantulaMiniBoss boss, double speedTowardsTarget) {
        this.boss = boss;
        this.speedTowardsTarget = speedTowardsTarget;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (boss.getTarget() == null) return false;
        if (!boss.getTarget().isAlive()) return false;
        if (--findAttemptCount <= 0) {
            findAttemptCount = 4 + boss.getRandom().nextInt(7);
            path = boss.getNavigation().createPath(boss.getTarget(), 0);
            return path != null;
        }

        return path != null;
    }

    @Override
    public void start() {
        super.start();

        boss.getNavigation().moveTo(path, boss.isInDesperation() ? speedTowardsTarget : 0);
        findAttemptCount = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return boss.getTarget() != null && boss.getTarget().isAlive() && !boss.getNavigation().isDone();
    }

    @Override
    public void stop() {
        boss.getNavigation().stop();
        path = null;
        findAttemptCount = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = boss.getTarget();
        if (target == null || !target.isAlive()) return;

        boss.getLookControl().setLookAt(target, 30.0F, 30.0F);

        attackTick = Math.max(attackTick - 1, 0);
        double lungeRange = boss.getBbWidth() * boss.getBbWidth() + target.getBbWidth();

        double direction = Math.toRadians(boss.getYRot());
        double targetX = target.getX() - boss.getX();
        double targetY = target.getBoundingBox().minY + (double) (target.getBbHeight()) - (boss.getY() + (double) (boss.getBbHeight()));
        double targetZ = target.getZ() - boss.getZ();

        if (boss.isInDesperation() && boss.getSensing().hasLineOfSight(target) && --findAttemptCount <= 0) {
            findAttemptCount = failedPathFindingPenalty + 4 + boss.getRandom().nextInt(7);
            boss.getNavigation().moveTo(target, speedTowardsTarget);

            if (boss.getNavigation().getPath() != null) {
                Node endNode = boss.getNavigation().getPath().getEndNode();
                if (endNode != null && target.distanceToSqr(endNode.x, endNode.y, endNode.z) < 1) {
                    failedPathFindingPenalty = 0;
                } else {
                    failedPathFindingPenalty += 10;
                }
            } else {
                failedPathFindingPenalty += 10;
            }
        }

        if (boss.distanceToSqr(target.getX(), target.getBoundingBox().minY, target.getZ()) <= lungeRange) {
            if (attackTick <= 0) {
                attackTick = 10;
                boss.doHurtTarget(getServerLevel(boss), target);
                target.addDeltaMovement(new Vec3(-Mth.sin(boss.getYRot() * Mth.PI / 180.0F) * 0.5F, 0.1D, Mth.cos(boss.getYRot() * Mth.PI / 180.0F) * 0.5F));
            }
        }

        if (boss.distanceToSqr(target.getX(), target.getBoundingBox().minY, target.getZ()) > lungeRange + 1 && boss.distanceToSqr(target.getX(), target.getBoundingBox().minY, target.getZ()) < lungeRange + 256.0D && boss.isInDesperation()) {
            if (attackTick <= 0) {
                ++shouldDo;
                if (shouldDo == 1)
                    attackTick = 40;
                else if (shouldDo <= 2)
                    attackTick = 20;
                else {
                    attackTick = 20;
                    shouldDo = 0;
                }
                if (shouldDo == 1) {
                    PoisonJet jet = ModEntities.POISON_JET.get().create(boss.level(), EntitySpawnReason.TRIGGERED);
                    if (jet != null) {
                        jet.setOwner(boss);
                        jet.setPos(boss.getX() + -Math.sin(direction) * 3.5D, boss.getY() + boss.getBbHeight() * 0.5, boss.getZ() + Math.cos(direction) * 3.5D);
                        jet.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
                        boss.level().addFreshEntity(jet);
                    }
                }
            }
        }

        if (boss.distanceToSqr(target.getX(), target.getBoundingBox().minY, target.getZ()) > lungeRange + 9D && boss.distanceToSqr(target.getX(), target.getBoundingBox().minY, target.getZ()) < lungeRange + 256.0D && !boss.isInDesperation()) {
            if (attackTick <= 0) {
                ++shouldDo;
                if (shouldDo == 1)
                    attackTick = 200;
                else if (shouldDo <= 2)
                    attackTick = 20;
                else {
                    attackTick = 40;
                    shouldDo = 0;
                }
                if (shouldDo == 1) {
                    TarantulaEgg babyEgg = ModEntities.TARANTULA_EGG.get().create(boss.level(), EntitySpawnReason.TRIGGERED);
                    if (babyEgg != null) {
                        babyEgg.setOwner(boss);
                        babyEgg.setPos(new Vec3(boss.getX() - Math.sin(direction) * 3.5, boss.getY() + boss.getBbHeight(), boss.getZ() + Math.cos(direction) * 3.5));
                        babyEgg.shoot(targetX, targetY, targetZ, 0.7F, 0.0F);
                        boss.level().addFreshEntity(babyEgg);
                    }
                }

                if (boss.getRandom().nextInt(3) == 1) {
                    attackTick = 30;
                    Vec3 deltaMovement = boss.getDeltaMovement();
                    boss.setDeltaMovement(new Vec3(deltaMovement.x, 0.61999998688697815D, deltaMovement.z));
                    jumpAttack = true;
                }
            }
            if (jumpAttack && boss.getDeltaMovement().y == -0.0784000015258789D) {
                areaOfEffect();
                boss.spawnBlamParticles();
                jumpAttack = false;
            }
        }
    }

    protected void areaOfEffect() {
        List<Entity> list = boss.level().getEntities(boss, boss.getBoundingBox().inflate(8, 1, 8), e -> !e.is(ModEntityTypeTags.TARANTULA));

        list.forEach(entity -> {
            if (entity != null) {
                if (entity instanceof LivingEntity target) {
                    float knockback = 2;
                    boss.doHurtTarget(getServerLevel(boss), target);
                    target.addDeltaMovement(new Vec3(-Mth.sin(boss.getYRot() * Mth.PI / 180.0F) * knockback * 0.5F, 0.4D, Mth.cos(boss.getYRot() * Mth.PI / 180.0F) * knockback * 0.5F));
                    boss.playSound(ModSounds.BLAM_SOUND.get(), 1.5F, 1.0F);
                    target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 160, 0));
                    target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
                }
            }
        });
    }
}
