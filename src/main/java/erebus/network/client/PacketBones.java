package erebus.network.client;

import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityBones;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketBones implements IMessage, IMessageHandler<PacketBones, IMessage> {
	
	private int x, y, z;
	private String name;

	public PacketBones() {
	}

	public PacketBones(int x, int y, int z, String name) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.name = name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(PacketBones message, MessageContext ctx) {
		World world = FMLClientHandler.instance().getWorldClient();
		if (world == null)
			return null;
		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityPlayerSP player = FMLClientHandler.instance().getClientPlayerEntity();
		if (player == null)
			return null;
		mc.addScheduledTask(new Runnable() {
			public void run() {
				TileEntityBones tile = Utils.getTileEntity(world, new BlockPos(message.x, message.y, message.z), TileEntityBones.class);
				if (tile != null)
					tile.setOwner(message.name);
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		name = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		ByteBufUtils.writeUTF8String(buf, name);
	}

}
