package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.ModTabs;

public class BlockCapstone extends Block {

	public BlockCapstone() {
		super(Material.rock);
		setHarvestLevel("pickaxe", 3);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.capstone");
		setBlockTextureName("erebus:capstone");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.getBlockMetadata(x, y, z) != 0)
			return false;
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack != null && stack.getItem() == ModItems.idols) {
			if (!world.isRemote) {
				world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage() + 1, 3);
				if (!player.capabilities.isCreativeMode)
					stack.stackSize--;
			}
			return true;
		}
		return false;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) != 0 ? 1 : -1;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return null;
	}
}