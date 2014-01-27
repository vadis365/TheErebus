package erebus.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class BlockOreFossil extends Block {

	public BlockOreFossil(int id) {
		super(id, Material.rock);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return Item.bone.itemID;
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		if (world.rand.nextInt(50 - Math.min(6, fortune * 2)) == 0) {
			ret.add(new ItemStack(ModItems.fossilClub, 1));
			return ret;
		}

		if (world.rand.nextInt(30 - Math.min(6, fortune * 2)) == 0) {
			ret.add(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataAltarFragment));
			return ret;
		}

		int count = 1 + world.rand.nextInt(3);

		for (int i = 0; i < count; i++) {
			int id = -1;
			int damage = 0;

			if (world.rand.nextInt(3) == 0)
				id = Item.bone.itemID;
			else {
				id = ModItems.erebusMaterials.itemID;
				damage = 2;
			}

			if (id > 0)
				ret.add(new ItemStack(id, 1, damage));
		}
		return ret;
	}
}
