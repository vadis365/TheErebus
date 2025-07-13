package erebus.utils;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

public class NamedFluidTank extends FluidTank {
    private final String name;

    public NamedFluidTank(String name, int capacity) {
        super(capacity);
        this.name = name;
    }

    @Override
    public @NotNull FluidTank readFromNBT(HolderLookup.@NotNull Provider provider, CompoundTag tag) {
        fluid = FluidStack.parseOptional(provider, tag.getCompound(name));
        return this;
    }

    @Override
    public @NotNull CompoundTag writeToNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag tag) {
        if(!fluid.isEmpty()) {
            tag.put(name, fluid.save(provider));
        }

        return tag;
    }
}
