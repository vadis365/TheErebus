package erebus.entity.projectile;

import erebus.entity.BabyTarantula;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class TarantulaEgg extends ThrowableProjectile {

    public float rotationTicks;

    public TarantulaEgg(EntityType<? extends ThrowableProjectile> type, Level level) {
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
    protected void onHit(@NonNull HitResult hitResult) {
        for(int c = 0; c < 2; c++) {
            BabyTarantula baby = ModEntities.BABY_TARANTULA.get().create(level(), EntitySpawnReason.EVENT);
            baby.setPos(new Vec3(getX() + (random.nextFloat() * 0.03D - random.nextFloat() * 0.03D), getY() + 1, getZ() + (random.nextFloat() * 0.03D - random.nextFloat() * 0.03D)));
            level().addFreshEntity(baby);
        }

        kill((ServerLevel) level());
        playSound(ModSounds.BEETLE_LARVA_SPLAT.get());
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity other) {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
    }
}
