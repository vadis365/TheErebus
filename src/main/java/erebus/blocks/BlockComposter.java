package erebus.blocks;

import java.util.Random;

import erebus.Erebus;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.proxy.CommonProxy;
import erebus.tileentity.TileEntityComposter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
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

public class BlockComposter extends BlockContainer {

	public BlockComposter() {
		super(Material.ROCK);
		setHardness(2.0F);
		setSoundType(SoundType.WOOD);
		setCreativeTab(ModTabs.BLOCKS);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
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
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntityComposter composter = Utils.getTileEntity(world, pos, TileEntityComposter.class);
		if (composter != null)
			player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.COMPOSTER.ordinal(), world, pos.getX(), pos.getY(),pos.getZ());
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityComposter();
	}

	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityComposter composter = Utils.getTileEntity(world, pos, TileEntityComposter.class);
		if (composter != null)
			for (int i = 0; i < composter.getSizeInventory(); i++) {
				ItemStack is = composter.getStackInSlot(i);
				if (!is.isEmpty())
					Utils.dropStack(world, pos, is);
			}
		super.breakBlock(world, pos, state);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		TileEntityComposter composter = Utils.getTileEntity(world, pos, TileEntityComposter.class);
		if (composter != null && composter.composterBurnTime > 0) {
			double particleX = pos.getX() + 0.5F;
			double particleY = pos.getY() + 1.1F + rand.nextFloat() * 6.0F / 16.0F;
			double particleZ = pos.getZ() + 0.5F;
			Erebus.PROXY.spawnCustomParticle("bonemeal", world, particleX, particleY, particleZ, 0D, 0D, 0D);
		}
	}

	@Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

	@Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }
}