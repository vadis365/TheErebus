package erebus.item.shield.type;

import erebus.Erebus;
import erebus.item.shield.IShieldType;
import erebus.registries.item.ModItems;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;

public class BambooShieldType implements IShieldType {
    @Override
    public ItemStack getShieldItem() {
        return new ItemStack(ModItems.BAMBOO_SHIELD.get());
    }

    @Override
    public ItemStack getRepairItem() {
        return new ItemStack(ModItems.BAMBOO.get());
    }

    @Override
    public Identifier getTexture() {
        return Erebus.prefix("textures/item/bamboo_shield.png");
    }

    @Override
    public SoundEvent getHitSound() {
        return SoundEvents.SHIELD_BLOCK.getDelegate().value();
    }

    @Override
    public SoundEvent getBreakSound() {
        return SoundEvents.SHIELD_BREAK.getDelegate().value();
    }
}
