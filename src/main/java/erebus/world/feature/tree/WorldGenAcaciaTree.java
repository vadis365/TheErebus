package erebus.world.feature.tree;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;

// @formatter:off
public class WorldGenAcaciaTree extends WorldGenerator{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		int extraHeight = rand.nextInt(3);

		for(int i = 0; i < 6+extraHeight; i++){
			if (!world.isAirBlock(x,y+i,z))return false;
		}

		for(int i = 0; i < 6+extraHeight; i++)world.setBlock(x,y+i,z,ModBlocks.logErebusGroup1,BlockLogErebus.dataAcacia,2);

		int leafSizeFactor = world.rand.nextInt(1+extraHeight),leafSize = 4+leafSizeFactor;
		y+=3+extraHeight;

		for(int h = leafSizeFactor+3; h > 0; h--){
			for(int xx=-(leafSize-h); xx <= leafSize-h; xx++){
				for(int zz=-(leafSize-h); zz <= leafSize-h; zz++){
					if (world.isAirBlock(x+xx,y+h,z+zz)){
						world.setBlock(x+xx,y+h,z+zz,ModBlocks.leavesErebus,BlockLeavesErebus.dataAcaciaDecay,2);
					}
				}
			}
		}

		return true;
	}
}
// @formatter:on