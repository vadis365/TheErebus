package erebus.item.shield;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        ErebusShieldItem shield = (ErebusShieldItem) stack.getItem();

        tooltipComponents.add(Component.translatable("tooltip.erebus.shield.damage").append("%d/%d".formatted(stack.getDamageValue(), stack.getMaxDamage())));
        tooltipComponents.add(Component.translatable("tooltip.erebus.shield.repair").append("%s".formatted(shield.getShieldType().getRepairItem().getDescriptionId())));
    }
}
