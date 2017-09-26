package erebus.network.server;

import erebus.ModItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGlider implements IMessage, IMessageHandler<PacketGlider, IMessage> {

	private boolean isGliding;

	public PacketGlider() {
	}

	public PacketGlider(boolean isGliding) {
		this.isGliding = isGliding;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isGliding);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		isGliding = buf.readBoolean();
	}

	@Override
	public IMessage onMessage(PacketGlider message, MessageContext ctx) {
		final EntityPlayerMP player = ctx.getServerHandler().player;
		ItemStack chestplate = player.inventory.armorInventory.get(2);
		player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				if (!chestplate.isEmpty() && chestplate.getItem() == ModItems.GLIDER_CHESTPLATE) {
					if (!chestplate.hasTagCompound())
						chestplate.setTagCompound(new NBTTagCompound());
					chestplate.getTagCompound().setBoolean("isGliding", message.isGliding);
					System.out.println("Gliding: " + message.isGliding);
				}
			}	
		});
		return null;
	}
}