package erebus.world.biomes;

import net.minecraft.init.Blocks;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityFly;
import erebus.entity.EntityMidgeSwarm;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntityTarantula;
import erebus.world.biomes.decorators.BiomeDecoratorUlteriorOutback;

// @formatter:off
public class BiomeUlteriorOutback extends BiomeBaseErebus{

	@SuppressWarnings("unchecked")
	public BiomeUlteriorOutback(int biomeID){
		super(biomeID,new BiomeDecoratorUlteriorOutback());

		setBiomeName("Ulterior Outback");
		setColors(0xC1B668);
		setFog(234,194,114);
		setTemperatureRainfall(1.1F,0.2F);
		setWeight(15);

		spawnableMonsterList.add(new SpawnEntryOld(EntityScytodes.class,30,1,4));
		spawnableMonsterList.add(new SpawnEntryOld(EntityScorpion.class,10,2,2));
		spawnableMonsterList.add(new SpawnEntryOld(EntitySolifuge.class,8,1,2));
		spawnableMonsterList.add(new SpawnEntryOld(EntityBlackWidow.class,5,1,1));
		spawnableMonsterList.add(new SpawnEntryOld(EntitySolifuge.class,5,1,3));
		spawnableMonsterList.add(new SpawnEntryOld(EntityCentipede.class,10,4,8));
		spawnableMonsterList.add(new SpawnEntryOld(EntityBotFly.class,10,2,3));
		spawnableMonsterList.add(new SpawnEntryOld(EntityTarantula.class,8,4,8));
		spawnableMonsterList.add(new SpawnEntryOld(EntityChameleonTick.class,10,1,2));
		spawnableMonsterList.add(new SpawnEntryOld(EntityMidgeSwarm.class,10,1,2));

		spawnableCreatureList.add(new SpawnEntryOld(EntityRhinoBeetle.class,5,1,1));
		spawnableCreatureList.add(new SpawnEntryOld(EntityBeetleLarva.class,8,2,4));
		spawnableCaveCreatureList.add(new SpawnEntryOld(EntityFly.class,10,2,2));

		topBlock = Blocks.sand;
		topBlockMeta = 1;
	}
}
// @formatter:on
