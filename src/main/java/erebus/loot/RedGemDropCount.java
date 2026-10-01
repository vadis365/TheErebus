package erebus.loot;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NonNull;

import java.util.Set;

public record RedGemDropCount() implements LootItemFunction {
    public static final RedGemDropCount INSTANCE = new RedGemDropCount();
    public static final MapCodec<RedGemDropCount> CODEC = MapCodec.unit(INSTANCE);
    public static final DeferredRegister<MapCodec<? extends LootItemFunction>> TYPES = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, Erebus.MODID);

    static {
        TYPES.register("red_gem_drop_count", () -> CODEC);
    }

    public static int sample(RandomSource random, int fortune) {
        return 1 + random.nextInt(2 + fortune);
    }

    @Override
    public @NonNull MapCodec<? extends LootItemFunction> codec() {
        return CODEC;
    }

    @Override
    public @NonNull Set<ContextKey<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.TOOL);
    }

    @Override
    public ItemStack apply(ItemStack stack, LootContext context) {
        var tool = context.getOptionalParameter(LootContextParams.TOOL);
        int fortune = 0;
        if (tool != null) {
            var enchantment = context.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE);
            fortune = EnchantmentHelper.getItemEnchantmentLevel(enchantment, tool);
            fortune = EventHooks.getBlockLootEnchantmentLevel(tool, enchantment, fortune, context);
        }
        stack.setCount(sample(context.getRandom(), Math.max(0, fortune)));
        return stack;
    }
}
