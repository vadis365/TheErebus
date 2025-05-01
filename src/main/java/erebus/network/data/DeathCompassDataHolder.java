package erebus.network.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class DeathCompassDataHolder {
    public static final DeathCompassData DEFAULT = new DeathCompassData(0, 0, 0);

    public static final Codec<DeathCompassData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("x").forGetter(DeathCompassData::x),
                    Codec.INT.fieldOf("y").forGetter(DeathCompassData::y),
                    Codec.INT.fieldOf("z").forGetter(DeathCompassData::z)
            ).apply(instance, DeathCompassData::new)
    );

    public static final StreamCodec<ByteBuf, DeathCompassData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, DeathCompassData::x,
            ByteBufCodecs.INT, DeathCompassData::y,
            ByteBufCodecs.INT, DeathCompassData::z,
            DeathCompassData::new
    );
}
