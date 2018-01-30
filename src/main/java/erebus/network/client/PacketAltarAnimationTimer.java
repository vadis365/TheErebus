package erebus.network.client;

import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltar;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketAltarAnimationTimer implements IMessage, IMessageHandler<PacketAltarAnimationTimer, IMessage> {

	private int x, y, z, animationTicks;

	public PacketAltarAnimationTimer() {
	}

	public PacketAltarAnimationTimer(int x, int y, int z, int time) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.animationTicks = time;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(PacketAltarAnimationTimer message, MessageContext ctx) {
		World world = FMLClientHandler.instance().getWorldClient();
		if (world == null)
			return null;
		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityPlayerSP player = FMLClientHandler.instance().getClientPlayerEntity();
		if (player == null)
			return null;
		mc.addScheduledTask(new Runnable() {
			public void run() {
				TileEntityErebusAltar tile = Utils.getTileEntity(world, new BlockPos(message.x, message.y, message.z), TileEntityErebusAltar.class);
				if (tile != null)
					tile.animationTicks = message.animationTicks;
			}
		});
		return null;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(animationTicks);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		animationTicks = buf.readInt();
	}
}