package erebus.network.client;

import erebus.Erebus;
import erebus.events.AntiVenomHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jspecify.annotations.NonNull;

public record AntiVenomPacket(int seconds) implements CustomPacketPayload {
    public static final Type<AntiVenomPacket> TYPE = new Type<>(Erebus.prefix("anti_venom"));
    public static final StreamCodec<RegistryFriendlyByteBuf, AntiVenomPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, AntiVenomPacket::seconds, AntiVenomPacket::new);

    public static void handle(AntiVenomPacket message, IPayloadContext context) {
        context.player().getPersistentData().putInt(AntiVenomHandler.DURATION,
                Math.clamp(message.seconds, 0, AntiVenomHandler.MAX_SECONDS));
    }

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
