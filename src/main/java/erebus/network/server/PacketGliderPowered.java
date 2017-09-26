package erebus.network.server;

import erebus.ModItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGliderPowered implements IMessage, IMessageHandler<PacketGliderPowered, IMessage> {

	private boolean isPowered;

	public PacketGliderPowered() {
	}

	public PacketGliderPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isPowered);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		isPowered = buf.readBoolean();
	}
	
	@Override
	public IMessage onMessage(PacketGliderPowered message, MessageContext ctx) {
		final EntityPlayerMP player = ctx.getServerHandler().player;
		ItemStack chestplate = player.inventory.armorInventory.get(2);
		player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				if (!chestplate.isEmpty() && chestplate.getItem() == ModItems.GLIDER_CHESTPLATE_POWERED) {
					if (!chestplate.hasTagCompound())
						chestplate.setTagCompound(new NBTTagCompound());
					chestplate.getTagCompound().setBoolean("isPowered", message.isPowered);
				}
			}	
		});
		return null;
	}

}