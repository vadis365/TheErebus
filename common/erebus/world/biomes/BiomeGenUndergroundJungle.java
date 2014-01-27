package erebus.world.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFly;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityMoth;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityScytodes;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityWasp;
import erebus.world.feature.WorldGenAmberGround;
import erebus.world.feature.WorldGenAmberUmberstone;
import erebus.world.feature.WorldGenBamboo;
import erebus.world.feature.WorldGenEucalyptus;
import erebus.world.feature.WorldGenMelon;
import erebus.world.feature.WorldGenPonds;
import erebus.world.feature.WorldGenQuickSand;
import erebus.world.feature.WorldGenRedGem1;
import erebus.world.feature.WorldGenRedGem2;
import erebus.world.feature.WorldGenTurnips;
import erebus.world.feature.WorldGenWaspDungeon;
import erebus.world.feature.trees.WorldGenAsperTree;
import erebus.world.feature.trees.WorldGenErebusHugeTree;
import erebus.world.feature.trees.WorldGenErebusTrees;
import erebus.world.feature.trees.WorldGenMossbarkTree;
import erebus.world.feature.trees.WorldGenTallJungleTree;

public class BiomeGenUndergroundJungle extends BiomeGenBaseErebus {

	public BiomeGenUndergroundJungle(int biomeID) {
		super(biomeID);
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityWasp.class, 30, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityCentipede.class, 10, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityPrayingMantis.class, 10, 4, 8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBotFly.class, 10, 4, 8));
		// spawnableCaveCreatureList.add(newSpawnListEntry(EntityVelvetWorm.class,10,2,4));
		spawnableMonsterList.add(new SpawnListEntry(EntityScytodes.class, 35, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityJumpingSpider.class, 10, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityTarantula.class, 5, 4, 8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBeetle.class, 8, 1, 2));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBeetleLarva.class, 8, 2, 4));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityFly.class, 10, 8, 8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityMoth.class, 5, 4, 4));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityMosquito.class, 60, 1, 3));
		topBlock = (byte) Block.grass.blockID;
		fillerBlock = (byte) Block.dirt.blockID;
	}

	@Override
	public float getSpawningChance() {
		return 0.2F;
	}

	public int getRandomXZOffset(Random rand) {
		return rand.nextInt(16) + 8;
	}

	@Override
	public void generateTerrain(World worldObj, Random rand, IChunkProvider chunkProvider, int x, int z) {
		/** Generating big lakes first to avoid complications **/
		/**
		 * TODO: Currently broken, generates into other chunks and causes
		 * floating trees
		 **/
		/*
		 * for(int c = 5; c > 0; c--) { int j2 = x + getRandomXZOffset(rand);
		 * int l3 = rand.nextInt(64); int j5 = z + getRandomXZOffset(rand);
		 * if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3
		 * - 1, j5) == Block.grass.blockID) { (new
		 * WorldGenBigLake(Block.waterMoving.blockID, Block.sand.blockID,
		 * Block.blockClay.blockID, 8D)).generate(worldObj, rand, j2, l3, j5); }
		 * }
		 */

		/** Generating lakes first to avoid complications **/
		for (int c = 20; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = rand.nextInt(120);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenPonds(Block.waterMoving.blockID, Block.blockClay.blockID, (rand.nextDouble() + 1.0D) * 2D).generate(worldObj, rand, j2, l3, j5);
		}

		if (rand.nextInt(37) == 0)
			for (int attempt = 0; attempt < 5; attempt++)
				if (new WorldGenWaspDungeon().generate(worldObj, rand, x + rand.nextInt(16) + 8, 127, z + rand.nextInt(16) + 8))
					break;

		if (rand.nextInt(5) == 0)
			for (int attempt = 0; attempt < 4; attempt++)
				if (new WorldGenAmberGround().generate(worldObj, rand, x + rand.nextInt(16) + 8, rand.nextInt(120), z + rand.nextInt(16) + 8))
					break;

		if (rand.nextInt(3) == 0)
			for (int attempt = 0; attempt < 3; attempt++)
				if (new WorldGenAmberUmberstone().generate(worldObj, rand, x + rand.nextInt(16) + 8, rand.nextInt(120), z + rand.nextInt(16) + 8))
					break;

		new WorldGenFlowers(Block.mushroomBrown.blockID).generate(worldObj, rand, x + getRandomXZOffset(rand), rand.nextInt(128), z + getRandomXZOffset(rand));
		new WorldGenFlowers(Block.mushroomRed.blockID).generate(worldObj, rand, x + getRandomXZOffset(rand), rand.nextInt(128), z + getRandomXZOffset(rand));

		for (int c = 10; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = rand.nextInt(120);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenQuickSand().generate(worldObj, rand, j2, l3, j5);
		}

		// Trees

		WorldGenerator gen;
		for (int c = 270; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenHugeTrees(true, 4 + rand.nextInt(40), 3, 3).generate(worldObj, rand, j2, l3, j5);
		}

		gen = new WorldGenMossbarkTree();
		for (int c = 270; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				gen.generate(worldObj, rand, j2, l3, j5);
		}

		gen = new WorldGenTallJungleTree();
		for (int c = 270; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				gen.generate(worldObj, rand, j2, l3, j5);
		}

		gen = new WorldGenAsperTree();
		for (int c = 270; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				gen.generate(worldObj, rand, j2, l3, j5);
		}

		gen = new WorldGenTrees(true, 6, 3, 3, true);
		for (int c = 270; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				gen.generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 270; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenErebusHugeTree(true, 20 + rand.nextInt(5), BlockLogErebus.dataMahogany, BlockLeavesErebus.dataMahoganyDecay, false, ModBlocks.logErebusGroup1.blockID, ModBlocks.leavesErebus.blockID).generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 270; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenErebusTrees(true, 5, BlockLogErebus.dataMahogany, BlockLeavesErebus.dataMahoganyDecay, false, ModBlocks.logErebusGroup1.blockID, ModBlocks.leavesErebus.blockID, ModBlocks.thorns.blockID).generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 270; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenEucalyptus(ModBlocks.logErebusGroup1.blockID, BlockLogErebus.dataEucalyptus/* 2 */, ModBlocks.leavesErebus.blockID, BlockLeavesErebus.dataEucalyptusDecay, 8 + rand.nextInt(4), 5, 8, Block.grass.blockID).generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 10; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = rand.nextInt(128);
			int j5 = z + getRandomXZOffset(rand);
			new WorldGenRedGem1().generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 10; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = rand.nextInt(128);
			int j5 = z + getRandomXZOffset(rand);
			new WorldGenRedGem2().generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 12; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenBigMushroom(0).generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 20; c > 0; c--) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenBigMushroom(1).generate(worldObj, rand, j2, l3, j5);
		}

		if (rand.nextInt(11) == 0)
			for (int yy = 90; yy > 20; yy--) {
				int xx = x + 4 + rand.nextInt(8), zz = z + 4 + rand.nextInt(8);
				if (worldObj.getBlockId(xx, yy, zz) == 0 && worldObj.getBlockId(xx, yy - 1, zz) == Block.grass.blockID)
					if (new WorldGenBamboo(13).generate(worldObj, rand, xx, yy, zz))
						break;
			}

		for (int c = 0; c < 40; c++) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenTallGrass(ModBlocks.fern.blockID, 1).generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 0; c < 16; c++) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				new WorldGenTallGrass(ModBlocks.fiddlehead.blockID, 1).generate(worldObj, rand, j2, l3, j5);
		}

		gen = new WorldGenTallGrass(ModBlocks.erebusGrass.blockID, 1);
		for (int c = 0; c < 900; c++) {
			int j2 = x + getRandomXZOffset(rand);
			int l3 = 15 + rand.nextInt(90);
			int j5 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				gen.generate(worldObj, rand, j2, l3, j5);
		}

		/** Generating jungle vines **/
		for (int i1 = 0; i1 < 250; i1++) {
			int posX3 = x + getRandomXZOffset(rand);
			int posY3 = 6 + rand.nextInt(120);
			int posZ3 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(posX3, posY3, posZ3) == 0) {
				if (Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 + 1)))
					for (int c = rand.nextInt(30); c > 0; c--)
						if (worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
							worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 1, 3);
				if (Block.isNormalCube(worldObj.getBlockId(posX3 - 1, posY3, posZ3)))
					for (int c = rand.nextInt(30); c > 0; c--)
						if (worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
							worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 2, 3);
				if (Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 - 1)))
					for (int c = rand.nextInt(30); c > 0; c--)
						if (worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
							worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 4, 3);
				if (Block.isNormalCube(worldObj.getBlockId(posX3 + 1, posY3, posZ3)))
					for (int c = rand.nextInt(30); c > 0; c--)
						if (worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
							worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 0, 3);
			}
		}

		/** Generating thorns **/
		for (int i1 = 0; i1 < 250; i1++) {
			int posX3 = x + getRandomXZOffset(rand);
			int posY3 = 6 + rand.nextInt(120);
			int posZ3 = z + getRandomXZOffset(rand);
			if (worldObj.getBlockId(posX3, posY3, posZ3) == 0) {
				if (Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 + 1)))
					for (int c = rand.nextInt(30); c > 0; c--)
						if (worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
							worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 1, 3);
				if (Block.isNormalCube(worldObj.getBlockId(posX3 - 1, posY3, posZ3)))
					for (int c = rand.nextInt(30); c > 0; c--)
						if (worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
							worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 2, 3);
				if (Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 - 1)))
					for (int c = rand.nextInt(30); c > 0; c--)
						if (worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
							worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 4, 3);
				if (Block.isNormalCube(worldObj.getBlockId(posX3 + 1, posY3, posZ3)))
					for (int c = rand.nextInt(30); c > 0; c--)
						if (worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
							worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 0, 3);
			}
		}

		if (rand.nextInt(3) == 0)
			for (int var5 = 0; var5 < 6; ++var5) {
				int j2 = x + getRandomXZOffset(rand);
				int l3 = 15 + rand.nextInt(90);
				int j5 = z + getRandomXZOffset(rand);
				if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
					new WorldGenTurnips().generate(worldObj, rand, j2, l3, j5);
			}
		else if (rand.nextInt(2) == 0)
			for (int var5 = 0; var5 < 3; ++var5) {
				int j2 = x + getRandomXZOffset(rand);
				int l3 = 15 + rand.nextInt(90);
				int j5 = z + getRandomXZOffset(rand);
				if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
					new WorldGenMelon().generate(worldObj, rand, j2, l3, j5);
			}
	}
}
