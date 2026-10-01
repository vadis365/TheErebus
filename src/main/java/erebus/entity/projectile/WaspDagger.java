package erebus.entity.projectile;

import erebus.entity.Wasp;
import erebus.registries.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public final class WaspDagger extends ThrowableItemProjectile {
    public WaspDagger(EntityType<? extends WaspDagger> type, Level level) {
        super(type, level);
    }

    @Override
    protected @NonNull Item getDefaultItem() {
        return ModItems.WASP_DAGGER.get();
    }

    @Override
    protected void onHitEntity(@NonNull EntityHitResult hit) {
        super.onHitEntity(hit);
        if (!(level() instanceof ServerLevel level)) return;
        var target = hit.getEntity();
        if (target != getOwner()) {
            if (!(target instanceof Wasp)) target.hurtServer(level, damageSources().thrown(this, getOwner()), 6);
            if (isOnFire() && !(target instanceof EnderMan)) target.igniteForSeconds(5);
            if (target instanceof LivingEntity living && !(target instanceof Wasp)) living.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
        }
    }

    @Override
    protected void onHit(HitResult hit) {
        if (hit.getType() == HitResult.Type.MISS || isRemoved()) return;
        super.onHit(hit);
        if (level() instanceof ServerLevel level) {
            level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, ItemStackTemplate.fromNonEmptyStack(getItem())),
                    getX(), getY(), getZ(), 8, 0.1, 0.1, 0.1, 0.05);
            discard();
        }
    }
}
