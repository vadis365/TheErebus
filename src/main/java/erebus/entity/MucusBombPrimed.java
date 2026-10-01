package erebus.entity;

import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public final class MucusBombPrimed extends PrimedTnt {
    public MucusBombPrimed(EntityType<? extends MucusBombPrimed> type, Level level) {
        super(type, level);
        setBlockState(ModBlocks.MUCUS_BOMB.get().defaultBlockState());
    }

    @Override
    protected void explode() {
        if (!(level() instanceof ServerLevel level)) return;
        var area = new AABB(getX() - 4.5, getY() - 4.5, getZ() - 4.5, getX() + 4.5, getY() + 4.5, getZ() + 4.5);
        for (var living : level.getEntitiesOfClass(LivingEntity.class, area, LivingEntity::isAlive)) {
            double yaw = Math.toRadians(living.getYRot());
            living.push(-Math.sin(yaw) * 2, 1, Math.cos(yaw) * 2);
            living.hurtMarked = true;
            living.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 200, 3));
            living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
        }
        level.playSound(null, blockPosition(), ModSounds.BEETLE_LARVA_SPLAT.get(), SoundSource.BLOCKS, 1, 0.5F);
        level.playSound(null, blockPosition(), ModSounds.SQUISH.get(), SoundSource.BLOCKS, 1, 0.7F);
        level.sendParticles(ModParticles.REPELLENT.get(), getX(), getY() + 0.5, getZ(), 60, 1, 0.2, 1, 0.3);
        level.sendParticles(ParticleTypes.ITEM_SLIME, getX(), getY() + 0.5, getZ(), 90, 1, 0.5, 1, 0.3);
    }
}
