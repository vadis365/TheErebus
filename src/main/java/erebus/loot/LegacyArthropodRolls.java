package erebus.loot;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NonNull;

import java.util.Set;

public record LegacyArthropodRolls() implements NumberProvider {
    public static final LegacyArthropodRolls INSTANCE = new LegacyArthropodRolls();
    public static final MapCodec<LegacyArthropodRolls> CODEC = MapCodec.unit(INSTANCE);
    public static final DeferredRegister<MapCodec<? extends NumberProvider>> TYPES = DeferredRegister.create(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE, Erebus.MODID);

    static {
        TYPES.register("legacy_arthropod_rolls", () -> CODEC);
    }

    @Override
    public @NonNull MapCodec<? extends NumberProvider> codec() {
        return CODEC;
    }

    @Override
    public float getFloat(@NonNull LootContext context) {
        return JumpingSpiderDropCount.roll(context);
    }

    @Override
    public @NonNull Set<ContextKey<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.ATTACKING_ENTITY);
    }
}
