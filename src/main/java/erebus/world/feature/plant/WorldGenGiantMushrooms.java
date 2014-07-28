package erebus.world.feature.plant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

//@formatter:off
public class WorldGenGiantMushrooms extends WorldGenerator{
	public enum MushroomType{
		BULB_CAPPED(ModBlocks.erebusMushroomCap0),
		GRANDMAS_SHOES(ModBlocks.erebusMushroomCap1),
		SARCASTIC_CZECH(ModBlocks.erebusMushroomCap2),
		KAIZERS_FINGERS(ModBlocks.erebusMushroomCap3),
		DUTCH_CAP(ModBlocks.erebusMushroomCap4);
		
		public final Block block;
		
		MushroomType(Block block){
			this.block = block;
		}
	}
	
	private MushroomType mushroomType = MushroomType.BULB_CAPPED;
	private final List<ChunkCoordinates> bulbs = new ArrayList<ChunkCoordinates>();
	
	public void setMushroomType(MushroomType type){
		this.mushroomType = type;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		bulbs.clear();
		Block mushroom = mushroomType.block;
		
		// TODO gen - this is just for '/erebus gen' testing
		
		bulbs.add(new ChunkCoordinates(x,y+7,z));
		bulbs.add(new ChunkCoordinates(x+1,y+6,z));
		bulbs.add(new ChunkCoordinates(x-1,y+6,z));
		bulbs.add(new ChunkCoordinates(x,y+6,z+1));
		bulbs.add(new ChunkCoordinates(x,y+6,z-1));
		bulbs.add(new ChunkCoordinates(x-1,y+5,z-1));
		bulbs.add(new ChunkCoordinates(x-2,y+5,z));
		bulbs.add(new ChunkCoordinates(x,y+5,z-2));
		bulbs.add(new ChunkCoordinates(x-1,y+5,z+1));
		bulbs.add(new ChunkCoordinates(x,y+5,z+2));
		bulbs.add(new ChunkCoordinates(x+2,y+5,z));
		bulbs.add(new ChunkCoordinates(x+1,y+5,z-1));
		bulbs.add(new ChunkCoordinates(x+1,y+5,z+1));

		bulbs.add(new ChunkCoordinates(x,y,z));
		
		for(int a = -1; a <= 1; a++){
			for(int b = -1; b <= 1; b++)bulbs.add(new ChunkCoordinates(x+a,y,z+b));
		}
		
		for(int c = 1; c <= 2; c++){
			for(int a = -1; a <= 1; a++){
				bulbs.add(new ChunkCoordinates(x+2,y-c,z+a));
				bulbs.add(new ChunkCoordinates(x-2,y-c,z+a));
				bulbs.add(new ChunkCoordinates(x+a,y-c,z+2));
				bulbs.add(new ChunkCoordinates(x+a,y-c,z-2));
			}
		}
		
		// TODO gen - this is just for '/erebus gen' testing
		
		for(ChunkCoordinates bulb:bulbs)world.setBlock(bulb.posX,bulb.posY,bulb.posZ,mushroom);
		for(ChunkCoordinates bulb:bulbs)world.setBlockMetadataWithNotify(bulb.posX,bulb.posY,bulb.posZ,getMetadata(world,x,z,bulb,mushroom),2);
		
		return true;
	}
	
	public static int getMetadata(World world, int centerX, int centerZ, ChunkCoordinates bulb, Block mushroom){
		boolean posX = world.getBlock(bulb.posX+1,bulb.posY,bulb.posZ) == mushroom,
				negX = world.getBlock(bulb.posX-1,bulb.posY,bulb.posZ) == mushroom,
				posZ = world.getBlock(bulb.posX,bulb.posY,bulb.posZ+1) == mushroom,
				negZ = world.getBlock(bulb.posX,bulb.posY,bulb.posZ-1) == mushroom;
		
		if (posX && negX && posZ && negZ)return 5; // if surrounded, use top only
		
		if (posX && negX){ // two sides on one axis, north/south from center
			if (bulb.posZ > centerZ)return 8; // use top and south
			else return 2; // use top and north
		}
		
		if (posZ && negZ){ // two sides on one axis, east/west from center
			if (bulb.posX > centerX)return 6; // use top and east
			else return 4; // use top and west
		}
		
		if (posX && posZ)return 1; // use top, north and west
		if (negX && negZ)return 9; // use top, south and east
		if (negX && posZ)return 3; // use top, north and east
		if (posX && negZ)return 7; // use top, south and west
		
		int sides = (posX ? 1 : 0)+(negX ? 1 : 0)+(posZ ? 1 : 0)+(negZ ? 1 : 0);
		if (sides > 1)return 0; // go away, you're no longer needed here
		
		boolean posXposZ = world.getBlock(bulb.posX+1,bulb.posY,bulb.posZ+1) == mushroom,
				negXposZ = world.getBlock(bulb.posX-1,bulb.posY,bulb.posZ+1) == mushroom,
				posXnegZ = world.getBlock(bulb.posX+1,bulb.posY,bulb.posZ-1) == mushroom,
				negXnegZ = world.getBlock(bulb.posX-1,bulb.posY,bulb.posZ-1) == mushroom;
		
		int corners = (posXposZ ? 1 : 0)+(negXposZ ? 1 : 0)+(posXnegZ ? 1 : 0)+(negXnegZ ? 1 : 0);
		
		if (sides == 0 && corners == 0)return 14; // use full cap for lonely shrooms
		if ((posXposZ && negXposZ) || (negXnegZ && posXnegZ) || (posXposZ && posXnegZ) || (negXposZ && negXnegZ))return 14; // use full cap for + pattern
		
		if (sides == 0 && corners == 2){
			for(int dir = 0, meta; dir < 4; dir++){
				meta = world.getBlockMetadata(bulb.posX+Direction.offsetX[dir],bulb.posY-1,bulb.posZ+Direction.offsetZ[dir]);
				if (meta != 0)return meta; // use meta of cap above and to the side - either 2 side cap or full cap
			}
			
			return 14; // if not found, fallback to full cap
		}
		else if (sides == 1 && corners == 1){
			if (bulb.posX > centerX){
				if (bulb.posZ > centerZ)return 9; // use top, south and east
				else return 3; // use top, north and east
			}
			else{
				if (bulb.posZ > centerZ)return 7; // use top, south and west
				else return 1; // use top, north and west
			}
		}
		
		return 0;
	}
}

//@formatter:on