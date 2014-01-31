package erebus.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;

import erebus.entity.EntityRhinoBeetle;
import erebus.network.IPacket;

public class PacketBeetleRamAttack implements IPacket {

	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		boolean ramming = data.readBoolean();

		if (player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle) {
			((EntityRhinoBeetle) player.ridingEntity).setRamAttack(ramming);
				return;
		}
	}
}