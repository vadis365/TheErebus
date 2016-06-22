package erebus.world.feature.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.world.biomes.BiomeSubmergedSwamp;
import erebus.world.biomes.BiomeUndergroundJungle;

public class WorldGenPonds extends WorldGenerator {

	private double size;

	public void prepare(double size) {
		this.size = size;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		x -= 8;
		z -= 8;

		for (; y > 30 && world.isAirBlock(new BlockPos(x, y, z)); --y)
			;

		if (y <= 29 || world.isAirBlock(new BlockPos(x, y, z)))
			return false;
		y -= 4;

		boolean[] placeWater = new boolean[2048];

		for (int iteration = 0, iterAmount = rand.nextInt(3) + 5; iteration < iterAmount; ++iteration) {
			double d0 = (rand.nextDouble() * 6D + 3D) * size * (0.4D + rand.nextDouble() * 0.6D);
			double d1 = (rand.nextDouble() * 4D + 2D) * size / 2.5D;
			double d2 = (rand.nextDouble() * 6D + 3D) * size * (0.4D + rand.nextDouble() * 0.6D);
			double d3 = rand.nextDouble() * (16D - d0 - 2D) + 1D + d0 / 2D;
			double d4 = rand.nextDouble() * (8D - d1 - 4D) + 2D + d1 / 2D;
			double d5 = rand.nextDouble() * (16D - d2 - 2D) + 1D + d2 / 2D;

			for (int xx = 1; xx < 15; ++xx)
				for (int zz = 1; zz < 15; ++zz)
					for (int yy = 1; yy < 7; ++yy) {
						double d6 = (xx - d3) / (d0 / 2.0D);
						double d7 = (yy - d4) / (d1 / 2.0D);
						double d8 = (zz - d5) / (d2 / 2.0D);
						double dist = d6 * d6 + d7 * d7 + d8 * d8;

						if (dist < 1D)
							placeWater[(xx * 16 + zz) * 8 + yy] = true;
					}
		}

		for (int xx = 0; xx < 16; ++xx)
			for (int zz = 0; zz < 16; ++zz)
				for (int yy = 0; yy < 8; ++yy) {
					boolean flag = !placeWater[(xx * 16 + zz) * 8 + yy] && (xx < 15 && placeWater[((xx + 1) * 16 + zz) * 8 + yy] || xx > 0 && placeWater[((xx - 1) * 16 + zz) * 8 + yy] || zz < 15 && placeWater[(xx * 16 + zz + 1) * 8 + yy] || zz > 0 && placeWater[(xx * 16 + zz - 1) * 8 + yy] || yy < 7 && placeWater[(xx * 16 + zz) * 8 + yy + 1] || yy > 0 && placeWater[(xx * 16 + zz) * 8 + yy - 1]);
					if (!flag)
						continue;

					Material mat = world.getBlockState(new BlockPos(x + xx, y + yy, z + zz)).getMaterial();
					if (yy >= 4 && mat.isLiquid() || yy < 4 && !mat.isSolid() && world.getBlockState(new BlockPos(x + xx, y + yy, z + zz)) != Blocks.FLOWING_WATER.getDefaultState())
						return false;
				}

		for (int xx = 0; xx < 16; ++xx)
			for (int zz = 0; zz < 16; ++zz)
				for (int yy = 0; yy < 8; ++yy)
					if (placeWater[(xx * 16 + zz) * 8 + yy])
						world.setBlockState(new BlockPos(x + xx, y + yy, z + zz), yy >= 4 ? Blocks.AIR.getDefaultState() : Blocks.FLOWING_WATER.getDefaultState(), 3);

		for (int xx = 0; xx < 16; ++xx)
			for (int zz = 0; zz < 16; ++zz)
				for (int yy = 4; yy < 8; ++yy)
					if (placeWater[(xx * 16 + zz) * 8 + yy] && world.getBlockState(new BlockPos(x + xx, y + yy - 1, z + zz)) == Blocks.DIRT.getDefaultState() && world.getLightFor(EnumSkyBlock.SKY, new BlockPos(x + xx, y + yy, z + zz)) > 0) {
						Biome biome = world.getBiomeGenForCoords(new BlockPos(x + xx, 0, z + zz));

						if (biome.topBlock == Blocks.MYCELIUM.getDefaultState())
							world.setBlockState(new BlockPos(x + xx, y + yy - 1, z + zz), Blocks.MYCELIUM.getDefaultState(), 2);
						else
							world.setBlockState(new BlockPos(x + xx, y + yy - 1, z + zz), Blocks.GRASS.getDefaultState(), 2);
					}

		for (int xx = 0; xx < 16; ++xx)
			for (int zz = 0; zz < 16; ++zz)
				if (world.canBlockFreezeWater(new BlockPos(x + xx, y + 4, z + zz)))
					world.setBlockState(new BlockPos(x + xx, y + 4, z + zz), Blocks.ICE.getDefaultState(), 2);

		Biome biome = world.getBiomeGenForCoords(new BlockPos(x + 8, 0, z + 8));
		boolean isJungle = biome instanceof BiomeUndergroundJungle;
		boolean isSwamp = biome instanceof BiomeSubmergedSwamp;

		for (int xx = 0; xx < 16; ++xx)
			for (int zz = 0; zz < 16; ++zz)
				for (int yy = 0; yy < 8; ++yy) {
					boolean flag = !placeWater[(xx * 16 + zz) * 8 + yy] && (xx < 15 && placeWater[((xx + 1) * 16 + zz) * 8 + yy] || xx > 0 && placeWater[((xx - 1) * 16 + zz) * 8 + yy] || zz < 15 && placeWater[(xx * 16 + zz + 1) * 8 + yy] || zz > 0 && placeWater[(xx * 16 + zz - 1) * 8 + yy] || yy < 7 && placeWater[(xx * 16 + zz) * 8 + yy + 1] || yy > 0 && placeWater[(xx * 16 + zz) * 8 + yy - 1]);

					if (flag && (yy < 4 || rand.nextBoolean()) && world.getBlockState(new BlockPos(x + xx, y + yy, z + zz)).getMaterial().isSolid())
						world.setBlockState(new BlockPos(x + xx, y + yy, z + zz), isJungle || isSwamp ? ModBlocks.UMBERSTONE.getDefaultState() : Blocks.SAND.getDefaultState(), 2); // UMBERSTONE > MUD
				}

		if (rand.nextBoolean())
			for (int attempt = 0, xx, zz; attempt < 2 + rand.nextInt(6); attempt++) {
				xx = x + rand.nextInt(8) + 4;
				zz = z + rand.nextInt(8) + 4;

				for (int yy = 0; yy < 8; yy++)
					if (world.getBlockState(new BlockPos(xx, y + yy, zz)) == Blocks.SAND.getDefaultState()) {
						double rad = rand.nextDouble() * 1.3D + 1.8D;
						int irad = (int) Math.ceil(rad);

						for (int px = xx - irad; px <= xx + irad; px++)
							for (int pz = zz - irad; pz <= zz + irad; pz++)
								for (int py = y + yy - irad; py <= y + yy + irad; py++)
									if (world.getBlockState(new BlockPos(px, py, pz)) == Blocks.SAND.getDefaultState() && Math.sqrt(Math.pow(px - xx, 2) + Math.pow(pz - zz, 2) + Math.pow(py - y + yy, 2)) <= rad + rand.nextFloat() * 0.3F)
										world.setBlockState(new BlockPos(px, py, pz), Blocks.CLAY.getDefaultState());

						break;
					}
			}

		WorldGenWaterlily genLily = new WorldGenWaterlily();
		for (int attempt = 0; attempt < 5; attempt++)
			genLily.generate(world, rand, new BlockPos(x + rand.nextInt(8) - rand.nextInt(8) + 8, y + 2 + rand.nextInt(6), z + rand.nextInt(8) - rand.nextInt(8) + 8));

		Block block;
		for (int sugarCaneAttempt = 0, xx, yy, zz; sugarCaneAttempt < 30; sugarCaneAttempt++) {
			xx = x + rand.nextInt(16);
			yy = y + 3 + rand.nextInt(5);
			zz = z + rand.nextInt(16);
			block = world.getBlockState(new BlockPos(xx, yy - 1, zz)).getBlock();

			if ((block == Blocks.GRASS || block == Blocks.SAND /*|| block == ModBlocks.mud*/) && Blocks.REEDS.canPlaceBlockAt(world,new BlockPos(xx, yy, zz)))
				for (int height = 0; height < 1 + rand.nextInt(7); height++)
					if (world.isAirBlock(new BlockPos(xx, yy + height, zz)))
						world.setBlockState(new BlockPos(xx, yy + height, zz), Blocks.REEDS.getDefaultState());
					else
						break;
		}
/*
		for (int bullRushAttempt = 0, xx, yy, zz; bullRushAttempt < 50; bullRushAttempt++) {
			xx = x + rand.nextInt(16);
			yy = y + 3 + rand.nextInt(5);
			zz = z + rand.nextInt(16);
			block = world.getBlock(xx, yy - 1, zz);

			if (block == Blocks.SAND || block == ModBlocks.mud)
				for (int height = 0; height < 1; height++)
					if (world.isAirBlock(xx, yy + height, zz)) {
						world.setBlock(xx, yy, zz, ModBlocks.bullrush, 0, 2);
						world.setBlock(xx, yy + 1, zz, ModBlocks.bullrush, 8, 2);
					} else
						break;
		}
*/
		return true;
	}
}