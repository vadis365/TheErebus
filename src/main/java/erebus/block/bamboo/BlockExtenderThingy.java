package erebus.block.bamboo;

import erebus.Erebus;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityExtenderThingy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockExtenderThingy extends BlockContainer {

	public BlockExtenderThingy() {
		super(Material.wood);
		setHardness(0.4F);
		setHarvestLevel("axe", 0);
		setStepSound(soundTypeLadder);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.extenderThingy");
		setBlockTextureName("erebus:planks_bamboo");
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		if (world.getTileEntity(x, y, z) != null)
			player.openGui(Erebus.instance, CommonProxy.GuiID.EXTENDER_THINGY.ordinal(), world, x, y, z);

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, BlockPistonBase.determineOrientation(world, x, y, z, player), 2);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		TileEntityExtenderThingy tile = Utils.getTileEntity(world, x, y, z, TileEntityExtenderThingy.class);
		tile.setExtending(world.isBlockIndirectlyGettingPowered(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityExtenderThingy();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		IInventory tile = Utils.getTileEntity(world, x, y, z, IInventory.class);
		if (tile != null)
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack stack = tile.getStackInSlot(i);
				if (stack != null)
					Utils.dropStack(world, x, y, z, stack);
			}
		super.breakBlock(world, x, y, z, block, meta);
	}
}