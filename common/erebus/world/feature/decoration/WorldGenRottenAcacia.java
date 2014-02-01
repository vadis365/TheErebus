package erebus.world.feature.decoration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

//@formatter:off
public class WorldGenRottenAcacia extends WorldGenerator{

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		for(int a=0; a<25; a++){
			if (world.isAirBlock(x,y,z) && world.getBlockId(x,y-1,z)==Block.grass.blockID)break;
			if (--y<=10)return false;
		}

		int len=rand.nextInt(3)+3;
		int offsetX=rand.nextInt(2),offsetZ=1-offsetX;

		for(int a=0; a<len; a++){
			if (!world.isAirBlock(x+offsetX*a,y,z+offsetZ*a) || world.getBlockId(x+offsetX*a,y-1,z+offsetZ*a)!=Block.grass.blockID)return false;
		}
		
		for(int a=0; a<len; a++){
			world.setBlock(x+offsetX*a,y,z+offsetZ*a,ModBlocks.hollowLogAcacia.blockID,offsetX==0?0:1,2);
		}
		
		return true;
	}
}
//@formatter:on