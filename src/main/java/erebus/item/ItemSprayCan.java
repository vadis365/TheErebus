package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.network.PacketTypeHandler;
import erebus.network.packet.PacketParticle;

public class ItemSprayCan extends Item {

	public ItemSprayCan() {
		maxStackSize = 9;
		setCreativeTab(Erebus.tabErebusGear);
	}

	protected String getSprayCanSound() {
		return "erebus:SprayCanSound";
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is)) {
			int i1 = world.getBlockId(x, y, z);
			Block block = Block.blocksList[i1];
			int i2 = ModBlocks.insectRepellent.blockID;
			if (block != null && world.doesBlockHaveSolidTopSurface(x, y, z) && i1 != i2) {
				world.setBlock(x, y + 1, z, i2);
				PacketDispatcher.sendPacketToAllPlayers(PacketTypeHandler.populatePacket(new PacketParticle((byte) 1, player.entityId)));
				is.stackSize--;
				world.playSoundEffect(x, y + 1, z, getSprayCanSound(), 1.0F, 1.0F);
				return true;
			} else
				return false;
		} else
			return false;
	}
}