package erebus.blocks;

import java.util.Random;

import erebus.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockWaspSpawner extends BlockSpawner {

	public BlockWaspSpawner(String mobName) {
		super(mobName);
	}

	@Override
	  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.ANTI_VENOM_BOTTLE;//ModItems.waspSword; Temp for testing
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}
}