package erebus.block.types;

import erebus.registries.blocks.ModBlocks;
import erebus.utils.IErebusEnum;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum EnumAmberType implements IErebusEnum {
    AMBER, AMBER_GLASS;

    @Override
    public ItemStack createStack(int count) {
        if (this == AMBER_GLASS) {
            return new ItemStack(ModBlocks.PRESERVED_AMBER_GLASS.get(), count);
        } else {
            return new ItemStack(ModBlocks.PRESERVED_AMBER.get(), count);
        }
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase();
    }
}
