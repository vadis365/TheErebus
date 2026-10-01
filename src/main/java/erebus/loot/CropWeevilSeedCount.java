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

public record CropWeevilSeedCount() implements LootItemFunction {
    public static final CropWeevilSeedCount INSTANCE = new CropWeevilSeedCount();
    public static final MapCodec<CropWeevilSeedCount> CODEC = MapCodec.unit(INSTANCE);
    public static final DeferredRegister<MapCodec<? extends LootItemFunction>> TYPES = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, Erebus.MODID);

    static {
        TYPES.register("crop_weevil_seed_count", () -> CODEC);
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
        int fortune = context.getRandom().nextInt(3) + Math.max(0, looting);
        stack.setCount(1 + context.getRandom().nextInt(fortune * 2 + 1));
        return stack;
    }
}
