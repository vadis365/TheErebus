package erebus.block.altars;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityCraftingAltar;
import erebus.tileentity.TileEntityOfferingAltar;

public class CraftingAltar extends BlockContainer {
	public CraftingAltar() {
		super(Material.rock);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.craftingAltar");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCraftingAltar();
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

	public static void formAltar(World world, int x, int y, int z) {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				Utils.breakBlockWithParticles(world, x + i, y, z + j);
	//	world.setBlock(x, y, z, ModBlocks.craftingAltar);
	}

	public static boolean isValid(World world, int x, int y, int z) {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if (world.getBlock(x + i, y, z + j) != ModBlocks.altarBase)
					return false;
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityCraftingAltar tile = Utils.getTileEntity(world, x, y, z, TileEntityCraftingAltar.class);
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
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.templeTile.getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}
}