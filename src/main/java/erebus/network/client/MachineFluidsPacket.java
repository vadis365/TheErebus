package erebus.network.client;

import erebus.Erebus;
import erebus.inventory.server.BlenderMenu;
import erebus.inventory.server.LiquifierMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public record MachineFluidsPacket(int menuId, List<FluidStack> fluids) implements CustomPacketPayload {
    public static final Type<MachineFluidsPacket> TYPE = new Type<>(Erebus.prefix("machine_fluids"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MachineFluidsPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, MachineFluidsPacket::menuId,
            FluidStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list(4)), MachineFluidsPacket::fluids,
            MachineFluidsPacket::new);

    public static void handle(MachineFluidsPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            AbstractContainerMenu menu = context.player().containerMenu;
            FluidStacksResourceHandler tanks = switch (menu) {
                case BlenderMenu blender -> blender.tanks;
                case LiquifierMenu liquifier -> liquifier.tank;
                default -> null;
            };
            if (menu.containerId == packet.menuId && tanks != null && packet.fluids.size() == tanks.size()) {
                for (int tank = 0; tank < tanks.size(); tank++) {
                    var stack = packet.fluids.get(tank);
                    tanks.set(tank, FluidResource.of(stack), stack.getAmount());
                }
            }
        });
    }

    public static List<FluidStack> sendChanges(ServerPlayer player, int menuId, FluidStacksResourceHandler tanks, List<FluidStack> previous) {
        List<FluidStack> current = new ArrayList<>();
        boolean changed = previous == null;
        for (int tank = 0; tank < tanks.size(); tank++) {
            var stack = tanks.getResource(tank).toStack(tanks.getAmountAsInt(tank));
            current.add(stack);
            changed |= previous != null && !FluidStack.matches(stack, previous.get(tank));
        }
        if (changed) PacketDistributor.sendToPlayer(player, new MachineFluidsPacket(menuId, current));
        return changed ? current : previous;
    }

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
