package erebus.world.feature.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

//@formatter:off
public class WorldGenQuickSand extends WorldGenerator{ // TODO tweak?

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		while(world.isAirBlock(x,y,z) && y>2) --y;

		if (world.getBlockId(x,y,z)!=Block.grass.blockID)return false;

		world.setBlock(x,y,z,ModBlocks.quickSand.blockID);
		world.setBlock(x,y-1,z,ModBlocks.quickSand.blockID);
		world.setBlock(x,y-2,z,ModBlocks.quickSand.blockID);

		world.setBlock(x,y,z-1,ModBlocks.quickSand.blockID);
		world.setBlock(x,y-1,z-1,ModBlocks.quickSand.blockID);

		world.setBlock(x,y,z+1,ModBlocks.quickSand.blockID);
		world.setBlock(x,y-1,z+1,ModBlocks.quickSand.blockID);

		world.setBlock(x-1,y,z,ModBlocks.quickSand.blockID);
		world.setBlock(x-1,y-1,z,ModBlocks.quickSand.blockID);

		world.setBlock(x+1,y,z,ModBlocks.quickSand.blockID);
		world.setBlock(x+1,y-1,z,ModBlocks.quickSand.blockID);

		world.setBlock(x-2,y,z,ModBlocks.quickSand.blockID);
		world.setBlock(x-1,y,z-1,ModBlocks.quickSand.blockID);

		world.setBlock(x+2,y,z,ModBlocks.quickSand.blockID);
		world.setBlock(x+1,y,z+1,ModBlocks.quickSand.blockID);

		world.setBlock(x,y,z-2,ModBlocks.quickSand.blockID);
		world.setBlock(x-1,y,z+1,ModBlocks.quickSand.blockID);

		world.setBlock(x,y,z+2,ModBlocks.quickSand.blockID);
		world.setBlock(x+1,y,z-1,ModBlocks.quickSand.blockID);

		// Top Layer
		if (rand.nextBoolean()) world.setBlock(x+1,y-2,z+0,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x+0,y-2,z+1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-0,y-2,z-1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-1,y-2,z+0,ModBlocks.quickSand.blockID);

		// Middle Layer
		if (rand.nextBoolean()) world.setBlock(x+2,y-1,z+0,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x+1,y-1,z+1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-0,y-1,z+2,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-1,y-1,z+1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-2,y-1,z-0,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x+1,y-1,z-1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-0,y-1,z-2,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-1,y-1,z-1,ModBlocks.quickSand.blockID);

		// Bottom Layer
		if (rand.nextBoolean()) world.setBlock(x+3,y,z+0,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x+2,y,z+1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x+1,y,z+2,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-3,y,z+0,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-2,y,z+1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-1,y,z+2,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x+0,y,z-3,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x+2,y,z-1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x+1,y,z-2,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-0,y,z+3,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-2,y,z-1,ModBlocks.quickSand.blockID);

		if (rand.nextBoolean()) world.setBlock(x-1,y,z-2,ModBlocks.quickSand.blockID);

		return true;
	}
}
//@formatter:on