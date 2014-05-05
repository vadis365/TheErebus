package erebus.world.biomes;
import java.util.Random;

import net.minecraft.block.Block;
import erebus.ModBlocks;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFly;
import erebus.entity.EntityScorpion;
import erebus.entity.EntitySolifuge;
import erebus.world.biomes.decorators.BiomeDecoratorVolcanicDesert;

// @formatter:off
public class BiomeVolcanicDesert extends BiomeBaseErebus{
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
		spawnableMonsterList.add(new SpawnEntry(EntityAntlion.class,30,1,8));
		spawnableMonsterList.add(new SpawnEntry(EntityBlackWidow.class,5,1,1));

		spawnableCaveCreatureList.add(new SpawnEntry(EntityFly.class,10,8,8));
		spawnableCaveCreatureList.add(new SpawnEntry(EntityBotFly.class,10,2,3));

		topBlock = (byte)Block.sand.blockID;
		fillerBlock = (byte)Block.sandStone.blockID;
	}
	
	@Override
	public byte placeCaveBlock(byte blockID, int x, int y, int z, Random rand){
		return blockID == (byte)ModBlocks.umberstone.blockID || blockID == topBlock || blockID == fillerBlock || blockID == Block.sandStone.blockID ? (y < 17 ? (byte)Block.lavaMoving.blockID : 0) : blockID;
	}
}
// @formatter:on