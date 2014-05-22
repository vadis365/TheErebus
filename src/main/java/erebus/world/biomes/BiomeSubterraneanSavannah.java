package erebus.world.biomes;

import net.minecraft.block.Block;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityWasp;
import erebus.world.biomes.decorators.BiomeDecoratorSubterraneanSavannah;

// @formatter:off
public class BiomeSubterraneanSavannah extends BiomeBaseErebus{
	public BiomeSubterraneanSavannah(int biomeID){
		super(biomeID,new BiomeDecoratorSubterraneanSavannah());
		
		setBiomeName("Subterranean Savannah");
		setColors(0xB6B957,0xA3A52D);
		setFog(140,116,9);
		setTemperatureRainfall(0.95F,0.05F);
		setWeight(18);
		
		spawnableCreatureList.add(new SpawnEntry(EntityGrasshopper.class,14,4,8));
		spawnableCreatureList.add(new SpawnEntry(EntityGlowWorm.class,14,4,8));

		spawnableMonsterList.add(new SpawnEntry(EntityScytodes.class,35,1,4));
		spawnableMonsterList.add(new SpawnEntry(EntityWasp.class,30,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityTarantula.class,18,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityScorpion.class,10,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityJumpingSpider.class,10,1,4));
		spawnableMonsterList.add(new SpawnEntry(EntityChameleonTick.class,10,1,2));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityMosquito.class,60,1,3));

		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,8,8));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetleLarva.class,8,2,4));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBeetle.class,8,1,2));

		topBlock = (byte)Block.grass.blockID;
		fillerBlock = (byte)Block.dirt.blockID;
	}
}
// @formatter:on