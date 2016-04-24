package erebus.block.trees;

import java.util.ArrayList;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityWoodlouse;
import erebus.item.ItemMaterials;
import erebus.lib.EnumWood;
import erebus.lib.Reference;

public class BlockLogErebus extends BlockLog {

	private final EnumWood wood;

	public BlockLogErebus(EnumWood wood) {
		this.wood = wood;
		setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		setCreativeTab(ModTabs.blocks);
		setRegistryName("log_" + wood.name().toLowerCase());
		setUnlocalizedName(getRegistryName().toString());
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iblockstate = getDefaultState();

		switch (meta & 12)
		{
		case 0:
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
			break;
		case 4:
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
			break;
		case 8:
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
			break;
		default:
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}

		return iblockstate;
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;

		switch (state.getValue(LOG_AXIS))
		{
		case X:
			i |= 4;
			break;
		case Z:
			i |= 8;
			break;
		case NONE:
			i |= 12;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
	}

	//TODO Later fix all the things
/*
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		if (wood == EnumWood.Sap) {
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
			drops.add(new ItemStack(ModBlocks.saplessLog));
			for (int i = 0; i < 1 + world.rand.nextInt(2 + fortune); i++)
				drops.add(new ItemStack(ModItems.materials, 1, ItemMaterials.DATA.sapBall.ordinal()));
			return drops;
		}
		return super.getDrops(world, x, y, z, metadata, fortune);
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return wood == EnumWood.Sap ? true : super.canSilkHarvest(world, player, x, y, z, metadata);
	}


	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		if (!world.isRemote && wood.name() == "Rotten")
			if (world.rand.nextInt(30) == 0) {
				EntityWoodlouse entity = new EntityWoodlouse(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			} else if (world.rand.nextInt(30) == 0) {
				EntityBeetleLarva entity = new EntityBeetleLarva(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			} else if (world.rand.nextInt(30) == 0) {
				EntitySilverfish entity = new EntitySilverfish(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			}
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
	*/
}