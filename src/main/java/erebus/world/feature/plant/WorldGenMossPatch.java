package erebus.world.feature.plant;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenMossPatch extends WorldGenerator {
	boolean blockPlaced = false;
	private int mossType = -1;

	public WorldGenMossPatch(int type) {
		super(true);
		mossType = type;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		placeBlockAt(world, rand, x, y, z);
		if (blockPlaced)
			createPatch(world, rand, x, y, z);
		return true;
	}

	private void placeBlockAt(World world, Random rand, int x, int y, int z) {
		int offset = 1;
		int metaMapped = 0;
		int randomiseSide = rand.nextInt(6);
		if (mossType == 1)
			metaMapped += 6;
		switch (randomiseSide) {
			case 0:
				if (world.isSideSolid(x, y + offset, z, DOWN) && world.getBlock(x, y + offset, z) == ModBlocks.umberstone) {
					world.setBlock(x, y, z, ModBlocks.wallPlants, metaMapped + 2, 2);
					blockPlaced = true;
				}
				break;
			case 1:
				if (world.isSideSolid(x, y - offset, z, UP) && world.getBlock(x, y - offset, z) == ModBlocks.umberstone) {
					world.setBlock(x, y, z, ModBlocks.wallPlants, metaMapped + 3, 2);
					blockPlaced = true;
				}
				break;
			case 2:
				if (world.isSideSolid(x, y, z + offset, NORTH) && world.getBlock(x, y, z + offset) == ModBlocks.umberstone) {
					world.setBlock(x, y, z, ModBlocks.wallPlants, metaMapped + 4, 2);
					blockPlaced = true;
				}
				break;
			case 3:
				if (world.isSideSolid(x, y, z - offset, SOUTH) && world.getBlock(x, y, z - offset) == ModBlocks.umberstone) {
					world.setBlock(x, y, z, ModBlocks.wallPlants, metaMapped + 5, 2);
					blockPlaced = true;
				}
				break;
			case 4:
				if (world.isSideSolid(x + offset, y, z, WEST) && world.getBlock(x + offset, y, z) == ModBlocks.umberstone) {
					world.setBlock(x, y, z, ModBlocks.wallPlants, metaMapped + 6, 2);
					blockPlaced = true;
				}
				break;
			case 5:
				if (world.isSideSolid(x - offset, y, z, EAST) && world.getBlock(x - offset, y, z) == ModBlocks.umberstone) {
					world.setBlock(x, y, z, ModBlocks.wallPlants, metaMapped + 7, 2);
					blockPlaced = true;
				}
				break;
			default:
				blockPlaced = false;
				break;
		}
	}

	private void createPatch(World world, Random rand, int x, int y, int z) {
		byte radius = 2;
		for (int xx = x - radius; xx <= x + radius; ++xx)
			for (int zz = z - radius; zz <= z + radius; ++zz)
				for (int yy = y - radius; yy <= y + radius; ++yy)
					if (world.isAirBlock(xx, yy, zz))
						for (int attempt = 0; attempt < 3; attempt++)
							placeBlockAt(world, rand, xx, yy, zz);
	}

}