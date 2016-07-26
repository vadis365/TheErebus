package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import erebus.world.biomes.BiomeSubmergedSwamp;
import erebus.world.biomes.BiomeUndergroundJungle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPonds extends WorldGenerator {
	private double size;

	public void prepare(double size) {
		this.size = size;
	}

	@Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (position = position.add(-8, 0, -8); position.getY() > 5 && worldIn.isAirBlock(position); position = position.down());

        if (position.getY() <= 4)
            return false;
        else {
            position = position.down(4);
            boolean[] placeWater = new boolean[2048];
            int i = rand.nextInt(4) + 4;

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

    					Material mat = worldIn.getBlockState(position.add(xx, yy, zz)).getMaterial();
    					if (yy >= 4 && mat.isLiquid() || yy < 4 && !mat.isSolid() && worldIn.getBlockState(position.add(xx, yy, zz)).getBlock() != Blocks.FLOWING_WATER)
    						return false;
    				}
        	
    		for (int xx = 0; xx < 16; ++xx)
    			for (int zz = 0; zz < 16; ++zz)
    				for (int yy = 0; yy < 8; ++yy)
    					if (placeWater[(xx * 16 + zz) * 8 + yy])
    						worldIn.setBlockState(position.add(xx, yy, zz), yy >= 4 ? Blocks.AIR.getDefaultState() : Blocks.FLOWING_WATER.getDefaultState(), 3);


    		for (int xx = 0; xx < 16; ++xx)
    			for (int zz = 0; zz < 16; ++zz)
    				for (int yy = 4; yy < 8; ++yy)
    					if (placeWater[(xx * 16 + zz) * 8 + yy]) {
    						BlockPos blockpos = position.add(xx, yy - 1, zz);

                            if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && worldIn.getLightFor(EnumSkyBlock.SKY, position.add(xx, yy, zz)) > 0) {
                                Biome biome = worldIn.getBiomeGenForCoords(blockpos);

                                if (biome.topBlock.getBlock() == Blocks.MYCELIUM)
                                    worldIn.setBlockState(blockpos, Blocks.MYCELIUM.getDefaultState(), 2);
                                else
                                    worldIn.setBlockState(blockpos, Blocks.GRASS.getDefaultState(), 2);
                            }
    					}

    		for (int xx = 0; xx < 16; ++xx)
    			for (int zz = 0; zz < 16; ++zz)
    				if (worldIn.canBlockFreezeWater(position.add(xx, 4, zz)))
    					worldIn.setBlockState(position.add(xx, 4, zz), Blocks.ICE.getDefaultState(), 2);
    		

    		Biome biome = worldIn.getBiomeGenForCoords(new BlockPos(position.getX() + 8, 0, position.getZ() + 8));
    		boolean isJungle = biome instanceof BiomeUndergroundJungle;
    		boolean isSwamp = biome instanceof BiomeSubmergedSwamp;
    		
    		for (int xx = 0; xx < 16; ++xx)
    			for (int zz = 0; zz < 16; ++zz)
    				for (int yy = 0; yy < 8; ++yy) {
    					boolean flag = !placeWater[(xx * 16 + zz) * 8 + yy] && (xx < 15 && placeWater[((xx + 1) * 16 + zz) * 8 + yy] || xx > 0 && placeWater[((xx - 1) * 16 + zz) * 8 + yy] || zz < 15 && placeWater[(xx * 16 + zz + 1) * 8 + yy] || zz > 0 && placeWater[(xx * 16 + zz - 1) * 8 + yy] || yy < 7 && placeWater[(xx * 16 + zz) * 8 + yy + 1] || yy > 0 && placeWater[(xx * 16 + zz) * 8 + yy - 1]);
    					if (!flag)
    						continue;

    					Material mat = worldIn.getBlockState(position.add(xx, yy, zz)).getMaterial();
    					if ((yy < 4 || rand.nextBoolean()) && mat.isSolid())
    						worldIn.setBlockState(position.add(xx, yy, zz), isJungle || isSwamp ? ModBlocks.MUD.getDefaultState() : Blocks.SAND.getDefaultState(), 2);
    				}
    		
    		if (rand.nextBoolean())
    			for (int attempt = 0, xx, zz; attempt < 2 + rand.nextInt(6); attempt++) {
    				xx = position.getX() + rand.nextInt(8) + 4;
    				zz = position.getZ() + rand.nextInt(8) + 4;

    				for (int yy = 0; yy < 8; yy++)
    					if (worldIn.getBlockState(new BlockPos(xx, position.getY() + yy, zz)) == Blocks.SAND.getDefaultState()) {
    						double rad = rand.nextDouble() * 1.3D + 1.8D;
    						int irad = (int) Math.ceil(rad);

    						for (int px = xx - irad; px <= xx + irad; px++)
    							for (int pz = zz - irad; pz <= zz + irad; pz++)
    								for (int py = position.getY() + yy - irad; py <= position.getY() + yy + irad; py++)
    									if (worldIn.getBlockState(new BlockPos(px, py, pz)) == Blocks.SAND.getDefaultState() && Math.sqrt(Math.pow(px - xx, 2) + Math.pow(pz - zz, 2) + Math.pow(py - position.getY() + yy, 2)) <= rad + rand.nextFloat() * 0.3F)
    										worldIn.setBlockState(new BlockPos(px, py, pz), Blocks.CLAY.getDefaultState());

    						break;
    					}
    			}


    		WorldGenWaterlily genLily = new WorldGenWaterlily();
    		for (int attempt = 0; attempt < 5; attempt++)
    			genLily.generate(worldIn, rand, new BlockPos(position.getX() + rand.nextInt(8) - rand.nextInt(8) + 8, position.getY() + 2 + rand.nextInt(6), position.getZ() + rand.nextInt(8) - rand.nextInt(8) + 8));

    		Block block;
    		for (int sugarCaneAttempt = 0, xx, yy, zz; sugarCaneAttempt < 30; sugarCaneAttempt++) {
    			xx = position.getX() + rand.nextInt(16);
    			yy = position.getY() + 3 + rand.nextInt(5);
    			zz = position.getZ() + rand.nextInt(16);
    			block = worldIn.getBlockState(new BlockPos(xx, yy - 1, zz)).getBlock();

    			if ((block == Blocks.GRASS || block == Blocks.SAND || block == ModBlocks.MUD) && Blocks.REEDS.canPlaceBlockAt(worldIn,new BlockPos(xx, yy, zz)))
    				for (int height = 0; height < 1 + rand.nextInt(7); height++)
    					if (worldIn.isAirBlock(new BlockPos(xx, yy + height, zz)))
    						worldIn.setBlockState(new BlockPos(xx, yy + height, zz), Blocks.REEDS.getDefaultState());
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
    */	}
    		return true;
	}
}