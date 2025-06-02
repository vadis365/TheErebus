package erebus.registries.network;

import erebus.Erebus;
import erebus.network.client.*;
import erebus.network.server.ColossalCratePage;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class ModNetwork {
	 public static void register(final RegisterPayloadHandlersEvent event) {
		 event.registrar(Erebus.MODID)
				 .playToClient(LightningAltarRenderPacket.TYPE, LightningAltarRenderPacket.STREAM_CODEC, LightningAltarRenderPacket::handle)
				 .playToClient(AltarAnimatonTimerPacket.TYPE, AltarAnimatonTimerPacket.STREAM_CODEC, AltarAnimatonTimerPacket::handle)
				 .playToClient(OfferingAltarTimerPacket.TYPE, OfferingAltarTimerPacket.STREAM_CODEC, OfferingAltarTimerPacket::handle)
				 .playToClient(OfferingAltarNBTPacket.TYPE, OfferingAltarNBTPacket.STREAM_CODEC, OfferingAltarNBTPacket::handle)
				 .playToClient(PreservedBlockNBTPacket.TYPE, PreservedBlockNBTPacket.STREAM_CODEC, PreservedBlockNBTPacket::handle)
				 .playToClient(ParticlePacket.TYPE, ParticlePacket.STREAM_CODEC, ParticlePacket::handle)
				 .playToClient(AntlionParticlePacket.TYPE, AntlionParticlePacket.STREAM_CODEC, AntlionParticlePacket::handle)
				 .playToServer(ColossalCratePage.TYPE, ColossalCratePage.STREAM_CODEC, ColossalCratePage::handle);
		 // .playToServer(PacketBeetleDig.class, PacketBeetleDig.class, 2, Side.SERVER)
		 // .playToServer(PacketBeetleRamAttack.class, PacketBeetleRamAttack.class, 3, Side.SERVER)
		 //	.playToClient(PacketAntiVenom.class, PacketAntiVenom.class, 4, Side.CLIENT)
		 //	.playToServer(PacketGlider.class, PacketGlider.class, 5, Side.SERVER)
		 //	.playToServer(PacketGliderPowered.class, PacketGliderPowered.class, 6, Side.SERVER)
		 //	.playToClient(PacketBones.class, PacketBones.class, 7, Side.CLIENT)
		 //	.playToClient(MessageSyncEntityCapabilities.class, MessageSyncEntityCapabilities.class, 8, Side.CLIENT)
		 //
		 //	.playToClient(OfferingAltarTimerPacket.class, PacketOfferingAltarTimer.class, 10, Side.CLIENT)
		 //	.playToClient(PacketSmoothieMakerGUI.class, PacketSmoothieMakerGUI.class, 11, Side.CLIENT)
		 //	.playToClient(PacketAltarAnimationTimer.class, PacketAltarAnimationTimer.class, 12, Side.CLIENT);
	 }

}
