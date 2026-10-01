package erebus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class SmoothieItem extends Item {
    private final ExtraEffect extraEffect;

    public SmoothieItem(Properties properties, ExtraEffect extraEffect) {
        super(properties);
        this.extraEffect = extraEffect;
    }

    @Override
    public void appendHoverText(ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> lines, @NonNull TooltipFlag flag) {
        var consumable = stack.get(DataComponents.CONSUMABLE);
        if (consumable != null) for (var effect : consumable.onConsumeEffects()) {
            if (effect instanceof ApplyStatusEffectsConsumeEffect status)
                PotionContents.addPotionTooltip(status.effects(), lines, 1, context.tickRate());
            else if (effect instanceof ClearAllStatusEffectsConsumeEffect)
                lines.accept(Component.translatable("erebus.smoothie.effect.milk").withStyle(ChatFormatting.YELLOW));
        }
        String key = switch (extraEffect) {
            case EXTINGUISH -> "extinguish";
            case IGNITE -> "set_fire";
            case SMALL_HEAL, HEAL -> "heal";
            case NONE -> null;
        };
        if (key != null) lines.accept(Component.translatable("erebus.smoothie.effect." + key).withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);
        if (!level.isClientSide()) {
            switch (extraEffect) {
                case EXTINGUISH -> user.clearFire();
                case IGNITE -> user.igniteForSeconds(5);
                case SMALL_HEAL -> user.heal(0.5F);
                case HEAL -> user.heal(1.5F);
                case NONE -> {
                }
            }
        }
        return result;
    }

    public enum ExtraEffect {NONE, EXTINGUISH, IGNITE, SMALL_HEAL, HEAL}
}
