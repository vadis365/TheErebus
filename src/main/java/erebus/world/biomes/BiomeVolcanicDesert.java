package erebus.world.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import erebus.ModBlocks;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFireAntSoldier;
import erebus.entity.EntityFly;
import erebus.entity.EntityLavaWebSpider;
import erebus.entity.EntityScorpion;
import erebus.entity.EntitySolifuge;
import erebus.world.biomes.decorators.BiomeDecoratorVolcanicDesert;

// @formatter:off
public class BiomeVolcanicDesert extends BiomeBaseErebus{
	@SuppressWarnings("unchecked")
	public BiomeVolcanicDesert(int biomeID){
		super(biomeID,new BiomeDecoratorVolcanicDesert());

		setBiomeName("Volcanic Desert");
		setColors(0xA6BB4E,0x91A922);
		setFog(255,231,10);
		setTemperatureRainfall(1.9F,0.2F);
		setWeight(16);

		spawnableMonsterList.add(new SpawnEntry(EntityScorpion.class,30,1,8));
		spawnableMonsterList.add(new SpawnEntry(EntitySolifuge.class,30,1,8));
		spawnableMonsterList.add(new SpawnEntry(EntityFireAnt.class,30,1,8));
		spawnableMonsterList.add(new SpawnEntry(EntityFireAntSoldier.class,30,1,3));
		spawnableMonsterList.add(new SpawnEntry(EntityAntlion.class,30,1,8));
		spawnableMonsterList.add(new SpawnEntry(EntityBlackWidow.class,5,1,1));
		spawnableMonsterList.add(new SpawnEntry(EntityLavaWebSpider.class,300,1,1));
		spawnableMonsterList.add(new SpawnEntry(EntityChameleonTick.class,10,1,2));

		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,8,8));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBotFly.class,10,2,3));

		topBlock = Blocks.sand;
		fillerBlock = Blocks.sandstone;
	}

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand){
		return block == ModBlocks.umberstone || block == topBlock || block == fillerBlock || block == Blocks.sandstone ? y < 17 ? Blocks.flowing_lava : Blocks.air : block;
	}
}
// @formatter:on