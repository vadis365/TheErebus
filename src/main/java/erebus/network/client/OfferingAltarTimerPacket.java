package erebus.network.client;

import erebus.Erebus;
import erebus.block.entity.OfferingAltarBlockEntity;
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

public record OfferingAltarTimerPacket(int xPos, int yPos, int zPos, int time) implements CustomPacketPayload {

	public static final Type<OfferingAltarTimerPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "offering_altar_timer"));
	public static final StreamCodec<RegistryFriendlyByteBuf, OfferingAltarTimerPacket> STREAM_CODEC = StreamCodec.composite(
					ByteBufCodecs.INT,
					OfferingAltarTimerPacket::xPos,
					ByteBufCodecs.INT,
					OfferingAltarTimerPacket::yPos,
					ByteBufCodecs.INT, OfferingAltarTimerPacket::zPos,
					ByteBufCodecs.INT, OfferingAltarTimerPacket::time,
					OfferingAltarTimerPacket::new
					);

	public static void handle(OfferingAltarTimerPacket message, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			Level level = Minecraft.getInstance().level;
			BlockEntity tile = level.getBlockEntity(new BlockPos(message.xPos, message.yPos, message.zPos));
			if (tile instanceof OfferingAltarBlockEntity)
				((OfferingAltarBlockEntity) tile).time = message.time;
		});
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}