package erebus.entity.ai;

import erebus.entity.MagmaCrawler;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class MagmaCrawlerFireballGoal extends Goal {
    private final MagmaCrawler crawler;
    private int attackStep;
    private int attackTime;

    public MagmaCrawlerFireballGoal(MagmaCrawler crawler) {
        this.crawler = crawler;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return crawler.getTarget() != null && crawler.getTarget().isAlive();
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
        var target = crawler.getTarget();
        if (target == null || !target.isAlive()) return;
        --attackTime;
        double distance = crawler.distanceToSqr(target);
        if (distance < 4) {
            if (attackTime <= 0) {
                attackTime = 20;
                crawler.doHurtTarget(getServerLevel(crawler), target);
            }
        } else if (distance > 9 && distance < 256 && attackTime <= 0) {
            if (++attackStep <= 4) attackTime = 60;
            else {
                attackTime = 100;
                attackStep = 0;
            }
            if (attackStep > 1) {
                double spread = Math.sqrt(Math.sqrt(distance)) * 0.5;
                var direction = new Vec3(target.getX() - crawler.getX() + crawler.getRandom().nextGaussian() * spread,
                        target.getY() + target.getBbHeight() / 2 - (crawler.getY() + crawler.getBbHeight() / 2),
                        target.getZ() - crawler.getZ() + crawler.getRandom().nextGaussian() * spread);
                var fireball = new SmallFireball(crawler.level(), crawler, direction);
                fireball.setPos(crawler.getX(), crawler.getY() + crawler.getBbHeight() / 2 + 0.5, crawler.getZ());
                if (crawler.level().addFreshEntity(fireball)) crawler.level().levelEvent(null, 1018, crawler.blockPosition(), 0);
            }
        }
    }
}
