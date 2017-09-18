package erebus.block.cooking;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntitySmoothieMaker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSmoothieMaker extends BlockContainer {

	public BlockSmoothieMaker() {
		super(Material.rock);
		setHardness(2.0F);
		setResistance(5.0F);
		setBlockName("erebus.smoothieMaker");
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.umberstone.getIcon(side, 0);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TileEntitySmoothieMaker tile = Utils.getTileEntity(world, x, y, z, TileEntitySmoothieMaker.class);

		if (player.isSneaking())
			return false;

		if (player.getCurrentEquippedItem() != null) {
			ItemStack oldItem = player.getCurrentEquippedItem();
			ItemStack newItem = tile.fillTankWithBucket(oldItem);

			if (!ItemStack.areItemStacksEqual(oldItem, newItem)) {
				if (!player.capabilities.isCreativeMode)
					if (oldItem.stackSize > 1) {
						oldItem.stackSize--;
						if (!player.inventory.addItemStackToInventory(newItem))
							player.dropPlayerItemWithRandomChoice(newItem, false);
						else
							player.inventory.markDirty();
					} else {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, newItem);
						player.inventory.markDirty();
					}
				return true;
			}
		}

		if (tile != null)
			player.openGui(Erebus.instance, CommonProxy.GuiID.SMOOTHIE_MAKER.ordinal(), world, x, y, z);

		return true;
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySmoothieMaker();
	}
}