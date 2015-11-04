package erebus.block.altars;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityOfferingAltar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class OfferingAltar extends BlockContainer {

	public OfferingAltar() {
		super(Material.rock);
		setHardness(2.0F);
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.offeringAltar");
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityOfferingAltar();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityOfferingAltar tile = Utils.getTileEntity(world, x, y, z, TileEntityOfferingAltar.class);
		if (tile == null)
			return false;

		ItemStack stack = player.getCurrentEquippedItem();
		if (stack == null) {
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

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		Utils.dropInventoryContents(Utils.getTileEntity(world, x, y, z, TileEntityOfferingAltar.class));
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory(Utils.getTileEntity(world, x, y, z, IInventory.class));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.stone.getIcon(side, 0);
	}
}