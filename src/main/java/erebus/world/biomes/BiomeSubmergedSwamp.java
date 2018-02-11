package erebus.world.biomes;

import java.util.Random;

import erebus.ModBlocks;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBogMaw;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityPondSkater;
import erebus.world.ChunkProviderErebus;
import erebus.world.SpawnerErebus.SpawnEntry;
import erebus.world.biomes.decorators.BiomeDecoratorSubmergedSwamp;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeSubmergedSwamp extends BiomeBaseErebus {

	public BiomeSubmergedSwamp(BiomeProperties properties) {
		super(properties, new BiomeDecoratorSubmergedSwamp());
		
		properties.setBaseBiome("Submerged Swamp");
		properties.setTemperature(0.75F);
		properties.setRainDisabled();
		setColors(0x314D31);
		setFog(8, 128, 8);
		/*

		
		spawningGradual.add(new SpawnEntry(EntityLeech.class, 20).setGroupSize(3, 5));
		spawningGradual.add(new SpawnEntry(EntityBloodSnail.class, 10).setGroupSize(1, 2));
		*/

		spawningGradual.add(new SpawnEntry(EntityDragonfly.class, 20).setGroupSize(1, 3));
		spawningGradual.add(new SpawnEntry(EntityCentipede.class, 10).setGroupSize(4, 8));
		spawningGradual.add(new SpawnEntry(EntityBeetleLarva.class, 25).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityBeetle.class, 20).setGroupSize(1, 2));
		spawningGradual.add(new SpawnEntry(EntityJumpingSpider.class, 10).setGroupSize(2, 4));
		spawningGradual.add(new SpawnEntry(EntityPondSkater.class, 100).setGroupSize(3, 5));
		spawningGradual.add(new SpawnEntry(EntityBogMaw.class, 20).setGroupSize(2, 3));
		spawningGradual.add(new SpawnEntry(EntityMosquito.class, 20).setGroupSize(3, 4));
	}

	@Override
	public Block placeCaveBlock(Block block, int x, int y, int z, Random rand) {
		return block == ModBlocks.UMBERSTONE || block == topBlock || block == fillerBlock || block == Blocks.SANDSTONE ? y < ChunkProviderErebus.swampWaterHeight - 1 ? Blocks.FLOWING_WATER : Blocks.AIR : block;
	}
	
    @SideOnly(Side.CLIENT)
	@Override
    public int getWaterColorMultiplier() {
        return 6051893;
    }
}