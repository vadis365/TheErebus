package erebus.block.ores;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import java.util.Random;

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