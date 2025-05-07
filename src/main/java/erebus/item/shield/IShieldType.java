package erebus.item.shield;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;

public interface IShieldType {
    ItemStack getShieldItem();

    ItemStack getRepairItem();

    ResourceLocation getTexture();

    SoundEvent getHitSound();

    SoundEvent getBreakSound();
}
