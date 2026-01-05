package erebus.network.client;

import erebus.Erebus;
import erebus.block.entity.LightningAltarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.joml.Vector3f;

public record LightningAltarRenderPacket(int xPos, int yPos, int zPos, Vector3f targetVector) implements CustomPacketPayload {

	public static final Type<LightningAltarRenderPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "altar_lightning_render"));
	public static final StreamCodec<RegistryFriendlyByteBuf, LightningAltarRenderPacket> STREAM_CODEC = StreamCodec.composite(
					ByteBufCodecs.INT,
					LightningAltarRenderPacket::xPos,
					ByteBufCodecs.INT,
					LightningAltarRenderPacket::yPos,
					ByteBufCodecs.INT,
					LightningAltarRenderPacket::zPos,
					ByteBufCodecs.VECTOR3F,
					LightningAltarRenderPacket::targetVector,
					LightningAltarRenderPacket::new
					);


	public static void handle(LightningAltarRenderPacket message, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			Level level = Minecraft.getInstance().level;
			BlockEntity tile = level.getBlockEntity(new BlockPos(message.xPos, message.yPos, message.zPos));
			if (tile instanceof LightningAltarBlockEntity altar) {
				//TODO THIS IS TEMPORARY UNTIL NEW RENDER IS MADE
				// altar.targetVector = message.targetVector;
				for (int particles = 0; particles < 40; particles++) {
					float offsetLen = level.random.nextFloat();
					Vector3f offset = new Vector3f(
							message.targetVector.x * offsetLen + level.random.nextFloat() * 0.2f - 0.1f,
							message.targetVector.y * offsetLen + level.random.nextFloat() * 0.2f - 0.1f,
							message.targetVector.z * offsetLen + level.random.nextFloat() * 0.2f - 0.1f);

					float vx = (level.random.nextFloat() * 0.5f - 0.25f) * 0.00125f;
					float vy = (level.random.nextFloat() * 0.5f - 0.25f) * 0.00125f;
					float vz = (level.random.nextFloat() * 0.5f - 0.25f) * 0.00125f;
					level.addParticle(ParticleTypes.ELECTRIC_SPARK, false, message.xPos + 0.5D + offset.x, message.yPos + 1.6D + offset.y, message.zPos + 0.5D + offset.z, vx, vy, vz);
				}
			}
		});
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
