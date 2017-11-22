package erebus.blocks;

import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.tileentity.TileEntityGaeanKeystone;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGaeanKeystone extends Block implements ITileEntityProvider {
	public static final PropertyBool ACTIVE = PropertyBool.create("active");

	public BlockGaeanKeystone() {
		super(Material.ROCK);
		setHardness(3.0f);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(ACTIVE, false));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	   public int damageDropped(IBlockState state) {
		return 0;
	}

	public static boolean isGemActive(int metadata) {
		return metadata > 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { ACTIVE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		switch (meta) {
		case 0:
			state = state.withProperty(ACTIVE, Boolean.valueOf(false));
			break;
		case 1:
			state = state.withProperty(ACTIVE, Boolean.valueOf(true));
			break;
		default:
			state = state.withProperty(ACTIVE, Boolean.valueOf(false));
		}
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		if (((Boolean) state.getValue(ACTIVE)).booleanValue())
			meta = meta | 1;
		return meta;
	}

	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    ItemStack stack = player.getHeldItemMainhand();
		if (!stack.isEmpty() && stack.getItem() == ModItems.PORTAL_ACTIVATOR) {
			if (!world.isRemote) {
				if (ErebusPortal.makePortal(world, pos.down(2))) {
					world.setBlockState(pos, getDefaultState().withProperty(ACTIVE, Boolean.valueOf(true)), 3);
					stack.damageItem(1, player);
					return true;
				} else
					world.setBlockState(pos, getDefaultState().withProperty(ACTIVE, Boolean.valueOf(false)), 3);
			}
			return true;
		}
		
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGaeanKeystone();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		super.breakBlock(world, pos, state);
		if (world.getBlockState(pos.down(1)) == ModBlocks.PORTAL.getDefaultState())
			world.setBlockToAir(pos.down(1));
		if (world.getBlockState(pos.down(2)) == ModBlocks.PORTAL.getDefaultState())
			world.setBlockToAir(pos.down(2));
	}
}