package erebus.block;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.item.Item;

public class BlockAntlionSpawner extends BlockSpawner {

	public BlockAntlionSpawner(String mobName) {
		super("erebus." + mobName);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public Item getItemDropped(int id, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.ghostSand);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1 + rand.nextInt(3);
	}
}