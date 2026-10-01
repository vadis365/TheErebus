package erebus.network.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record GliderData(boolean gliding, boolean powered, int fuelTicks) {
    public static final GliderData EMPTY = new GliderData(false, false, 0);

    public static final Codec<GliderData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.intRange(0, 79).optionalFieldOf("fuel_ticks", 0).forGetter(GliderData::fuelTicks)
    ).apply(instance, ticks -> new GliderData(false, false, ticks)));

    public static final StreamCodec<ByteBuf, GliderData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, GliderData::gliding, ByteBufCodecs.BOOL, GliderData::powered,
            ByteBufCodecs.VAR_INT, GliderData::fuelTicks, GliderData::new);

    public boolean active() {
        return gliding || powered;
    }
}
