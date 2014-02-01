package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

//@formatter:off
public class WorldGenMelon extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		for(int attempt=0,xx,yy,zz; attempt<64; ++attempt){
			xx=x+rand.nextInt(8)-rand.nextInt(8);
			yy=y+rand.nextInt(4)-rand.nextInt(4);
			zz=z+rand.nextInt(8)-rand.nextInt(8);

			if (world.isAirBlock(xx,yy,zz) && world.getBlockId(xx,yy-1,zz)==Block.grass.blockID && Block.melon.canPlaceBlockAt(world,xx,yy,zz)){
				world.setBlock(xx,yy,zz,Block.melon.blockID,rand.nextInt(4),3);
			}
		}
		
		return true;
	}
}
//@formatter:on