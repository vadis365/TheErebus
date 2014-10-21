package erebus.world.biomes.decorators;

import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenRottenAcacia;

public class BiomeDecoratorBetweenlands extends BiomeDecoratorBaseErebus
{
	private final WorldGenRottenAcacia genRottenAcacia = new WorldGenRottenAcacia();


	@Override
	public void decorate()
	{
		for (attempt = 0; attempt < rand.nextInt(3); attempt++)
		{
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(25) * (1 + rand.nextInt(3)); // more likely in lower levels
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.MIXED, xx, yy, zz))
			{
				genRottenAcacia.generate(world, rand, xx, yy, zz);
			}
		}

	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres)
	{
		switch (oreType)
		{
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
				oreGen.setChance(0.06F);
				break; // more rare
		}
	}

	@Override
	public void generateFeature(FeatureType featureType)
	{
		if (featureType == FeatureType.REDGEM)
		{
			for (attempt = 0; attempt < 8; attempt++)
			{
				genRedGem.generate(world, rand, x + offsetXZ(), 64 + rand.nextInt(60), z + offsetXZ());
			}
		} else
		{
			super.generateFeature(featureType);
		}
	}
}