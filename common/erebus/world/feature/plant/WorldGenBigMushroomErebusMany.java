package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
// Do whatever here....I don't know all the clever stuff
// This will all need to be re-written
// MC Vanilla uses a shitty method of blockID+type 
public class WorldGenBigMushroomErebusMany extends WorldGenerator
{
    private int mushroomType = -1;

    public WorldGenBigMushroomErebusMany(int par1)
    {
        super(true);
        this.mushroomType = par1;
    }

    public WorldGenBigMushroomErebusMany()
    {
        super(false);
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int type = this.mushroomType;

        int i1 = rand.nextInt(3) + 4;
        boolean flag = true;

        if (y >= 1 && y + i1 + 1 < 256)
        {
            int j1;
            int k1;
            int l1;
            int i2;

            for (j1 = y; j1 <= y + 1 + i1; ++j1)
            {
                byte b0 = 3;

                if (j1 <= y + 3)
                {
                    b0 = 0;
                }

                for (k1 = x - b0; k1 <= x + b0 && flag; ++k1)
                {
                    for (l1 = z - b0; l1 <= z + b0 && flag; ++l1)
                    {
                        if (j1 >= 0 && j1 < 256)
                        {
                            i2 = world.getBlockId(k1, j1, l1);

                            Block block = Block.blocksList[i2];
                            
                            if (block != null && !block.isAirBlock(world, k1, j1, l1) && !block.isLeaves(world, k1, j1, l1))
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
                j1 = world.getBlockId(x, y - 1, z);

                if (j1 != Block.dirt.blockID && j1 != Block.grass.blockID && j1 != Block.mycelium.blockID)
                {
                    return false;
                }
                else
                {
                    int j2 = y + i1;

                    if (type == 0)
                    {
                        j2 = y + i1 - 3;
                    }

                    for (k1 = j2; k1 <= y + i1; ++k1)
                    {
                        l1 = 1;

                        if (k1 < y + i1)
                        {
                            ++l1;
                        }

                        if (type == 1)
                        {
                            l1 = 3;
                        }

                        for (i2 = x - l1; i2 <= x + l1; ++i2)
                        {
                            for (int k2 = z - l1; k2 <= z + l1; ++k2)
                            {
                                int l2 = 5;

                                if (i2 == x - l1)
                                {
                                    --l2;
                                }

                                if (i2 == x + l1)
                                {
                                    ++l2;
                                }

                                if (k2 == z - l1)
                                {
                                    l2 -= 3;
                                }

                                if (k2 == z + l1)
                                {
                                    l2 += 3;
                                }

                                if (type == 1 || k1 < y + i1)
                                {
                                    if ((i2 == x - l1 || i2 == x + l1) && (k2 == z - l1 || k2 == z + l1))
                                    {
                                        continue;
                                    }

                                    if (i2 == x - (l1 - 1) && k2 == z - l1)
                                    {
                                        l2 = 1;
                                    }

                                    if (i2 == x - l1 && k2 == z - (l1 - 1))
                                    {
                                        l2 = 1;
                                    }

                                    if (i2 == x + (l1 - 1) && k2 == z - l1)
                                    {
                                        l2 = 3;
                                    }

                                    if (i2 == x + l1 && k2 == z - (l1 - 1))
                                    {
                                        l2 = 3;
                                    }

                                    if (i2 == x - (l1 - 1) && k2 == z + l1)
                                    {
                                        l2 = 7;
                                    }

                                    if (i2 == x - l1 && k2 == z + (l1 - 1))
                                    {
                                        l2 = 7;
                                    }

                                    if (i2 == x + (l1 - 1) && k2 == z + l1)
                                    {
                                        l2 = 9;
                                    }

                                    if (i2 == x + l1 && k2 == z + (l1 - 1))
                                    {
                                        l2 = 9;
                                    }
                                }

                                if (l2 == 5 && k1 < y + i1)
                                {
                                    l2 = 0;
                                }

                                Block block = Block.blocksList[world.getBlockId(i2, k1, k2)];

                                if ((l2 != 0 || y >= y + i1 - 1) && (block == null || block.canBeReplacedByLeaves(world, i2, k1, k2)))
                                {
                                    this.setBlockAndMetadata(world, i2, k1, k2, ModBlocks.erebusMushroomCap0.blockID + type, l2);
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < i1; ++k1)
                    {
                        l1 = world.getBlockId(x, y + k1, z);

                        Block block = Block.blocksList[l1];

                        if (block == null || block.canBeReplacedByLeaves(world, x, y + k1, z))
                        {
                            this.setBlockAndMetadata(world, x, y + k1, z, ModBlocks.erebusMushroomCap0.blockID + type, 10);
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
