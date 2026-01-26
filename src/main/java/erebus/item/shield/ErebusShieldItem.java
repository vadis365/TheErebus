package erebus.item.shield;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import org.jspecify.annotations.NonNull;

public class ErebusShieldItem extends ShieldItem {

    private final IShieldType shieldType;

    public ErebusShieldItem(Properties properties, IShieldType shieldType) {
        super(properties);
        this.shieldType = shieldType;
    }

    public IShieldType getShieldType() {
        return shieldType;
    }

    @Override
    public @NonNull Component getHighlightTip(ItemStack stack, @NonNull Component displayName) {
        ErebusShieldItem shield = (ErebusShieldItem) stack.getItem();
        return Component
                .translatable("tooltip.erebus.shield.damage")
                .append("%d/%d".formatted(stack.getDamageValue(), stack.getMaxDamage()))
                .append("tooltip.erebus.shield.repair").append(shield.getShieldType().getRepairItem().getItemName());
    }
}
