package erebus.network.client;

import erebus.Erebus;
import erebus.block.entity.OfferingAltarBlockEntity;
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

public record OfferingAltarTimerPacket(int xPos, int yPos, int zPos, int time, boolean isCrafting) implements CustomPacketPayload {

	public static final Type<OfferingAltarTimerPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Erebus.MODID, "offering_altar_timer"));
	public static final StreamCodec<RegistryFriendlyByteBuf, OfferingAltarTimerPacket> STREAM_CODEC = StreamCodec.composite(
					ByteBufCodecs.INT,
					OfferingAltarTimerPacket::xPos,
					ByteBufCodecs.INT,
					OfferingAltarTimerPacket::yPos,
					ByteBufCodecs.INT,
					OfferingAltarTimerPacket::zPos,
					ByteBufCodecs.INT,
					OfferingAltarTimerPacket::time,
					ByteBufCodecs.BOOL,
					OfferingAltarTimerPacket::isCrafting,
					OfferingAltarTimerPacket::new
					);

	public static void handle(OfferingAltarTimerPacket message, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			Level level = Minecraft.getInstance().level;
			BlockEntity tile = level.getBlockEntity(new BlockPos(message.xPos, message.yPos, message.zPos));
			if (tile instanceof OfferingAltarBlockEntity altar) {
				altar.time = message.time;
				altar.isCrafting = message.isCrafting;
			}
		});
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
