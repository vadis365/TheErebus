package erebus.blocks;

import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityWoodlouse;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLogErebus extends BlockLog {

	public BlockLogErebus() {
		setHarvestLevel("axe", 0);
		setCreativeTab(ModTabs.BLOCKS);
		Blocks.FIRE.setFireInfo(this, 5, 5);
		setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		switch (meta & 12) {
		case 0:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
			break;
		case 4:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
			break;
		case 8:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
			break;
		default:
			state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}
		return state;
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	public int getMetaFromState(IBlockState state) {
		int meta = 0;

		switch (state.getValue(LOG_AXIS)) {
		case X:
			meta |= 4;
			break;
		case Z:
			meta |= 8;
			break;
		case NONE:
			meta |= 12;
		}
		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer.Builder(this).add(LOG_AXIS).build();
	}

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (state.getBlock() == EnumWood.BALSAM.getLog()) {
			Random rand = world instanceof World ? ((World) world).rand : RANDOM;
			drops.add(new ItemStack(ModBlocks.LOG_BALSAM_RESINLESS));
			for (int i = 0; i < 1 + rand.nextInt(2 + fortune); i++)
				drops.add(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.RESIN.ordinal()));
			return;
		}
		super.getDrops(drops, world, pos, state, fortune);
	}

	@Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return state.getBlock() == EnumWood.BALSAM.getLog() ? true : super.canSilkHarvest(world,pos, state,  player);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote && state.getBlock() == EnumWood.ROTTEN.getLog())
			if (world.rand.nextInt(30) == 0) {
				EntityWoodlouse entity = new EntityWoodlouse(world);
				entity.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
				world.spawnEntity(entity);
			} else if (world.rand.nextInt(30) == 0) {
				EntityBeetleLarva entity = new EntityBeetleLarva(world);
				entity.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
				world.spawnEntity(entity);
			} else if (world.rand.nextInt(30) == 0) {
				EntitySilverfish entity = new EntitySilverfish(world);
				entity.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
				world.spawnEntity(entity);
			}
		super.onBlockDestroyedByPlayer(world, pos, state);
	}
}