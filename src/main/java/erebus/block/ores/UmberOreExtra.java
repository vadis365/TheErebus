package erebus.block.ores;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class UmberOreExtra extends UmberOre {
	public UmberOreExtra(String name, int harvestLevel) {
		super(Blocks.iron_ore, name, harvestLevel);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}
}