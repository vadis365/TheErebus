package erebus.block.ores;

import erebus.ModItems;
import erebus.item.ItemMaterials;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import java.util.Random;

public class UmberOrePetrifiedWood extends UmberOre {

	public UmberOrePetrifiedWood() {
		super(Blocks.diamond_ore, "PetrifiedWood", 2);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return ModItems.materials;
	}

	@Override
	public int damageDropped(int meta) {
		return ItemMaterials.DATA.petrifiedWood.ordinal();
	}
}