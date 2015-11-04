package erebus.network.server;

import erebus.inventory.ContainerColossalCrate;
import erebus.network.AbstractServerPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class S00ColossalCratePage extends AbstractServerPacket {

	private byte page;

	public S00ColossalCratePage() {
	}

	public S00ColossalCratePage(int page) {
		this.page = (byte) page;
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeByte(page);
	}

	@Override
	public void read(ByteBuf buffer) {
		page = buffer.readByte();
	}

	@Override
	protected void handle(World world, EntityPlayerMP player) {
		if (player.openContainer instanceof ContainerColossalCrate)
			((ContainerColossalCrate) player.openContainer).changePage(page);
	}
}