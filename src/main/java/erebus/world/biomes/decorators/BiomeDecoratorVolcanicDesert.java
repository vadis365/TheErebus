package erebus.world.biomes.decorators;

import erebus.ModBlocks;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenScorchedWood;
import erebus.world.feature.structure.WorldGenAntlionLair;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class BiomeDecoratorVolcanicDesert extends BiomeDecoratorBaseErebus {
	private final WorldGenAntlionLair genAntlionLair = new WorldGenAntlionLair();
	private final WorldGenLakes genLavaLakes = new WorldGenLakes(Blocks.flowing_lava);
	private final WorldGenScorchedWood genScorchedWood = new WorldGenScorchedWood();

	@Override
	public void populate() {
		for (attempt = 0; attempt < 35; attempt++) {
			xx = x + offsetXZ();
			yy = 15 + rand.nextInt(90);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.SAND, xx, yy, zz))
				genLavaLakes.generate(world, world.rand, xx, yy, zz);
		}
	}

	@Override
	public void decorate() {
		for (attempt = 0; attempt < 10; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();

			if (world.getBlock(xx, yy, zz) == ModBlocks.umberstone && world.isAirBlock(xx, yy - 1, zz)) {
				world.setBlock(xx, yy, zz, Blocks.flowing_lava);
				world.scheduledUpdatesAreImmediate = true;
				Blocks.flowing_lava.updateTick(world, xx, yy, zz, rand);
				world.scheduledUpdatesAreImmediate = false;
			}
		}

		for (attempt = 0; attempt < 22; attempt++) {
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.SAND, xx, yy, zz) && !world.isAirBlock(xx, yy - 2, zz)) {
				genScorchedWood.generate(world, rand, xx, yy, zz);
				if (rand.nextInt(4) != 0)
					break;
			}
		}

		if (rand.nextInt(34) == 0)
			for (int attempt = 0; attempt < 15; attempt++)
				if (genAntlionLair.generate(world, rand, x + 5 + rand.nextInt(6) + 8, 15 + rand.nextInt(35), z + 5 + rand.nextInt(6) + 8))
					break;
	}

	@Override
	public void generateFeature(FeatureType featureType) {
		if (featureType == FeatureType.REDGEM)
			for (attempt = 0; attempt < 10; attempt++)
				genRedGem.generate(world, rand, x + offsetXZ(), rand.nextInt(64), z + offsetXZ());
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