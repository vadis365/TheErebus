package erebus.network.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class QuakeHammerDataHolder {
    public static final QuakeHammerData DEFAULT = new QuakeHammerData(1);

    public static final Codec<QuakeHammerData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("charge").forGetter(QuakeHammerData::charge)
            ).apply(instance, QuakeHammerData::new)
    );

    public static final StreamCodec<ByteBuf, QuakeHammerData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, QuakeHammerData::charge,
            QuakeHammerData::new
    );
}
