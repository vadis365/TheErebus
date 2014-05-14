package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityHoneyComb;

public class BlockHoneyComb extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon top, frontAndBack;

	public BlockHoneyComb(int id) {
		super(id, Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return blockID;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityHoneyComb();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntityHoneyComb tileComb = (TileEntityHoneyComb) world.getBlockTileEntity(x, y, z);
		if (tileComb != null)
			if (world.getBlockMetadata(x, y, z) == 0) {
				ItemStack current = player.inventory.getCurrentItem();
				if (current != null && current.itemID == blockID || current != null && current.itemID == ModItems.beeTamingAmulet.itemID)
					return false;
				player.openGui(Erebus.instance, CommonProxy.GUI_ID_HONEY_COMB, world, x, y, z);
			}
		return true;
	}


	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

		TileEntityHoneyComb tile = (TileEntityHoneyComb) world.getBlockTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack is = tile.getStackInSlot(i);
				if (is != null)
					Utils.dropStack(world, x, y, z, is);
			}
			world.func_96440_m(x, y, z, par5);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? top : side == 1 ? top : side == 2 || side == 3 ? frontAndBack : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:honeyCombSides");// Sides
		top = reg.registerIcon("erebus:honeyCombTop");// Top & Bottom
		frontAndBack = reg.registerIcon("erebus:honeyCombFrontAndBack");//Front & Back
	}
}
