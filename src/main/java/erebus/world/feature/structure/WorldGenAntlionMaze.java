package erebus.world.feature.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.world.feature.util.BasicShapeGen;


public class WorldGenAntlionMaze extends WorldGenerator  {

    private Block solid = ModBlocks.gneiss;

/*	public MazeSystem()
	{
		range = 8; //Radius of chunks that it will generate before trying to generate  8 chunks = 8*16 blocks
	}
	
	@Override
	protected void func_151538_a(World world, int localX, int localZ, int chunkX, int chunkZ, Block[] blocks)
	{
		if (rand.nextInt(20) == 0) // Should probably change this
		{*/
    public boolean generate(World world, Random rand, int x, int y, int z) {
			
			int sizeX = 48;
			int sizeY = y+4;
			int sizeZ = 48;

			//if (world.getBiomeGenForCoords(x, z).biomeID == ModBiomes.volcanicDesertID)
			//{
			        int mazeWidth = sizeX/2;
			        int mazeHeight = sizeZ/2;
			        if (mazeWidth < 2 || mazeHeight < 2 || sizeY < 1) {
			        	return false;
			        }
			        int[][] maze = null;
			        MazeGenerator generator = new PerfectMazeGenerator(mazeWidth, mazeHeight);
                    maze = generator.generateMaze();
			        for (int yy = y; yy < sizeY; yy++) {
			            switch (yy % 4) {
			             case 0:
			                   buildFloor(world, x, yy - 4, z, mazeWidth, mazeHeight, rand);
			                   buildRoof(world, x, yy, z, mazeWidth, mazeHeight, rand);
			                   break;
			                case 1:
			                	buildLevel(world, x, yy - 4, z, mazeWidth, mazeHeight, maze, solid, 2);
			                    buildLevel(world, x, yy - 3, z, mazeWidth, mazeHeight, maze, solid, 1);
			                    buildLevel(world, x, yy - 2, z, mazeWidth, mazeHeight, maze, solid, 2);
			                    // TODO Create loot chests and mimic chests
			                    // TODO Create traps, new spawners and shizz
			                    addTorch(world, x, yy - 3 , z, mazeWidth, mazeHeight, maze, rand);
			                    break;
			            }
			       }
					buildCourtyard(world, ModBlocks.templePillar, 0, x+sizeX, y - 4, z+sizeZ, 32, 4, 32);
					// TODO Make a proper pyramid with a couple of levels and Boss arena at base
					// TODO Make Pyramid indestructible apart from 4 cap stones (need all 4 idols to open the entrance capstones)
					BasicShapeGen.createPyramid(world, ModBlocks.templeBrickUnbreaking, 0, true, x+sizeX/2+9, z+sizeZ/2+9, 30, 30, y - 6);
					placeSpecialChests(world, x, y, z);
					buildEntrances(world, ModBlocks.templeBrick, 0, true, x, z, 5, 5, y, rand);
			        return true;
			//}
		//}
	}
    
    private void placeSpecialChests(World world, int x, int y, int z) {
    	// TODO These chests will contain the 4 new idol items, one in each.
    	// when attempted to be opened they will transform in to a mob which you have beat to get the idol.
    	world.setBlock(x + 1, y - 3, z + 1, ModBlocks.petrifiedWoodChest);
    	world.setBlock(x + 95, y - 3, z + 1, ModBlocks.petrifiedWoodChest);
    	world.setBlock(x + 95, y - 3, z + 95, ModBlocks.petrifiedWoodChest);
    	world.setBlock(x + 1, y - 3, z + 95, ModBlocks.petrifiedWoodChest);
    	
    	world.setBlockMetadataWithNotify(x + 1, y - 3, z + 1, 5, 3);
    	world.setBlockMetadataWithNotify(x + 95, y - 3, z + 1, 3, 3);
    	world.setBlockMetadataWithNotify(x + 95, y - 3, z + 95, 4, 3);
    	world.setBlockMetadataWithNotify(x + 1, y - 3, z + 95, 2, 3);
	}

	private void buildEntrances(World world, Block block, int metaData, boolean isHollow, int x, int z, int baseLengthX, int baseLengthZ, int yStart, Random rand) {
		// TODO these are just markers - they should probably be shafts from the surface that go down to the dungeon
		// Perhaps inside it a basic chest containing a book with instructions written in an ancient style - just so the mission is clear?
    	BasicShapeGen.createPyramid(world, block, 0, true, x, z, baseLengthX, baseLengthZ, yStart);
    	BasicShapeGen.createPyramid(world, block, 0, true, x + 97 - baseLengthX, z, baseLengthX, baseLengthZ, yStart);
    	BasicShapeGen.createPyramid(world, block, 0, true, x + 97 - baseLengthX, z + 97 - baseLengthZ, baseLengthX, baseLengthZ, yStart);
    	BasicShapeGen.createPyramid(world, block, 0, true, x, z + 97 - baseLengthZ, baseLengthX, baseLengthZ, yStart);
	}

	private void buildCourtyard(World world, Block block, int metaData, int x, int y, int z, int baseLengthX, int heightY, int baseLengthZ) {
		for (int yy = y; yy < heightY + y; yy++) {
			for (int xx = x - baseLengthX / 2; xx < x + baseLengthX / 2; xx++)
				for (int zz = z - baseLengthZ / 2; zz < z + baseLengthZ / 2; zz++) {
					if (yy > y) {
						world.setBlockToAir(xx, yy, zz);
						if (xx == x - baseLengthX / 2 || xx == x + baseLengthX / 2 - 1)
							if (zz > z - baseLengthZ / 2 && zz < z + baseLengthZ / 2)
								for(int i = 3; i < 29; i += 5)
									world.setBlock(xx, yy, z - baseLengthZ / 2 + i, block, metaData, 3);
						
						if (zz == z - baseLengthZ / 2 || zz == z + baseLengthZ / 2 - 1)
							if (xx > x - baseLengthX / 2 && xx < x + baseLengthX / 2)
								for(int i = 3; i < 29; i += 5)
									world.setBlock(x - baseLengthZ / 2 + i, yy, zz, block, metaData, 3);
					}
					world.setBlock(xx, y + 4, zz, ModBlocks.templeBrick, 0, 3);

					if (xx > x - baseLengthX / 2 && xx < x + baseLengthX / 2 - 1)
						if (zz > z - baseLengthZ / 2 && zz < z + baseLengthZ / 2 - 1)
							world.setBlockToAir(xx, y + 4, zz);
				}
		}
	}

    private void buildRoof(World world, int x, int y, int z, int w, int h, Random rand) {
        for (int i = 0; i <= h * 4; i++) {
            for (int j = 0; j <= w * 4; j++) {
                world.setBlock(x + j, y, z + i, solid, 3, 2); //turn to Blocks.air to see amazing maze
            }
        }
    }
    
    private void buildFloor(World world, int x, int y, int z, int w, int h, Random rand) {
    	// TODO k will be 4 not 13 to clear space - will need to make air above pyramid in another method
        for (int i = 0; i <= h * 4; i++) {
            for (int j = 0; j <= w * 4; j++) {
            	for(int k = 0; k <= 13; k++)
                world.setBlock(x + j, y + k, z + i, Blocks.air);
            }
        }
        for (int i = 0; i <= h * 4; i++) {
            for (int j = 0; j <= w * 4; j++) {
            	if (rand.nextInt(20) == 0)
            		if(rand.nextBoolean() && rand.nextBoolean())
            			world.setBlock(x + j, y, z + i, Blocks.lava);
            		else
            			world.setBlock(x + j, y, z + i, ModBlocks.gneissVent);
            	else
            		world.setBlock(x + j, y, z + i, solid, 5, 2);
            }
        }
    }
    
    private void addTorch(World world, int x, int y, int z, int w, int h, int[][] maze, Random rand) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if ((maze[j][i] & 1) == 0) {
                    if(rand.nextInt(25) == 0) {
                    	world.setBlock(x + 1 + j * 4, y, z + 1 + i * 4, Blocks.torch, 3, 2);
                    	if(rand.nextInt(2) == 0)
                    		world.setBlock(x + 1 + j * 4, y - 1, z + 1 + i * 4, Blocks.chest, 3, 2);
                    }
                }
            }

            for (int j = 0; j < w; j++) {
            	if ((maze[j][i] & 8) == 0) {
            		if(rand.nextInt(25) == 0) {
            			world.setBlock(x + 1 + j * 4, y, z + 1 + i * 4, Blocks.torch, 1, 2);
            			if(rand.nextInt(2) == 0)
            				world.setBlock(x + 1 + j * 4, y - 1, z + 1 + i * 4, Blocks.chest, 1, 2);
            		}
            	}
            }
        }
	}

    private void buildLevel(World world, int x, int y, int z, int w, int h, int[][] maze, Block blockType, int blockMeta) {
        for (int i = 0; i < h; i++) {
            // draw the north edge
            for (int j = 0; j < w; j++) {
                if ((maze[j][i] & 1) == 0) {
                    world.setBlock(x + j * 4, y, z + i * 4, blockType, blockMeta, 2);
                    world.setBlock(x + j * 4 + 1, y, z + i * 4, blockType, blockMeta, 2);
                    world.setBlock(x + j * 4 + 2, y, z + i * 4, blockType, blockMeta, 2);
                    world.setBlock(x + j * 4 + 3, y, z + i * 4, blockType, blockMeta, 2);
                } else {
                    world.setBlock(x + j * 4, y, z + i * 4, blockType, blockMeta, 2);
                }
            }
            // draw the west edge
            for (int j = 0; j < w; j++) {
                if ((maze[j][i] & 8) == 0) {
                    world.setBlock(x + j * 4, y, z + i * 4 + 1, blockType, blockMeta, 2);
                    world.setBlock(x + j * 4, y, z + i * 4 + 2, blockType, blockMeta, 2);
                    world.setBlock(x + j * 4, y, z + i * 4 + 3, blockType, blockMeta, 2);
                }
            }
            world.setBlock(x + w * 4, y, z + i * 4, blockType, blockMeta, 2);
            world.setBlock(x + w * 4, y, z + i * 4 + 1, blockType, blockMeta, 2);
            world.setBlock(x + w * 4, y, z + i * 4 + 2, blockType, blockMeta, 2);
            world.setBlock(x + w * 4, y, z + i * 4 + 3, blockType, blockMeta, 2);
        }
        // draw the bottom line
        for (int j = 0; j <= w * 4; j++) {
        	world.setBlock(x + j, y, z + h * 4, blockType, blockMeta, 2);
        }

    }

}
