package erebus.block.types;

import erebus.registries.blocks.providers.PlantBlocks;
import erebus.utils.IErebusEnum;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum EnumGlowshroomPart implements IErebusEnum {
    MAIN,
    DOWN_1,
    DOWN_2,
    DOWN_3,
    NORTH_1,
    NORTH_2,
    NORTH_3,
    SOUTH_1,
    SOUTH_2,
    SOUTH_3,
    EAST_1,
    EAST_2,
    EAST_3,
    WEST_1,
    WEST_2,
    WEST_3;

    @Override
    public ItemStack createStack(int count) {
        return new ItemStack(PlantBlocks.GLOWSHROOM_STALK, count);
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
