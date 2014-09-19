package erebus.world.feature.plant;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenRottonLogs extends WorldGenerator
{
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        world.setBlock(x, y, z, Blocks.dirt);
        return false;
    }
}
