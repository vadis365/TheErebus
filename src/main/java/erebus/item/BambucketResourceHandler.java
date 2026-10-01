package erebus.item;

import erebus.registries.ModFluids;
import erebus.registries.item.ModItems;
import net.minecraft.world.item.BucketItem;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.ItemAccessResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public final class BambucketResourceHandler extends ItemAccessResourceHandler<FluidResource> {
    public BambucketResourceHandler(ItemAccess access) {
        super(access, 1);
    }

    @Override
    protected @NonNull FluidResource getResourceFrom(ItemResource item, int index) {
        return item.getItem() instanceof BucketItem bucket ? FluidResource.of(bucket.content) : FluidResource.EMPTY;
    }

    @Override
    protected int getAmountFrom(@NonNull ItemResource item, int index) {
        return getResourceFrom(item, index).isEmpty() ? 0 : FluidType.BUCKET_VOLUME;
    }

    @Override
    protected ItemResource update(@NonNull ItemResource item, int index, @NonNull FluidResource fluid, int amount) {
        if (amount == 0) return ItemResource.of(ModItems.BAMBUCKET.get());
        if (amount != FluidType.BUCKET_VOLUME) return ItemResource.EMPTY;
        if (fluid.is(ModFluids.ANTI_VENOM_STILL.get())) return ItemResource.of(ModItems.ANTI_VENOM_BAMBUCKET.get());
        var stack = fluid.toStack(amount);
        return ItemResource.of(stack.getFluidType().getBucket(stack));
    }

    @Override
    protected int getCapacity(int index, @NonNull FluidResource fluid) {
        Objects.checkIndex(index, size());
        return FluidType.BUCKET_VOLUME;
    }
}
