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
import erebus.world.biomes.decorators.BiomeDecoratorBaseErebus;
import erebus.world.biomes.decorators.BiomeDecoratorElysianFields;

// @formatter:off
public class BiomeElysianFields extends BiomeBaseErebus{
	public BiomeElysianFields(int biomeID){
		this(biomeID,new BiomeDecoratorElysianFields());
	}

	@SuppressWarnings("unchecked")
	public BiomeElysianFields(int biomeID, BiomeDecoratorBaseErebus decorator){
		super(biomeID,decorator);

		setBiomeName("Elysian Fields");
		setColors(0xC6FF54);
		setFog(213,228,127);
		setTemperatureRainfall(0.85F,0.5F);
		setWeight(20);

		spawnableCreatureList.add(new SpawnEntryOld(EntityGrasshopper.class,4,1,3));
		spawnableCreatureList.add(new SpawnEntryOld(EntityGlowWorm.class,4,1,3));
		spawnableCreatureList.add(new SpawnEntryOld(EntityTitanBeetle.class,4,1,1));
		spawnableCreatureList.add(new SpawnEntryOld(EntityWorkerBee.class,14,1,2));
		spawnableCaveCreatureList.add(new SpawnEntryOld(EntityMoth.class,10,2,3));
		spawnableCaveCreatureList.add(new SpawnEntryOld(EntityFly.class,10,2,2));
		spawnableCreatureList.add(new SpawnEntryOld(EntityBeetle.class,6,1,2));
		spawnableCreatureList.add(new SpawnEntryOld(EntityBeetleLarva.class,4,2,3));
		spawnableCreatureList.add(new SpawnEntryOld(EntityCicada.class,100,1,1));
		spawnableCreatureList.add(new SpawnEntryOld(EntityWheatWeevil.class,6,1,2));

		spawnableMonsterList.add(new SpawnEntryOld(EntityDragonfly.class,5,1,3));
		spawnableMonsterList.add(new SpawnEntryOld(EntityVelvetWorm.class,10,1,2));
		spawnableMonsterList.add(new SpawnEntryOld(EntityBotFly.class,10,2,3));
		spawnableMonsterList.add(new SpawnEntryOld(EntityChameleonTick.class,10,1,2));
	}

	@Override
	public BiomeBaseErebus getRandomSubBiome(int randomValue){
		return randomValue < 40 ? ModBiomes.fieldsSubForest : null;
	}

	public static class BiomeElysianForest extends BiomeElysianFields{
		public BiomeElysianForest(int biomeID){
			super(biomeID,new BiomeDecoratorElysianFields.BiomeDecoratorElysianForest());

			setBiomeName("Elysian Forest");
			setColors(0x74BF26);
		}
	}
}
// @formatter:on