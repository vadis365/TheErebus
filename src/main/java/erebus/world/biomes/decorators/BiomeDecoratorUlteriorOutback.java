package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.blocks.BlockDoubleHeightPlant;
import erebus.blocks.BlockSmallPlant;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenRottenAcacia;
import erebus.world.feature.structure.WorldGenDungPile;
import erebus.world.feature.tree.WorldGenBalsamTree;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenerator;


public class BiomeDecoratorUlteriorOutback extends BiomeDecoratorBaseErebus {

	private final WorldGenRottenAcacia genRottenAcacia = new WorldGenRottenAcacia();
	private final WorldGenerator genTreeAcacia = new WorldGenSavannaTree(true);
	private final WorldGenerator genTreeEucalyptus = new WorldGenEucalyptusTree();
	private final WorldGenerator genTreeBalsam = new WorldGenBalsamTree();
	private final WorldGenDungPile genDungPile = new WorldGenDungPile();

	@Override
	public void decorate() {
		for (attempt = 0; attempt < 240; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextInt(2) + 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (world.getBlockState(pos) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) && world.isAirBlock(pos.up())) {
					if (rand.nextInt(3) == 0)
						world.setBlockState(pos, Blocks.GRASS.getDefaultState(), 2);
					else
						world.setBlockState(pos, Blocks.DIRT.getDefaultState(), 2);
					break;
				}
			}
		}
		
		for (attempt = 0; attempt < rand.nextInt(3); attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(25) * (1 + rand.nextInt(3)); // more likely in lower levels
			zz = z + offsetXZ();

			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.MIXED, pos))
				genRottenAcacia.generate(world, rand, pos.up());
		}

		if (rand.nextBoolean())
			for (attempt = 0; attempt < 30; attempt++) {
				xx = x + offsetXZ();
				yy = 20 + rand.nextInt(80);
				zz = z + offsetXZ();
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					genTreeAcacia.generate(world, rand, pos.up());
					if (rand.nextBoolean())
						break;
				}
			}

		if (rand.nextBoolean())
			for (attempt = 0; attempt < 30; attempt++) {
				xx = x + offsetXZ();
				yy = 20 + rand.nextInt(80);
				zz = z + offsetXZ();
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					genTreeBalsam.generate(world, rand, pos.up());
					if (rand.nextBoolean())
						break;
				}
			}

		if (rand.nextBoolean() && rand.nextBoolean()) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 100; yy > 20; yy--) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.MIXED, pos)) {
					if(genDungPile.generate(world, rand, pos.up()))
						break;
				}
			}
		}

		for (attempt = 0; attempt < 420; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos) && world.isAirBlock(pos.up()) && world.isAirBlock(pos.up(2))) {
				ModBlocks.DOUBLE_PLANT.placeAt(world, pos.up(), BlockDoubleHeightPlant.EnumPlantType.DROUGHTED_SHRUB, 2);
				break;
			}
		}
		
		for (attempt = 0; attempt < 16; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.GRASS, pos))
				if (world.isAirBlock(pos.up())) {
					world.setBlockState(pos.up(), ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, BlockSmallPlant.EnumSmallPlantType.DESERT_SHRUB), 2);
					break;
				}
		}

		for (attempt = 0; attempt < 50; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(pos.up()) && world.isAirBlock(pos.up(2))) {
						Blocks.DOUBLE_PLANT.placeAt(world, pos.up(), BlockDoublePlant.EnumPlantType.GRASS, 2);
					} else if (rand.nextInt(80) == 0 && world.isAirBlock(pos.up()))
						world.setBlockState(pos.up(), ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, BlockSmallPlant.EnumSmallPlantType.FIRE_BLOOM), 2);
					else if (world.isAirBlock(pos.up()))
						world.setBlockState(pos.up(), Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS), 2);
					break;
				}
			}
		}

		if (rand.nextBoolean())
			for (attempt = 0; attempt < 180; attempt++) {
				xx = x + offsetXZ();
				yy = 20 + rand.nextInt(80);
				zz = z + offsetXZ();
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.GRASS, pos))
					if (genTreeEucalyptus.generate(world, rand, pos.up()) && rand.nextBoolean())
						break;
			}
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
		switch (oreType) {
			case COAL:
				oreGen.setChance(0.85F).setIterations(extraOres ? 2 : 3).setOreAmount(7, 10).setY(5, 56);
				break; // less common, lowered amount too, also ~2 times smaller area
			case EMERALD:
				oreGen.setIterations(1, 3);
				break; // one more vein
			case DIAMOND:
				oreGen.setIterations(3, 4);
				break; // one more vein
			case PETRIFIED_WOOD:
				oreGen.setIterations(extraOres ? 1 : 2, extraOres ? 2 : 3).setY(20, 64).setCheckArea(3);
				break; // more common, but ~1.5 times smaller area
			case FOSSIL:
				oreGen.setChance(0.25F);
				break; // more rare
		}
	}

	@Override
	public void generateFeature(FeatureType featureType) {
		if (featureType == FeatureType.RED_GEM)
			for (attempt = 0; attempt < 8; attempt++)
				genRedGem.generate(world, rand, new BlockPos(x + offsetXZ(), 64 + rand.nextInt(60), z + offsetXZ()));
		else
			super.generateFeature(featureType);
	}
}