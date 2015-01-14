package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.EntityUmberGolemDungeonTypes;
import erebus.item.Materials;
import erebus.item.Materials.DATA;
import erebus.tileentity.TileEntityTempleTeleporter;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;


public class WorldGenAntlionMaze extends WorldGenerator  {

    private Block solid = ModBlocks.gneiss;
      
	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] { new LootItemStack(Items.book).setAmount(1, 4).setWeight(18), new LootItemStack(Items.paper).setAmount(2, 6).setWeight(16), new LootItemStack(Blocks.web).setAmount(2, 7).setWeight(13), new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.jade.ordinal()).setWeight(10), new LootItemStack(ModItems.materials).setAmount(4, 8).setDamage(DATA.plateExo.ordinal()).setWeight(9), new LootItemStack(Items.enchanted_book).setWeight(8), new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1), new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1), new LootItemStack(Items.golden_pickaxe).setWeight(3), new LootItemStack(Items.iron_pickaxe).setWeight(2),
	new LootItemStack(ModItems.jadePickaxe).setWeight(1), new LootItemStack(Items.stone_pickaxe).setWeight(1), new LootItemStack(Items.golden_shovel).setWeight(3), new LootItemStack(Items.iron_shovel).setWeight(2), new LootItemStack(ModItems.jadeShovel).setWeight(1), new LootItemStack(Items.stone_shovel).setWeight(1), new LootItemStack(Items.golden_axe).setWeight(3), new LootItemStack(Items.iron_axe).setWeight(2), new LootItemStack(ModItems.jadeAxe).setWeight(1), new LootItemStack(Items.stone_axe).setWeight(1), new LootItemStack(Items.golden_sword).setWeight(3), new LootItemStack(Items.iron_sword).setWeight(2), new LootItemStack(ModItems.jadeSword).setWeight(1), new LootItemStack(Items.stone_sword).setWeight(1), new LootItemStack(Items.iron_chestplate).setWeight(2),
	new LootItemStack(ModItems.jadeBody).setWeight(1), new LootItemStack(Items.golden_chestplate).setWeight(1), new LootItemStack(Items.iron_helmet).setWeight(2), new LootItemStack(ModItems.jadeHelmet).setWeight(1), new LootItemStack(Items.golden_helmet).setWeight(1), new LootItemStack(Items.iron_leggings).setWeight(2), new LootItemStack(ModItems.jadeLegs).setWeight(1), new LootItemStack(Items.golden_leggings).setWeight(1), new LootItemStack(Items.iron_boots).setWeight(2), new LootItemStack(ModItems.jadeBoots).setWeight(1), new LootItemStack(Items.golden_boots).setWeight(1) }).setPostProcessor(new IPostProcess() {

		@SuppressWarnings("rawtypes")
		@Override
		public ItemStack postProcessItem(ItemStack is, Random rand) {
			if (rand.nextBoolean() && (is.getItem() == Items.enchanted_book || is.getItem() instanceof ItemTool || is.getItem() instanceof ItemArmor || is.getItem() instanceof ItemSword)) {
				boolean enchBook = is.getItem() == Items.enchanted_book;
				if (enchBook)
					is.func_150996_a(Items.book);
				List enchList = EnchantmentHelper.buildEnchantmentList(rand, is, 7 + rand.nextInt(10));
				if (enchBook)
					is.func_150996_a(Items.enchanted_book);

				if (enchList != null && enchList.size() > 0)
					for (int a = 0; a < enchList.size(); ++a) {
						EnchantmentData data = (EnchantmentData) enchList.get(a);
						if (is.getItem() == Items.enchanted_book)
							Items.enchanted_book.addEnchantment(is, data);
						else
							is.addEnchantment(data.enchantmentobj, data.enchantmentLevel);
					}
			}
			return is;
		}
	});
    

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
			
			int sizeX = 60;
			int sizeY = y + 4;
			int sizeZ = 60;

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
			            	 addFeature(world, x, yy - 3 , z, mazeWidth, mazeHeight, maze, rand);
			            	 break;
			            }
			       }
			        buildCourtyard(world, ModBlocks.templePillar, 0, x + sizeX, y - 4, z + sizeZ, 52, 4, 52);
					createPyramid(world, ModBlocks.templeBrickUnbreaking, 0, true, x + sizeX/2 + 8, z + sizeZ/2 + 8, 44, 44, y - 6);
					decoratePyramid(world, x + sizeX/2 + 8, y - 6, z + sizeZ/2 + 8);
					addCapstones(world, x + sizeX -1, y + 15, z + sizeZ -1, ModBlocks.capstone, 0);
					spawnIdolGuardians(world, x, y, z);
			        return true;
			//}
		//}
	}

	public static void decoratePyramid(World world, int x, int y, int z) {
		// create floors
		for (int yy = y; yy < y + 30; yy++)
			for (int xx = x; xx < x + 44; xx++)
				for (int zz = z; zz < z + 44; zz++) {
					if (yy == y)
						world.setBlock(xx, yy, zz, ModBlocks.templeBrickUnbreaking, 0, 1);
					if (yy == y + 1) {
						if (xx > x + 1 && xx < x + 42 && zz > z + 1 && zz < z + 42)
							world.setBlock(xx, yy, zz, Blocks.sand, 0, 2);
						if (xx > x + 4 && xx < x + 39 && zz > z + 4 && zz < z + 39)
							if (xx % 3 == 0 || zz % 3 == 0)
								world.setBlock(xx, yy, zz, ModBlocks.gneissVent, 0, 2);
							else
								world.setBlock(xx, yy, zz, Blocks.sand, 0, 2);
					}

					if (yy == y + 9) {
						if (xx > x + 9 && xx < x + 34 && zz > z + 9 && zz < z + 34)
							world.setBlock(xx, yy, zz, ModBlocks.templeBrickUnbreaking, 0, 2);
						
						// room 1
						world.setBlock(x + 13, yy, z + 13, ModBlocks.capstone, 0, 2); // TODO make a new block state that is not idol affected
						setTeleporter(world, x + 16, yy, z + 16, 1, x + 30, yy, z + 13); // on to next room's capstone
						setTeleporter(world, x + 19, yy, z + 19, 10, x + 19, y + 14, z + 19); // back to floor above's capstone
						
						// room 2
						world.setBlock(x + 30, yy, z + 13, ModBlocks.capstone, 0, 2);
						setTeleporter(world, x + 27, yy, z + 16, 2, x + 30, yy, z + 30);
						setTeleporter(world, x + 24, yy, z + 19, 10, x + 13, yy, z + 13);
						
						// room 3
						world.setBlock(x + 30, yy, z + 30, ModBlocks.capstone, 0, 2);
						setTeleporter(world, x + 27, yy, z + 27, 3, x + 13, yy, z + 30);
						setTeleporter(world, x + 24, yy, z + 24, 10, x + 30, yy, z + 13);
						
						// room 4
						world.setBlock(x + 13, yy, z + 30, ModBlocks.capstone, 0, 2);
						setTeleporter(world, x + 16, yy, z + 27, 4, x + 13, yy, z + 13); //this one should remove forcefield in room 1 (debug teleport added - will be removed)
						setTeleporter(world, x + 19, yy, z + 24, 10, x + 30, yy, z + 30);	
					}

					if (yy == y + 10) {
						//TODO Blocks.glass == forcefield block (have to make it yet)
						for(int d = 0; d < 4; d++) {
							for(int wx = 0 + d; wx < 9; wx++) {
								world.setBlock(x + 11 + wx, yy + d, z + 21, Blocks.glass, 0, 2);
								world.setBlock(x + 11 + wx, yy + d, z + 22, Blocks.glass, 0, 2);
								world.setBlock(x + 21, yy + d, z + 11 + wx, Blocks.glass, 0, 2);
								world.setBlock(x + 22, yy + d, z + 11 + wx, Blocks.glass, 0, 2);
								
								world.setBlock(x + 21, yy + d, z + 32 - wx, Blocks.glass, 0, 2);
								world.setBlock(x + 22, yy + d, z + 32 - wx, Blocks.glass, 0, 2);
								world.setBlock(x + 32 - wx, yy + d, z + 21 , Blocks.glass, 0, 2);
								world.setBlock(x + 32 - wx, yy + d, z + 22, Blocks.glass, 0, 2);
							}
	
							for(int dx = x + 20; dx < x + 24; dx ++)
								for(int dz = z + 20; dz < z + 24; dz ++)
									world.setBlock(dx, yy + d, dz, Blocks.glass, 0, 2);

							for(int dx1 = x + 21; dx1 < x + 23; dx1 ++)
								for(int dz1 = z + 21; dz1 < z + 23; dz1 ++)
									world.setBlockToAir(dx1, yy + d, dz1);	
						}
					}
					
					if (yy == y + 14) {
						if (xx > x + 14 && xx < x + 29 && zz > z + 14 && zz < z + 29)
							world.setBlock(xx, yy, zz, ModBlocks.templeBrickUnbreaking, 0, 2);
						world.setBlock(x + 19, yy, z + 19, ModBlocks.capstone, 0, 2); // TODO make a new block state that is not idol affected
						setTeleporter(world, x + 22, yy, z + 22, 0, x + 13, y + 9, z + 13);
						setTeleporter(world, x + 25, yy, z + 25, 10, x + 19, yy, z + 19);
					}
					
					if (yy == y + 15) {
						// contents is 8 pieces of jade for spoon feeding tutorial
						world.setBlock(x + 19, yy, z + 22, Blocks.chest, 2, 2);
						TileEntityChest chest = (TileEntityChest) world.getTileEntity(x + 19, yy, z + 22);
						if (chest != null)
							chest.setInventorySlotContents(0, Materials.createStack(DATA.jade, 8));
						world.setBlockMetadataWithNotify(x + 19, yy, z + 22, 2, 3);
					}
					
					if (yy == y + 16) {
						// TODO Lighting?
					}
				}
	}
	
	public static void setTeleporter(World world, int x, int y, int z, int metaData, int targetX, int targetY, int targetZ) {
		world.setBlock(x, y, z, ModBlocks.templeTeleporter, metaData, 2);
		TileEntityTempleTeleporter teleporter = (TileEntityTempleTeleporter) world.getTileEntity(x, y, z);
		if (teleporter != null)
			teleporter.setTargetDestination(targetX, targetY, targetZ, metaData);	
	}
	

	private void addCapstones(World world, int x, int y, int z, Block capstone, int metaData) {
    	world.setBlock(x, y, z, capstone, metaData, 3);
    	world.setBlock(x + 1, y, z, capstone, metaData, 3);
    	world.setBlock(x + 1, y, z + 1, capstone, metaData, 3);
    	world.setBlock(x, y, z + 1, capstone, metaData, 3);
	}

	public static void createPyramid(World world, Block block, int metaData, boolean isHollow, int x, int z, int baseLengthX, int baseLengthZ, int yStart) {
		int yStop = Math.min((baseLengthZ - 1) / 2, (baseLengthX - 1) / 2) + yStart;
		for (int i = 0; i + yStart <= yStop -1; i++) {
			int y = yStart + i;
			int maxX = x + baseLengthX - 1;
			int maxZ = z + baseLengthZ - 1;
			for (int ix = 0; x + ix + i <= maxX; ix++) 
				for (int iz = 0; z + iz + i <= maxZ; iz++) 
					if (ix == 0 || ix + i + 1 == baseLengthX || iz == 0 || iz + i + 1 == baseLengthZ)
						world.setBlock(ix + x + i, y, iz + z + i, block, metaData, 2);
					else if (isHollow)
						world.setBlockToAir(ix + x + i, y, iz + z + i);

			baseLengthX--;
			baseLengthZ--;
		}
	}
    
    private void spawnIdolGuardians(World world, int x, int y, int z) {
		if (!world.isRemote) {
			for(byte spawn = 0; spawn < 4; spawn ++) {
				EntityUmberGolemDungeonTypes entityUmberGolem;
				entityUmberGolem = new EntityUmberGolemDungeonTypes(world);
				entityUmberGolem.setType(spawn);
				entityUmberGolem.setHealth(entityUmberGolem.getMaxHealth()); //hack because of stupid attributes setting 
				switch(spawn){
					case 0:
						entityUmberGolem.setPosition(x + 2.5D, y -3.0D, z + 2.5D);
						break;	
					case 1:
						entityUmberGolem.setPosition(x + 118.5D, y -3.0D, z + 2.5D);
						break;
					case 2:
						entityUmberGolem.setPosition(x + 118.5D, y -3.0D, z + 118.5D);
						break;
					case 3:
						entityUmberGolem.setPosition(x + 2.5D, y -3.0D, z + 118.5D);
						break;
				}
				world.spawnEntityInWorld(entityUmberGolem);
			}
		}
	}

	private void buildCourtyard(World world, Block block, int metaData, int x, int y, int z, int baseLengthX, int heightY, int baseLengthZ) {
		for (int yy = y; yy < heightY + y; yy++) {
			for (int xx = x - baseLengthX / 2; xx < x + baseLengthX / 2; xx++)
				for (int zz = z - baseLengthZ / 2; zz < z + baseLengthZ / 2; zz++) {
					if (yy > y) {
						world.setBlockToAir(xx, yy, zz);
						if (xx == x - baseLengthX / 2 || xx == x + baseLengthX / 2 - 1)
							if (zz > z - baseLengthZ / 2 && zz < z + baseLengthZ / 2)
								for(int i = 3; i < 49; i += 5)
									world.setBlock(xx, yy, z - baseLengthZ / 2 + i, block, metaData, 2);
						
						if (zz == z - baseLengthZ / 2 || zz == z + baseLengthZ / 2 - 1)
							if (xx > x - baseLengthX / 2 && xx < x + baseLengthX / 2)
								for(int i = 3; i < 49; i += 5)
									world.setBlock(x - baseLengthZ / 2 + i, yy, zz, block, metaData, 2);
					}
					world.setBlock(xx, y + 4, zz, ModBlocks.templeBrick, 0, 2);

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
    	// TODO make air above pyramid in another method
        for (int i = 0; i <= h * 4; i++) {
            for (int j = 0; j <= w * 4; j++) {
            	for(int k = 0; k <= 4; k++)
            		if(!world.isAirBlock(x + j, y + k, z + i))
            			world.setBlockToAir(x + j, y + k, z + i);
            }
        }
        for (int i = 0; i <= h * 4; i++) {
            for (int j = 0; j <= w * 4; j++) {
            	if (rand.nextInt(15) == 0)
            		if(rand.nextBoolean() && rand.nextBoolean())
            			world.setBlock(x + j, y, z + i, Blocks.lava);
            		else
            			world.setBlock(x + j, y, z + i, ModBlocks.gneissVent);
            	else
            		world.setBlock(x + j, y, z + i, solid, 5, 2);
            }
        }
    }
    
    private void addFeature(World world, int x, int y, int z, int w, int h, int[][] maze, Random rand) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if ((maze[j][i] & 1) == 0) {
                    if(rand.nextInt(25) == 0) {
                    	world.setBlock(x + 1 + j * 4, y, z + 1 + i * 4, Blocks.torch, 3, 2);
                    	if(rand.nextInt(4) == 0)
                    		placeChest(world, x + 1 + j * 4, y - 1, z + 1 + i * 4, 3, rand);
                    }
                    else if (rand.nextInt(10) == 0) {
                    	if(rand.nextBoolean())
                    		world.setBlock(x + 2 + j * 4, y - 2, z + 2 + i * 4, ModBlocks.antlionSpawner);
                    	else
                    		world.setBlock(x + 2 + j * 4, y + 2, z + 2 + i * 4, ModBlocks.magmaCrawlerSpawner);
                    }
                }
            }

            for (int j = 0; j < w; j++) {
            	if ((maze[j][i] & 8) == 0) {
            		if(rand.nextInt(25) == 0) {
            			world.setBlock(x + 1 + j * 4, y, z + 2 + i * 4, Blocks.torch, 1, 2);
            			if(rand.nextInt(4) == 0)
            				placeChest(world, x + 1 + j * 4, y - 1, z + 2 + i * 4, 1, rand);
            			}
            	}
            }

            for (int j = 0; j < w; j++) {
                if ((maze[j][i] & 4) == 0) {
                    if(rand.nextInt(25) == 0) {
                    	world.setBlock(x + 3 + j * 4, y, z + 2 + i * 4, Blocks.torch, 2, 2);
                    	if(rand.nextInt(4) == 0)
                    		placeChest(world, x + 3 + j * 4, y - 1, z + 2 + i * 4, 2, rand);
                   }
                }
            }

            for (int j = 0; j < w; j++) {
            	if ((maze[j][i] & 2) == 0) {
            		if(rand.nextInt(25) == 0) {
            			world.setBlock(x + 2 + j * 4, y, z + 3 + i * 4, Blocks.torch, 4, 2);
            			if(rand.nextInt(4) == 0)
            				placeChest(world, x + 2 + j * 4, y - 1, z + 3 + i * 4, 4, rand);
            			}
            	}
            }
    	}
	}
    
    private void placeChest(World world, int x, int y, int z, int directionMeta, Random rand) {
		world.setBlock(x, y, z, Blocks.chest, directionMeta, 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y, z);
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 3, 10);
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

	public static void openBossPortal(World world, int x, int y, int z) {
		System.out.println("X: "+x+" Y: "+y+" Z: "+z +" Destroys walls and spawns shit here");
	
		for(int d = 0; d < 4; d++) {
			for(int wx = 0 + d; wx < 9; wx++) {
				world.playAuxSFXAtEntity(null, 2001, x + 11 + wx, y + d, z + 21, Block.getIdFromBlock(world.getBlock(x + 11 + wx, y + d, z + 21)));
				world.setBlockToAir(x + 11 + wx, y + d, z + 21);
				world.playAuxSFXAtEntity(null, 2001, x + 11 + wx, y + d, z + 22, Block.getIdFromBlock(world.getBlock(x + 11 + wx, y + d, z + 22)));
				world.setBlockToAir(x + 11 + wx, y + d, z + 22);
				
				world.playAuxSFXAtEntity(null, 2001, x + 21, y + d, z + 11 + wx, Block.getIdFromBlock(world.getBlock(x + 21, y + d, z + 11 + wx)));
				world.setBlockToAir(x + 21, y + d, z + 11 + wx);
				world.playAuxSFXAtEntity(null, 2001, x + 22, y + d, z + 11 + wx, Block.getIdFromBlock(world.getBlock(x + 22, y + d, z + 11 + wx)));
				world.setBlockToAir(x + 22, y + d, z + 11 + wx);
				
				world.playAuxSFXAtEntity(null, 2001, x + 21, y + d, z + 32 - wx, Block.getIdFromBlock(world.getBlock(x + 21, y + d, z + 32 - wx)));
				world.setBlockToAir(x + 21, y + d, z + 32 - wx);
				world.playAuxSFXAtEntity(null, 2001, x + 22, y + d, z + 32 - wx, Block.getIdFromBlock(world.getBlock(x + 22, y + d, z + 32 - wx)));
				world.setBlockToAir(x + 22, y + d, z + 32 - wx);
				
				world.playAuxSFXAtEntity(null, 2001, x + 32 - wx, y + d, z + 21, Block.getIdFromBlock(world.getBlock(x + 32 - wx, y + d, z + 21)));
				world.setBlockToAir(x + 32 - wx, y + d, z + 21);
				world.playAuxSFXAtEntity(null, 2001, x + 32 - wx, y + d, z + 22, Block.getIdFromBlock(world.getBlock(x + 32 - wx, y + d, z + 22)));
				world.setBlockToAir(x + 32 - wx, y + d, z + 22);
			}
	
			for(int dx = x + 20; dx < x + 24; dx ++)
				for(int dz = z + 20; dz < z + 24; dz ++)
					if(!world.isAirBlock(dx, y + d, dz)) {
						world.playAuxSFXAtEntity(null, 2001, dx, y + d, dz, Block.getIdFromBlock(world.getBlock(dx, y + d, dz)));
						world.setBlockToAir(dx, y + d, dz);
					}
		}
		
	}
}
