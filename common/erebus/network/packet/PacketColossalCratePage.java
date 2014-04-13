package erebus.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import erebus.inventory.ContainerColossalCrate;
import erebus.network.PacketTypeHandler;

public class PacketColossalCratePage extends CustomPacket {

	private int page;

	public PacketColossalCratePage() {
		super(PacketTypeHandler.COLOSSAL_CRATE_PAGE);
	}

	public PacketColossalCratePage(int page) {
		this();
		this.page = page;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		page = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(page);
	}

	@Override
	public void execute(World world, EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			if (playerMP.openContainer instanceof ContainerColossalCrate) {
				ContainerColossalCrate crate = (ContainerColossalCrate) playerMP.openContainer;
				crate.changePage(page);
			}
		}
	}
}