package erebus.world.feature.plant;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGiantBulbCappedMushroom extends WorldGenerator {


    public WorldGenGiantBulbCappedMushroom(int par1) {
        super(true);
    }

    public WorldGenGiantBulbCappedMushroom() {
        super(false);
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {

        int i1 = par2Random.nextInt(3) + 4;
        boolean flag = true;

        if (par4 >= 1 && par4 + i1 + 1 < 256)
        {
            int j1;
            int k1;
            int l1;
            int i2;

            for (j1 = par4; j1 <= par4 + 1 + i1; ++j1)
            {
                byte b0 = 3;

                if (j1 <= par4 + 3)
                {
                    b0 = 0;
                }

                for (k1 = par3 - b0; k1 <= par3 + b0 && flag; ++k1)
                {
                    for (l1 = par5 - b0; l1 <= par5 + b0 && flag; ++l1)
                    {
                        if (j1 >= 0 && j1 < 256)
                        {
                            i2 = par1World.getBlockId(k1, j1, l1);

                            Block block = Block.blocksList[i2];
                            
                            if (block != null && !block.isAirBlock(par1World, k1, j1, l1) && !block.isLeaves(par1World, k1, j1, l1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                j1 = par1World.getBlockId(par3, par4 - 1, par5);

                if (j1 != Block.dirt.blockID && j1 != Block.grass.blockID && j1 != Block.mycelium.blockID)
                {
                    return false;
                }
                else
                {
                    int j2 = par4 + i1;


                    for (k1 = j2; k1 <= par4 + i1; ++k1)
                    {
                        l1 = 1;

                        if (k1 < par4 + i1)
                        {
                            ++l1;
                        }

                        for (i2 = par3 - l1; i2 <= par3 + l1; ++i2)
                        {
                            for (int k2 = par5 - l1; k2 <= par5 + l1; ++k2)
                            {
                                int l2 = 5;

                                if (i2 == par3 - l1)
                                {
                                    --l2;
                                }

                                if (i2 == par3 + l1)
                                {
                                    ++l2;
                                }

                                if (k2 == par5 - l1)
                                {
                                    l2 -= 3;
                                }

                                if (k2 == par5 + l1)
                                {
                                    l2 += 3;
                                }

                                if (k1 < par4 + i1)
                                {
                                    if ((i2 == par3 - l1 || i2 == par3 + l1) && (k2 == par5 - l1 || k2 == par5 + l1))
                                    {
                                        continue;
                                    }

                                    if (i2 == par3 - (l1 - 1) && k2 == par5 - l1)
                                    {
                                        l2 = 1;
                                    }

                                    if (i2 == par3 - l1 && k2 == par5 - (l1 - 1))
                                    {
                                        l2 = 1;
                                    }

                                    if (i2 == par3 + (l1 - 1) && k2 == par5 - l1)
                                    {
                                        l2 = 3;
                                    }

                                    if (i2 == par3 + l1 && k2 == par5 - (l1 - 1))
                                    {
                                        l2 = 3;
                                    }

                                    if (i2 == par3 - (l1 - 1) && k2 == par5 + l1)
                                    {
                                        l2 = 7;
                                    }

                                    if (i2 == par3 - l1 && k2 == par5 + (l1 - 1))
                                    {
                                        l2 = 7;
                                    }

                                    if (i2 == par3 + (l1 - 1) && k2 == par5 + l1)
                                    {
                                        l2 = 9;
                                    }

                                    if (i2 == par3 + l1 && k2 == par5 + (l1 - 1))
                                    {
                                        l2 = 9;
                                    }
                                }

                                if (l2 == 5 && k1 < par4 + i1)
                                {
                                    l2 = 0;
                                }

                                Block block = Block.blocksList[par1World.getBlockId(i2, k1, k2)];

                                if ((l2 != 0 || par4 >= par4 + i1 - 1) && (block == null || block.canBeReplacedByLeaves(par1World, i2, k1, k2)))
                                {
                                    this.setBlockAndMetadata(par1World, i2, k1, k2, ModBlocks.giantBulbCappedMushroom.blockID, l2);
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < i1; ++k1)
                    {
                        l1 = par1World.getBlockId(par3, par4 + k1, par5);

                        Block block = Block.blocksList[l1];

                        if (block == null || block.canBeReplacedByLeaves(par1World, par3, par4 + k1, par5))
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + k1, par5, ModBlocks.giantBulbCappedMushroom.blockID, 10);
                        }
                    }

                    return true;
                }
            }
        }
        else
        {
            return false;
        }
    }
}
