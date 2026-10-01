package erebus.entity.ai;

import erebus.entity.projectile.ThrownBlockAsItem;
import erebus.entity.projectile.WebSling;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

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
        --attackTime;
        LivingEntity livingentity = mob.getTarget();
        if (livingentity == null || !livingentity.isAlive()) return;
        double distance = mob.distanceToSqr(livingentity);

        if (distance < 4.0D) {
            if (attackTime <= 0 && mob.hasLineOfSight(livingentity)) {
                attackTime = 20;
                mob.doHurtTarget(getServerLevel(mob.level()), livingentity);
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

                } else if (attackStep <= 4) {
                    attackTime = 6;
                } else {
                    attackTime = 100;
                    attackStep = 0;
                }

                if (attackStep > 1 && livingentity instanceof Player && mob.hasLineOfSight(livingentity)) {
                    ThrowableProjectile shot;
                    if (blockstate.is(Blocks.COBWEB) || blockstate.is(ModBlocks.WITHER_WEB)) {
                        WebSling web = ModEntities.WEB_SLING.get().create(mob.level(), EntitySpawnReason.TRIGGERED);
                        if (web == null) return;
                        web.setOwner(mob);
                        web.setWither(blockstate.is(ModBlocks.WITHER_WEB));
                        shot = web;
                    } else {
                        // Preserve the current lava-web design while its fire-shot parity decision is pending.
                        shot = new ThrownBlockAsItem(mob.level(), mob, blockstate, 0, ModSounds.WEBSLING_SPLAT.get());
                    }
                    shot.setPos(mob.getX(), mob.getY() + mob.getBbHeight() / 2.0D + 0.5D, mob.getZ());
                    shot.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
                    if (mob.level().addFreshEntity(shot))
                        mob.level().playSound(null, mob.blockPosition(), ModSounds.WEBSLING_THROW.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
                }
            }
            mob.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
            mob.getNavigation().stop();
            mob.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), speedModifier);
        }
        super.tick();
    }
}

