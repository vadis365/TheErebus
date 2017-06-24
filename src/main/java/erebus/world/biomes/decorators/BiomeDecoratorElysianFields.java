package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.blocks.BlockDarkFruitVine;
import erebus.blocks.BlockSmallPlant;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.plant.WorldGenGiantFlowers;
import erebus.world.feature.plant.WorldGenNettlePatch;
import erebus.world.feature.tree.WorldGenCypressTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;


public class BiomeDecoratorElysianFields extends BiomeDecoratorBaseErebus {

	protected final WorldGenPonds genPonds = new WorldGenPonds();
	protected final WorldGenerator genTreeCypress = new WorldGenCypressTree();
	protected final WorldGenerator genGiantFlowers = new WorldGenGiantFlowers();

	//protected final WorldGenerator genMossPatch = new WorldGenMossPatch(0);
	protected final WorldGenNettlePatch genNettle = new WorldGenNettlePatch();
	
	protected boolean generateFlowers = true;

	@Override
	protected void populate() {
		if (rand.nextInt(3) == 0)
			for (attempt = 0; attempt < 25; attempt++) {
				xx = x + 16;
				yy = 20 + rand.nextInt(90);
				zz = z + 16;
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					genPonds.prepare((rand.nextDouble() + 0.7D) * 1.5D);
					genPonds.generate(world, rand, pos.up());
					if (rand.nextBoolean())
						break;
				}
			}
	}

	@Override
	protected void decorate() {
		//System.out.println("Elysian Fields Decorating");
		for (attempt = 0; attempt < 105; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			yy = 20 + rand.nextInt(80);
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos)) {
				genTreeCypress.generate(world, rand, pos.up());
				break;
			}
		}

		if (generateFlowers && rand.nextBoolean())
			for (int attempt = 0, xx, yy, zz; attempt < 65; attempt++) {
				xx = x + offsetXZ();
				yy = 15 + rand.nextInt(90);
				zz = z + offsetXZ();
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					genGiantFlowers.generate(world, rand, pos.up());
					break;
				}
			}

		if (rand.nextInt(6) == 0)
			for (attempt = 0; attempt < rand.nextInt(4); attempt++) {
				xx = x + offsetXZ();
				yy = 25 + rand.nextInt(75);
				zz = z + offsetXZ();
				BlockPos pos = new BlockPos(xx, yy, zz);
				for (; yy > 20; yy--)
					if (checkSurface(SurfaceType.GRASS, pos.up(yy))) {
						genNettle.generate(world, rand, pos.up(yy + 1));
						break;
					}
			}

		IBlockState tallGrassState = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);
		for (attempt = 0; attempt < 850; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			for (yy = rand.nextInt(3) == 0 ? 40 + rand.nextInt(35) : 22; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(pos.up(2))) {
						Blocks.DOUBLE_PLANT.placeAt(world, pos.up(), BlockDoublePlant.EnumPlantType.GRASS, 2);
						break;
					} else if (world.isAirBlock(pos.up())) {
						world.setBlockState(pos.up(), tallGrassState);
						break;
					}
				}
			}
		}

		for (attempt = 0; attempt < 12; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();
			
			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.MIXED, pos)) {
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
/*
		for (attempt = 0; attempt < 5; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.getBlock(xx, yy - 1, zz) == Blocks.grass && world.isAirBlock(xx, yy, zz) && world.isAirBlock(xx, yy + 1, zz)) {
				world.setBlock(xx, yy, zz, ModBlocks.weepingBlue, 0, 2);
				world.setBlock(xx, yy + 1, zz, ModBlocks.weepingBlue, 8, 2);
			}
		}
*/
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
/*
		for (attempt = 0; attempt < 15; attempt++) {
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.isAirBlock(xx, yy, zz))
				genMossPatch.generate(world, rand, xx, yy, zz);
		}

		for (attempt = 0; attempt < 15; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.getBlock(xx, yy - 1, zz) == Blocks.grass && world.isAirBlock(xx, yy, zz) && world.isAirBlock(xx, yy + 1, zz)) {
				world.setBlock(xx, yy, zz, ModBlocks.waterFlower, 0, 2);
				world.setBlock(xx, yy + 1, zz, ModBlocks.waterFlower, 8, 2);
			}
		}*/
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
		switch (oreType) {
			case COAL:
				oreGen.setIterations(extraOres ? 2 : 3, extraOres ? 3 : 4).setY(5, 48);
				break; // ~2.5 times smaller area, thus less iterations
			case IRON:
				oreGen.setChance(0.75F).setIterations(extraOres ? 2 : 3, extraOres ? 4 : 5).setY(5, 42);
				break; // ~3 times smaller area, thus lower chance and
			// iterations
			case GOLD:
				oreGen.setIterations(extraOres ? 2 : 3);
				break; // 2 veins less
			case EMERALD:
				oreGen.setIterations(2, 4).setCheckArea(2);
				break; // 2 veins more
			case JADE:
				oreGen.setIterations(2, 5);
				break; // 1 vein more
			case PETRIFIED_WOOD:
				oreGen.setChance(0F);
				break;
			case FOSSIL:
				oreGen.setChance(0.25F).setOreAmount(5, 8);
				break; // double chance, lower amount per vein
		}
	}

	@Override
	public void generateFeature(FeatureType featureType) {
		if (featureType == FeatureType.RED_GEM)
			for (attempt = 0; attempt < 2 + rand.nextInt(2); attempt++)
				genRedGem.generate(world, rand, new BlockPos(x + offsetXZ(), 64 + rand.nextInt(60), z + offsetXZ()));
		else
			super.generateFeature(featureType);
	}

	


	public static class BiomeDecoratorElysianForest extends BiomeDecoratorElysianFields {
		private static final IBlockState OAK_TRUNK = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
		private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
		private static final IBlockState BIRCH_TRUNK = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
		private static final IBlockState BIRCH_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
		private final WorldGenerator genOakTree = new WorldGenTrees(false, 5, OAK_TRUNK, OAK_LEAF, false);
		private final WorldGenerator genBirchTree = new WorldGenTrees(false, 5, BIRCH_TRUNK, BIRCH_LEAF, false);

		public BiomeDecoratorElysianForest() {
			generateFlowers = false;
		}

		@Override
		protected void populate() {
		if (rand.nextInt(4) == 0)
			for (attempt = 0; attempt < 45; attempt++) {
				xx = x + 16;
				yy = 20 + rand.nextInt(90);
				zz = z + 16;
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					genPonds.prepare((rand.nextDouble() + 0.7D) * 1.5D);
					genPonds.generate(world, rand, pos.up());
					if (rand.nextBoolean())
						break;
				}
			}
			super.populate();
		}
	
		@Override
		public void decorate() {
			for (attempt = 0; attempt < 400; attempt++) {
				xx = x + offsetXZ();
				zz = z + offsetXZ();
				yy = 20 + rand.nextInt(80);
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos))
					if (rand.nextBoolean())
						genOakTree.generate(world, rand, pos.up());
					else
						genBirchTree.generate(world, rand, pos.up());
			}

			for (attempt = 0; attempt < 300; attempt++) {
				xx = x + offsetXZ();
				zz = z + offsetXZ();
				yy = 20 + rand.nextInt(80);
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos.up()))
					genTreeCypress.generate(world, rand, pos.up());
			}

			super.decorate();
		}
	}
}