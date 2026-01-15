package erebus.block.types;

import erebus.registries.blocks.ModBlocks;
import erebus.utils.IErebusEnum;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum EnumCrateType implements IErebusEnum {
    DEFAULT,
    BTL,
    BTR,
    BBL,
    BBR,
    TTL,
    TTR,
    TBL,
    TBR;

    @Override
    public ItemStack createStack(int count) {
        return new ItemStack(ModBlocks.BAMBOO_CRATE.get(), count);
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
