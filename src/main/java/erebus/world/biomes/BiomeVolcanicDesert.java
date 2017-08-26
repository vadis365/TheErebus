package erebus.world.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import erebus.ModBlocks;
import erebus.world.biomes.decorators.BiomeDecoratorVolcanicDesert;

public class BiomeVolcanicDesert extends BiomeBaseErebus {
	public BiomeVolcanicDesert(BiomeProperties properties) {
		super(properties, new BiomeDecoratorVolcanicDesert());

		properties.setBaseBiome("Volcanic Desert");
		properties.setTemperature(1.9F);
		properties.setRainDisabled();
		setColors(0xA6BB4E, 0x91A922);
		setFog(255, 231, 10);

/*
		spawningGradual.add(new SpawnEntry(EntityScorpion.class, 20).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntitySolifuge.class, 25).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityFireAnt.class, 30).setGroupSize(1, 8));
		spawningGradual.add(new SpawnEntry(EntityFireAntSoldier.class, 30).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityAntlion.class, 30).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityBlackWidow.class, 5).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityLavaWebSpider.class, 300).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(8, 8));
		spawningGradual.add(new SpawnEntry(EntityBotFly.class, 10).setGroupSize(2, 3));
*/
		topBlock = Blocks.SAND.getDefaultState();
		fillerBlock = Blocks.SANDSTONE.getDefaultState();
	}

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand) {
		return block == ModBlocks.UMBERSTONE || block == topBlock || block == fillerBlock || block == Blocks.SANDSTONE ? y < 17 ? Blocks.FLOWING_LAVA : Blocks.AIR : block;
	}
}