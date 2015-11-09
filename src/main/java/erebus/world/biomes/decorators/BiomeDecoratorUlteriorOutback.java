package erebus.world.biomes.decorators;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenRottenAcacia;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import erebus.world.feature.tree.WorldGenSapTree;

public class BiomeDecoratorUlteriorOutback extends BiomeDecoratorBaseErebus {

	private final WorldGenRottenAcacia genRottenAcacia = new WorldGenRottenAcacia();

	private final WorldGenerator genTreeAcacia = new WorldGenSavannaTree(true);
	private final WorldGenerator genTreeEucalyptus = new WorldGenEucalyptusTree();
	private final WorldGenerator genTreeSap = new WorldGenSapTree();

	@Override
	public void decorate() {
		for (attempt = 0; attempt < 240; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextInt(2) + 1)
				if (world.getBlock(xx, yy, zz) == Blocks.sand && world.isAirBlock(xx, yy + 1, zz)) {
					if (rand.nextInt(3) == 0)
						world.setBlock(xx, yy, zz, Blocks.grass);
					else
						world.setBlock(xx, yy, zz, Blocks.dirt, 1, 2);
					break;
				}
		}

		for (attempt = 0; attempt < rand.nextInt(3); attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(25) * (1 + rand.nextInt(3)); // more likely in lower levels
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.MIXED, xx, yy, zz))
				genRottenAcacia.generate(world, rand, xx, yy, zz);
		}

		if (rand.nextBoolean())
			for (attempt = 0; attempt < 20; attempt++) {
				xx = x + offsetXZ();
				yy = 20 + rand.nextInt(80);
				zz = z + offsetXZ();

				if (checkSurface(SurfaceType.GRASS, xx, yy, zz)) {
					genTreeAcacia.generate(world, rand, xx, yy, zz);
					if (rand.nextBoolean())
						break;
				}
			}

		for (attempt = 0; attempt < 420; attempt++) {
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.SAND, xx, yy, zz) && world.isAirBlock(xx, yy + 1, zz)) {
				world.setBlock(xx, yy, zz, ModBlocks.droughtedShrub, 0, 2);
				world.setBlock(xx, yy + 1, zz, ModBlocks.droughtedShrub, 8, 2);
			}
		}

		for (attempt = 0; attempt < 194; attempt++) {
			xx = x + offsetXZ();
			zz = z + offsetXZ();

			for (yy = 20; yy < 100; yy += rand.nextBoolean() ? 2 : 1)
				if (checkSurface(SurfaceType.GRASS, xx, yy, zz)) {
					if (rand.nextInt(10) == 0 && world.isAirBlock(xx, yy + 1, zz)) {
						world.setBlock(xx, yy, zz, Blocks.double_plant, 2, 2);
						world.setBlock(xx, yy + 1, zz, Blocks.double_plant, 10, 2);
					} else if (rand.nextInt(80) == 0)
						world.setBlock(xx, yy, zz, ModBlocks.fireBloom, 0, 2);
					else
						world.setBlock(xx, yy, zz, Blocks.tallgrass, 1, 2);

					break;
				}
		}

		if (rand.nextBoolean())
			for (attempt = 0; attempt < 180; attempt++) {
				xx = x + offsetXZ();
				yy = 20 + rand.nextInt(80);
				zz = z + offsetXZ();

				if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
					if (genTreeEucalyptus.generate(world, rand, xx, yy, zz) && rand.nextBoolean())
						break;
			}

		if (rand.nextBoolean())
			for (attempt = 0; attempt < 20; attempt++) {
				xx = x + offsetXZ();
				yy = 20 + rand.nextInt(80);
				zz = z + offsetXZ();

				if (checkSurface(SurfaceType.GRASS, xx, yy, zz)) {
					genTreeSap.generate(world, rand, xx, yy, zz);
					if (rand.nextBoolean())
						break;
				}
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
		if (featureType == FeatureType.REDGEM)
			for (attempt = 0; attempt < 8; attempt++)
				genRedGem.generate(world, rand, x + offsetXZ(), 64 + rand.nextInt(60), z + offsetXZ());
		else
			super.generateFeature(featureType);
	}
}