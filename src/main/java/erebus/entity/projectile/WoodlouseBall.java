package erebus.entity.projectile;

import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public final class WoodlouseBall extends ThrowableItemProjectile {
    public WoodlouseBall(EntityType<? extends WoodlouseBall> type, Level level) {
        super(type, level);
    }

    @Override
    protected @NonNull Item getDefaultItem() {
        return ModItems.WOODLOUSE_BALL.get();
    }

    @Override
    protected void onHitEntity(@NonNull EntityHitResult hit) {
        super.onHitEntity(hit);
        if (!(level() instanceof ServerLevel level)) return;
        var target = hit.getEntity();
        target.hurtServer(level, damageSources().thrown(this, getOwner()), 4);
        if (isOnFire() && !(target instanceof EnderMan)) target.igniteForSeconds(5);
        double yaw = Math.toRadians(getOwner() == null ? getYRot() : getOwner().getYRot());
        target.push(-Math.sin(yaw) * 0.5, 0.1, Math.cos(yaw) * 0.5);
        target.hurtMarked = true;
    }

    @Override
    protected void onHit(@NonNull HitResult hit) {
        if (isRemoved() || hit.getType() == HitResult.Type.MISS) return;
        super.onHit(hit);
        if (level() instanceof ServerLevel level) {
            var woodlouse = ModEntities.WOODLOUSE.get().create(level, EntitySpawnReason.TRIGGERED);
            if (woodlouse != null) {
                woodlouse.setPos(position());
                level.addFreshEntity(woodlouse);
            }
            discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide() && !isRemoved()) level().addParticle(new DustParticleOptions(0xFFFFFF, 1),
                getX(), getY(), getZ(), 0, 0, 0);
    }
}
