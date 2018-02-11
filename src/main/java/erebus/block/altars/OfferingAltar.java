package erebus.block.altars;

import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityOfferingAltar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
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

public class OfferingAltar extends BlockContainer {

	public OfferingAltar() {
		super(Material.ROCK);
		setHardness(2.0F);
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityOfferingAltar();
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
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityOfferingAltar tile = Utils.getTileEntity(world, pos, TileEntityOfferingAltar.class);
		if (tile == null)
			return false;

		ItemStack stack = player.inventory.getCurrentItem();
		if (stack.isEmpty()) {
			if (player.isSneaking()) {
				tile.popStack();
				return true;
			}
		} else if (!player.isSneaking()) {
			tile.addStack(stack);
			return true;
		}

		return false;
	}

    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.ParticleManager manager) {
        return true;
    }

	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
		world.playEvent(2001, pos, Block.getStateId(Blocks.OBSIDIAN.getDefaultState()));
		Utils.dropInventoryContents(Utils.getTileEntity(world, pos, TileEntityOfferingAltar.class));
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World world, BlockPos pos) {
		return Container.calcRedstoneFromInventory(Utils.getTileEntity(world, pos, IInventory.class));
	}

}