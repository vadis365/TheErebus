package erebus.world.biomes;

import java.util.Random;

import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBombardierBeetleLarva;
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
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorUndergroundJungle;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BiomeUndergroundJungle extends BiomeBaseErebus {

	public BiomeUndergroundJungle(int biomeID) {
		super(biomeID, new BiomeDecoratorUndergroundJungle());

		setBiomeName("Undergound Jungle");
		setColors(0x53CA37, 0x29BC05);
		setFog(8, 128, 8);
		setTemperatureRainfall(1.35F, 0.9F);
		setWeight(22);

		spawningGradual.add(new SpawnEntry(EntityScytodes.class, 20).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityWasp.class, 20).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityCentipede.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityPrayingMantis.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityJumpingSpider.class, 10).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityTarantula.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityVelvetWorm.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityMosquito.class, 60).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(8, 8));
		spawningGradual.add(new SpawnEntry(EntityBotFly.class, 10).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 20).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityBeetle.class, 15).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBombardierBeetle.class, 8).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityBombardierBeetleLarva.class, 20).setGroupSize(2, 4));

		topBlock = Blocks.grass;
		fillerBlock = Blocks.dirt;
	}

	@Override
	public float getSpawningChance() {
		return 0.2F;
	}

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand) {
		return block == ModBlocks.umberstone || block == topBlock || block == fillerBlock || block == Blocks.sandstone ? y < 24 ? Blocks.flowing_water : Blocks.air : block;
	}

	@Override
	public BiomeBaseErebus getRandomSubBiome(int randomValue) {
		return ModBiomes.jungleSubLake;
	}
}