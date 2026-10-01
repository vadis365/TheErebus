package erebus.registries.network;

import erebus.Erebus;
import erebus.network.client.*;
import erebus.network.server.BeetleDig;
import erebus.network.server.BeetleRamAttack;
import erebus.network.server.ColossalCratePage;
import erebus.network.server.GliderControls;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class ModNetwork {
    public static void register(final RegisterPayloadHandlersEvent event) {
        event.registrar(Erebus.MODID)
                .playToClient(AntiVenomPacket.TYPE, AntiVenomPacket.STREAM_CODEC, AntiVenomPacket::handle)
                .playToClient(MachineFluidsPacket.TYPE, MachineFluidsPacket.STREAM_CODEC, MachineFluidsPacket::handle)
                .playToClient(LightningAltarRenderPacket.TYPE, LightningAltarRenderPacket.STREAM_CODEC, LightningAltarRenderPacket::handle)
                .playToClient(AltarAnimationTimerPacket.TYPE, AltarAnimationTimerPacket.STREAM_CODEC, AltarAnimationTimerPacket::handle)
                .playToClient(OfferingAltarTimerPacket.TYPE, OfferingAltarTimerPacket.STREAM_CODEC, OfferingAltarTimerPacket::handle)
                .playToClient(OfferingAltarNBTPacket.TYPE, OfferingAltarNBTPacket.STREAM_CODEC, OfferingAltarNBTPacket::handle)
                .playToClient(PreservedBlockNBTPacket.TYPE, PreservedBlockNBTPacket.STREAM_CODEC, PreservedBlockNBTPacket::handle)
                .playToClient(ParticlePacket.TYPE, ParticlePacket.STREAM_CODEC, ParticlePacket::handle)
                .playToClient(AntlionParticlePacket.TYPE, AntlionParticlePacket.STREAM_CODEC, AntlionParticlePacket::handle)
                .playToServer(GliderControls.TYPE, GliderControls.STREAM_CODEC, GliderControls::handle)
                .playToServer(BeetleDig.TYPE, BeetleDig.STREAM_CODEC, BeetleDig::handle)
                .playToServer(BeetleRamAttack.TYPE, BeetleRamAttack.STREAM_CODEC, BeetleRamAttack::handle)
                .playToServer(ColossalCratePage.TYPE, ColossalCratePage.STREAM_CODEC, ColossalCratePage::handle);
    }

}
