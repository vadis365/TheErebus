package erebus.registries.network;

import erebus.Erebus;
import erebus.network.client.ParticlePacket;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class ModNetwork {
	 public static void register(final RegisterPayloadHandlersEvent event) {
		 event.registrar(Erebus.MODID)
		 .playToClient(ParticlePacket.TYPE, ParticlePacket.STREAM_CODEC, ParticlePacket::handle);
		// .playToServer(ColossalCratePage.class, ColossalCratePage.class, 1, Side.SERVER)
		// .playToServer(PacketBeetleDig.class, PacketBeetleDig.class, 2, Side.SERVER)
		// .playToServer(PacketBeetleRamAttack.class, PacketBeetleRamAttack.class, 3, Side.SERVER)
		//	.playToClient(PacketAntiVenom.class, PacketAntiVenom.class, 4, Side.CLIENT)
		//	.playToServer(PacketGlider.class, PacketGlider.class, 5, Side.SERVER)
		//	.playToServer(PacketGliderPowered.class, PacketGliderPowered.class, 6, Side.SERVER)
		//	.playToClient(PacketBones.class, PacketBones.class, 7, Side.CLIENT)
		//	.playToClient(MessageSyncEntityCapabilities.class, MessageSyncEntityCapabilities.class, 8, Side.CLIENT)
		//	.playToClient(PacketOfferingAltar.class, PacketOfferingAltar.class, 9, Side.CLIENT)
		//	.playToClient(PacketOfferingAltarTimer.class, PacketOfferingAltarTimer.class, 10, Side.CLIENT)
		//	.playToClient(PacketSmoothieMakerGUI.class, PacketSmoothieMakerGUI.class, 11, Side.CLIENT)
		//	.playToClient(PacketAltarAnimationTimer.class, PacketAltarAnimationTimer.class, 12, Side.CLIENT);
	 }

}
