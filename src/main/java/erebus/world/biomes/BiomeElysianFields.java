package erebus.world.biomes;

import erebus.ModBiomes;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityCicada;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityMoth;
import erebus.entity.EntityTitanBeetle;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWheatWeevil;
import erebus.entity.EntityWorkerBee;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorBaseErebus;
import erebus.world.biomes.decorators.BiomeDecoratorElysianFields;

public class BiomeElysianFields extends BiomeBaseErebus
{
	public BiomeElysianFields(int biomeID)
	{
		this(biomeID, new BiomeDecoratorElysianFields());
	}

	public BiomeElysianFields(int biomeID, BiomeDecoratorBaseErebus decorator)
	{
		super(biomeID, decorator);

		setBiomeName("Elysian Fields");
		setColors(0xC6FF54);
		setFog(213, 228, 127);
		setTemperatureRainfall(0.85F, 0.5F);
		setWeight(20);

		spawningGradual.add(new SpawnEntry(EntityGrasshopper.class, 4).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityGlowWorm.class, 4).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityTitanBeetle.class, 4).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityWorkerBee.class, 14).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityMoth.class, 10).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(2, 2));
		spawningGradual.add(new SpawnEntry(EntityBeetle.class, 6).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 4).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityCicada.class, 100).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityWheatWeevil.class, 6).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityDragonfly.class, 5).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityVelvetWorm.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBotFly.class, 10).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
	}

	@Override
	public BiomeBaseErebus getRandomSubBiome(int randomValue)
	{
		return randomValue < 40 ? ModBiomes.fieldsSubForest : null;
	}

	public static class BiomeElysianForest extends BiomeElysianFields
	{
		public BiomeElysianForest(int biomeID)
		{
			super(biomeID, new BiomeDecoratorElysianFields.BiomeDecoratorElysianForest());

			setBiomeName("Elysian Forest");
			setColors(0x74BF26);
		}
	}
}