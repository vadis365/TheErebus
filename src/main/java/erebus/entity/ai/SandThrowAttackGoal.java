package erebus.entity.ai;

import erebus.entity.AntlionBoss;
import erebus.entity.projectile.ThrownSand;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.List;

public class SandThrowAttackGoal extends Goal {

    private final AntlionBoss boss;
    private final ServerLevel level;
    private int attackStep;
    private int attackTime;
    private boolean jumpAttack;

    public SandThrowAttackGoal(AntlionBoss bossIn) {
        boss = bossIn;
        level = getServerLevel(bossIn.level());
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = boss.getTarget();
        return livingentity != null && livingentity.isAlive();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void stop() {
        jumpAttack = false;
        boss.getNavigation().stop();
    }

    @Override
    public void start() {
        attackStep = 0;
    }

    @Override
    public void tick() {
        --attackTime;
        LivingEntity target = boss.getTarget();
        if (target == null || !target.isAlive()) return;
        double distance = boss.distanceToSqr(target);

        if (distance <= 25.0D) {
            if (attackTime <= 0) {
                attackTime = 20;
                boss.level().playSound(null, boss.getOnPos(), ModSounds.ANTLION_GROWL.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
                target.hurtServer(level, boss.damageSources().mobAttack(boss), 8);
                target.push(-Mth.sin(boss.getYRot() * Mth.PI / 180.0F) * 0.3F, 0.1D, Mth.cos(boss.getYRot() * Mth.PI / 180.0F) * 0.3F);
            }

            boss.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), boss.getAttributeValue(Attributes.MOVEMENT_SPEED));
        } else if (distance > 36.0D && distance < 256.0D) {
            if (attackTime <= 0) {
                ++attackStep;
                switch (attackStep) {
                    case 1 -> attackTime = 60;
                    case 2 -> attackTime = 5;
                    default -> {
                        attackTime = 0;
                        attackStep = 0;
                    }
                }

                if (attackStep == 1) {
                    double direction = Math.toRadians(boss.yBodyRot);
                    double targetX = target.getX() - boss.getX();
                    double targetY = target.getBoundingBox().minY + target.getBbHeight() - (boss.getY() + boss.getBbHeight());
                    double targetZ = target.getZ() - boss.getZ();
                    var thrownSand = new ThrownSand(ModEntities.THROWN_SAND.get(), boss.level());
                    thrownSand.setOwner(boss);
                    thrownSand.setPos(boss.getX() - Mth.sin(direction) * 3.5, boss.getY() + boss.getBbHeight(), boss.getZ() + Mth.cos(direction) * 3.5);
                    thrownSand.shoot(targetX, targetY, targetZ, 0.7F, 0.0F);
                    boss.level().addFreshEntity(thrownSand);
                }
            }
            boss.lookAt(target, 10, 10);
            boss.getNavigation().stop();
            boss.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), boss.getAttributeValue(Attributes.MOVEMENT_SPEED));
        } else if (distance > 16.0D && distance <= 1024.0D) {
            if (attackTime <= 0) {
                int x = level.getRandom().nextInt(4);

                if (x == 0) {
                    attackTime = 60;
                    boss.setDeltaMovement(boss.getDeltaMovement().x, 0.61999998688697815D, boss.getDeltaMovement().z);
                    jumpAttack = true;
                } else if (x == 1 && !jumpAttack && boss.onGround()) {
                    attackTime = 60;
                    boss.setDeltaMovement(boss.getDeltaMovement().x, 0, boss.getDeltaMovement().z);
                    boss.setBlam(10, (byte) 1);
                }
            }

            if (jumpAttack && boss.verticalCollision && !(boss.getDeltaMovement().y > 0.0D)) {
                areaOfEffect();
                boss.spawnBlamParticles();
                jumpAttack = false;
            }
            boss.lookAt(target, 10, 10);
            boss.getNavigation().stop();
            boss.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), boss.getAttributeValue(Attributes.MOVEMENT_SPEED));
        }
        super.tick();
    }

    protected void areaOfEffect() {
        List<Entity> list = level.getEntities(boss, boss.getBoundingBox().inflate(8, 1, 8));

        for (Entity entity : list) {
            if (entity != null) {
                if (entity instanceof LivingEntity target && !(target instanceof AntlionBoss)) {
                    target.hurtServer(level, boss.damageSources().mobAttack(boss), 8);
                    target.push(-Mth.sin(boss.getYRot() * Mth.PI / 180.0F), 0.4D, Mth.cos(boss.getYRot() * Mth.PI / 180.0F));
                    boss.level().playSound(null, boss.getOnPos(), ModSounds.ANTLION_SLAM.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
                    boss.level().playSound(null, boss.getOnPos(), ModSounds.ANTLION_EXPLODE.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
                    target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 160, 0));
                    target.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 160, 0));
                }
            }
        }
    }
}
