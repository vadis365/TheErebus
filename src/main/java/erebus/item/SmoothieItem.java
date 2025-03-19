package erebus.item;

import erebus.registries.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;

public class SmoothieItem extends Item {
    public SmoothieItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (stack.is(ModItems.GREEN_GIANT)) {
            livingEntity.removeEffectsCuredBy(EffectCures.MILK);
        }

        if (stack.is(ModItems.GIVIN_ME_THE_BLUES)) {
            livingEntity.extinguishFire();
        }

        if (stack.is(ModItems.HOT_HOT_BABY)) {
            livingEntity.igniteForTicks(5);
        }

        if (stack.is(ModItems.LIQUID_GOLD)) {
            livingEntity.heal(0.5F);
        }

        if (stack.is(ModItems.BRYUFS_BREW)) {
            livingEntity.heal(1.5F);
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }
}
