package erebus.blocks;

import erebus.ModTabs;
import erebus.entity.EntityMucusBombPrimed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockMucusBomb extends Block {
    public static final PropertyBool EXPLODE = PropertyBool.create("explode");

	public BlockMucusBomb() {
		super(Material.TNT);
		setDefaultState(this.blockState.getBaseState().withProperty(EXPLODE, Boolean.valueOf(false)));
		setCreativeTab(ModTabs.BLOCKS);

	}

	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);

        if (world.isBlockPowered(pos)) {
            onBlockDestroyedByPlayer(world, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)));
            world.setBlockToAir(pos);
        }
    }

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (world.isBlockPowered(pos)) {
            onBlockDestroyedByPlayer(world, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)));
            world.setBlockToAir(pos);
        }
    }

	@Override
    public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosionIn) {
        if (!world.isRemote) {
        	EntityMucusBombPrimed primed = new EntityMucusBombPrimed(world, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F));
        	primed.setFuse((short)(world.rand.nextInt(primed.getFuse() / 4) + primed.getFuse() / 8));
            world.spawnEntity(primed);
        }
    }

	@Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		primeTnt(world, pos, state);
    }

	public void primeTnt(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote)
			if (((Boolean)state.getValue(EXPLODE)).booleanValue()) {
				EntityMucusBombPrimed primed = new EntityMucusBombPrimed(world, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F));
				world.spawnEntity(primed);
				world.playSound((EntityPlayer)null, primed.posX, primed.posY, primed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		if (!itemstack.isEmpty() && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)) {
			primeTnt(world, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)));
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
			if (itemstack.getItem() == Items.FLINT_AND_STEEL)
				itemstack.damageItem(1, player);
			 else if (!player.capabilities.isCreativeMode)
				itemstack.shrink(1);
			return true;
		} else {
			return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!world.isRemote && entity instanceof EntityArrow) {
			EntityArrow entityarrow = (EntityArrow) entity;

			if (entityarrow.isBurning()) {
				primeTnt(world, pos, world.getBlockState(pos).withProperty(EXPLODE, Boolean.valueOf(true)));
				world.setBlockToAir(pos);
			}
		}
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(EXPLODE, Boolean.valueOf((meta & 1) > 0));
    }

	@Override
    public int getMetaFromState(IBlockState state) {
        return ((Boolean)state.getValue(EXPLODE)).booleanValue() ? 1 : 0;
    }

	@Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {EXPLODE});
    }
}