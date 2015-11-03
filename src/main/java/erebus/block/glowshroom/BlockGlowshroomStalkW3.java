package erebus.block.glowshroom;

import erebus.ModBlocks;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkW3 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkW3() {
		super("StalkW3", ForgeDirection.EAST);
		setBlockBounds(0.3125F, 0.3125F, 0.3125F, 1F, 1F, 0.6875F);
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.GLOWSHROOM_STALK.id();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (!world.isRemote && world.getBlock(x, y + 1, z) != ModBlocks.glowshroom)
			world.setBlock(x, y, z, ModBlocks.glowshroomStalkW1);

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkWE2;
	}
}