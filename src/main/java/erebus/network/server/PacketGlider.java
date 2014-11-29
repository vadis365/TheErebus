package erebus.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.network.AbstractServerPacket;

public class PacketGlider extends AbstractServerPacket {

	private boolean isGliding;

	public PacketGlider() {
	}

	public PacketGlider(boolean isGliding) {
		this.isGliding = isGliding;
	}

	@Override
	protected void handle(World world, EntityPlayerMP player) {
		ItemStack chestplate = player.inventory.armorInventory[2];

		if (chestplate != null && chestplate.getItem() == ModItems.armorGlider) {
			if (!chestplate.hasTagCompound())
				chestplate.stackTagCompound = new NBTTagCompound();
			chestplate.getTagCompound().setBoolean("isGliding", isGliding);
		}
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeBoolean(isGliding);

	}

	@Override
	public void read(ByteBuf buffer) {
		isGliding = buffer.readBoolean();
	}
}