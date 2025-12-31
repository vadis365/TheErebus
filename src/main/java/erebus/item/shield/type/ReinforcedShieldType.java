package erebus.item.shield.type;

import erebus.Erebus;
import erebus.item.shield.IShieldType;
import erebus.registries.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;

public class ReinforcedShieldType implements IShieldType {
    @Override
    public ItemStack getShieldItem() {
        return new ItemStack(ModItems.REIN_EXOSKELETON_SHIELD.get());
    }

    @Override
    public ItemStack getRepairItem() {
        return new ItemStack(ModItems.REINFORCED_PLATE_EXO.get());
    }

    @Override
    public ResourceLocation getTexture() {
        return Erebus.prefix("textures/item/rein_exoskeleton_shield.png");
    }

    @Override
    public SoundEvent getHitSound() {
        return SoundEvents.SHIELD_BLOCK;
    }

    @Override
    public SoundEvent getBreakSound() {
        return SoundEvents.SHIELD_BREAK;
    }
}
