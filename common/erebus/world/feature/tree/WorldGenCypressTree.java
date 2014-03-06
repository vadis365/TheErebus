package erebus.world.feature.tree;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

//@formatter:off
public class WorldGenCypressTree extends WorldGenerator{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		int height = rand.nextInt(6)+4;
		float rad = 0.6F+rand.nextFloat()*0.4F+height/10F;
		int irad = (int)Math.ceil(rad);
		
		for(int xx = x-irad; xx <= x+irad; xx++){
			for(int zz = z-irad; zz <= z+irad; zz++){
				for(int yy = y; yy <= y+height+4; yy++){
					if (!world.isAirBlock(xx,yy,zz))return false;
				}
			}
		}
		
		generateEllipsoidAt(world,rand,x,y+3+(height-2)/2,z,rad,height/2F);
		
		for(int yy = y; yy <= y+height+1; yy++)world.setBlock(x,yy,z,ModBlocks.logErebusGroup1.blockID);
		for(int yy = y+height+2; yy < y+height+2+(rand.nextBoolean()?2:1); yy++)world.setBlock(x,yy,z,ModBlocks.leavesErebus.blockID);
		
		return true;
	}

	private void generateEllipsoidAt(World world, Random rand, float x, float y, float z, float radXZ, float radY){
		for(float xf=x-radXZ; xf<=x+radXZ; xf+=0.5F){
			for(float zf=z-radXZ; zf<=z+radXZ; zf+=0.5F){
				for(float yf=y-radY; yf<=y+radY; yf++){
					if (Math.pow(xf-x,2)/(radXZ*radXZ)+Math.pow(yf-y,2)/(radY*radY)+Math.pow(zf-z,2)/(radXZ*radXZ)<=1){
						world.setBlock(Math.round(xf),Math.round(yf),Math.round(zf),ModBlocks.leavesErebus.blockID);
					}
				}
			}
		}
	}
}
//@formatter:on