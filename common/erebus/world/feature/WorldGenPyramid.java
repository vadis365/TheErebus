package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPyramid extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int h = 10; h > 0; h--)
			for (int w = -(14 - h); w < 14 - h; w++)
				for (int w2 = -(14 - h); w2 < 14 - h; w2++) {
					if (w < 0 && w <= -(14 - h))
						world.setBlock(x + w, y + h + 1, z + w2, Block.stairsSandStone.blockID, 0, 3);
					if (w > 0 && w >= 14 - h - 1)
						world.setBlock(x + w, y + h + 1, z + w2, Block.stairsSandStone.blockID, 1, 3);
					if (w2 < 0 && w2 <= -(14 - h))
						world.setBlock(x + w, y + h + 1, z + w2, Block.stairsSandStone.blockID, 2, 3);
					if (w2 > 0 && w2 >= 14 - h - 1)
						world.setBlock(x + w, y + h + 1, z + w2, Block.stairsSandStone.blockID, 3, 3);
					if (w < 0 && w <= -(14 - h))
						if (w2 < 0 && w2 <= -(14 - h))
							world.setBlock(x + w, y + h + 1, z + w2, Block.stoneSingleSlab.blockID);
					if (w > 0 && w >= 14 - h - 1)
						if (w2 > 0 && w2 >= 14 - h - 1)
							world.setBlock(x + w, y + h + 1, z + w2, Block.stoneSingleSlab.blockID);
					if (w < 0 && w <= -(14 - h))
						if (w2 > 0 && w2 >= 14 - h - 1)
							world.setBlock(x + w, y + h + 1, z + w2, Block.stoneSingleSlab.blockID);
					if (w > 0 && w >= 14 - h - 1)
						if (w2 < 0 && w2 <= -(14 - h))
							world.setBlock(x + w, y + h + 1, z + w2, Block.stoneSingleSlab.blockID);

					world.setBlock(x + w, y + h, z + w2, Block.sandStone.blockID);
				}

		for (int w = -3; w < 3; w++)
			for (int w2 = -3; w2 < 3; w2++)
				world.setBlock(x + w, y + 11, z + w2, Block.sandStone.blockID);

		return true;
	}
}
