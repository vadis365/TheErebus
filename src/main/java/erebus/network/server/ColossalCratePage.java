package erebus.network.server;

import erebus.Erebus;
import erebus.inventory.server.ColossalCrateMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ColossalCratePage(int page) implements CustomPacketPayload {

    public static final Type<ColossalCratePage> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Erebus.MODID, "colossal_crate_page"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ColossalCratePage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            ColossalCratePage::page,
            ColossalCratePage::new
    );

    public static void handle(ColossalCratePage message, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            if (ctx.player() instanceof ServerPlayer player) apply(player, message.page());
        });
    }

    public static void apply(ServerPlayer player, int requested) {
        if (requested < 1 || requested > 3 || !player.isAlive()) return;
        if (player.containerMenu instanceof ColossalCrateMenu menu && menu.stillValid(player)) {
            menu.changePage(requested);
            menu.broadcastFullState();
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
