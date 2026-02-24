package erebus.entity.projectile;

import com.google.common.base.MoreObjects;
import erebus.client.particle.ClientParticles;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PoisonJet extends ThrowableProjectile {

    public float rotationTicks;

    public PoisonJet(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected void updateRotation() {
        super.updateRotation();
        if(rotationTicks < 360F) {
            rotationTicks = rotationTicks + 20F;
            if(rotationTicks >= 360F) {
                rotationTicks = 0F;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(level().isClientSide()) {
            makeParticles();
        }
    }

    private void makeParticles() {
        for(int c = 0; c < 5; c++) {
            ClientParticles.spawnParticles(ClientParticles.ParticleType.POISON, getX(), getY(), getZ(), 0D, 0D, 0D);
        }
    }

    @Override
    protected void onHitEntity(@NonNull EntityHitResult result) {
        super.onHitEntity(result);
        Entity target = result.getEntity();
        Entity owner = this.getOwner();
        LivingEntity livingOwner = owner instanceof LivingEntity ? (LivingEntity)owner : null;
        DamageSource damageSource = this.damageSources().mobProjectile(this, livingOwner);
        boolean wasHurt = target.hurtOrSimulate(damageSource, 1.0F);
        if (wasHurt) {
            if (this.level() instanceof ServerLevel serverLevel) {
                EnchantmentHelper.doPostAttackEffects(serverLevel, target, damageSource);
            }

            if (target instanceof LivingEntity livingTarget) {
                livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON, 100), MoreObjects.firstNonNull(owner, this));
            }
        }
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity other) {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
    }
}
