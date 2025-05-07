package erebus.registries.data;

import com.mojang.serialization.Codec;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidContents {
    private final FluidStack inner;
    public static final Codec<FluidContents> CODEC = FluidStack.OPTIONAL_CODEC.xmap(FluidContents::of, FluidContents::inner);
    public static final StreamCodec<RegistryFriendlyByteBuf, FluidContents> STREAM_CODEC = FluidStack.OPTIONAL_STREAM_CODEC.map(FluidContents::of, FluidContents::inner);

    public static final FluidContents EMPTY = FluidContents.of(FluidStack.EMPTY);

    public static FluidContents of(FluidStack stack) {
        return new FluidContents(stack);
    }

    private	FluidStack inner() {
        return inner;
    }

    private FluidContents(FluidStack inner) {
        this.inner = inner;
    }

    public FluidStack get() {
        return inner.copy();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FluidStack other) {
            return FluidStack.matches(inner, other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return inner.hashCode();
    }
}
