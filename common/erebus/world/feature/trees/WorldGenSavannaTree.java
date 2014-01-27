package erebus.world.feature.trees;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;

public class WorldGenSavannaTree extends WorldGenerator {

	private final int extraHeight;

	public WorldGenSavannaTree(int extraHeight) {
		this.extraHeight = extraHeight;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int i = 0; i < 6 + extraHeight; i++)
			if (world.getBlockId(x, y + i, z) != 0)
				return false;

		for (int i = 0; i < 6 + extraHeight; i++)
			world.setBlock(x, y + i, z, ModBlocks.logErebusGroup1.blockID, BlockLogErebus.dataAcacia, 2);

		int leafSizeFactor = world.rand.nextInt(1 + extraHeight), leafSize = 4 + leafSizeFactor;
		y += 3 + extraHeight;

		for (int h = leafSizeFactor + 3; h > 0; h--)
			for (int xx = -(leafSize - h); xx <= leafSize - h; xx++)
				for (int zz = -(leafSize - h); zz <= leafSize - h; zz++)
					if (world.getBlockId(x + xx, y + h, z + zz) == 0)
						world.setBlock(x + xx, y + h, z + zz, ModBlocks.leavesErebus.blockID, BlockLeavesErebus.dataAcaciaDecay, 2);

		return true;
	}
}
