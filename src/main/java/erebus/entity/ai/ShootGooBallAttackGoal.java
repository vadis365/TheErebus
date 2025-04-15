package erebus.entity.ai;

import java.util.EnumSet;

import erebus.entity.projectile.GooBall;
import erebus.registries.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class ShootGooBallAttackGoal extends Goal {

	private final PathfinderMob mob;
	private final double speedModifier;
	private int attackStep;
	private int attackTime;

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
		attackStep = 0;
	}

	@Override
	public void tick() {
		--attackTime;
		LivingEntity livingentity = mob.getTarget();
		double distance = mob.distanceToSqr(livingentity);

		if (distance < 4.0D) {
			if (attackTime <= 0) {
				attackTime = 20;
				mob.doHurtTarget(livingentity);
			}

			mob.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), speedModifier);

		} else if (distance < 256.0D) {
			double targetX = livingentity.getX() - mob.getX();
			double targetY = livingentity.getBoundingBox().minY + (double) (livingentity.getBbHeight()) - (mob.getY() + (double) (mob.getBbHeight()));
			double targetZ = livingentity.getZ() - mob.getZ();

			if (attackTime <= 0) {
				++attackStep;
				
				if (attackStep == 1) {
					attackTime = 60;

				} else if (attackStep <= 4) {
					attackTime = 6;
				} else {
					attackTime = 100;
					attackStep = 0;
				}

				if (attackStep == 1 && livingentity instanceof Player) {
						mob.level().playSound(null, mob.blockPosition(), ModSounds.WEBSLING_THROW.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
						GooBall gooBall = new GooBall(mob.level(), mob, 0F);
						gooBall.setPos(mob.getX(), mob.getY() + (double) (mob.getBbHeight()  + 0.3D), mob.getZ());
						gooBall.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
						mob.level().addFreshEntity(gooBall);
					}
			}
			mob.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
			mob.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), speedModifier);
		}
		super.tick();
	}
}

