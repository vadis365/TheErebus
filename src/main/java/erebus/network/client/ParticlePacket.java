package erebus.network.client;


import erebus.Erebus;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ParticlePacket(Byte particleType, double xPos, double yPos, double zPos) implements CustomPacketPayload {
	public static final Type<ParticlePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "erebus_particle"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ParticlePacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.BYTE,
			ParticlePacket::particleType, 
			ByteBufCodecs.DOUBLE,
			ParticlePacket::xPos,
			ByteBufCodecs.DOUBLE,
			ParticlePacket::yPos,
			ByteBufCodecs.DOUBLE,
			ParticlePacket::zPos,
			ParticlePacket::new
		);

	public static void handle(ParticlePacket message, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			ClientParticlePackets.spawnParticles(message.particleType, message.xPos, message.yPos, message.zPos, 0D, 0D, 0D);
		});
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}