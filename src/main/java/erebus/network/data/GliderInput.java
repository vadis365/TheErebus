package erebus.network.data;

import net.minecraft.world.item.Item;
import org.jspecify.annotations.Nullable;

public record GliderInput(@Nullable Item item, boolean gliding, boolean powered) {
    public static final GliderInput NONE = new GliderInput(null, false, false);
}
