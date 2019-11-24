package erebus.blocks;

import java.util.Random;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.helper.Utils;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockForceLock extends Block {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final AxisAlignedBB FORCE_LOCK_AABB = new AxisAlignedBB(0.125D, 0D, 0.125D, 0.875D, 1D, 0.875D);

	public BlockForceLock() {
		super(Material.GLASS);
		setSoundType(SoundType.GLASS);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setLightLevel(0.8F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return FORCE_LOCK_AABB;
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (!stack.isEmpty() && stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.FORCE_KEY.ordinal()) {
			if (!player.capabilities.isCreativeMode)
				stack.shrink(1);
			if (!world.isRemote) {
				EnumFacing type = state.getValue(FACING);
				if (type == EnumFacing.EAST || type == EnumFacing.WEST) {
					if(world.getBlockState(pos.add(0, 0, 1)).getBlock() == ModBlocks.FORCE_FIELD)
						Utils.breakBlockWithParticles(world, pos.add(0, 0, 1));
					if(world.getBlockState(pos.add(0, 0, - 1)).getBlock() == ModBlocks.FORCE_FIELD)
						Utils.breakBlockWithParticles(world, pos.add(0, 0, - 1));
				}
				if (type == EnumFacing.SOUTH || type == EnumFacing.NORTH) {
					if(world.getBlockState(pos.add(1, 0, 0)).getBlock() == ModBlocks.FORCE_FIELD)
						Utils.breakBlockWithParticles(world, pos.add(1, 0, 0));
					if(world.getBlockState(pos.add(- 1, 0, 0)).getBlock() == ModBlocks.FORCE_FIELD)
						Utils.breakBlockWithParticles(world, pos.add(- 1, 0, 0));
				}
				Utils.breakBlockWithParticles(world, pos, state);
			}
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = world.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();
		return (block == this || block == ModBlocks.FORCE_FIELD)  ? false : super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {

		Random random = world.rand;
		double pixel = 0.0625D;
		if (rand.nextInt(5) == 0) {
			for (int sideIndex = 0; sideIndex < 6; ++sideIndex) {
				double particleX = (double) ((float) pos.getX() + random.nextFloat());
				double particleY = (double) ((float) pos.getY() + random.nextFloat());
				double particleZ = (double) ((float) pos.getZ() + random.nextFloat());

				if (sideIndex == 0 && !world.getBlockState(pos.up()).isOpaqueCube())
					particleY = (double) pos.getY() + 0.0625D + 1.0D;

				if (sideIndex == 1 && !world.getBlockState(pos.down()).isOpaqueCube())
					particleY = (double) pos.getY() - 0.0625D;

				if (sideIndex == 2 && !world.getBlockState(pos.south()).isOpaqueCube())
					particleZ = (double) pos.getZ() + 0.0625D + 1.0D;

				if (sideIndex == 3 && !world.getBlockState(pos.north()).isOpaqueCube())
					particleZ = (double) pos.getZ() - 0.0625D;

				if (sideIndex == 4 && !world.getBlockState(pos.east()).isOpaqueCube())
					particleX = (double) pos.getX() + 0.0625D + 1.0D;

				if (sideIndex == 5 && !world.getBlockState(pos.west()).isOpaqueCube())
					particleX = (double) pos.getX() - 0.0625D;

				if (particleX < (double) pos.getX() || particleX > (double) (pos.getX() + 1) || particleY < 0.0D || particleY > (double) (pos.getY() + 1) || particleZ < (double) pos.getZ() || particleZ > (double) (pos.getZ() + 1))
					Erebus.PROXY.spawnCustomParticle("sparks", world, particleX, particleY, particleZ, 0, 0, 0);
			}
		}
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			int Knockback = 1;
			entity.attackEntityFrom(DamageSource.CACTUS, 1);
			entity.addVelocity(MathHelper.sin(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F, 0.08D, -MathHelper.cos(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F);
			entity.getEntityWorld().playSound(null, pos, ModSounds.GLOW_WORM_HURT, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}
}