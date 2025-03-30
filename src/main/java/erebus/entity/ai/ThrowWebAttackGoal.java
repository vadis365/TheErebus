package erebus.entity.ai;

import java.util.EnumSet;

import erebus.entity.IErebusAnimationExtras;
import erebus.entity.projectile.ThrownBlockAsItem;
import erebus.registries.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class ThrowWebAttackGoal extends Goal {

	private final PathfinderMob mob;
	private final double speedModifier;
	private final BlockState blockstate;
	private int attackStep;
	private int attackTime;

	public ThrowWebAttackGoal(PathfinderMob mobIn, double speedModifierIn, BlockState blockstateIn) {
		mob = mobIn;
		speedModifier = speedModifierIn;
		blockstate = blockstateIn;
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
			double targetY = livingentity.getBoundingBox().minY + (double) (livingentity.getBbHeight() / 2.0F) - (mob.getY() + (double) (mob.getBbHeight() / 2.0F));
			double targetZ = livingentity.getZ() - mob.getZ();

			if (attackTime <= 0) {
				++attackStep;

				if (attackStep == 1) {
					attackTime = 60;
					if(mob instanceof IErebusAnimationExtras && ((IErebusAnimationExtras) mob).hasAnimation())
						((IErebusAnimationExtras) mob).setShouldTickAnimation(true);
				} else if (attackStep <= 4) {
					attackTime = 6;
				} else {
					attackTime = 100;
					attackStep = 0;
					if(mob instanceof IErebusAnimationExtras && ((IErebusAnimationExtras) mob).hasAnimation())
						((IErebusAnimationExtras) mob).setShouldTickAnimation(false);
				}

				if (attackStep > 1 && livingentity instanceof Player) {

					mob.level().playSound(null, mob.blockPosition(), ModSounds.WEBSLING_THROW.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
					for (int count = 0; count < 1; ++count) {
						ThrownBlockAsItem webSling = new ThrownBlockAsItem(mob.level(), mob, blockstate, 0, ModSounds.WEBSLING_SPLAT.get());
						webSling.setPos(mob.getX(), mob.getY() + (double) (mob.getBbHeight() / 2.0F) + 0.5D, mob.getZ());
						webSling.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
						mob.level().addFreshEntity(webSling);
					}
				}
			}
			mob.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
			mob.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), speedModifier);
		}
		super.tick();
	}
}

