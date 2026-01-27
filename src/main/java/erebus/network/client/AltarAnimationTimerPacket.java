package erebus.network.client;

import erebus.Erebus;
import erebus.block.entity.AltarAbstractBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jspecify.annotations.NonNull;

public record AltarAnimationTimerPacket(int xPos, int yPos, int zPos, int animationTicks) implements CustomPacketPayload {

	public static final Type<AltarAnimationTimerPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Erebus.MODID, "altar_animation_timer"));
	public static final StreamCodec<RegistryFriendlyByteBuf, AltarAnimationTimerPacket> STREAM_CODEC = StreamCodec.composite(
					ByteBufCodecs.INT,
					AltarAnimationTimerPacket::xPos,
					ByteBufCodecs.INT,
					AltarAnimationTimerPacket::yPos,
					ByteBufCodecs.INT,
					AltarAnimationTimerPacket::zPos,
					ByteBufCodecs.INT,
					AltarAnimationTimerPacket::animationTicks,
					AltarAnimationTimerPacket::new
					);


	public static void handle(AltarAnimationTimerPacket message, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			Level level = Minecraft.getInstance().level;
            BlockEntity tile = null;
            if (level != null) {
                tile = level.getBlockEntity(new BlockPos(message.xPos, message.yPos, message.zPos));
            }
            if (tile instanceof AltarAbstractBlockEntity altar) {
				altar.animationTicks = message.animationTicks;
			}
		});
	}

	@Override
	public @NonNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
