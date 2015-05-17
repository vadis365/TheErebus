package erebus.block.ores;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import erebus.ModItems;

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
		return ModItems.DATA.jade.ordinal();
	}
}