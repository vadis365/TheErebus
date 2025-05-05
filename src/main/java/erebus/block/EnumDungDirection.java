package erebus.block;

import erebus.registries.ModBlocks;
import erebus.utils.IErebusEnum;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum EnumDungDirection implements IErebusEnum {
    DOWN_NORTH,
    DOWN_SOUTH,
    DOWN_WEST,
    DOWN_EAST,
    UP_NORTH,
    UP_SOUTH,
    UP_WEST,
    UP_EAST,
    NORTH,
    SOUTH,
    WEST,
    EAST;

    @Override
    public ItemStack createStack(int count) {
        return new ItemStack(ModBlocks.DUNG_SPAWNER_BOT_FLY, count);
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
