package erebus.blocks;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockAntlionSpawner extends BlockSpawner {

	public BlockAntlionSpawner(ResourceLocation mobName) {
		super(mobName);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.GHOST_SAND);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1 + rand.nextInt(3);
	}
}