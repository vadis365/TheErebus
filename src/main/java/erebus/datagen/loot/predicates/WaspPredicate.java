package erebus.datagen.loot.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.entity.Wasp;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record WaspPredicate(boolean isBoss) implements EntitySubPredicate {

    public static final MapCodec<WaspPredicate> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.BOOL.fieldOf("is_boss").forGetter(WaspPredicate::isBoss)
            ).apply(instance, WaspPredicate::new)
    );

    public static WaspPredicate isBoss(boolean isBoss) {
        return new WaspPredicate(isBoss);
    }

    @Override
    public @NotNull MapCodec<? extends EntitySubPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean matches(@NotNull Entity entity, @NotNull ServerLevel level, @Nullable Vec3 position) {
        return entity instanceof Wasp wasp && this.isBoss == wasp.getIsBoss();
    }
}
