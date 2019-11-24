package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVelocity extends Block {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final AxisAlignedBB VELOCITY_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.875D, 1D);
	public BlockVelocity() {
		super(Material.ROCK);
		setHardness(1.5F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return VELOCITY_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.byIndex(meta);
		if (facing.getAxis() == EnumFacing.Axis.Y)
			facing = EnumFacing.NORTH;
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		meta = meta | ((EnumFacing) state.getValue(FACING)).getIndex();
		return meta;
	}

	@Override
	 public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity.isSneaking())
			return;

		if (entity instanceof EntityLiving)
			((EntityLiving) entity).enablePersistence();
		else if (entity instanceof EntityItem)
			((EntityItem) entity).setNoDespawn();

		double speed = speed();
		int meta = state.getValue(FACING).getIndex() - 2;
		int[] factorX = { 0, 0, -1, 1 };
		int[] factorZ = { -1, 1, 0, 0 };

		if (entity.posY > pos.getY() + 0.5D) {
			if (factorX[meta] == 0 && Math.abs(pos.getX() + 0.5D - entity.posX) < 0.5D && Math.abs(pos.getX() + 0.5D - entity.posX) > 0.1D)
				entity.motionX += Math.signum(pos.getX() + 0.5D - entity.posX) * Math.min(speed, Math.abs(pos.getX() + 0.5D - entity.posX)) / 1.2D;

			if (factorZ[meta] == 0 && Math.abs(pos.getZ() + 0.5D - entity.posZ) < 0.5D && Math.abs(pos.getZ() + 0.5D - entity.posZ) > 0.1D)
				entity.motionZ += Math.signum(pos.getZ() + 0.5D - entity.posZ) * Math.min(speed, Math.abs(pos.getZ() + 0.5D - entity.posZ)) / 1.2D;

			entity.motionX += factorX[meta] * speed;
			entity.motionZ += factorZ[meta] * speed;
		}
	}

	protected double speed() {
		return 0.2D;
	}

}