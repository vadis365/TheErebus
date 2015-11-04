package erebus.network.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import erebus.inventory.ContainerSmoothieMaker;
import erebus.network.AbstractClientPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PacketSmoothieMakerGUI extends AbstractClientPacket {

	private NBTTagCompound nbt;

	public PacketSmoothieMakerGUI() {
	}

	public PacketSmoothieMakerGUI(NBTTagCompound nbt) {
		this();
		this.nbt = nbt;
	}

	@Override
	protected void handle(World world, EntityClientPlayerMP player) {
		Container container = player.openContainer;
		if (container != null && container instanceof ContainerSmoothieMaker)
			((ContainerSmoothieMaker) container).readPacketData(nbt);
	}

	@Override
	public void write(ByteBuf buffer) {
		ByteBufUtils.writeTag(buffer, nbt);
	}

	@Override
	public void read(ByteBuf buffer) {
		nbt = ByteBufUtils.readTag(buffer);
	}
}