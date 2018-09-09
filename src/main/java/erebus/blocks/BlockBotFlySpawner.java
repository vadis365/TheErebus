package erebus.blocks;

import java.util.Locale;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBotFlySpawner extends BlockSpawner {
	
	public static final PropertyEnum<EnumDungDirection> TYPE = PropertyEnum.create("type", EnumDungDirection.class);

	public BlockBotFlySpawner(ResourceLocation mobName) {
		super(mobName);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumDungDirection.DOWN_NORTH));
		setHarvestLevel("pickaxe", 0);
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
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote) {
			EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
			entityareaeffectcloud.setRadius(3F);
			entityareaeffectcloud.setRadiusOnUse(-0.5F);
			entityareaeffectcloud.setWaitTime(10);
			entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() * 2);
			entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float) entityareaeffectcloud.getDuration());
			entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 140));
			entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.NAUSEA, 200));
			world.spawnEntity(entityareaeffectcloud);
		}
	}
	
	@Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
        return canPlaceBlock(world, pos, side);
    }

	@Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        for (EnumFacing enumfacing : EnumFacing.values())
            if (canPlaceBlock(world, pos, enumfacing))
                return true;
        return false;
    }

    protected static boolean canPlaceBlock(World world, BlockPos pos, EnumFacing direction) {
        BlockPos blockpos = pos.offset(direction.getOpposite());
        IBlockState iblockstate = world.getBlockState(blockpos);
        boolean flag = iblockstate.getBlockFaceShape(world, blockpos, direction) == BlockFaceShape.SOLID;
        Block block = iblockstate.getBlock();

        return world.isBlockNormalCube(blockpos, true) && block.isOpaqueCube(iblockstate) && flag;
    }

	@SuppressWarnings("incomplete-switch")
	@Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

		int direction = MathHelper.floor(placer.rotationYaw * 4F / 360F + 0.5D) & 3;

		EnumDungDirection newFacing = EnumDungDirection.DOWN_NORTH;
		if (facing == EnumFacing.UP) {
			switch (direction) {
				case 0:
					newFacing = EnumDungDirection.DOWN_SOUTH;
					break;
				case 1:
					newFacing = EnumDungDirection.DOWN_EAST;
					break;
				case 2:
					newFacing = EnumDungDirection.DOWN_NORTH;
					break;
				case 3:
					newFacing = EnumDungDirection.DOWN_WEST;
					break;
			}
		}
		else if (facing == EnumFacing.DOWN) {
			switch (direction) {
			case 0:
				newFacing = EnumDungDirection.UP_SOUTH;
				break;
			case 1:
				newFacing = EnumDungDirection.UP_EAST;
				break;
			case 2:
				newFacing = EnumDungDirection.UP_NORTH;
				break;
			case 3:
				newFacing = EnumDungDirection.UP_WEST;
				break;
			}
		}
		else {
			switch (facing) {
			case SOUTH:
				newFacing = EnumDungDirection.NORTH;
				break;
			case EAST:
				newFacing = EnumDungDirection.WEST;
				break;
			case NORTH:
				newFacing = EnumDungDirection.SOUTH;
				break;
			case WEST:
				newFacing = EnumDungDirection.EAST;
				break;
			}
		}

		return getDefaultState().withProperty(TYPE, newFacing);
    }

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.BLOCKS)
			list.add(new ItemStack(this, 1, EnumDungDirection.DOWN_NORTH.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumDungDirection.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumDungDirection type = state.getValue(TYPE);
		return type.ordinal();
	}

	public enum EnumDungDirection implements IErebusEnum {
		DOWN_NORTH,
		DOWN_SOUTH,
		DOWN_WEST,
		DOWN_EAST,
		UP_NORTH,
		UP_SOUTH,
		UP_WEST,
		UP_EAST,
		NORTH,
		SOUTH,
		WEST,
		EAST;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModBlocks.DUNG_SPAWNER_BOT_FLY, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}