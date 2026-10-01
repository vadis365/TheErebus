package erebus.entity;

import erebus.registries.client.ModParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class SwampVentGas extends Entity {
    private static final EntityDataAccessor<Boolean> VOLCANIC = SynchedEntityData.defineId(SwampVentGas.class, EntityDataSerializers.BOOLEAN);

    public SwampVentGas(EntityType<? extends SwampVentGas> type, Level level) {
        super(type, level);
        setNoGravity(true);
    }

    public boolean isVolcanic() {
        return entityData.get(VOLCANIC);
    }

    public void setVolcanic(boolean volcanic) {
        entityData.set(VOLCANIC, volcanic);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            if (tickCount == 1) {
                if (isVolcanic()) {
                    for (double y = getY(); y < getY() + 2; y += 0.5) {
                        for (double x : new double[]{-0.075, 0.075})
                            for (double z : new double[]{-0.075, 0.075})
                                level().addParticle(ParticleTypes.FLAME, getX() + x, y, getZ() + z, 0, 0.05, 0);
                        level().addParticle(ParticleTypes.FLAME, getX(), y + 0.25, getZ(), 0, 0.05, 0);
                    }
                } else level().addParticle(ModParticles.SWAMP_VENT.get(), getX(), getY(), getZ(), 0.1, 0, 0.1);
            }
            return;
        }
        if (tickCount > 20) {
            discard();
            return;
        }
        for (var target : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox(), LivingEntity::isAlive)) {
            target.igniteForSeconds(5);
            discard();
            break;
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(VOLCANIC, false);
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
    }
}
