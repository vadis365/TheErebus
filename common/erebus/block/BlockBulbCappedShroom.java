package erebus.block;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBulbCappedShroom extends BlockFlower {
    public BlockBulbCappedShroom(int id)
    {
        super(id);
        float var3 = 0.2F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setTickRandomly(true);
    }
    

    @Override
	public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (rand.nextInt(25) == 0)
        {
            byte var6 = 4;
            int var7 = 5;
            int xx;
            int yy;
            int zz;

            for (xx = x - var6; xx <= x + var6; ++xx)
            {
                for (yy = z - var6; yy <= z+ var6; ++yy)
                {
                    for (zz = y - 1; zz <= y + 1; ++zz)
                    {
                        if (world.getBlockId(xx, zz, yy) == this.blockID)
                        {
                            --var7;

                            if (var7 <= 0)
                            {
                                return;
                            }
                        }
                    }
                }
            }

            xx = x + rand.nextInt(3) - 1;
            yy = y + rand.nextInt(2) - rand.nextInt(2);
            zz = z + rand.nextInt(3) - 1;

            for (int var11 = 0; var11 < 4; ++var11)
            {
                if (world.isAirBlock(xx, yy, zz) && this.canBlockStay(world, xx, yy, zz))
                {
                    x = xx;
                    y = yy;
                    z = zz;
                }

                xx = x + rand.nextInt(3) - 1;
                yy = y + rand.nextInt(2) - rand.nextInt(2);
                zz = z + rand.nextInt(3) - 1;
            }

            if (world.isAirBlock(xx, yy, zz) && this.canBlockStay(world, xx, yy, zz))
            {
                world.setBlock(xx, yy, zz, this.blockID, 0, 2);
            }
        }
    }


    @Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && this.canBlockStay(par1World, par2, par3, par4);
    }


    @Override
	protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return Block.opaqueCubeLookup[par1];
    }


    @Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        if (par3 >= 0 && par3 < 256)
        {
            int var5 = par1World.getBlockId(par2, par3 - 1, par4);
            return var5 == Block.mycelium.blockID || par1World.getFullBlockLightValue(par2, par3, par4) < 13 && this.canThisPlantGrowOnThisBlockID(var5);
        }
        else
        {
            return false;
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {
    	this.blockIcon = icon.registerIcon("erebus:bulbCappedShroom");
    }

    /**
     * Fertilize the mushroom.
     
    public boolean fertilizeMushroom(World par1World, int par2, int par3, int par4, Random rand)
    {
        //WorldGenBigMushroom.generate(par1World, rand, par2, par3, par4)
    }*/
}
