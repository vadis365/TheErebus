package erebus.world.feature.tree;
import java.util.Random;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

//@formatter:off
public class WorldGenCypressTree extends WorldGenerator{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		int height = rand.nextInt(4)+4;
		float rad = 0.65F+rand.nextFloat()*0.5F+height/10F;
		int irad = (int)Math.ceil(rad);
		
		for(int xx = x-irad; xx <= x+irad; xx++){
			for(int zz = z-irad; zz <= z+irad; zz++){
				for(int yy = y; yy <= y+height+4; yy++){
					if (!world.isAirBlock(xx,yy,zz))return false;
				}
			}
		}
		
		generateEllipsoidAt(world,rand,x,y+2+(height-2)/2+rand.nextInt(2),z,rad,height/2F-(height>6?0.25F:0F));
		
		for(int yy = y; yy <= y+height; yy++)world.setBlock(x,yy,z,ModBlocks.logErebusGroup1.blockID);
		for(int yy = y+height+1; yy < y+height+1+(rand.nextBoolean()?2:1); yy++){
			for(int xx = x-1; xx <= x+1; xx++){
				for(int zz = z-1; zz <= z+1; zz++){
					if (xx == x && zz == z)continue;
					world.setBlockToAir(xx,yy,zz);
				}
			}
			world.setBlock(x,yy,z,ModBlocks.leavesErebus.blockID);
		}
		for(int a = 0; a < 4; a++)world.setBlock(x+Direction.offsetX[a],y+height,z+Direction.offsetZ[a],ModBlocks.leavesErebus.blockID);
		
		return true;
	}

	private void generateEllipsoidAt(World world, Random rand, float x, float y, float z, float radXZ, float radY){
		for(float xf=x-radXZ; xf<=x+radXZ; xf+=0.5F){
			for(float zf=z-radXZ; zf<=z+radXZ; zf+=0.5F){
				for(float yf=y-radY; yf<=y+radY; yf++){
					if (Math.pow(xf-x,2)/(radXZ*radXZ)+Math.pow(yf-y,2)/(radY*radY)+Math.pow(zf-z,2)/(radXZ*radXZ)<=0.9+rand.nextFloat()*0.1F){
						world.setBlock(Math.round(xf),Math.round(yf),Math.round(zf),ModBlocks.leavesErebus.blockID);
					}
				}
			}
		}
	}
}
//@formatter:on