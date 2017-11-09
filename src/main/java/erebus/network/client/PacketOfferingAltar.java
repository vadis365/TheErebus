package erebus.network.client;

import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityOfferingAltar;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOfferingAltar implements IMessage, IMessageHandler<PacketOfferingAltar, IMessage> {

	private int x, y, z;
	private NBTTagCompound nbt;

	public PacketOfferingAltar() {
	}

	public PacketOfferingAltar(int x, int y, int z, NBTTagCompound nbt) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.nbt = nbt;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(PacketOfferingAltar message, MessageContext ctx) {
		World world = FMLClientHandler.instance().getWorldClient();
		if (world == null)
			return null;
		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityPlayerSP player = FMLClientHandler.instance().getClientPlayerEntity();
		if (player == null)
			return null;
		mc.addScheduledTask(new Runnable() {
			public void run() {
				TileEntityOfferingAltar tile = Utils.getTileEntity(world, new BlockPos(message.x, message.y, message.z), TileEntityOfferingAltar.class);
				if (tile != null)
					tile.readFromNBT(message.nbt);
			}
		});
		return null;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		ByteBufUtils.writeTag(buf, nbt);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		nbt = ByteBufUtils.readTag(buf);
	}
}