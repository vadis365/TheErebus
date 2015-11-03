package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkDown3 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkDown3() {
		super("StalkDown3", ForgeDirection.UP);
		setBlockBounds(0.1875F, 0F, 0.1875F, 0.8125F, 1F, 0.8125F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain);
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain;
	}
}