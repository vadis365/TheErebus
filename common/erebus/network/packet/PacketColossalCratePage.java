package erebus.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;

import erebus.inventory.ContainerColossalCrate;
import erebus.network.IPacket;

public class PacketColossalCratePage implements IPacket {

	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		if (player instanceof EntityPlayerMP) {
			int page = data.readInt();
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			if (playerMP.openContainer instanceof ContainerColossalCrate) {
				ContainerColossalCrate crate = (ContainerColossalCrate) playerMP.openContainer;
				crate.changePage(page);
			}
		}
	}
}
