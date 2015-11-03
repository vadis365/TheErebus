package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkN1 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkN1() {
		super("StalkN1", ForgeDirection.SOUTH);
		setBlockBounds(0.3125F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 1F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (world.getBlock(x, y, z + 1) == ModBlocks.glowshroomStalkMain)
			world.setBlock(x, y, z, ModBlocks.glowshroomStalkNS2);
		if (rand.nextInt(2) == 0)
			if (world.getBlock(x, y, z + 1) == ModBlocks.glowshroomStalkNS2 && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkN3);
				world.setBlock(x, y + 1, z, ModBlocks.glowshroom);
			} else {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkNS2);
				world.setBlock(x, y, z + 1, ModBlocks.glowshroomStalkMain);
			}
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain || block == ModBlocks.glowshroomStalkNS2;
	}
}