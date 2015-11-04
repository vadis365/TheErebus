package erebus.network.server;

import erebus.ModItems;
import erebus.network.AbstractServerPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PacketGliderPowered extends AbstractServerPacket {

	private boolean isPowered;

	public PacketGliderPowered() {
	}

	public PacketGliderPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}

	@Override
	protected void handle(World world, EntityPlayerMP player) {
		ItemStack chestplate = player.inventory.armorInventory[2];

		if (chestplate != null && chestplate.getItem() == ModItems.armorGliderPowered) {
			if (!chestplate.hasTagCompound())
				chestplate.stackTagCompound = new NBTTagCompound();
			chestplate.getTagCompound().setBoolean("isPowered", isPowered);
		}
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeBoolean(isPowered);
	}

	@Override
	public void read(ByteBuf buffer) {
		isPowered = buffer.readBoolean();
	}
}