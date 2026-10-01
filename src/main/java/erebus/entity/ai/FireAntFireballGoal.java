package erebus.entity.ai;

import erebus.entity.FireAnt;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class FireAntFireballGoal extends Goal {
    private final FireAnt ant;
    private final boolean large;
    private int attackStep;
    private int attackTime;

    public FireAntFireballGoal(FireAnt ant, boolean large) {
        this.ant = ant;
        this.large = large;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return ant.getTarget() != null && ant.getTarget().isAlive();
    }

    @Override
    public void start() {
        attackStep = 0;
        attackTime = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = ant.getTarget();
        if (target == null || !target.isAlive()) return;
        --attackTime;
        double distance = ant.distanceToSqr(target);
        if (distance < 4) {
            if (attackTime <= 0) {
                attackTime = 20;
                ant.doHurtTarget(getServerLevel(ant), target);
            }
            ant.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 0.6);
        } else if (distance < 256) {
            if (attackTime <= 0) {
                if (++attackStep <= 4) attackTime = 60;
                else {
                    attackTime = 100;
                    attackStep = 0;
                }
                if (attackStep > 1) {
                    double spread = Math.sqrt(Math.sqrt(distance)) * 0.5;
                    var direction = new Vec3(target.getX() - ant.getX() + ant.getRandom().nextGaussian() * spread,
                            target.getY() + target.getBbHeight() / 2 - (ant.getY() + ant.getBbHeight() / 2),
                            target.getZ() - ant.getZ() + ant.getRandom().nextGaussian() * spread);
                    var fireball = large ? new LargeFireball(ant.level(), ant, direction, 1) : new SmallFireball(ant.level(), ant, direction);
                    fireball.setPos(ant.getX(), ant.getY() + ant.getBbHeight() / 2 + 0.5, ant.getZ());
                    if (ant.level().addFreshEntity(fireball)) ant.level().levelEvent(null, large ? 1009 : 1018, ant.blockPosition(), 0);
                }
            }
            ant.getLookControl().setLookAt(target, 10, 10);
            ant.getNavigation().stop();
            ant.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 0.6);
        }
    }
}
