package erebus.network.server;

import erebus.Erebus;
import erebus.events.GliderFlightHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record GliderControls(boolean gliding, boolean powered) implements CustomPacketPayload {
    public static final Type<GliderControls> TYPE = new Type<>(Erebus.prefix("glider_controls"));
    public static final StreamCodec<RegistryFriendlyByteBuf, GliderControls> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, GliderControls::gliding, ByteBufCodecs.BOOL, GliderControls::powered, GliderControls::new);

    public static void handle(GliderControls message, IPayloadContext context) {
        context.enqueueWork(() -> message.apply(context.player()));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void apply(Player player) {
        GliderFlightHandler.controls(player, gliding, powered);
    }
}
