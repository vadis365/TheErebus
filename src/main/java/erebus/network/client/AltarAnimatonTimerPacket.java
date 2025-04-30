package erebus.network.client;

import erebus.Erebus;
import erebus.block.entity.AltarAbstractBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AltarAnimatonTimerPacket(int xPos, int yPos, int zPos, int animationTicks) implements CustomPacketPayload {

	public static final Type<AltarAnimatonTimerPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "altar_animation_timer"));
	public static final StreamCodec<RegistryFriendlyByteBuf, AltarAnimatonTimerPacket> STREAM_CODEC = StreamCodec.composite(
					ByteBufCodecs.INT,
					AltarAnimatonTimerPacket::xPos,
					ByteBufCodecs.INT,
					AltarAnimatonTimerPacket::yPos,
					ByteBufCodecs.INT,
					AltarAnimatonTimerPacket::zPos,
					ByteBufCodecs.INT,
					AltarAnimatonTimerPacket::animationTicks,
					AltarAnimatonTimerPacket::new
					);


	public static void handle(AltarAnimatonTimerPacket message, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			Level level = Minecraft.getInstance().level;
			BlockEntity tile = level.getBlockEntity(new BlockPos(message.xPos, message.yPos, message.zPos));
			if (tile instanceof AltarAbstractBlockEntity altar) {
				altar.animationTicks = message.animationTicks;
			}
		});
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}