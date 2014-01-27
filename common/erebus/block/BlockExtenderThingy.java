package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityExtenderThingy;
import erebus.utils.Utils;

public class BlockExtenderThingy extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private Icon iconTop, iconBottom;

	public BlockExtenderThingy(int id) {
		super(id, Material.wood);
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

		if (world.getBlockTileEntity(x, y, z) != null)
			player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_EXTENDER_THINGY, world, x, y, z);

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, BlockPistonBase.determineOrientation(world, x, y, z, player), 2);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		TileEntityExtenderThingy tile = (TileEntityExtenderThingy) world.getBlockTileEntity(x, y, z);
		tile.setExtending(world.isBlockIndirectlyGettingPowered(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityExtenderThingy();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		IInventory tile = (IInventory) world.getBlockTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack stack = tile.getStackInSlot(i);
				if (stack != null)
					Utils.dropStack(world, x, y, z, stack);
			}
			world.func_96440_m(x, y, z, par5);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? iconBottom : side == 1 ? iconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:extenderThingy");// Side
		iconTop = iconRegister.registerIcon("erebus:extenderThingy");// Top
		iconBottom = iconRegister.registerIcon("erebus:extenderThingy");
	}
}