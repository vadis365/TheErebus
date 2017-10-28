package erebus.blocks;

import java.util.Random;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.proxy.CommonProxy;
import erebus.tileentity.TileEntityHoneyComb;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHoneyComb extends BlockContainer {

	public BlockHoneyComb() {
		super(Material.ROCK);
		setHardness(0.5F);
		setLightLevel(0.5F);
		setResistance(10.0F);
		setSoundType(SoundType.CLOTH);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityHoneyComb();
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntityHoneyComb tileComb = Utils.getTileEntity(world, pos, TileEntityHoneyComb.class);
		if (tileComb != null) {
			ItemStack stack = player.getHeldItem(hand);
			if (!stack.isEmpty() && stack.getItem() == Item.getItemFromBlock(this) || !stack.isEmpty() && stack.getItem() == ModItems.BEE_TAMING_AMULET)
				return false;
			player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.HONEY_COMB.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityHoneyComb tile = (TileEntityHoneyComb) world.getTileEntity(pos);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack stack = tile.getStackInSlot(i);
				if (!stack.isEmpty())
					Utils.dropStack(world, pos, stack);
			}
		}
		world.playEvent(2001, pos, Block.getStateId(state));
		super.breakBlock(world, pos, state);
	}
}