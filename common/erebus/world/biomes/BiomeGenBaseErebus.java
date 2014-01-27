package erebus.world.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import erebus.ModBlocks;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.core.handler.ConfigurationHandler;
import erebus.world.feature.WorldGenErebusMinable;

public abstract class BiomeGenBaseErebus extends BiomeGenBase {

	public BiomeGenBaseErebus(int biomeID) {
		super(biomeID);
	}

	public abstract void generateTerrain(World worldObj, Random rand, IChunkProvider chunkProvider, int x, int z);

	public void generateDefault(World worldObj, Random rand, IChunkProvider chunkProvider, int x, int z) {
		boolean extraOres = ConfigurationHandler.lead || ConfigurationHandler.silver || ConfigurationHandler.copper || ConfigurationHandler.tin || ConfigurationHandler.aluminium;

		for (int a = 0; a < (extraOres ? 8 : 10); ++a)
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataCoal, 11 + rand.nextInt(4), worldObj, rand, x, z, 6, 112, 3);

		for (int a = 0; a < (extraOres ? 9 : 11); ++a)
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataIron, 8 + rand.nextInt(3), worldObj, rand, x, z, 6, 112, 3);

		for (int a = 0; a < (extraOres ? 4 : 5); ++a)
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataGold, 6, worldObj, rand, x, z, 6, 112, 3);

		for (int a = 0; a < 3; ++a)
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataLapis, 5, worldObj, rand, x, z, 6, 112, 2);

		for (int a = 0; a < 1 + rand.nextInt(3); ++a)
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataEmerald, 3, worldObj, rand, x, z, 6, 112, 1);

		if (rand.nextInt(3) != 0)
			for (int a = 0; a < 2 + rand.nextInt(2); ++a)
				generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataDiamond, 1, worldObj, rand, x, z, 6, 112, 1);

		if (rand.nextInt(2) == 0)
			for (int a = 0; a < 1 + rand.nextInt(4); ++a)
				generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataJade, 4, worldObj, rand, x, z, 6, 112, 2);

		for (int a = 0; a < (extraOres ? 3 : 4) + rand.nextInt(2); ++a)
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataPetrifiedWood, 7 + rand.nextInt(3), worldObj, rand, x, z, 6, 112, 2);

		if (rand.nextInt(5) == 0)
			for (int a = 0; a < 1 + rand.nextInt(3); ++a)
				generateOreCluster(ModBlocks.oreFossil, 3, 8 + rand.nextInt(4), worldObj, rand, x, z, 36, 112, 3);

		if (ConfigurationHandler.aluminium)
			for (int a = 0; a < 2 + rand.nextInt(2); ++a)
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataAluminium, 3 + rand.nextInt(2), worldObj, rand, x, z, 6, 112, 2);
		if (ConfigurationHandler.copper)
			for (int a = 0; a < 8 + rand.nextInt(3); ++a)
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataCopper, 5 + rand.nextInt(3), worldObj, rand, x, z, 6, 112, 3);
		if (ConfigurationHandler.lead)
			for (int a = 0; a < 4; ++a)
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataLead, 3, worldObj, rand, x, z, 6, 112, 2);
		if (ConfigurationHandler.silver)
			for (int a = 0; a < 6; ++a)
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataSilver, 6 + rand.nextInt(3), worldObj, rand, x, z, 6, 112, 3);
		if (ConfigurationHandler.tin)
			for (int a = 0; a < 2 + rand.nextInt(3); ++a)
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataTin, 3 + rand.nextInt(2), worldObj, rand, x, z, 6, 112, 2);
	}

	private static final byte[] checkX = new byte[] { -1, -1, 1, 1, 0, 0 }, checkY = new byte[] { 0, 0, 0, 0, -1, 1 }, checkZ = new byte[] { -1, 1, -1, 1, 0, 0 };

	private void generateOreCluster(Block oreBlock, int oreMeta, int oreNumber, World world, Random rand, int x, int z, int minY, int maxY, int checkArea) {
		for (int xx, yy, zz, attempt = 0; attempt < 12; attempt++) {
			xx = x + 2 + rand.nextInt(12);
			zz = z + 2 + rand.nextInt(12);
			yy = minY + rand.nextInt(Math.max(1, 1 + maxY - minY));

			for (int a = 0; a < 6; a++) {
				int testX = xx + checkX[a] * checkArea, testY = yy + checkY[a] * checkArea, testZ = zz + checkZ[a] * checkArea;
				if (testX >> 4 != x >> 4)
					testX = x;
				if (testZ >> 4 != z >> 4)
					testZ = z;

				if (world.isAirBlock(testX, testY, testZ)) {
					if (oreNumber == 1) {
						if (world.getBlockId(xx, yy, zz) == ModBlocks.umberstone.blockID)
							world.setBlock(xx, yy, zz, oreBlock.blockID, oreMeta, 2);
					} else
						new WorldGenErebusMinable(oreBlock.blockID, oreMeta, oreNumber).generate(world, rand, xx, yy, zz);
					return;
				}
			}
		}
	}
}
