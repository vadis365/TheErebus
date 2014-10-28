package erebus.world.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.lib.EnumWood;

public class WorldGenWeedwoodTree extends WorldGenTreeBase
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    public WorldGenWeedwoodTree(int par2)
    {
    	super(EnumWood.Sap);
        this.minTreeHeight = par2;
    }

    public boolean generate(World world, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = this.minTreeHeight;

        if (par4 >= 1 && par4 + var6 + 1 <= 128)
        {/*
        	for(int y = (int)(var6 / 3); y > 0; y--)
        	{
        		this.growWood(world, 3, par3 + 2, par5 + 2, par4 + y, 0, par2Random);
        	}
        	
        	for(int y = (int)(var6 / 4); y > 0; y--)
        	{
        		this.growWood(world, 4, par3 + 2, par5 + 2, par4 + y, 0, par2Random);
        	}
        	
        	for(int y = (int)(var6 / 5); y > 0; y--)
        	{
        		this.growWood(world, 5, par3 + 2, par5 + 2, par4 + y, 0, par2Random);
        	}
        	*/
        	for(int x = 4; x > -1; x--)
        	{
        		for(int z = 4; z > -1; z--)
            	{
        			for(int y = var6; y > 0; y--)
                	{
        				int posX = par3 + x;
        				int posY = par4 + y;
        				int posZ = par5 + z;
        				
        				world.setBlock(posX, posY, posZ, log, 0, 2);
                	}

        			for(int i = 2; i > 0; i--)
        			{
        					this.growLeaves(world, par3 + 2, par5 + 2, par4 + var6 + 5 - i, 0, par2Random);
        			}
        			
        			for(int i = 2; i > 0; i--)
        			{
        					this.growSmallLeaves(world, par3 + 2 + par2Random.nextInt(3) - par2Random.nextInt(3), par5 + 2 + par2Random.nextInt(3) - par2Random.nextInt(3), par4 + var6 - 6  + par2Random.nextInt(3) - par2Random.nextInt(3), 0, par2Random);
        			}
        			
        			genRoot(world, par3 + 1, par5 + 1, par4 + 2, 0, par2Random);
        			
        			for(int i = 3; i > 0; i--)
        			{
	        			for (int var14 = par4 + var6 - 2 - par2Random.nextInt(4); var14 > par4 + var6 - var6 / 5; var14 -= 2 + par2Random.nextInt(4))
	                    {
	                        float var15 = par2Random.nextFloat() * (float)Math.PI * 2.0F;
	                        int var11;
	                        int var12;
	
	                        for (int var13 = 0; var13 < 6; ++var13)
	                        {
	                            var11 = par3 + (int)(1.5F + MathHelper.cos(var15) * (float)var13);
	                            var12 = par5 + (int)(1.5F + MathHelper.sin(var15) * (float)var13);
	                            world.setBlock(var11, var14 + 3 - var13 + 1, var12, log, 0, 2);
	                        }
	                    }
        			}
            	}
        	}
        	
        	return true;
        }
        else
        {
            return false;
        }
    }

	private void growLeaves(World world, int par2, int par3, int par4, int par5, Random par6Random)
    {
        byte var7 = 6;

        for (int var8 = par4 - var7; var8 <= par4; ++var8)
        {
            int var9 = var8 - par4;
            int var10 = par5 + 1 - var9;

            for (int var11 = par2 - var10; var11 <= par2 + var10 + 1; ++var11)
            {
                int var12 = var11 - par2;

                for (int var13 = par3 - var10; var13 <= par3 + var10 + 1; ++var13)
                {
                    int var14 = var13 - par3;

                    Block block = world.getBlock(var11, var8, var13);

                    if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && 
                        (var12 <= 0 && var14 <= 0 || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && 
                        (par6Random.nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1)) && 
                        (block == null || block.canBeReplacedByLeaves(world, var11, var8, var13)))
                    {
                    	world.setBlock(var11, var8, var13, leaves, 0, 2);
                    	if(par6Random.nextInt(20) == 0)
                    	{
                    		if(world.getBlock(var11, var8 - 1, var13) == Blocks.air)
                			{
                    			world.setBlock(var11, var8 - 1, var13, ModBlocks.hanger, 4, 2);
                			}
                    		for(int c = 10 + par6Random.nextInt(20); c > 0; c--)
                    		{
                    			if(world.getBlock(var11, var8 - 1 - c, var13) == Blocks.air)
                    			{
                    				world.setBlock(var11, var8 - 1 - c, var13, ModBlocks.hanger, 4, 2);
                    			}
                    		}
                    	}
                    }
                }
            }
        }
    }
	
	private void growSmallLeaves(World world, int par2, int par3, int par4, int par5, Random par6Random)
    {
        byte var7 = 2;

        for (int var8 = par4 - var7; var8 <= par4; ++var8)
        {
            int var9 = var8 - par4;
            int var10 = par5 + 1 - var9;

            for (int var11 = par2 - var10; var11 <= par2 + var10 + 1; ++var11)
            {
                int var12 = var11 - par2;

                for (int var13 = par3 - var10; var13 <= par3 + var10 + 1; ++var13)
                {
                    int var14 = var13 - par3;

                    Block block = world.getBlock(var11, var8, var13);

                    if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && 
                        (var12 <= 0 && var14 <= 0 || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && 
                        (par6Random.nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1)) && 
                        (block == null || block.canBeReplacedByLeaves(world, var11, var8, var13)))
                    {
                    	world.setBlock(var11, var8, var13+1, leaves, 0, 2);
                    	if(par6Random.nextInt(20) == 0)
                    	{
                    		if(world.getBlock(var11, var8 - 1, var13) == Blocks.air)
                			{
                    			world.setBlock(var11, var8 - 1, var13, ModBlocks.hanger, 4, 2);
                			}
                    		for(int c = 10 + par6Random.nextInt(20); c > 0; c--)
                    		{
                    			if(world.getBlock(var11, var8 - 1 - c, var13) == Blocks.air)
                    			{
                    				world.setBlock(var11, var8 - 1 - c, var13, ModBlocks.hanger, 4, 2);
                    			}
                    		}
                    	}
                    }
                }
            }
        }
    }
    
    private void genRoot(World world, int x, int y, int z, int meta, Random par6Random)
    {
        byte var7 = 6;

        for (int var8 = z - var7; var8 <= z; ++var8)
        {
            int var9 = var8 - z;
            int var10 = meta + 1 - var9;

            for (int var11 = x - var10; var11 <= x + var10 + 1; ++var11)
            {
                int var12 = var11 - x;

                for (int var13 = y - var10; var13 <= y + var10 + 1; ++var13)
                {
                    int var14 = var13 - y;

                    Block block = world.getBlock(var11, var8, var13);

                    if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && 
                        (var12 <= 0 && var14 <= 0 || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && 
                        (par6Random.nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1)))
                    {
                    	world.setBlock(var11, var8, var13, log, 0, 2);
                    	world.setBlock(var11 + 1, var8, var13 + 1, log, 0, 2);
                    	for(int yy = 3; yy > 0; yy--)
                    	{
            				if(world.getBlock(var11, z + 3 - var13 + 1 - yy, var12) == Blocks.air ||world.getBlock(var11, z + 3 - var13 + 1 - yy, var12) == Blocks.water)
            				{
            					world.setBlock(var11, var8 - yy, var13, log, 0, 2);
            				}
            				if(world.getBlock(var11 + 1, z + 3 - var13 + 1 - yy, var12 + 1) == Blocks.air ||world.getBlock(var11 + 1, z + 3 - var13 + 1 - yy, var12 + 1) == Blocks.water)
            				{
            					world.setBlock(var11 + 1, var8 - yy, var13 + 1, log, 0, 2);
            				}
                    	}
                    }
                }
            }
        }
    }

	private void growWood(World world, int width, int par2, int par3, int par4, int par5, Random par6Random)
    {
		byte var7 = (byte)width;

        for(int x = var7*2; x > -var7*2; x--)
        {
        	for(int z = var7*2; z > -var7*2; z--)
            {
            	if(distance(par2, par3, par2 + x, par3 + z) < var7)
            	{
            		world.setBlock(par2 + x, par4, par3 + z, log, 0, 2);
            	}
            }
        }
    }
	
	public double distance(int px, int py, int qx, int qy)
	{ 
		double dx   = px - qx;
		double dy   = py - qy;
		double dist = Math.sqrt( dx*dx + dy*dy );
		return dist;
	}
}
