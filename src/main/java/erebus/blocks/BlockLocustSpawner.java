package erebus.blocks;

import java.util.Random;

import erebus.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockLocustSpawner extends BlockSpawner {

	public BlockLocustSpawner(ResourceLocation mobName) {
		super(mobName);
	}

	@Override
	  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.REIN_EXOSKELETON_SHIELD;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}
}