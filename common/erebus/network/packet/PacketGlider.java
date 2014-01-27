package erebus.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;

import erebus.ModItems;
import erebus.network.IPacket;

public class PacketGlider implements IPacket {

	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		boolean isGliding = data.readBoolean();
		ItemStack chestplate = player.inventory.armorInventory[2];

		if (chestplate != null && chestplate.getItem() == ModItems.armorGlider)
			if (!chestplate.hasTagCompound()) {
				chestplate.stackTagCompound = new NBTTagCompound();
				return;
			} else
				chestplate.getTagCompound().setBoolean("isGliding", isGliding);
	}
}