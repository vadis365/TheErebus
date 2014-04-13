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

public class PacketGliderPowered extends CustomPacket {

	private boolean isPowered;

	public PacketGliderPowered() {
		super(PacketTypeHandler.GLIDER_POWERED);
	}

	public PacketGliderPowered(boolean isPowered) {
		this();
		this.isPowered = isPowered;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		isPowered = data.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeBoolean(isPowered);
	}

	@Override
	public void execute(World world, EntityPlayer player) {
		ItemStack chestplate = player.inventory.armorInventory[2];

		if (chestplate != null && chestplate.getItem().itemID == ModItems.armorGliderPowered.itemID) {
			if (!chestplate.hasTagCompound())
				chestplate.stackTagCompound = new NBTTagCompound();
			chestplate.getTagCompound().setBoolean("isPowered", isPowered);
		}
	}
}