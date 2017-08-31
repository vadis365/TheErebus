package erebus.world.biomes;

import java.util.Random;

import erebus.ModBlocks;
import erebus.entity.EntityFly;
import erebus.entity.EntityWasp;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorUndergroundJungle;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BiomeUndergroundJungle extends BiomeBaseErebus {
	public BiomeUndergroundJungle(BiomeProperties properties) {
		super(properties, new BiomeDecoratorUndergroundJungle());

		properties.setBaseBiome("Underground Jungle");
		properties.setTemperature(1.35F);
		properties.setRainDisabled();
		setColors(0x53CA37, 0x29BC05);
		setFog(8, 128, 8);
		//setWeight(22);
/*
		spawningGradual.add(new SpawnEntry(EntityScytodes.class, 20).setGroupSize(1, 4));
		
		spawningGradual.add(new SpawnEntry(EntityCentipede.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityPrayingMantis.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityJumpingSpider.class, 10).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityTarantula.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityVelvetWorm.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityMosquito.class, 60).setGroupSize(1, 3));
		
		spawningGradual.add(new SpawnEntry(EntityBotFly.class, 10).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 20).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityBeetle.class, 15).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBombardierBeetle.class, 8).setGroupSize(1, 2));
*/
		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(8, 8));
		spawningGradual.add(new SpawnEntry(EntityWasp.class, 20).setGroupSize(4, 8));

		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
	}

	@Override
	public float getSpawningChance() {
		return 0.2F;
	}

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand) {
		return block == ModBlocks.UMBERSTONE || block == topBlock || block == fillerBlock || block == Blocks.SANDSTONE ? y < 24 ? Blocks.FLOWING_WATER : Blocks.AIR : block;
	}
/*
	@Override
	public BiomeBaseErebus getRandomSubBiome(int randomValue) {
		return ModBiomes.jungleSubLake;
	}
	*/
}