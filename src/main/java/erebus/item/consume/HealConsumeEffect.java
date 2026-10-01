package erebus.item.consume;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NonNull;

public record HealConsumeEffect(float amount) implements ConsumeEffect {
    public static final MapCodec<HealConsumeEffect> CODEC = Codec.floatRange(0, 1024).fieldOf("amount").xmap(HealConsumeEffect::new, HealConsumeEffect::amount);
    public static final StreamCodec<RegistryFriendlyByteBuf, HealConsumeEffect> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.FLOAT, HealConsumeEffect::amount, HealConsumeEffect::new);

    public static final DeferredRegister<Type<?>> TYPES = DeferredRegister.create(Registries.CONSUME_EFFECT_TYPE, Erebus.MODID);
    public static final DeferredHolder<Type<?>, Type<HealConsumeEffect>> TYPE = TYPES.register("heal", () -> new Type<>(CODEC, STREAM_CODEC));

    @Override
    public @NonNull Type<HealConsumeEffect> getType() {
        return TYPE.get();
    }

    @Override
    public boolean apply(@NonNull Level level, @NonNull ItemStack stack, LivingEntity user) {
        float before = user.getHealth();
        user.heal(amount);
        return user.getHealth() > before;
    }
}
