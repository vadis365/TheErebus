package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkDown2 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkDown2() {
		super("StalkDown2", ForgeDirection.UP);
		setBlockBounds(0.1875F, 0.375F, 0.1875F, 0.8125F, 1F, 0.8125F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		world.setBlock(x, y, z, ModBlocks.glowshroomStalkDown3);
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain;
	}
}