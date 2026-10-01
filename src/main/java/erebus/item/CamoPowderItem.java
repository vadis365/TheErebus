package erebus.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.jspecify.annotations.NonNull;

public class CamoPowderItem extends InstantEffectItem {
    public CamoPowderItem() {
        super("camo_powder", MobEffects.INVISIBILITY, 280, 1, 3, true);
    }

    @Override
    public @NonNull InteractionResult onItemUseFirst(@NonNull ItemStack stack, UseOnContext context) {
        return context.getPlayer() == null ? InteractionResult.PASS : use(context.getLevel(), context.getPlayer(), context.getHand());
    }
}
