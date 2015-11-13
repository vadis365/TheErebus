package erebus.block.silo;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSiloTank extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon siloTankActive;

	public BlockSiloTank(Material material) {
		super(material);
		setCreativeTab(ModTabs.blocks);
		setBlockBounds(0.125F, 0F, 0.125F, 0.875F, 1F, 0.875F);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (isSiloComplete(world, x, y, z)) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileEntitySiloTank) {
				((TileEntitySiloTank) tile).setActive(true);
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
				return;
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (isSiloComplete(world, x, y, z)) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileEntitySiloTank) {
				((TileEntitySiloTank) tile).setActive(true);
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
			}
		} else if (!isSiloComplete(world, x, y, z)) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileEntitySiloTank) {
				((TileEntitySiloTank) tile).setActive(false);
				world.setBlockMetadataWithNotify(x, y, z, 0, 3);
				breakBlock(world, x, y, z, this, 0);
				dropBlockAsItem(world, x, y, z, 0, 0);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof TileEntitySiloTank) {
			ItemStack current = player.inventory.getCurrentItem();
			if (current != null && current.getItem() == Item.getItemFromBlock(ModBlocks.siloRoof))
				return false;
			if (isSiloComplete(world, x, y, z)) {
				player.openGui(Erebus.instance, CommonProxy.GuiID.SILO_INVENTORY.ordinal(), world, x, y, z);
				return true;
			}
		}
		return true;
	}

	public boolean isSiloComplete(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z) == ModBlocks.siloSupports && world.getBlock(x, y + 1, z) == ModBlocks.siloRoof;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if (world.getBlock(x, y - 1, z) == ModBlocks.siloSupports)
			return true;
		return false;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntitySiloTank tile = Utils.getTileEntity(world, x, y, z, TileEntitySiloTank.class);
		if (tile != null)
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack is = tile.getStackInSlot(i);
				if (is != null)
					Utils.dropStack(world, x, y, z, is);
			}
		world.setBlockToAir(x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta == 1 ? siloTankActive : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:siloTankInactive");
		siloTankActive = reg.registerIcon("erebus:siloTankActive");
	}

	@Override
	public int getRenderType() {
		return 0;
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySiloTank();
	}
}