package erebus.item;

import erebus.registries.data.ModToolMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WaspSwordItem extends Item {
    public WaspSwordItem() {
        super(new Item.Properties().sword(ModToolMaterials.WASP_SWORD, 6, -1));
    }

    @Override
    public void hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
    }
}
