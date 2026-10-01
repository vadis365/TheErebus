package erebus.item.shield;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class ErebusShieldItem extends ShieldItem {

    private final IShieldType shieldType;

    public ErebusShieldItem(Properties properties, IShieldType shieldType) {
        super(properties);
        this.shieldType = shieldType;
    }

    @Override
    public void appendHoverText(ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.shield.damage").append("%d/%d".formatted(stack.getDamageValue(), stack.getMaxDamage())));
        lines.accept(Component.translatable("tooltip.erebus.shield.repair").append(shieldType.getRepairItem().getHoverName()));
    }
}
