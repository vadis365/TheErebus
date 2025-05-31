package erebus.block.types;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum EnumTorchBlockHalf implements StringRepresentable {
    UPPER, LOWER;

    @Override
    public @NotNull String getSerializedName() {
        return this == UPPER ? "upper" : "lower";
    }
}
