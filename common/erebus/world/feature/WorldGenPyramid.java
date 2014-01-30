package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

//@formatter:off
public class WorldGenPyramid extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		for(int yy=10; yy>0; yy--){
			for(int xx=-(14-yy); xx<14-yy; xx++){
				for(int zz=-(14-yy); zz<14-yy; zz++){
					if (xx<0&&xx<=-(14-yy))world.setBlock(x+xx,y+yy+1,z+zz,Block.stairsSandStone.blockID,0,3);
					
					if (xx>0&&xx>=14-yy-1)world.setBlock(x+xx,y+yy+1,z+zz,Block.stairsSandStone.blockID,1,3);
					
					if (zz<0&&zz<=-(14-yy))world.setBlock(x+xx,y+yy+1,z+zz,Block.stairsSandStone.blockID,2,3);
					
					if (zz>0&&zz>=14-yy-1)world.setBlock(x+xx,y+yy+1,z+zz,Block.stairsSandStone.blockID,3,3);
					
					if (xx<0&&xx<=-(14-yy)){
						if (zz<0&&zz<=-(14-yy))world.setBlock(x+xx,y+yy+1,z+zz,Block.stoneSingleSlab.blockID);
					}
					
					if (xx>0&&xx>=14-yy-1){
						if (zz>0&&zz>=14-yy-1)world.setBlock(x+xx,y+yy+1,z+zz,Block.stoneSingleSlab.blockID);
					}
					
					if (xx<0&&xx<=-(14-yy)){
						if (zz>0&&zz>=14-yy-1)world.setBlock(x+xx,y+yy+1,z+zz,Block.stoneSingleSlab.blockID);
					}
					
					if (xx>0&&xx>=14-yy-1){
						if (zz<0&&zz<=-(14-yy))world.setBlock(x+xx,y+yy+1,z+zz,Block.stoneSingleSlab.blockID);
					}

					world.setBlock(x+xx,y+yy,z+zz,Block.sandStone.blockID);
				}
			}
		}

		for(int xx=-3; xx<3; xx++){
			for(int zz=-3; zz<3; zz++){
				world.setBlock(x+xx,y+11,z+zz,Block.sandStone.blockID);
			}
		}

		return true;
	}
}
//@formatter:on
