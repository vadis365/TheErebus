package erebus.block;

import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockSpiderSpawner extends BlockSpawner {

	public BlockSpiderSpawner(String mobName) {
		super(mobName);
	}

	@Override
	public Item getItemDropped(int id, Random rand, int fortune) {
		return Items.string;
	}

	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(3);
	}
}