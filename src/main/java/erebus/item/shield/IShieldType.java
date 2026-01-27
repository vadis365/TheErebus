package erebus.item.shield;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;

public interface IShieldType {
    ItemStack getShieldItem();

    ItemStack getRepairItem();

    Identifier getTexture();

    SoundEvent getHitSound();

    SoundEvent getBreakSound();
}
