package erebus.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketAntiVenom implements IMessage, IMessageHandler<PacketAntiVenom, IMessage> {

	private int duration;

	public PacketAntiVenom() {
	}

	public PacketAntiVenom(int duration) {
		this.duration = duration;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(duration);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		duration = buf.readInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(PacketAntiVenom message, MessageContext ctx) {
		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityPlayerSP player = FMLClientHandler.instance().getClientPlayerEntity();
		if (player == null)
			return null;
		mc.addScheduledTask(new Runnable() {
			public void run() {
				player.getEntityData().setInteger("anti_venom_duration", message.duration);
			}
		});
		return null;
	}
}