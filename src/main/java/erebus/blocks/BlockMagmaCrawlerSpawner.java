package erebus.blocks;

import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockMagmaCrawlerSpawner extends BlockSpawner {

	public BlockMagmaCrawlerSpawner(String mobName) {
		super("erebus." + mobName);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public Item getItemDropped(int id, Random rand, int fortune) {
		return Items.magma_cream;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1 + rand.nextInt(3);
	}
}