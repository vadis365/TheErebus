package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.blocks.BlockDarkFruitVine;
import erebus.blocks.BlockSmallPlant;
import erebus.blocks.EnumWood;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenAmberGround;
import erebus.world.feature.decoration.WorldGenAmberUmberstone;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.decoration.WorldGenQuickSand;
import erebus.world.feature.plant.WorldGenBamboo;
import erebus.world.feature.plant.WorldGenMelon;
import erebus.world.feature.plant.WorldGenTurnips;
import erebus.world.feature.structure.WorldGenWaspDungeon;
import erebus.world.feature.tree.WorldGenAsperTree;
import erebus.world.feature.tree.WorldGenErebusHugeTree;
import erebus.world.feature.tree.WorldGenErebusTrees;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import erebus.world.feature.tree.WorldGenMossbarkTree;
import erebus.world.feature.tree.WorldGenTallJungleTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;


public class BiomeDecoratorUndergroundJungle extends BiomeDecoratorBaseErebus {
	private final WorldGenWaspDungeon genWaspDungeon = new WorldGenWaspDungeon();
	private final WorldGenQuickSand genQuickSand = new WorldGenQuickSand();
	private final WorldGenPonds genPonds = new WorldGenPonds();
	private final WorldGenAmberGround genAmberGround = new WorldGenAmberGround();
	private final WorldGenAmberUmberstone genAmberUmberstone = new WorldGenAmberUmberstone();

	private final WorldGenBush genMushroomsBrown = new WorldGenBush(Blocks.BROWN_MUSHROOM);
	private final WorldGenBush genMushroomsRed = new WorldGenBush(Blocks.RED_MUSHROOM);
	private final WorldGenBigMushroom genBigMushroomRed = new WorldGenBigMushroom(Blocks.BROWN_MUSHROOM_BLOCK);
	private final WorldGenBigMushroom genBigMushroomBrown = new WorldGenBigMushroom(Blocks.RED_MUSHROOM_BLOCK);
	
// TODO FIX the below trees
	private static final IBlockState JUMGLE_TRUNK = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
	private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	    
	private final WorldGenerator genTreeMahogany = new WorldGenErebusTrees(true, 5, false, EnumWood.MAHOGANY, Blocks.VINE); // should have thorns not vines
	private final WorldGenerator genTreeMahoganyLarge = new WorldGenErebusHugeTree(true, true, EnumWood.MAHOGANY);
	private final WorldGenerator genTreeJungle = new WorldGenTrees(true, 6, JUMGLE_TRUNK, JUNGLE_LEAF, true);
	private final WorldGenerator genTreeJungleLarge = new WorldGenMegaJungle(false, 10, 20, JUMGLE_TRUNK, JUNGLE_LEAF);
	private final WorldGenerator genTreeMossbark = new WorldGenMossbarkTree();
	private final WorldGenerator genTreeAsper = new WorldGenAsperTree();
	private final WorldGenerator genTreeJungleTall = new WorldGenTallJungleTree();
	private final WorldGenerator genTreeEucalyptus = new WorldGenEucalyptusTree();

	private final WorldGenerator genBamboo = new WorldGenBamboo(13, false);
	private final WorldGenerator genTurnips = new WorldGenTurnips();
	private final WorldGenerator genMelons = new WorldGenMelon();
	
	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	@Override
	protected void populate() {
		for (attempt = 0; attempt < 35; attempt++) {
			xx = x + 16;
			yy = rand.nextInt(120);
			zz = z + 16;
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos)) {
				genPonds.prepare((rand.nextDouble() + 0.7D) * 1.5D);
				genPonds.generate(world, rand, pos.up());
			}
		}
	}

	@Override
	protected void decorate() {
		
		if (rand.nextInt(3) == 0)
			for (attempt = 0; attempt < 5; attempt++)
				if (genAmberUmberstone.generate(world, rand, new BlockPos(x + offsetXZ(), rand.nextInt(120), z + offsetXZ())))
					break;

		if (rand.nextInt(6) == 0)
			for (attempt = 0; attempt < 4; attempt++)
				if (genAmberGround.generate(world, rand, new BlockPos(x + offsetXZ(), 10 + rand.nextInt(40), z + offsetXZ())))
					break;

		if (rand.nextInt(60) == 0)
			for (attempt = 0; attempt < 5; attempt++)
				if (genWaspDungeon.generate(world, rand, new BlockPos(x + offsetXZ(), 127, z + offsetXZ())))
					break;

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);

			if (checkSurface(SurfaceType.GRASS, pos)) {
				genQuickSand.generate(world, rand, pos);
				break;
			}
		}

		for (attempt = 0; attempt < 2200; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos)) {
				WorldGenerator treeGen = null;
				int r = rand.nextInt(31);

				if (r <= 6) {
					xx = x + 9 + rand.nextInt(14);
					zz = z + 9 + rand.nextInt(14);
					pos = new BlockPos(xx, yy, zz);
					treeGen = genTreeJungleLarge;
				} else if (r <= 11)
					treeGen = genTreeMahogany;
				else
				if (r <= 16) {
					xx = x + 9 + rand.nextInt(14);
					zz = z + 9 + rand.nextInt(14);
					pos = new BlockPos(xx, yy, zz);
					((WorldGenErebusHugeTree) genTreeMahoganyLarge).prepare(20 + rand.nextInt(5));
					treeGen = genTreeMahoganyLarge;
				} else if (r <= 20)
					treeGen = genTreeAsper;
				else if (r <= 23)
					treeGen = genTreeJungle;
				else if (r <= 26)
					treeGen = genTreeMossbark;
				else if (r <= 28)
					treeGen = genTreeJungleTall;
				else
					treeGen = genTreeEucalyptus;

				if (treeGen != null)
					treeGen.generate(world, rand, pos.up());
			}
		}

		genMushroomsBrown.generate(world, rand, new BlockPos(x + offsetXZ(), rand.nextInt(128), z + offsetXZ()));
		genMushroomsRed.generate(world, rand, new BlockPos(x + offsetXZ(), rand.nextInt(128), z + offsetXZ()));

		for (attempt = 0; attempt < 12; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos)) {
				genBigMushroomRed.generate(world, rand, pos.up());
				break;
			}
		}

		for (attempt = 0; attempt < 20; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos)) {
				genBigMushroomBrown.generate(world, rand, pos.up());
				break;
			}
		}

		if (rand.nextInt(11) == 0) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			for (yy = 90; yy > 20; yy--) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos))
					if (genBamboo.generate(world, rand, pos.up()))
						break;
			}
		}

		for (attempt = 0; attempt < 50; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				if (world.isAirBlock(pos.up())) {
					world.setBlockState(pos.up(), ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, BlockSmallPlant.EnumSmallPlantType.FIDDLE_HEAD), 2);
				}
		}

		for (attempt = 0; attempt < 30; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			
			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(pos.up(2))) {
						Blocks.DOUBLE_PLANT.placeAt(world, pos.up(), BlockDoublePlant.EnumPlantType.FERN, 2);
						break;
					} else
						if (world.isAirBlock(pos.up()))
							world.setBlockState(pos.up(), ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, BlockSmallPlant.EnumSmallPlantType.FERN), 2);
					break;
				}
			}
		}

		IBlockState tallGrassState = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);
		for (attempt = 0; attempt < 250; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			for (yy = rand.nextInt(3) == 0 ? 40 + rand.nextInt(35) : 22; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(pos.up(2))) {
						Blocks.DOUBLE_PLANT.placeAt(world, pos.up(), BlockDoublePlant.EnumPlantType.GRASS, 2);
					} else if (world.isAirBlock(pos.up())) {
						world.setBlockState(pos.up(), tallGrassState);
					}
					break;
				}
			}
		}

		int offset;
		for (attempt = 0; attempt < 800; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (world.isAirBlock(pos)) {
				offset = rand.nextInt(4);

				if (!world.getBlockState(new BlockPos(xx + offsetX[offset], yy, zz + offsetZ[offset])).isNormalCube())
					continue;

				for (int vineY = rand.nextInt(30); vineY > 0; vineY--)
					if (world.isAirBlock(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]))) {
						if (offset == 3)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), Blocks.VINE.getDefaultState().withProperty(BlockVine.SOUTH, true), 3);
						if (offset == 2)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), Blocks.VINE.getDefaultState().withProperty(BlockVine.NORTH, true), 3);
						if (offset == 1)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), Blocks.VINE.getDefaultState().withProperty(BlockVine.EAST, true), 3);
						if (offset == 0)
							world.setBlockState(new BlockPos(xx + offsetX[offset], yy - vineY, zz + offsetZ[offset]), Blocks.VINE.getDefaultState().withProperty(BlockVine.WEST, true), 3);
					}
			}
		}

		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (!world.getBlockState(pos).isNormalCube())
				continue;

			for (int hangerY = rand.nextInt(20); hangerY > 0; hangerY--)
				if (world.isAirBlock(pos.down(hangerY)))
					world.setBlockState(pos.down(hangerY), ModBlocks.DARK_FRUIT_VINE.getDefaultState().withProperty(BlockDarkFruitVine.DARK_VINE_AGE, Integer.valueOf(4)), 2);
		}

		if (rand.nextInt(3) == 0)
			for (attempt = 0; attempt < 20; ++attempt) {
				xx = x + offsetXZ();
				yy = 15 + rand.nextInt(90);
				zz = z + offsetXZ();
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos))
					genTurnips.generate(world, rand, pos.up());
			}
		else if (rand.nextBoolean() || rand.nextBoolean())
			for (attempt = 0; attempt < 10; ++attempt) {
				xx = x + offsetXZ();
				yy = 15 + rand.nextInt(90);
				zz = z + offsetXZ();
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos))
					genMelons.generate(world, rand, pos.up());
			}
			
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
		switch (oreType) {
			case COAL:
				oreGen.setIterations(extraOres ? 3 : 4).setY(27, 48).generate(world, rand, x, z); // generate first half above caves
				oreGen.setIterations(extraOres ? 4 : 6).setOreAmount(12, 14).setY(6, 24); // setup more & bigger clusters below caves, let the base code generate
				break;
			case DIAMOND:
				oreGen.setChance(0.1F).setY(6, 16);
				break; // ~7 times smaller area, thus lowered chance
			case JADE:
				oreGen.setOreAmount(5);
				break; // 1 more vein
			case PETRIFIED_WOOD:
				oreGen.setChance(0.65F).setY(6, 64);
				break; // ~2 times smaller area, thus lowered chance
			case FOSSIL:
				oreGen.setChance(0.25F);
				break; // ~ 1/3 chance
		}
	}
}