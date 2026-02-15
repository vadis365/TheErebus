package erebus.network.client;

import erebus.Erebus;
import erebus.block.entity.OfferingAltarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record OfferingAltarNBTPacket(int xPos, int yPos, int zPos, CompoundTag nbt) implements CustomPacketPayload {
	public static final Type<OfferingAltarNBTPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Erebus.MODID, "offering_altar_nbt"));
	public static final StreamCodec<RegistryFriendlyByteBuf, OfferingAltarNBTPacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT,
			OfferingAltarNBTPacket::xPos,
			ByteBufCodecs.INT,
			OfferingAltarNBTPacket::yPos,
			ByteBufCodecs.INT,
			OfferingAltarNBTPacket::zPos,
			ByteBufCodecs.COMPOUND_TAG,
			OfferingAltarNBTPacket::nbt,
			OfferingAltarNBTPacket::new
			);

	public static void handle(OfferingAltarNBTPacket message, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			Level level = Minecraft.getInstance().level;
			if (level == null) return;
			BlockEntity tile = level.getBlockEntity(new BlockPos(message.xPos, message.yPos, message.zPos));
			if (tile instanceof OfferingAltarBlockEntity altar) {
				altar.loadCustomOnly(message.nbt);
			}
		});
	}

		@Override
		public Type<? extends CustomPacketPayload> type() {
			return TYPE;
		}
}
