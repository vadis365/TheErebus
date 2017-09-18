package erebus.network.server;

import erebus.inventory.ContainerColossalCrate;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ColossalCratePage implements IMessage, IMessageHandler<ColossalCratePage, IMessage> {

	private byte page;

	public ColossalCratePage() {
	}

	public ColossalCratePage(int page) {
		this.page = (byte) page;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(page);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		page = buf.readByte();
		
	}

	@Override
	public IMessage onMessage(ColossalCratePage message, MessageContext ctx) {

		final EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				if (player.openContainer instanceof ContainerColossalCrate)
					((ContainerColossalCrate) player.openContainer).changePage(message.page);
			}
		});

		return null;
	}

}