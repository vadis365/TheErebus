package erebus.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOreErebus2 extends BlockOreErebus {

	private final Item drop;
	private final int metaDrop, minDrop, maxDrop, minXP, maxXP;

	public BlockOreErebus2(int harvestLevel, Item drop, int metaDrop, int minDrop, int maxDrop, int minXP, int maxXP) {
		super(harvestLevel);
		this.drop = drop;
		this.metaDrop = metaDrop;
		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
		this.minXP = minXP;
		this.maxXP = maxXP;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return drop;
	}

	@Override
	public int quantityDropped(Random random) {
		int max = maxDrop - minDrop;
		return minDrop + (max > 0 ? random.nextInt(max) : 0);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		int i = random.nextInt(fortune + 2) - 1;
		if (i < 0)
			i = 0;
		return quantityDropped(random) * (i + 1);
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		return MathHelper.getInt(rand, minXP, maxXP);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return metaDrop;
	}
}