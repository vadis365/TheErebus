package erebus.entity.ai;

import erebus.entity.projectile.GooBall;
import erebus.registries.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class ShootGooBallAttackGoal extends Goal {

    private final PathfinderMob mob;
    private final double speedModifier;
    private int attackTime;
    private int shootCharge;

    public ShootGooBallAttackGoal(PathfinderMob mobIn, double speedModifierIn) {
        mob = mobIn;
        speedModifier = speedModifierIn;
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = mob.getTarget();
        return livingentity != null && livingentity.isAlive();
    }

    @Override
    public void start() {
        attackTime = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void stop() {
        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity livingentity = mob.getTarget();
        if (livingentity == null || !livingentity.isAlive()) return;
        --attackTime;
        double distance = mob.distanceToSqr(livingentity);

        if (distance <= 4.0D + livingentity.getBbWidth()) {
            if (attackTime <= 0 && mob.hasLineOfSight(livingentity)) {
                attackTime = 20;
                mob.doHurtTarget(getServerLevel(mob.level()), livingentity);
            }
        }
        // Legacy inflation gains two points per tick only beyond three blocks.
        if (distance > 9.0D) shootCharge = Math.min(100, shootCharge + 2);
        if (distance > 9.0D && distance < 256.0D && shootCharge >= 100
                && livingentity instanceof Player && mob.hasLineOfSight(livingentity)) {
            double targetX = livingentity.getX() - mob.getX();
            double targetY = livingentity.getBoundingBox().minY + (double) (livingentity.getBbHeight()) - (mob.getY() + (double) (mob.getBbHeight()));
            double targetZ = livingentity.getZ() - mob.getZ();

            GooBall gooBall = new GooBall(mob.level(), mob, 0F);
            gooBall.setPos(mob.getX(), mob.getY() + (mob.getBbHeight() + 0.3D), mob.getZ());
            gooBall.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
            if (mob.level().addFreshEntity(gooBall)) {
                shootCharge = 0;
                mob.level().playSound(null, mob.blockPosition(), ModSounds.WEBSLING_THROW.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
            }
        }
        mob.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
        mob.getNavigation().moveTo(livingentity, speedModifier);
        if (mob.onGround() && distance > 1 && distance < 36) {
            double dx = livingentity.getX() - mob.getX(), dz = livingentity.getZ() - mob.getZ();
            double length = Math.sqrt(dx * dx + dz * dz);
            if (length > 1.0E-6) {
                var motion = mob.getDeltaMovement();
                mob.setDeltaMovement(dx / length * 0.15 + motion.x * 0.1, 0, dz / length * 0.15 + motion.z * 0.1);
            }
        }
        super.tick();
    }
}

