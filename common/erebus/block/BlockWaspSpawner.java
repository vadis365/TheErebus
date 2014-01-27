package erebus.block;

import java.util.Random;

import erebus.ModItems;

public class BlockWaspSpawner extends BlockSpawner {

	public BlockWaspSpawner(int id, String mobName) {
		super(id, mobName);
	}

	@Override
	public int idDropped(int id, Random rand, int fortune) {
		return ModItems.waspSword.itemID;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}
}
