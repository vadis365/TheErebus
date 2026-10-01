package erebus.entity.projectile;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public final class ThrownSand extends ThrowableItemProjectile {
    public ThrownSand(EntityType<? extends ThrownSand> type, Level level) {
        super(type, level);
    }

    @Override
    protected @NonNull Item getDefaultItem() {
        return Items.SAND;
    }

    @Override
    protected void onHitEntity(@NonNull EntityHitResult hit) {
        super.onHitEntity(hit);
        if (!(level() instanceof ServerLevel server)) return;
        var target = hit.getEntity();
        if (target instanceof LivingEntity living) living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40), getOwner());
        target.hurtServer(server, damageSources().thrown(this, getOwner()), 4);
        server.levelEvent(2001, blockPosition(), Block.getId(Blocks.SAND.defaultBlockState()));
    }

    @Override
    protected void onHit(@NonNull HitResult hit) {
        if (!(level() instanceof ServerLevel server) || isRemoved() || hit.getType() == HitResult.Type.MISS) return;
        super.onHit(hit);
        if (hit instanceof BlockHitResult blockHit) {
            var pos = blockHit.getBlockPos();
            if (!server.getBlockState(pos).canBeReplaced()) pos = pos.relative(blockHit.getDirection());
            if (server.isInWorldBounds(pos) && server.getWorldBorder().isWithinBounds(pos) && server.hasChunkAt(pos)
                    && mayInteract(server, pos) && server.getBlockState(pos).isAir()) server.setBlockAndUpdate(pos, Blocks.SAND.defaultBlockState());
        }
        discard();
    }
}
