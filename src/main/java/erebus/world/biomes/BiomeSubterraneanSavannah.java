package erebus.world.biomes;

import net.minecraft.init.Blocks;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityWasp;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorSubterraneanSavannah;

public class BiomeSubterraneanSavannah extends BiomeBaseErebus
{
	public BiomeSubterraneanSavannah(int biomeID)
	{
		super(biomeID, new BiomeDecoratorSubterraneanSavannah());

		setBiomeName("Subterranean Savannah");
		setColors(0xB6B957, 0xA3A52D);
		setFog(140, 116, 9);
		setTemperatureRainfall(0.95F, 0.05F);
		setWeight(18);

		spawningGradual.add(new SpawnEntry(EntityGrasshopper.class, 14).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityGlowWorm.class, 14).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityScytodes.class, 35).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityWasp.class, 30).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityTarantula.class, 18).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityScorpion.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityMosquito.class, 60).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(8, 8));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 8).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityBeetle.class, 8).setGroupSize(1, 2));

		topBlock = Blocks.grass;
		fillerBlock = Blocks.dirt;
	}
}