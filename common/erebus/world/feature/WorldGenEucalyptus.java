package erebus.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;

public class WorldGenEucalyptus extends WorldGenerator{

	private static final int span = 5;
	private static final int branches = 8;

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		if (world.getBlockId(x,y-1,z)!=Block.grass.blockID)return false;

		int height=8+rand.nextInt(4);

		for(int yy=0; yy<span; yy++){
			for(int xx=-span; xx<=span; xx++){
				for(int zz=-span; zz<=span; zz++){
					if (!world.isAirBlock(x+xx,y+height+yy,z+zz))return false;
				}
			}
		}
		
		for(int p=-2; p<3; p++){
			for(int r=-1; r<2; r++){
				world.setBlock(x+p,y+height+span+1,z+r,ModBlocks.leavesErebus.blockID,BlockLeavesErebus.dataEucalyptusDecay,3);
				world.setBlock(x+r,y+height+span+1,z+p,ModBlocks.leavesErebus.blockID,BlockLeavesErebus.dataEucalyptusDecay,3);
			}
		}

		for(int p=-1; p<2; p++){
			for(int r=-1; r<2; r++){
				world.setBlock(x+r,y+height+span+2,z+p,ModBlocks.leavesErebus.blockID,BlockLeavesErebus.dataEucalyptusDecay,3);
			}
		}
		
		for(int a=0; a<branches; a++){
			int disX=rand.nextInt(span*2+1)-span;
			int disY=rand.nextInt(span+1);
			int disZ=rand.nextInt(span*2+1)-span;

			int posX=x+disX;
			int posY=y+height-1+disY;
			int posZ=z+disZ;

			for(int p=-2; p<3; p++){
				for(int r=-1; r<2; r++){
					world.setBlock(posX+p,posY,posZ+r,ModBlocks.leavesErebus.blockID,BlockLeavesErebus.dataEucalyptusDecay,3);
					world.setBlock(posX+r,posY,posZ+p,ModBlocks.leavesErebus.blockID,BlockLeavesErebus.dataEucalyptusDecay,3);
				}
			}

			for(int p=-1; p<2; p++){
				for(int r=-1; r<2; r++){
					world.setBlock(posX+r,posY+1,posZ+p,ModBlocks.leavesErebus.blockID,BlockLeavesErebus.dataEucalyptusDecay,3);
				}
			}
			for(int b=0; b<span; b++){
				int xx=disX*(b+1)/span;
				int yy=disY*(b+1)/span;
				int zz=disZ*(b+1)/span;

				world.setBlock(x+xx,y+height-1+yy,z+zz,ModBlocks.logErebusGroup1.blockID,BlockLogErebus.dataEucalyptus,3);
			}

			world.setBlock(posX,posY,posZ,ModBlocks.logErebusGroup1.blockID,BlockLogErebus.dataEucalyptus,3);
		}

		for(int yy=0; yy<height+span+2; yy++)world.setBlock(x,y+yy,z,ModBlocks.logErebusGroup1.blockID,BlockLogErebus.dataEucalyptus,3);
		
		return true;
	}
}
