package erebus.block.glowshroom;

import erebus.ModBlocks;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkE3 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkE3() {
		super("StalkE3", ForgeDirection.WEST);
		setBlockBounds(0F, 0.3125F, 0.3125F, 0.6875F, 1F, 0.6875F);
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.GLOWSHROOM_STALK.id();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (!world.isRemote && world.getBlock(x, y + 1, z) != ModBlocks.glowshroom)
			world.setBlock(x, y, z, ModBlocks.glowshroomStalkE1);

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkWE2;
	}
}