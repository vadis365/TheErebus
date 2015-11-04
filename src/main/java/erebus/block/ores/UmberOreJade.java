package erebus.block.ores;

import java.util.Random;

import erebus.ModItems;
import erebus.item.ItemMaterials;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class UmberOreJade extends UmberOre {

	public UmberOreJade() {
		super(Blocks.diamond_ore, "Jade", 2);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return ModItems.materials;
	}

	@Override
	public int damageDropped(int meta) {
		return ItemMaterials.DATA.jade.ordinal();
	}
}