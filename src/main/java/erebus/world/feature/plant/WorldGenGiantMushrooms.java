package erebus.world.feature.plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.core.helper.MathUtil;

//@formatter:off
public class WorldGenGiantMushrooms extends WorldGenerator{
	private static final int stalkMeta = 10;
	private static final int bulbFullMeta = 14;
	private static final Block tempBlock = Blocks.bedrock;

	public enum MushroomType{
		BULB_CAPPED(ModBlocks.mushroomCap0),
		GRANDMAS_SHOES(ModBlocks.mushroomCap1),
		SARCASTIC_CZECH(ModBlocks.mushroomCap2),
		KAIZERS_FINGERS(ModBlocks.mushroomCap3),
		DUTCH_CAP(ModBlocks.mushroomCap4);

		public final Block block;

		MushroomType(Block block){
			this.block = block;
		}
	}

	private MushroomType mushroomType = MushroomType.BULB_CAPPED;
	private final List<ChunkCoordinates> bulbs = new ArrayList<ChunkCoordinates>();

	public void setMushroomType(MushroomType type){
		mushroomType = type;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		bulbs.clear();
		Block mushroom = mushroomType.block;

		// TODO tmp
		mushroomType = MushroomType.KAIZERS_FINGERS;
		mushroom = mushroomType.block;
		//

		switch(mushroomType){
			case BULB_CAPPED: genBulbCapped(world,rand,x,y,z,mushroom); break;
			case KAIZERS_FINGERS: genKaizersFingers(world,rand,x,y,z,mushroom); break;
			default:
				break;
		}

		for(ChunkCoordinates bulb:bulbs)world.setBlock(bulb.posX,bulb.posY,bulb.posZ,tempBlock);
		for(ChunkCoordinates bulb:bulbs)world.setBlockMetadataWithNotify(bulb.posX,bulb.posY,bulb.posZ,getMetadata(world,x,z,bulb),2);
		for(ChunkCoordinates bulb:bulbs)world.setBlock(bulb.posX,bulb.posY,bulb.posZ,mushroom,world.getBlockMetadata(bulb.posX,bulb.posY,bulb.posZ),2);

		return true;
	}

	/*
	 * MUSHROOM TYPE - BULB CAPPED
	 */

	private void genBulbCapped(World world, Random rand, int x, int y, int z, Block mushroom){
		int stalkHeight = 3+rand.nextInt(3+rand.nextInt(2));
		int sideHeight = 1+rand.nextInt(stalkHeight > 3 ? 3 : 2);

		for(int py = 0; py <= stalkHeight; py++)
			world.setBlock(x,y++,z,mushroom,stalkMeta,2);

		for(int px = -1; px <= 1; px++)
			for(int pz = -1; pz <= 1; pz++)bulbs.add(new ChunkCoordinates(x+px,y,z+pz));


		for(int py = 1; py <= sideHeight; py++)
			for(int off = -1; off <= 1; off++){
				bulbs.add(new ChunkCoordinates(x+2,y-py,z+off));
				bulbs.add(new ChunkCoordinates(x-2,y-py,z+off));
				bulbs.add(new ChunkCoordinates(x+off,y-py,z+2));
				bulbs.add(new ChunkCoordinates(x+off,y-py,z-2));
			}
	}

	/*
	 * MUSHROOM TYPE - KAIZERS FINGERS
	 */

	private void genKaizersFingers(World world, Random rand, int x, int y, int z, Block mushroom){
		int mainShroomHeight = 4+rand.nextInt(4);

		for(int py = 0, sidesPlaced = 0; py <= mainShroomHeight; py++){
			world.setBlock(x,y+py,z,mushroom,stalkMeta,2);

			if (py >= 2 && py < mainShroomHeight-1 && rand.nextInt(4+sidesPlaced*2) == 0){
				int dir = rand.nextInt(4);
				world.setBlock(x+Direction.offsetX[dir],y+py,z+Direction.offsetZ[dir],mushroom,bulbFullMeta,2);
				++sidesPlaced;
			}
		}

		bulbs.add(new ChunkCoordinates(x,y+mainShroomHeight+1,z));
		for(int a = 0; a < 4; a++)bulbs.add(new ChunkCoordinates(x+Direction.offsetX[a],y+mainShroomHeight,z+Direction.offsetZ[a]));

		List<ChunkCoordinates> connectList = new ArrayList<ChunkCoordinates>();
		connectList.add(new ChunkCoordinates(x,y-1,z));

		for(int smallShroomAttempt = 0, xx, zz; smallShroomAttempt < 4+rand.nextInt(7); smallShroomAttempt++){
			xx = x+rand.nextInt(4)-rand.nextInt(4);
			zz = z+rand.nextInt(4)-rand.nextInt(4);
			if (!world.isAirBlock(xx,y,zz) || !world.isAirBlock(xx-1,y,zz) || !world.isAirBlock(xx+1,y,zz) || !world.isAirBlock(xx,y,zz-1) || !world.isAirBlock(xx,y,zz+1))continue;

			int smallShroomHeight = rand.nextBoolean() ? 1 : 1+rand.nextInt(2);
			for(int py = 0; py < smallShroomHeight; py++)world.setBlock(xx,y+py,zz,mushroom,stalkMeta,2);
			world.setBlock(xx,y+smallShroomHeight,zz,mushroom,bulbFullMeta,2);
			connectList.add(new ChunkCoordinates(xx,y-1,zz));
		}

		int coordAmt = connectList.size();

		for(int connectionAttempt = 0, dir, xx, zz; connectionAttempt < 48; connectionAttempt++){
			ChunkCoordinates coords1 = connectList.get(rand.nextInt(coordAmt));
			ChunkCoordinates coords2 = rand.nextInt(3) != 0 ? connectList.get(0) : connectList.get(rand.nextInt(coordAmt));
			if (coords1 == coords2)continue;

			double dist = MathUtil.distance(coords1.posX-coords2.posX,coords1.posZ-coords2.posZ);
			if (dist < 1D)continue;

			dir = rand.nextInt(4);
			xx = coords1.posX+Direction.offsetX[dir];
			zz = coords1.posZ+Direction.offsetZ[dir];

			if (MathUtil.distance(xx-coords2.posX,zz-coords2.posZ) < dist){
				world.setBlock(xx,y-1,zz,mushroom,stalkMeta,2);
				if (rand.nextInt(16) == 0)world.setBlock(xx,y,zz,mushroom,stalkMeta,2);
				coords1.posX = xx;
				coords1.posZ = zz;
			}
		}
	}

	/*
	 * METADATA UTILITY
	 */

	public static int getMetadata(World world, int centerX, int centerZ, ChunkCoordinates bulb){
		boolean posX = world.getBlock(bulb.posX+1,bulb.posY,bulb.posZ) == tempBlock,
		negX = world.getBlock(bulb.posX-1,bulb.posY,bulb.posZ) == tempBlock,
		posZ = world.getBlock(bulb.posX,bulb.posY,bulb.posZ+1) == tempBlock,
		negZ = world.getBlock(bulb.posX,bulb.posY,bulb.posZ-1) == tempBlock;

		if (posX && negX && posZ && negZ)return 5; // if surrounded, use top only

		if (posX && negX)
			if (bulb.posZ > centerZ)return 8; // use top and south
			else return 2; // use top and north

		if (posZ && negZ)
			if (bulb.posX > centerX)return 6; // use top and east
			else return 4; // use top and west

		if (posX && posZ)return 1; // use top, north and west
		if (negX && negZ)return 9; // use top, south and east
		if (negX && posZ)return 3; // use top, north and east
		if (posX && negZ)return 7; // use top, south and west

		int sides = (posX ? 1 : 0)+(negX ? 1 : 0)+(posZ ? 1 : 0)+(negZ ? 1 : 0);
		if (sides > 1)return 0; // go away, you're no longer needed here

		boolean posXposZ = world.getBlock(bulb.posX+1,bulb.posY,bulb.posZ+1) == tempBlock,
		negXposZ = world.getBlock(bulb.posX-1,bulb.posY,bulb.posZ+1) == tempBlock,
		posXnegZ = world.getBlock(bulb.posX+1,bulb.posY,bulb.posZ-1) == tempBlock,
		negXnegZ = world.getBlock(bulb.posX-1,bulb.posY,bulb.posZ-1) == tempBlock;

		int corners = (posXposZ ? 1 : 0)+(negXposZ ? 1 : 0)+(posXnegZ ? 1 : 0)+(negXnegZ ? 1 : 0);

		if (sides == 0 && corners == 0)return 14; // use full cap for lonely shrooms
		if (posXposZ && negXposZ || negXnegZ && posXnegZ || posXposZ && posXnegZ || negXposZ && negXnegZ)return 14; // use full cap for + pattern

		if (sides == 0 && corners == 2){
			for(int dir = 0, meta; dir < 4; dir++){
				meta = world.getBlockMetadata(bulb.posX+Direction.offsetX[dir],bulb.posY-1,bulb.posZ+Direction.offsetZ[dir]);
				if (meta != 0)return meta; // use meta of cap above and to the side - either 2 side cap or full cap
			}

			return 14; // if not found, fallback to full cap
		}
		else if (sides == 1 && corners == 1)
			if (bulb.posX > centerX){
				if (bulb.posZ > centerZ)return 9; // use top, south and east
				else return 3; // use top, north and east
			} else if (bulb.posZ > centerZ)return 7; // use top, south and west
			else return 1; // use top, north and west

		return 0;
	}
}

//@formatter:on
