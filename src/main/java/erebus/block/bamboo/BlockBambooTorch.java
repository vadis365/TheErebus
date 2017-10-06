package erebus.block.bamboo;

import java.util.Random;

import javax.annotation.Nullable;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBambooTorch extends Block {

	public static final PropertyEnum<EnumBlockTorchHalf> HALF = PropertyEnum.<EnumBlockTorchHalf>create("half", EnumBlockTorchHalf.class);
	protected static final AxisAlignedBB TORCH_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

	public BlockBambooTorch() {
		super(Material.WOOD);
		setHardness(0.0F);
		setLightLevel(0.9F);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return TORCH_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos.up(), getDefaultState().withProperty(HALF, EnumBlockTorchHalf.UPPER), 2);
    }

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		if (state.getBlock() == null || !world.isAirBlock(pos.up()))
			return false;
		if (state.getBlock().isLeaves(state, world, pos.down()))
			return false;
		return state.getMaterial().blocksMovement();
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		dropTorchIfCantStay(world, state, pos);
	}

	protected boolean dropTorchIfCantStay(World world, IBlockState state, BlockPos pos) {
		if (world.isAirBlock(pos.down())) {
			world.setBlockToAir(pos);
			if (state.getValue(HALF) == EnumBlockTorchHalf.UPPER)
				dropBlockAsItem(world, pos, state, 0);
			return false;
		}
		return true;
	}

	@Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (state.getValue(HALF) == EnumBlockTorchHalf.UPPER) {
            if (world.getBlockState(pos.down()).getBlock() == this) {
                if (player.capabilities.isCreativeMode)
                    world.setBlockToAir(pos.down());
                else {
                    world.destroyBlock(pos.down(), true);
                    if (world.isRemote)
                        world.setBlockToAir(pos.down());
                }
            }
        }
        else if (world.getBlockState(pos.up()).getBlock() == this) {
            world.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
            dropBlockAsItem(world, pos, state.withProperty(HALF, EnumBlockTorchHalf.UPPER), 0);
        }
        super.onBlockHarvested(world, pos, state, player);
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (state.getValue(HALF) == EnumBlockTorchHalf.LOWER)
            return Items.AIR;
        else {
            return super.getItemDropped(state, rand, fortune);
        }
    }

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
    public IBlockState getStateFromMeta(int meta) {
        return (meta & 8) > 0 ? getDefaultState().withProperty(HALF, EnumBlockTorchHalf.UPPER) : getDefaultState().withProperty(HALF, EnumBlockTorchHalf.LOWER);
    }

	@Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(HALF) == EnumBlockTorchHalf.UPPER) {
            IBlockState iblockstate = world.getBlockState(pos.down());
            if (iblockstate.getBlock() == this)
            	return state;
        }
        return state;
    }

	@Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF) == EnumBlockTorchHalf.UPPER ? 8 : 0;
    }

	@Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {HALF});
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state.getValue(HALF) == EnumBlockTorchHalf.UPPER) {
			double d0 = pos.getX() + 0.4375F;
			double d1 = pos.getY() + 1.0625F;
			double d2 = pos.getZ() + 0.4375F;
			double d3 = pos.getX() + 0.5625F;
			double d4 = pos.getZ() + 0.5625F;
			double d5 = pos.getX() + 0.5F;
			double d6 = pos.getY() + 1.25F;
			double d7 = pos.getZ() + 0.5F;
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d3, d1, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d3, d1, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d3, d1, d4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d3, d1, d4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d5, d6, d7, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d5, d6, d7, 0.0D, 0.0D, 0.0D);
		}
	}

    public static enum EnumBlockTorchHalf implements IStringSerializable {
        UPPER,
        LOWER;

        public String toString() {
            return getName();
        }

        public String getName() {
            return this == UPPER ? "upper" : "lower";
        }
    }
}