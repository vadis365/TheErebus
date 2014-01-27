package erebus.world.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFirebrat;
import erebus.entity.EntityFly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.world.feature.WorldGenAntlionLair;
import erebus.world.feature.trees.WorldGenScorchedTree;

public class BiomeGenUndergroundDesert extends BiomeGenBaseErebus {

	public BiomeGenUndergroundDesert(int biomeID) {
		super(biomeID);
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityScorpion.class, 30, 1, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntitySolifuge.class, 30, 1, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityFireAnt.class, 30, 1, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityFirebrat.class, 30, 1, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityBlackWidow.class, 5, 1, 1));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBotFly.class, 10, 4, 8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityFly.class, 10, 8, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityScytodes.class, 35, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityJumpingSpider.class, 10, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityAntlion.class, 30, 1, 8));
		topBlock = (byte) Block.sand.blockID;
		fillerBlock = (byte) Block.sandStone.blockID;
	}

	@Override
	public void generateTerrain(World worldObj, Random rand, IChunkProvider chunkProvider, int x, int z) {
		WorldGenerator gen = new WorldGenLakes(Block.lavaMoving.blockID);
		for (int c = 35; c > 0; c--) {
			int posX = x + rand.nextInt(16) + 8;
			int posY = 15 + rand.nextInt(90);
			int posZ = z + rand.nextInt(16) + 8;
			if (worldObj.getBlockId(posX, posY, posZ) == 0 && worldObj.getBlockId(posX, posY - 1, posZ) == Block.sand.blockID)
				gen.generate(worldObj, worldObj.rand, posX, posY, posZ);
		}

		for (int c = 10; c > 0; c--) {
			int posX = x + rand.nextInt(16) + 8;
			int posY = rand.nextInt(120);
			int posZ = z + rand.nextInt(16) + 8;
			if (worldObj.getBlockId(posX, posY, posZ) == ModBlocks.umberstone.blockID && worldObj.getBlockId(posX, posY - 1, posZ) == 0) {
				worldObj.setBlock(posX, posY, posZ, Block.lavaMoving.blockID);
				worldObj.scheduledUpdatesAreImmediate = true;
				Block.blocksList[Block.lavaMoving.blockID].updateTick(worldObj, posX, posY, posZ, rand);
				worldObj.scheduledUpdatesAreImmediate = false;
			}
		}

		for (int c = 22; c > 0; c--) {
			int j2 = x + rand.nextInt(16) + 8;
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16) + 8;
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.sand.blockID) {
				new WorldGenScorchedTree().generate(worldObj, rand, j2, l3, j5);
				if (rand.nextInt(4) != 0)
					break;
			}
		}

		if (rand.nextInt(34) == 0)
			for (int attempt = 0; attempt < 15; attempt++) {
				int xx = x + 5 + rand.nextInt(6) + 8, yy = 15 + rand.nextInt(35), zz = z + 5 + rand.nextInt(6) + 8;
				if (new WorldGenAntlionLair().generate(worldObj, rand, xx, yy, zz))
					break;
			}
	}
}