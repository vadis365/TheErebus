package erebus.entity.ai;

import erebus.entity.projectile.GooBall;
import erebus.registries.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ShootGooBallAttackGoal extends Goal {

	private final PathfinderMob mob;
	private final double speedModifier;
	private int attackTime;
	private int shootTime;

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
	public void tick() {
		--attackTime;
		--shootTime;
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

			if (shootTime <= 0) {
				mob.level().playSound(null, mob.blockPosition(), ModSounds.WEBSLING_THROW.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
				GooBall gooBall = new GooBall(mob.level(), mob, 0F);
				gooBall.setPos(mob.getX(), mob.getY() + (mob.getBbHeight() + 0.3D), mob.getZ());
				gooBall.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
				mob.level().addFreshEntity(gooBall);
				shootTime = 100;
			}
			mob.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
			mob.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), speedModifier);
		}
		super.tick();
	}
}

