package erebus.loot;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.LivingEntity;
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

public record ScytodesEyeCount() implements LootItemFunction {
    public static final ScytodesEyeCount INSTANCE = new ScytodesEyeCount();
    public static final MapCodec<ScytodesEyeCount> CODEC = MapCodec.unit(INSTANCE);
    public static final DeferredRegister<MapCodec<? extends LootItemFunction>> TYPES = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, Erebus.MODID);

    static {
        TYPES.register("scytodes_eye_count", () -> CODEC);
    }

    @Override
    public @NonNull MapCodec<? extends LootItemFunction> codec() {
        return CODEC;
    }

    @Override
    public @NonNull Set<ContextKey<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.ATTACKING_ENTITY);
    }

    @Override
    public ItemStack apply(ItemStack stack, LootContext context) {
        int looting = 0;
        if (context.getOptionalParameter(LootContextParams.ATTACKING_ENTITY) instanceof LivingEntity attacker) {
            var enchantment = context.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING);
            looting = EnchantmentHelper.getEnchantmentLevel(enchantment, attacker);
            looting = EventHooks.getEntityLootEnchantmentLevel(enchantment, looting, context);
        }
        stack.setCount(context.getRandom().nextInt(3) == 0 || context.getRandom().nextInt(1 + Math.max(0, looting)) > 0 ? 1 : 0);
        return stack;
    }
}
