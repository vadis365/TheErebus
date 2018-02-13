package erebus.blocks;

import java.util.Random;

import erebus.Erebus;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.teleporter.TeleporterHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ErebusPortal extends Block {

	public ErebusPortal() {
		super(Material.PORTAL);
		setLightLevel(1.0F);
		setBlockUnbreakable();
		setSoundType(SoundType.GLASS);
	}

	public static boolean obeysPortalRule(World world, int x, int y, int z, boolean actualPortal) {
		// >0 substrate neighbors
		// Each substrate neighbor has solid/substrate on the opposite side
		//
		int neighborPortals = 0;
		int axisFlag = 0;
		for (EnumFacing dir : EnumFacing.VALUES) {
			final int atX = x + dir.getFrontOffsetX();
			final int atY = y + dir.getFrontOffsetY();
			final int atZ = z + dir.getFrontOffsetZ();
			Block at = world.getBlockState(new BlockPos(atX, atY, atZ)).getBlock();
			if (!isSubstrate(at, actualPortal))
				continue;
			final int opX = x - dir.getFrontOffsetX();
			final int opY = y - dir.getFrontOffsetY();
			final int opZ = z - dir.getFrontOffsetZ();
			IBlockState op = world.getBlockState(new BlockPos(opX, opY, opZ));
			if (!op.isNormalCube() && !isSubstrate(op.getBlock(), actualPortal))
				return false;
			neighborPortals++;
			axisFlag |= 1 << (dir.ordinal() >> 1) /* Creates a mask formatted as: <Up|Down><North|South><East|West> */;
		}
		if (neighborPortals < 1)
			return false;
		if (axisFlag == 0x7)
			return false; // 0b111, meaning there's a neighbor on 3 sides, therefore not defining a plane
		return true;
	}

	private static boolean isSubstrate(Block block, boolean portalNotLeaf) {
		return portalNotLeaf ? block instanceof ErebusPortal : block instanceof BlockLeaves;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (!obeysPortalRule(world, pos.getX(), pos.getY(), pos.getZ(), true))
			world.setBlockToAir(pos);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.timeUntilPortal <= 0) {
			if (entityIn.dimension == 0)
				TeleporterHandler.transferToErebus(entityIn);
			else
				TeleporterHandler.transferToOverworld(entityIn);
			if (entityIn != null)
				entityIn.timeUntilPortal = ConfigHandler.INSTANCE.portalCooldown * 20;
			return;
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = world.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		if (state != iblockstate)
			return true;
		if (block == this)
			return false;

		return block == this ? false : super.shouldSideBeRendered(state, world, pos, side);
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
		for (int amount = 0; amount < 4; ++amount) {
			double x = (double) ((float) pos.getX() + rand.nextFloat());
			double y = (double) ((float) pos.getY() + rand.nextFloat());
			double z = (double) ((float) pos.getZ() + rand.nextFloat());
			double velX = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double velY = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double velZ = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			int offSet = rand.nextInt(2) * 2 - 1;

			if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
				x = (double) pos.getX() + 0.5D + 0.25D * (double) offSet;
				velX = (double) (rand.nextFloat() * 2.0F * (float) offSet);
			} else {
				z = (double) pos.getZ() + 0.5D + 0.25D * (double) offSet;
				velZ = (double) (rand.nextFloat() * 2.0F * (float) offSet);
			}
			Erebus.PROXY.spawnCustomParticle("erebus_portal", world, x, y, z, velX, velY, velZ);
		}
	}
}