package erebus.loot;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.entity.HoneyPotAnt;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NonNull;

import java.util.Set;

public record HoneyPotNectarCount() implements LootItemFunction {
    public static final HoneyPotNectarCount INSTANCE = new HoneyPotNectarCount();
    public static final MapCodec<HoneyPotNectarCount> CODEC = MapCodec.unit(INSTANCE);
    public static final DeferredRegister<MapCodec<? extends LootItemFunction>> TYPES = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, Erebus.MODID);

    static {
        TYPES.register("honey_pot_nectar_count", () -> CODEC);
    }

    @Override
    public @NonNull MapCodec<? extends LootItemFunction> codec() {
        return CODEC;
    }

    @Override
    public @NonNull Set<ContextKey<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.THIS_ENTITY);
    }

    @Override
    public ItemStack apply(ItemStack stack, LootContext context) {
        if (context.getParameter(LootContextParams.THIS_ENTITY) instanceof HoneyPotAnt ant)
            stack.setCount(ant.isTamedAnt() ? (int) (ant.getHoneyBelly() * 10) : 1);
        return stack;
    }
}
