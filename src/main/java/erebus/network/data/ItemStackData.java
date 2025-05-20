package erebus.network.data;

import com.mojang.serialization.Codec;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public class ItemStackData {
    private final ItemStack inner;
    public static final Codec<ItemStackData> CODEC = ItemStack.OPTIONAL_CODEC.xmap(ItemStackData::of, ItemStackData::inner);
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackData> STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC.map(ItemStackData::of, ItemStackData::inner);

    public static final ItemStackData EMPTY = ItemStackData.of(ItemStack.EMPTY);

    public static ItemStackData of(ItemStack stack) {
        return new ItemStackData(stack);
    }

    private	ItemStack inner() {
        return inner;
    }

    private ItemStackData(ItemStack inner) {
        this.inner = inner;
    }

    public ItemStack get() {
        return inner.copy();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemStack other) {
            return ItemStack.matches(inner, other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return inner.hashCode();
    }
}
