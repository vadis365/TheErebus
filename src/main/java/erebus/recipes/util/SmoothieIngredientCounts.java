package erebus.recipes.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public record SmoothieIngredientCounts(int fluidCount, int itemCount) {
    public static final MapCodec<SmoothieIngredientCounts> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("fluid_count").forGetter(SmoothieIngredientCounts::fluidCount),
            Codec.INT.fieldOf("item_count").forGetter(SmoothieIngredientCounts::itemCount)
    ).apply(instance, SmoothieIngredientCounts::new));

    public static final StreamCodec<FriendlyByteBuf, SmoothieIngredientCounts> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public @NotNull SmoothieIngredientCounts decode(FriendlyByteBuf buf) {
            return new SmoothieIngredientCounts(buf.readVarInt(), buf.readVarInt());
        }

        @Override
        public void encode(FriendlyByteBuf buf, SmoothieIngredientCounts counts) {
            buf.writeVarInt(counts.fluidCount());
            buf.writeVarInt(counts.itemCount());
        }
    };
}
