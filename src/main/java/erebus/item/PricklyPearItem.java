package erebus.item;

import erebus.Erebus;
import erebus.registries.item.ModFoods;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class PricklyPearItem extends Item {
    public PricklyPearItem() {
        super(new Properties().food(ModFoods.PRICKLY_PEAR_RAW)
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("prickly_pear_raw"))));
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity user) {
        var result = super.finishUsingItem(stack, level, user);
        if (level instanceof ServerLevel server) user.hurtServer(server, user.damageSources().cactus(), 1);
        return result;
    }
}
