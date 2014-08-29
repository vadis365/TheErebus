package erebus.world.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityFly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityScytodes;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;
import erebus.world.biomes.decorators.BiomeDecoratorUndergroundJungle;

// @formatter:off
public class BiomeUndergroundJungle extends BiomeBaseErebus{
	@SuppressWarnings("unchecked")
	public BiomeUndergroundJungle(int biomeID){
		super(biomeID,new BiomeDecoratorUndergroundJungle());

		setBiomeName("Undergound Jungle");
		setColors(0x53CA37,0x29BC05);
		setFog(8,128,8);
		setTemperatureRainfall(1.35F,0.9F);
		setWeight(22);

		spawnableMonsterList.add(new SpawnEntry(EntityScytodes.class,35,1,4));
		spawnableMonsterList.add(new SpawnEntry(EntityWasp.class,30,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityCentipede.class,10,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityPrayingMantis.class,10,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityJumpingSpider.class,10,1,4));
		spawnableMonsterList.add(new SpawnEntry(EntityTarantula.class,5,4,8));
		spawnableMonsterList.add(new SpawnEntry(EntityVelvetWorm.class,10,1,2));
		spawnableMonsterList.add(new SpawnEntry(EntityChameleonTick.class,10,1,2));

		spawnableMonsterList.add(new SpawnEntry(EntityMosquito.class,60,1,3));
		spawnableCreatureList.add(new SpawnEntry(EntityFly.class,10,8,8));
		spawnableMonsterList.add(new SpawnEntry(EntityBotFly.class,10,2,3));
		spawnableCreatureList.add(new SpawnEntry(EntityBeetleLarva.class,8,2,4));
		spawnableCreatureList.add(new SpawnEntry(EntityBeetle.class,8,1,2));

		topBlock = Blocks.grass;
		fillerBlock = Blocks.dirt;
	}

	@Override
	public float getSpawningChance(){
		return 0.2F;
	}

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand){
		return block == ModBlocks.umberstone || block == topBlock || block == fillerBlock || block == Blocks.sandstone ? y < 24 ? Blocks.flowing_water : Blocks.air : block;
	}

	@Override
	public BiomeBaseErebus getRandomSubBiome(int randomValue){
		return ModBiomes.jungleSubLake;
	}
}
// @formatter:on