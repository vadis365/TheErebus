package erebus.world.biomes;

import erebus.ModBiomes;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityFly;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorBaseErebus;
import erebus.world.biomes.decorators.BiomeDecoratorElysianFields;

public class BiomeElysianFields extends BiomeBaseErebus {
	public BiomeElysianFields(BiomeProperties properties) {
		this(properties, new BiomeDecoratorElysianFields());
	}

	public BiomeElysianFields(BiomeProperties properties, BiomeDecoratorBaseErebus decorator) {
		super(properties, decorator);

		properties.setBaseBiome("Elysian Fields");
		properties.setTemperature(0.85F);
		properties.setRainDisabled();
		setColors(0xC6FF54);
		setFog(213, 228, 127);
		//setWeight(20);
/*
		spawningGradual.add(new SpawnEntry(EntityGrasshopper.class, 10).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityGlowWorm.class, 10).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityTitanBeetle.class, 8).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityWorkerBee.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityMoth.class, 15).setGroupSize(2, 3));
		
		spawningGradual.add(new SpawnEntry(EntityBeetle.class, 20).setGroupSize(3, 5));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 18).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityCicada.class, 100).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityWheatWeevil.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityVelvetWorm.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBotFly.class, 10).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
		*/
		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(8, 8));
		spawningGradual.add(new SpawnEntry(EntityDragonfly.class, 10).setGroupSize(1, 3));
	}

	@Override
	public BiomeBaseErebus getRandomSubBiome(int randomValue) {
		return randomValue < 40 ? ModBiomes.FIELDS_SUB_FOREST : null;
	}

	public static class BiomeElysianForest extends BiomeElysianFields {
		public BiomeElysianForest(BiomeProperties properties) {
			super(properties, new BiomeDecoratorElysianFields.BiomeDecoratorElysianForest());

			properties.setBaseBiome("Elysian Forest");
			setColors(0x74BF26);
		}
	}
}