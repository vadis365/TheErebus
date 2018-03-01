package erebus.world.feature.structure;

import java.util.Random;

import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.blocks.BlockDoorErebus;
import erebus.blocks.BlockLogErebus;
import erebus.blocks.BlockPlanksErebus;
import erebus.blocks.BlockUmberstone;
import erebus.blocks.BlockUmberstone.EnumType;
import erebus.blocks.EnumWood;
import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenSwampHut implements IWorldGenerator {

	private IBlockState log = EnumWood.MOSSBARK.getLog().getDefaultState();
	private IBlockState plank = ModBlocks.PLANKS.getDefaultState().withProperty(BlockPlanksErebus.TYPE, EnumWood.MOSSBARK);
	private IBlockState stairs = EnumWood.MOSSBARK.getStairs().getDefaultState();
	private IBlockState bricks = ModBlocks.UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE);
	private IBlockState fence = EnumWood.MOSSBARK.getFence().getDefaultState();
	private IBlockState door = EnumWood.MOSSBARK.getDoor().getDefaultState();

	private int length = -1;
	private int width = -1;

	public WorldGenSwampHut() {
		length = 16;
		width = 16;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == ConfigHandler.INSTANCE.erebusDimensionID)
			generate(world, random, chunkX * 16, chunkZ * 16);
		
	}

	private void generate(World world, Random random, int x, int z) {
		int newY = 80;
		BlockPos pos = new BlockPos(x, newY, z);

		Biome biomeBase = world.getBiome(pos);
		if (biomeBase == ModBiomes.SUBMERGED_SWAMP)
			for (int newX = 0; newX <= length; ++newX)
				for (int newZ = 0; newZ <= width; ++newZ) {
					IBlockState block = world.getBlockState(pos.add(newX, newY, newZ));
					if (block != null && block == biomeBase.topBlock)
						//  if(random.nextInt(ConfigHandler.INSTANCE.SWAMP_HUT_FREQUENCY) == 0)
						generateStructure(world, random, pos);
				}
	}

	public boolean generateStructure(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		// air check
		/*  for(int newX = x; newX <= x + length; ++newX) {
		      for(int newZ = z; newZ <= z + width; ++newZ) {
		          for(int newY = y + 1; newY < y + height; ++newY ) {
		              if(!world.isAirBlock(newX, newY, newZ)) {
		                  return false;
		              }
		          }
		      }
		  } */
		//hut generation starts here ;)
		verticalBeam(world, rand, x + 5, y, z + 5, log, 4, 0);
		verticalBeam(world, rand, x + 10, y, z + 5, log, 4, 0);
		verticalBeam(world, rand, x + 5, y, z + 10, log, 4, 0);
		verticalBeam(world, rand, x + 10, y, z + 10, log, 4, 0);

		verticalBeam(world, rand, x + 4, y + 4, z + 4, log, 3, 0);
		verticalBeam(world, rand, x + 11, y + 4, z + 4, log, 3, 0);
		verticalBeam(world, rand, x + 4, y + 4, z + 11, log, 3, 0);
		verticalBeam(world, rand, x + 11, y + 4, z + 11, log, 3, 0);

		verticalBeam(world, rand, x + 4, y + 5, z + 5, log, 4, 0);
		verticalBeam(world, rand, x + 11, y + 5, z + 5, log, 4, 0);
		verticalBeam(world, rand, x + 4, y + 5, z + 10, log, 4, 0);
		verticalBeam(world, rand, x + 11, y + 5, z + 10, log, 4, 0);

		verticalBeam(world, rand, x + 5, y + 5, z + 4, log, 4, 0);
		verticalBeam(world, rand, x + 10, y + 5, z + 4, log, 4, 0);
		verticalBeam(world, rand, x + 5, y + 5, z + 11, log, 4, 0);
		verticalBeam(world, rand, x + 10, y + 5, z + 11, log, 4, 0);

		verticalBeam(world, rand, x + 4, y + 8, z + 6, log, 2, 0);
		verticalBeam(world, rand, x + 11, y + 8, z + 6, log, 2, 0);
		verticalBeam(world, rand, x + 4, y + 8, z + 9, log, 2, 0);
		verticalBeam(world, rand, x + 11, y + 8, z + 9, log, 2, 0);

		verticalBeam(world, rand, x + 6, y + 8, z + 4, log, 2, 0);
		verticalBeam(world, rand, x + 9, y + 8, z + 4, log, 2, 0);
		verticalBeam(world, rand, x + 6, y + 8, z + 11, log, 2, 0);
		verticalBeam(world, rand, x + 9, y + 8, z + 11, log, 2, 0);

		verticalBeam(world, rand, x + 4, y + 9, z + 7, log, 2, 0);
		verticalBeam(world, rand, x + 11, y + 9, z + 7, log, 2, 0);
		verticalBeam(world, rand, x + 4, y + 9, z + 8, log, 2, 0);
		verticalBeam(world, rand, x + 11, y + 9, z + 8, log, 2, 0);

		verticalBeam(world, rand, x + 7, y + 9, z + 4, log, 2, 0);
		verticalBeam(world, rand, x + 7, y + 9, z + 11, log, 2, 0);
		verticalBeam(world, rand, x + 8, y + 9, z + 4, log, 2, 0);
		verticalBeam(world, rand, x + 8, y + 9, z + 11, log, 2, 0);

		//house duplicate walls and stairs
		for (int direction = 0; direction < 4; direction++) {
			rotatedBeam(world, rand, x, y, z, 6, 5, bricks, 4, direction);

			// bottom window
			rotatedBeam(world, rand, x, y + 1, z, 6, 5, plank, 1, direction);
			rotatedBeam(world, rand, x, y + 1, z, 7, 5, fence, 2, direction);
			rotatedBeam(world, rand, x, y + 1, z, 9, 5, plank, 1, direction);

			rotatedBeam(world, rand, x, y + 2, z, 6, 5, plank, 4, direction);
			rotatedBeam(world, rand, x, y + 3, z, 6, 5, plank, 4, direction);
			rotatedBeam(world, rand, x, y + 4, z, 5, 5, plank, 6, direction);
			rotatedBeam(world, rand, x, y + 4, z, 5, 4, plank, 6, direction);
			rotatedBeam(world, rand, x, y + 3, z, 4, 4, getStairRotations(stairs, direction == 0 ? 6 : direction == 1 ? 4 : direction == 2 ? 7 : 5), 8, direction);

			// mid window
			rotatedBeam(world, rand, x, y + 5, z, 6, 4, plank, 1, direction);
			rotatedBeam(world, rand, x, y + 5, z, 7, 4, fence, 2, direction);
			rotatedBeam(world, rand, x, y + 5, z, 9, 4, plank, 1, direction);
			rotatedBeam(world, rand, x, y + 6, z, 6, 4, fence, 4, direction);
			rotatedBeam(world, rand, x, y + 7, z, 6, 4, plank, 4, direction);

			// top window
			rotatedBeam(world, rand, x, y + 8, z, 7, 4, fence, 2, direction);

			rotatedBeam(world, rand, x, y + 7, z, 4, 4, plank, 1, direction);
			rotatedBeam(world, rand, x, y + 7, z, 11, 4, plank, 1, direction);

			// stairs left
			rotatedBeam(world, rand, x, y + 11, z, 3, 7, getStairRotations(stairs, direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1), 4, direction);
			rotatedBeam(world, rand, x, y + 10, z, 3, 6, getStairRotations(stairs, direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1), 4, direction);
			rotatedBeam(world, rand, x, y + 9, z, 3, 5, getStairRotations(stairs, direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1), 3, direction);
			rotatedBeam(world, rand, x, y + 8, z, 3, 4, getStairRotations(stairs, direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1), 2, direction);
			rotatedBeam(world, rand, x, y + 10, z, 3, 7, getStairRotations(stairs, direction == 0 ? 7 : direction == 1 ? 5 : direction == 2 ? 6 : 4), 1, direction);
			rotatedBeam(world, rand, x, y + 9, z, 3, 6, getStairRotations(stairs, direction == 0 ? 7 : direction == 1 ? 5 : direction == 2 ? 6 : 4), 1, direction);
			rotatedBeam(world, rand, x, y + 8, z, 3, 5, getStairRotations(stairs, direction == 0 ? 7 : direction == 1 ? 5 : direction == 2 ? 6 : 4), 1, direction);

			// stairs right
			rotatedBeam(world, rand, x, y + 11, z, 3, 8, getStairRotations(stairs, direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0), 4, direction);
			rotatedBeam(world, rand, x, y + 10, z, 3, 9, getStairRotations(stairs, direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0), 4, direction);
			rotatedBeam(world, rand, x, y + 9, z, 3, 10, getStairRotations(stairs, direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0), 3, direction);
			rotatedBeam(world, rand, x, y + 8, z, 3, 11, getStairRotations(stairs, direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0), 2, direction);
			rotatedBeam(world, rand, x, y + 10, z, 3, 8, getStairRotations(stairs, direction == 0 ? 6 : direction == 1 ? 4 : direction == 2 ? 7 : 5), 1, direction);
			rotatedBeam(world, rand, x, y + 9, z, 3, 9, getStairRotations(stairs, direction == 0 ? 6 : direction == 1 ? 4 : direction == 2 ? 7 : 5), 1, direction);
			rotatedBeam(world, rand, x, y + 8, z, 3, 10, getStairRotations(stairs, direction == 0 ? 6 : direction == 1 ? 4 : direction == 2 ? 7 : 5), 1, direction);

			//stairs apex
			rotatedBeam(world, rand, x, y + 11, z, 7, 7, plank, 1, direction);
			rotatedBeam(world, rand, x, y + 11, z, 7, 8, plank, 1, direction);
		}

		// door/extension(s)
		boolean leftExtension = true;//rand.nextBoolean();
		boolean rightExtension = true;//rand.nextBoolean();
		// boolean frontExtension = rand.nextBoolean();
		boolean backExtension = true;//rand.nextBoolean();

		if (!leftExtension && !rightExtension && !backExtension) { //&& !frontExtension
			int direction = rand.nextInt(4);
			System.out.println("Door can only be on main building");
			//front doors
			rotatedBeam(world, rand, x, y, z, 7, 5, getDoorRotations(door, direction == 1 ? 1 : direction == 3 ? 3 : direction == 2 ? 2 : 0).withProperty(BlockDoorErebus.HINGE, direction == 0 || direction == 2 ? BlockDoorErebus.EnumHingePosition.RIGHT : BlockDoorErebus.EnumHingePosition.LEFT), 1, direction);
			rotatedBeam(world, rand, x, y + 1, z, 7, 5, getDoorRotations(door, direction == 1 ? 5 : direction == 3 ? 7 : direction == 2 ? 6 : 4).withProperty(BlockDoorErebus.HINGE, direction == 0 || direction == 2 ? BlockDoorErebus.EnumHingePosition.RIGHT : BlockDoorErebus.EnumHingePosition.LEFT), 1, direction);
			rotatedBeam(world, rand, x, y, z, 8, 5, getDoorRotations(door, direction == 1 ? 1 : direction == 3 ? 3 : direction == 2 ? 2 : 0).withProperty(BlockDoorErebus.HINGE, direction == 0 || direction == 2 ? BlockDoorErebus.EnumHingePosition.LEFT : BlockDoorErebus.EnumHingePosition.RIGHT), 1, direction);
			rotatedBeam(world, rand, x, y + 1, z, 8, 5, getDoorRotations(door, direction == 1 ? 5 : direction == 3 ? 7 : direction == 2 ? 6 : 4).withProperty(BlockDoorErebus.HINGE, direction == 0 || direction == 2 ? BlockDoorErebus.EnumHingePosition.LEFT : BlockDoorErebus.EnumHingePosition.RIGHT), 1, direction);
		}

		//   if(frontExtension) {
		//	   addExtention(world, rand,  x, y, z, 0);
		//	   System.out.println("Has Front Wing");
		//   }

		if (leftExtension) {
			addExtention(world, rand, x, y, z, 1);
			System.out.println("Has Left Wing");
		}

		if (backExtension) {
			addExtention(world, rand, x, y, z, 2);
			System.out.println("Has Back Wing");
		}

		if (rightExtension) {
			addExtention(world, rand, x, y, z, 3);
			System.out.println("Has Right Wing");
		}

		System.out.println("Added Hut at: " + x + " " + z);
		return true;
	}

	private void addExtention(World world, Random rand, int x, int y, int z, int direction) {
		//frame
		rotatedBeam(world, rand, x, y + 2, z, 2, 6, getLogRotations(log, direction == 0 || direction == 2 ? 4 : 8), 3, direction);
		rotatedBeam(world, rand, x, y + 2, z, 2, 9, getLogRotations(log, direction == 0 || direction == 2 ? 4 : 8), 3, direction);

		for (int beamHeight = 0; beamHeight < 2; beamHeight++) {
			rotatedBeam(world, rand, x, y + beamHeight, z, 1, 6, log, 1, direction);
			rotatedBeam(world, rand, x, y + beamHeight, z, 1, 9, log, 1, direction);
			rotatedBeam(world, rand, x, y + beamHeight, z, 4, 6, log, 1, direction);
			rotatedBeam(world, rand, x, y + beamHeight, z, 4, 9, log, 1, direction);
		}

		for (int beamLength = 0; beamLength < 4; beamLength++)
			rotatedBeam(world, rand, x, y + 2, z, 1, 6 + beamLength, getLogRotations(log, direction == 0 || direction == 2 ? 8 : 4), 1, direction);

		for (int beamLength = 0; beamLength < 2; beamLength++)
			rotatedBeam(world, rand, x, y + 3, z, 1, 7 + beamLength, plank, 1, direction);

		// air gap
		for (int beamLength = 0; beamLength < 2; beamLength++)
			for (int beamHeight = 0; beamHeight < 3; beamHeight++)
				rotatedBeam(world, rand, x, y + beamHeight, z, 5, 7 + beamLength, Blocks.AIR.getDefaultState(), 1, direction);

		//windows side
		for (int beamLength = 0; beamLength < 2; beamLength++) {
			rotatedBeam(world, rand, x, y, z, 1, 7 + beamLength, bricks, 1, direction);
			rotatedBeam(world, rand, x, y + 1, z, 1, 7 + beamLength, fence, 1, direction);
		}

		//windows back
		rotatedBeam(world, rand, x, y, z, 2, 6, bricks, 2, direction);
		rotatedBeam(world, rand, x, y + 1, z, 2, 6, fence, 2, direction);

		//front door
		rotatedBeam(world, rand, x, y, z, 2, 9, bricks, 1, direction);
		rotatedBeam(world, rand, x, y + 1, z, 2, 9, plank, 1, direction);
		
		rotatedBeam(world, rand, x, y, z, 3, 9, getDoorRotations(door, direction == 1 ? 1 : direction == 3 ? 3 : direction == 2 ? 2 : 0).withProperty(BlockDoorErebus.HINGE, direction == 0 || direction == 2 ? BlockDoorErebus.EnumHingePosition.RIGHT : BlockDoorErebus.EnumHingePosition.LEFT), 1, direction);
		rotatedBeam(world, rand, x, y + 1, z, 3, 9, getDoorRotations(door, direction == 1 ? 5 : direction == 3 ? 7 : direction == 2 ? 6 : 4).withProperty(BlockDoorErebus.HINGE, direction == 0 || direction == 2 ? BlockDoorErebus.EnumHingePosition.RIGHT : BlockDoorErebus.EnumHingePosition.LEFT), 1, direction);

		// stairs front
		rotatedBeam(world, rand, x, y + 2, z, 0, 5, getStairRotations(stairs, direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1), 5, direction);
		rotatedBeam(world, rand, x, y + 3, z, 0, 6, getStairRotations(stairs, direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1), 4, direction);
		rotatedBeam(world, rand, x, y + 4, z, 0, 7, getStairRotations(stairs, direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1), 4, direction);
		rotatedBeam(world, rand, x, y + 2, z, 0, 6, getStairRotations(stairs, direction == 0 ? 7 : direction == 1 ? 5 : direction == 2 ? 6 : 4), 1, direction);
		rotatedBeam(world, rand, x, y + 3, z, 0, 7, getStairRotations(stairs, direction == 0 ? 7 : direction == 1 ? 5 : direction == 2 ? 6 : 4), 1, direction);

		// stairs back
		rotatedBeam(world, rand, x, y + 4, z, 0, 8, getStairRotations(stairs, direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0), 4, direction);
		rotatedBeam(world, rand, x, y + 3, z, 0, 9, getStairRotations(stairs, direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0), 4, direction);
		rotatedBeam(world, rand, x, y + 2, z, 0, 10, getStairRotations(stairs, direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0), 5, direction);
		rotatedBeam(world, rand, x, y + 3, z, 0, 8, getStairRotations(stairs, direction == 0 ? 6 : direction == 1 ? 4 : direction == 2 ? 7 : 5), 1, direction);
		rotatedBeam(world, rand, x, y + 2, z, 0, 9, getStairRotations(stairs, direction == 0 ? 6 : direction == 1 ? 4 : direction == 2 ? 7 : 5), 1, direction);

		// stairs tidy up hole filler
		rotatedBeam(world, rand, x, y + 3, z, 4, 6, plank, 1, direction);
		rotatedBeam(world, rand, x, y + 3, z, 4, 9, plank, 1, direction);

	}

	public void rotatedBeam(World world, Random rand, int x, int y, int z, int a, int b, IBlockState blockType, int size, int direction) {
		switch (direction) {
			case 0:
				for (int xx = x + a; xx < x + a + size; xx++)
					world.setBlockState(new BlockPos(xx, y, z + b), blockType, 2);
				break;
			case 1:
				for (int zz = z + a; zz < z + a + size; zz++)
					world.setBlockState(new BlockPos(x + b, y, zz), blockType, 2);
				break;
			case 2:
				for (int xx = x + length - a - 1; xx > x + length - a - size - 1; xx--)
					world.setBlockState(new BlockPos(xx, y, z + length - b - 1), blockType, 2);
				break;
			case 3:
				for (int zz = z + length - a - 1; zz > z + length - a - size - 1; zz--)
					world.setBlockState(new BlockPos(x + length - b - 1, y, zz), blockType, 2);
				break;
		}
	}

	public void verticalBeam(World world, Random rand, int x, int y, int z, IBlockState blockType, int size, int direction) {
		for (int yy = y; yy < y + size; yy++)
			world.setBlockState(new BlockPos(x, yy, z), blockType, 2);
	}

	public void horizontalBeam(World world, Random rand, int x, int y, int z, IBlockState blockType, int size, int direction) {
		switch (direction) {
			case 0:
				for (int xx = x; xx < x + size; xx++)
					world.setBlockState(new BlockPos(xx, y, z), blockType, 2);
				break;
			case 1:
				for (int zz = z; zz < z + size; zz++)
					world.setBlockState(new BlockPos(x, y, zz), blockType, 2);
				break;
		}
	}

	public IBlockState getStairRotations(IBlockState state, int blockMeta) {
		int direction = blockMeta;
		switch (direction) {
		case 0:
			return state.withProperty(BlockStairs.FACING, EnumFacing.EAST);
		case 1:
			return state.withProperty(BlockStairs.FACING, EnumFacing.WEST);
		case 2:
			return state.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
		case 3:
			return state.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
		case 4:
			return state.withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, EnumHalf.TOP);
		case 5:
			return state.withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, EnumHalf.TOP);
		case 6:
			return state.withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.HALF, EnumHalf.TOP);
		case 7:
			return state.withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, EnumHalf.TOP);
		}
		return state;
	}

	public IBlockState getDoorRotations(IBlockState state, int direction) {
		switch (direction) {
		case 0:
			return state.withProperty(BlockDoorErebus.FACING, EnumFacing.SOUTH).withProperty(BlockDoorErebus.HALF, BlockDoorErebus.EnumDoorHalf.LOWER);
		case 1:
			return state.withProperty(BlockDoorErebus.FACING, EnumFacing.EAST).withProperty(BlockDoorErebus.HALF, BlockDoorErebus.EnumDoorHalf.LOWER);
		case 2:
			return state.withProperty(BlockDoorErebus.FACING, EnumFacing.NORTH).withProperty(BlockDoorErebus.HALF, BlockDoorErebus.EnumDoorHalf.LOWER);
		case 3:
			return state.withProperty(BlockDoorErebus.FACING, EnumFacing.WEST).withProperty(BlockDoorErebus.HALF, BlockDoorErebus.EnumDoorHalf.LOWER);
		case 4:
			state.withProperty(BlockDoorErebus.FACING, EnumFacing.SOUTH).withProperty(BlockDoorErebus.HALF, BlockDoorErebus.EnumDoorHalf.UPPER);
		case 5:
			return state.withProperty(BlockDoorErebus.FACING, EnumFacing.EAST).withProperty(BlockDoorErebus.HALF, BlockDoorErebus.EnumDoorHalf.UPPER);
		case 6:
			return state.withProperty(BlockDoorErebus.FACING, EnumFacing.NORTH).withProperty(BlockDoorErebus.HALF, BlockDoorErebus.EnumDoorHalf.UPPER);
		case 7:
			return state.withProperty(BlockDoorErebus.FACING, EnumFacing.WEST).withProperty(BlockDoorErebus.HALF, BlockDoorErebus.EnumDoorHalf.UPPER);
		}
		return state;
	}

	public IBlockState getLogRotations(IBlockState state, int axis) {
		if (axis == 4)
			return state.withProperty(BlockLogErebus.LOG_AXIS, BlockLog.EnumAxis.X);
		if (axis == 8)
			return state.withProperty(BlockLogErebus.LOG_AXIS, BlockLog.EnumAxis.Z);
		return state;
	}
}
