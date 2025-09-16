package erebus.network.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class SprintLeggingsDataHolder {
    public static final SprintLeggingsData DEFAULT = new SprintLeggingsData(1);

    public static final Codec<SprintLeggingsData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("tier").forGetter(SprintLeggingsData::tier)
            ).apply(instance, SprintLeggingsData::new)
    );

    public static final StreamCodec<ByteBuf, SprintLeggingsData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, SprintLeggingsData::tier,
            SprintLeggingsData::new
    );
}
