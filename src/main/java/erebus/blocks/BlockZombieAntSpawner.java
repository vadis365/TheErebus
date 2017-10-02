package erebus.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockZombieAntSpawner extends BlockSpawner {

	public BlockZombieAntSpawner(String mobName) {
		super(mobName);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.ENDER_PEARL;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1 + rand.nextInt(3);
	}
}