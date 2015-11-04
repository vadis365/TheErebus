package erebus.world.biomes;

import java.util.Random;

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
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorVolcanicDesert;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BiomeVolcanicDesert extends BiomeBaseErebus {
	public BiomeVolcanicDesert(int biomeID) {
		super(biomeID, new BiomeDecoratorVolcanicDesert());

		setBiomeName("Volcanic Desert");
		setColors(0xA6BB4E, 0x91A922);
		setFog(255, 231, 10);
		setTemperatureRainfall(1.9F, 0.2F);
		setWeight(16);

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

		topBlock = Blocks.sand;
		fillerBlock = Blocks.sandstone;
	}

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand) {
		return block == ModBlocks.umberstone || block == topBlock || block == fillerBlock || block == Blocks.sandstone ? y < 17 ? Blocks.flowing_lava : Blocks.air : block;
	}
}