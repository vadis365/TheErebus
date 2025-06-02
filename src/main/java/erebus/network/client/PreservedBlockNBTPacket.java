package erebus.network.client;

import erebus.Erebus;
import erebus.block.entity.PreservedBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PreservedBlockNBTPacket(int x, int y, int z, CompoundTag tag) implements CustomPacketPayload {
    public static final Type<PreservedBlockNBTPacket> TYPE = new Type<>(Erebus.prefix("preserved_block_nbt"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PreservedBlockNBTPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            PreservedBlockNBTPacket::x,
            ByteBufCodecs.INT,
            PreservedBlockNBTPacket::y,
            ByteBufCodecs.INT,
            PreservedBlockNBTPacket::z,
            ByteBufCodecs.COMPOUND_TAG,
            PreservedBlockNBTPacket::tag
            ,PreservedBlockNBTPacket::new
    );

    public static void handle(PreservedBlockNBTPacket message, final IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if(level != null) {
                BlockEntity entity = level.getBlockEntity(new BlockPos(message.x, message.y, message.z));
                if(entity instanceof PreservedBlockEntity preserved)
                    preserved.saveAdditional(message.tag, level.registryAccess());
            }
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
