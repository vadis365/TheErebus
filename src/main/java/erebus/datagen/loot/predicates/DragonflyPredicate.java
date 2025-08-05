package erebus.datagen.loot.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.entity.Dragonfly;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record DragonflyPredicate(int skin) implements EntitySubPredicate {

    public static final MapCodec<DragonflyPredicate> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.INT.fieldOf("skin").forGetter(DragonflyPredicate::skin)
            ).apply(instance, DragonflyPredicate::new)
    );

    public static DragonflyPredicate skin(int skin) {
        return new DragonflyPredicate(skin);
    }

    @Override
    public @NotNull MapCodec<? extends EntitySubPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean matches(@NotNull Entity entity, @NotNull ServerLevel level, @Nullable Vec3 position) {
        return entity instanceof Dragonfly dragonfly && dragonfly.getSkin() == this.skin;
    }
}
