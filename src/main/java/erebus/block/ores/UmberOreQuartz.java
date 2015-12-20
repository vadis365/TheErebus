package erebus.block.ores;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class UmberOreQuartz extends UmberOre {

	public UmberOreQuartz() {
		super(Blocks.quartz_ore, "Quartz", 2);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Items.quartz;
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}
}