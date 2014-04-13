package erebus.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.network.PacketTypeHandler;

public class PacketGlider extends CustomPacket {

	private boolean isGliding;

	public PacketGlider() {
		super(PacketTypeHandler.GLIDER);
	}

	public PacketGlider(boolean isGliding) {
		this();
		this.isGliding = isGliding;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		isGliding = data.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeBoolean(isGliding);
	}

	@Override
	public void execute(World world, EntityPlayer player) {
		ItemStack chestplate = player.inventory.armorInventory[2];

		if (chestplate != null && chestplate.getItem() == ModItems.armorGlider) {
			if (!chestplate.hasTagCompound())
				chestplate.stackTagCompound = new NBTTagCompound();
			chestplate.getTagCompound().setBoolean("isGliding", isGliding);
		}
	}
}