package erebus.block;

import java.util.Random;

import net.minecraft.item.Item;
import erebus.ModItems;

public class BlockWaspSpawner extends BlockSpawner {

	public BlockWaspSpawner(int id, String mobName) {
		super(id, mobName);
	}

	@Override
	public Item getItemDropped(int id, Random rand, int fortune) {
		return ModItems.waspSword;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}
}