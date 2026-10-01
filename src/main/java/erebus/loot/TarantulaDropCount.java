package erebus.loot;

import com.mojang.serialization.Codec;
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

public record TarantulaDropCount(boolean eyes) implements LootItemFunction {
    public static final TarantulaDropCount LEGS = new TarantulaDropCount(false);
    public static final TarantulaDropCount EYES = new TarantulaDropCount(true);
    public static final MapCodec<TarantulaDropCount> CODEC = Codec.BOOL.fieldOf("eyes").xmap(TarantulaDropCount::new, TarantulaDropCount::eyes);
    public static final DeferredRegister<MapCodec<? extends LootItemFunction>> TYPES = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, Erebus.MODID);

    static {
        TYPES.register("tarantula_drop_count", () -> CODEC);
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
        looting = Math.max(0, looting);
        stack.setCount(eyes ? context.getRandom().nextInt(2) + looting : 1 + context.getRandom().nextInt(2 + looting));
        return stack;
    }
}
