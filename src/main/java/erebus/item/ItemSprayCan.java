package erebus.item;

import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSprayCan extends Item {

	public ItemSprayCan() {
		setMaxStackSize(9);
		setCreativeTab(ModTabs.specials);
	}

	protected String getSprayCanSound() {
		return "erebus:spraycansound";
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is)) {
			Block block = world.getBlock(x, y, z);
			if (block != null && World.doesBlockHaveSolidTopSurface(world, x, y, z) && block != ModBlocks.insectRepellent) {
				world.setBlock(x, y + 1, z, ModBlocks.insectRepellent);
				PacketPipeline.sendToAll(new PacketParticle(player, ParticleType.SPRAY_CAN));
				is.stackSize--;
				world.playSoundEffect(x, y + 1, z, getSprayCanSound(), 1.0F, 1.0F);
				return true;
			} else
				return false;
		} else
			return false;
	}
}