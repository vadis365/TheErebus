package erebus.item.shield.type;

import erebus.Erebus;
import erebus.item.shield.IShieldType;
import erebus.registries.item.ModItems;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;

public class JadeShieldType implements IShieldType {
    @Override
    public ItemStack getShieldItem() {
        return new ItemStack(ModItems.JADE_SHIELD.get());
    }

    @Override
    public ItemStack getRepairItem() {
        return new ItemStack(ModItems.JADE.get());
    }

    @Override
    public Identifier getTexture() {
        return Erebus.prefix("textures/item/jade_shield.png");
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
