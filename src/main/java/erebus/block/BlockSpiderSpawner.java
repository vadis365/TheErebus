package erebus.block;

import java.util.Random;

import net.minecraft.item.Item;

public class BlockSpiderSpawner extends BlockSpawner {

	public BlockSpiderSpawner(int id, String mobName) {
		super(id, mobName);
	}

	@Override
	public int idDropped(int id, Random rand, int fortune) {
		return Item.silk.itemID;
	}

	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(3);
	}
}
