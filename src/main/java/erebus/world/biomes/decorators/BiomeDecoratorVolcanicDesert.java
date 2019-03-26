package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenDesertRockGneiss;
import erebus.world.feature.decoration.WorldGenErebusLakesWithEdge;
import erebus.world.feature.decoration.WorldGenScorchedWood;
import erebus.world.feature.plant.WorldGenPricklyPairPatch;
import erebus.world.feature.structure.WorldGenAntlionLair;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;


public class BiomeDecoratorVolcanicDesert extends BiomeDecoratorBaseErebus {

	private final WorldGenScorchedWood genScorchedWood = new WorldGenScorchedWood();
	protected final WorldGenPricklyPairPatch genPricklyPair = new WorldGenPricklyPairPatch();
	private final WorldGenAntlionLair genAntlionLair = new WorldGenAntlionLair();
	private final WorldGenErebusLakesWithEdge genLavaLakes = new WorldGenErebusLakesWithEdge(Blocks.FLOWING_LAVA, ModBlocks.VOLCANIC_ROCK);
	private final WorldGenDesertRockGneiss genRocks = new WorldGenDesertRockGneiss();
	

	@Override
	public void populate() {
		for (attempt = 0; attempt < 35; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.SAND, pos))
				genLavaLakes.generate(world, world.rand, pos.up());
		}
	}

	@Override
	public void decorate() {
		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (world.getBlockState(pos) == ModBlocks.UMBERSTONE.getDefaultState() && world.isAirBlock(pos.down())) {
				world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState());
				world.immediateBlockTick(pos, Blocks.FLOWING_LAVA.getDefaultState(), rand);
			}
		}

		for (attempt = 0; attempt < 22; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();
			BlockPos pos = new BlockPos(xx, yy, zz);
			if (checkSurface(SurfaceType.SAND, pos) && !world.isAirBlock(pos.down(2))) {
				genScorchedWood.generate(world, rand, pos);
				if (rand.nextInt(4) != 0)
					break;
			}
		}

		if (rand.nextInt(20) == 0)
			for (attempt = 0; attempt < rand.nextInt(4); attempt++) {
				xx = x + offsetXZ();
				yy = 25 + rand.nextInt(75);
				zz = z + offsetXZ();

				for (; yy > 20; yy--) {
					BlockPos pos = new BlockPos(xx, yy, zz);
					if (checkSurface(SurfaceType.SAND, pos)) {
						genPricklyPair.generate(world, rand, pos.up());
						break;
					}
				}
			}

		if (rand.nextInt(5) == 0)
			for (int attempt = 0; attempt < 300; attempt++)
				if (genAntlionLair.generate(world, rand, new BlockPos(x + 5 + rand.nextInt(6) + 8, 15 + rand.nextInt(35), z + 5 + rand.nextInt(6) + 8)))
					break;

		if (rand.nextBoolean() && rand.nextBoolean()) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 100; yy > 20; yy--) {
				BlockPos pos = new BlockPos(xx, yy, zz);
				if (checkSurface(SurfaceType.SAND, pos)) {
					if(genRocks.generate(world, rand, pos))
						break;
				}
			}
		}
	}

	@Override
	public void generateFeature(FeatureType featureType) {
		if (featureType == FeatureType.RED_GEM)
			for (attempt = 0; attempt < 10; attempt++)
				genRedGem.generate(world, rand, new BlockPos(x + offsetXZ(), rand.nextInt(64), z + offsetXZ()));
		else
			super.generateFeature(featureType);
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres) {
		switch (oreType) {
			case COAL:
				oreGen.setChance(0F);
				break;
			case GOLD:
				oreGen.setIterations(extraOres ? 1 : 2, extraOres ? 2 : 3);
				break; // less common
			case DIAMOND:
				oreGen.setType(OreType.DIAMOND_ENCRUSTED).setChance(0.4F).setIterations(1, 2).setOreAmount(2).setY(5, 16);
				break; // clusters of 4, ~7 times smaller area thus lowered chance and iterations
			case JADE:
				oreGen.setIterations(0, 2);
				break; // less common
			case FOSSIL:
				oreGen.setChance(0.25F).setIterations(0, 1);
				break; // much more rare
		}
	}
}