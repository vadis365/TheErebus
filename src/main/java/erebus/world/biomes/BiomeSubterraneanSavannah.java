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
import erebus.world.biomes.decorators.BiomeDecoratorSubterraneanSavannah;

// @formatter:off
public class BiomeSubterraneanSavannah extends BiomeBaseErebus{
	@SuppressWarnings("unchecked")
	public BiomeSubterraneanSavannah(int biomeID){
		super(biomeID,new BiomeDecoratorSubterraneanSavannah());

		setBiomeName("Subterranean Savannah");
		setColors(0xB6B957,0xA3A52D);
		setFog(140,116,9);
		setTemperatureRainfall(0.95F,0.05F);
		setWeight(18);

		spawnableCreatureList.add(new SpawnEntryOld(EntityGrasshopper.class,14,4,8));
		spawnableCreatureList.add(new SpawnEntryOld(EntityGlowWorm.class,14,4,8));

		spawnableMonsterList.add(new SpawnEntryOld(EntityScytodes.class,35,1,4));
		spawnableMonsterList.add(new SpawnEntryOld(EntityWasp.class,30,4,8));
		spawnableMonsterList.add(new SpawnEntryOld(EntityTarantula.class,18,4,8));
		spawnableMonsterList.add(new SpawnEntryOld(EntityScorpion.class,10,4,8));
		spawnableMonsterList.add(new SpawnEntryOld(EntityChameleonTick.class,10,1,2));
		spawnableMonsterList.add(new SpawnEntryOld(EntityMosquito.class,60,1,3));

		spawnableCaveCreatureList.add(new SpawnEntryOld(EntityFly.class,10,8,8));
		spawnableCreatureList.add(new SpawnEntryOld(EntityBeetleLarva.class,8,2,4));
		spawnableCreatureList.add(new SpawnEntryOld(EntityBeetle.class,8,1,2));

		topBlock = Blocks.grass;
		fillerBlock = Blocks.dirt;
	}
}
// @formatter:on