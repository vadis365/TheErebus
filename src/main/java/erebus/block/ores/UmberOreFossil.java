package erebus.block.ores;

import java.util.ArrayList;
import java.util.Random;

import erebus.ModItems;
import erebus.item.ItemMaterials;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UmberOreFossil extends UmberOre {

	public UmberOreFossil() {
		super(Blocks.diamond_ore, "Fossil", 2);
		setHardness(1.5F);
		setResistance(10.0F);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Items.bone;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

		if (world.rand.nextInt(50 - Math.min(6, fortune * 2)) == 0) {
			drops.add(new ItemStack(ModItems.cavemanClub));
			return drops;
		}

		if (world.rand.nextInt(10 - Math.min(6, fortune * 2)) == 0) {
			drops.add(ItemMaterials.DATA.altarFragment.makeStack());
			return drops;
		}

		for (int i = 0; i < 1 + world.rand.nextInt(3); i++) {
			Item item;
			int meta;

			if (world.rand.nextInt(3) == 0) {
				item = Items.bone;
				meta = 0;
			} else {
				item = ModItems.materials;
				meta = ItemMaterials.DATA.shardBone.ordinal();
			}

			drops.add(new ItemStack(item, 1, meta));
		}

		return drops;
	}
}