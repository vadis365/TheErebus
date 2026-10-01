package erebus.network.server;

import erebus.Erebus;
import erebus.entity.RhinoBeetle;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jspecify.annotations.NonNull;

public record BeetleRamAttack(boolean active) implements CustomPacketPayload {
    public static final Type<BeetleRamAttack> TYPE = new Type<>(Erebus.prefix("beetle_ram_attack"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BeetleRamAttack> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, BeetleRamAttack::active, BeetleRamAttack::new);

    public static void handle(BeetleRamAttack message, IPayloadContext context) {
        context.enqueueWork(() -> message.apply(context.player()));
    }

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void apply(Player player) {
        if (player.isAlive() && !player.isSpectator() && player.getVehicle() instanceof RhinoBeetle beetle && beetle.getControllingPassenger() == player) beetle.setRamming(active);
    }
}
