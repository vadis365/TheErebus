package erebus.network.client;


import erebus.Erebus;
import erebus.client.particle.ClientParticles;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AntlionParticlePacket(int blockType, double xPos, double yPos, double zPos, double offSetRadius, boolean reverse) implements CustomPacketPayload {
	public static final Type<AntlionParticlePacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Erebus.MODID, "antlion_particle"));
	public static final StreamCodec<RegistryFriendlyByteBuf, AntlionParticlePacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT,
			AntlionParticlePacket::blockType, 
			ByteBufCodecs.DOUBLE,
			AntlionParticlePacket::xPos,
			ByteBufCodecs.DOUBLE,
			AntlionParticlePacket::yPos,
			ByteBufCodecs.DOUBLE,
			AntlionParticlePacket::zPos,
			ByteBufCodecs.DOUBLE,
			AntlionParticlePacket::offSetRadius,
			ByteBufCodecs.BOOL,
			AntlionParticlePacket::reverse,
			AntlionParticlePacket::new
		);

	public static void handle(AntlionParticlePacket message, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			ClientParticles.spawnAntlionParticles(message.blockType, message.xPos, message.yPos, message.zPos, message.offSetRadius, message.reverse, 0D, 0D, 0D);
		});
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
