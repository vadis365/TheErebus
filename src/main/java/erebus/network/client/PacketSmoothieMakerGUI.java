package erebus.network.client;

import erebus.inventory.ContainerSmoothieMaker;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSmoothieMakerGUI implements IMessage, IMessageHandler<PacketSmoothieMakerGUI, IMessage> {

	private NBTTagCompound nbt;

	public PacketSmoothieMakerGUI() {
	}

	public PacketSmoothieMakerGUI(NBTTagCompound nbt) {
		this();
		this.nbt = nbt;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(PacketSmoothieMakerGUI message, MessageContext ctx) {
		World world = FMLClientHandler.instance().getWorldClient();
		if (world == null)
			return null;
		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityPlayerSP player = FMLClientHandler.instance().getClientPlayerEntity();
		if (player == null)
			return null;
		mc.addScheduledTask(new Runnable() {
			public void run() {
				Container container = player.openContainer;
				if (container != null && container instanceof ContainerSmoothieMaker)
					((ContainerSmoothieMaker) container).readPacketData(message.nbt);
			}
		});
		return null;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
	}
}