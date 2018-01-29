package erebus.network.server;

import erebus.entity.EntityRhinoBeetle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketBeetleRamAttack implements IMessage, IMessageHandler<PacketBeetleRamAttack, IMessage> {

	private boolean ramming;

	public PacketBeetleRamAttack() {
	}

	public PacketBeetleRamAttack(boolean ramming) {
		this.ramming = ramming;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(ramming);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		ramming = buf.readBoolean();
	}

	@Override
	public IMessage onMessage(PacketBeetleRamAttack message, MessageContext ctx) {
		final EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				if (player.isRiding() && player.getRidingEntity() instanceof EntityRhinoBeetle) {
					((EntityRhinoBeetle) player.getRidingEntity()).setRamming(message.ramming);
					return;
				}
			}
		});
		return null;
	}
}