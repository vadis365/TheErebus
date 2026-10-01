package erebus.network.server;

import erebus.Erebus;
import erebus.entity.StagBeetle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record BeetleDig(BlockPos pos) implements CustomPacketPayload {
    public static final Type<BeetleDig> TYPE = new Type<>(Erebus.prefix("beetle_dig"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BeetleDig> STREAM_CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, BeetleDig::pos, BeetleDig::new);

    public static void handle(BeetleDig message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) message.apply(player);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void apply(ServerPlayer player) {
        if (!player.isAlive() || player.isSpectator() || !(player.getVehicle() instanceof StagBeetle beetle)
                || !beetle.isAlive() || beetle.getControllingPassenger() != player) return;
        var level = player.level();
        var eye = player.getEyePosition();
        if (eye.distanceToSqr(pos.getCenter()) > 36 || !level.hasChunkAt(pos)) return;
        var hit = level.clip(new ClipContext(eye, eye.add(player.getLookAngle().scale(5)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        if (hit.getType() != HitResult.Type.BLOCK || !hit.getBlockPos().equals(pos)) return;
        int facing = Mth.floor(beetle.getYRot() * 4 / 360 + 0.5) & 3;
        boolean horizontal = hit.getDirection().getAxis() == Direction.Axis.Y;
        boolean widenX = horizontal || facing == 0 || facing == 2;
        boolean widenZ = horizontal || !widenX;
        boolean broke = false;
        for (var cursor : BlockPos.betweenClosed(pos.offset(widenX ? -1 : 0, horizontal ? 0 : -1, widenZ ? -1 : 0),
                pos.offset(widenX ? 1 : 0, horizontal ? 0 : 1, widenZ ? 1 : 0))) {
            var target = cursor.immutable();
            if (!level.isInWorldBounds(target) || !level.hasChunkAt(target) || !level.mayInteract(player, target)
                    || player.blockActionRestricted(level, target, player.gameMode.getGameModeForPlayer())) continue;
            var state = level.getBlockState(target);
            float hardness = state.getDestroySpeed(level, target);
            if (state.isAir() || hardness < 0 || hardness > 10) continue;
            if (CommonHooks.fireBlockBreak(level, player.gameMode.getGameModeForPlayer(), player, target, state).isCanceled()) continue;
            broke |= level.destroyBlock(target, !player.preventsBlockDrops(), player);
        }
        if (broke) beetle.startDigAnimation(hit.getLocation().y, player.getY());
    }
}
