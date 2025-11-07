package erebus.network.server;

import erebus.Erebus;
import erebus.block.entity.PetrifiedChestBlockEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ErebusChestOpennessPayload(BlockPos pos, boolean shouldBeOpen) implements CustomPacketPayload {
    public static final Type<ErebusChestOpennessPayload> TYPE = new Type<>(Erebus.prefix("chest_openness"));
    public static final StreamCodec<ByteBuf, ErebusChestOpennessPayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ErebusChestOpennessPayload::pos,
            ByteBufCodecs.BOOL,
            ErebusChestOpennessPayload::shouldBeOpen,
            ErebusChestOpennessPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ErebusChestOpennessPayload payload, IPayloadContext context) {
        if(context.player().level().getBlockEntity(payload.pos()) instanceof PetrifiedChestBlockEntity chest) {
            chest.setShouldBeOpen(payload.shouldBeOpen());
        }
    }
}
