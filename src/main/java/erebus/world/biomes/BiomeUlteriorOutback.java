package erebus.world.biomes;

import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFly;
import erebus.entity.EntityHoneyPotAnt;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorUlteriorOutback;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;

public class BiomeUlteriorOutback extends BiomeBaseErebus {
	public BiomeUlteriorOutback(BiomeProperties properties) {
		super(properties, new BiomeDecoratorUlteriorOutback());

		properties.setBaseBiome("Ulterior Outback");
		properties.setTemperature(1.1F);
		properties.setRainDisabled();
		setColors(0xC1B668);
		setFog(234, 194, 114);
		//setWeight(15);
/*
		spawningGradual.add(new SpawnEntry(EntityScytodes.class, 20).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityScorpion.class, 10).setGroupSize(2, 2));
		spawningGradual.add(new SpawnEntry(EntitySolifuge.class, 10).setGroupSize(1, 3));


		spawningGradual.add(new SpawnEntry(EntityTarantula.class, 10).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityChameleonTick.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityMidgeSwarm.class, 10).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityRhinoBeetle.class, 10).setGroupSize(1, 1));

*/
		spawningGradual.add(new SpawnEntry(EntityFly.class, 10).setGroupSize(2, 2));
		spawningGradual.add(new SpawnEntry(EntityCentipede.class, 10).setGroupSize(1, 4));
		spawningGradual.add(new SpawnEntry(EntityBlackWidow.class, 5).setGroupSize(1, 1));
		spawningGradual.add(new SpawnEntry(EntityBotFly.class, 10).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 15).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityHoneyPotAnt.class, 10).setGroupSize(2, 4));
		
		topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
	}
}