package erebus.world.biomes;

import erebus.entity.EntityBlackAnt;
import erebus.entity.EntityCrushroom;
import erebus.entity.EntityPunchroom;
import erebus.entity.EntitySporeling;
import erebus.entity.EntityZombieAnt;
import erebus.world.biomes.decorators.BiomeDecoratorBaseErebus;
import erebus.world.biomes.decorators.BiomeDecoratorFungalForest;

public class BiomeFungalForest extends BiomeBaseErebus{

	public BiomeFungalForest(int biomeID){
		this(biomeID,new BiomeDecoratorFungalForest());
	}

	public BiomeFungalForest(int biomeID, BiomeDecoratorBaseErebus decorator){
		super(biomeID,decorator);

		setBiomeName("Fungal Forest");
		setColors(0x4E8833);
		setTemperatureRainfall(0.9F,0.95F);
		setWeight(12);

		spawnableCreatureList.add(new SpawnEntryOld(EntitySporeling.class,10,1,1));
		spawnableCreatureList.add(new SpawnEntryOld(EntityBlackAnt.class,10,3,5));
		spawnableMonsterList.add(new SpawnEntryOld(EntityZombieAnt.class,5,1,4));
		spawnableMonsterList.add(new SpawnEntryOld(EntityCrushroom.class,5,1,2));
		spawnableMonsterList.add(new SpawnEntryOld(EntityPunchroom.class,10,1,2));
	}
}