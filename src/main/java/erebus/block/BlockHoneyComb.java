package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityHoneyComb;
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

public class BlockHoneyComb extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon top, frontAndBack;

	public BlockHoneyComb() {
		super(Material.rock);
		setHardness(0.5F);
		setLightLevel(0.5F);
		setResistance(10.0F);
		setStepSound(soundTypeCloth);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.honeyCombBlock");
		setBlockTextureName("erebus:honeyCombTop");
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
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityHoneyComb();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntityHoneyComb tileComb = Utils.getTileEntity(world, x, y, z, TileEntityHoneyComb.class);
		if (tileComb != null)
			if (world.getBlockMetadata(x, y, z) == 0) {
				ItemStack current = player.inventory.getCurrentItem();
				if (current != null && current.getItem() == Item.getItemFromBlock(this) || current != null && current.getItem() == ModItems.beeTamingAmulet)
					return false;
				player.openGui(Erebus.instance, CommonProxy.GuiID.HONEY_COMB.ordinal(), world, x, y, z);
			}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {

		TileEntityHoneyComb tile = (TileEntityHoneyComb) world.getTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack is = tile.getStackInSlot(i);
				if (is != null)
					Utils.dropStack(world, x, y, z, is);
			}
			world.func_147453_f(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? top : side == 1 ? top : side == 2 || side == 3 ? frontAndBack : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:honeyCombSides");// Sides
		top = reg.registerIcon("erebus:honeyCombTop");// Top & Bottom
		frontAndBack = reg.registerIcon("erebus:honeyCombFrontAndBack");// Front
		// &
		// Back
	}
}