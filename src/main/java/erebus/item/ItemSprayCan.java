package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.network.PacketPipeline;
import erebus.network.packet.PacketParticle;

public class ItemSprayCan extends Item {

	public ItemSprayCan() {
		maxStackSize = 9;
	}

	protected String getSprayCanSound() {
		return "erebus:SprayCanSound";
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is)) {
			Block block = world.getBlock(x, y, z);
			if (block != null && World.doesBlockHaveSolidTopSurface(world, x, y, z) && block != ModBlocks.insectRepellent) {
				world.setBlock(x, y + 1, z, ModBlocks.insectRepellent);
				PacketPipeline.sendPacketToAllPlayers(PacketTypeHandler.populatePacket(new PacketParticle((byte) 1, player.getEntityId())));
				is.stackSize--;
				world.playSoundEffect(x, y + 1, z, getSprayCanSound(), 1.0F, 1.0F);
				return true;
			} else
				return false;
		} else
			return false;
	}
}