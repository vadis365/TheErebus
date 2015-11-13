package erebus.block.bamboo;

import erebus.Erebus;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBambooCrate extends BlockContainer {

	public BlockBambooCrate() {
		super(Material.wood);
		setHardness(2.0F);
		setHarvestLevel("axe", 0);
		setStepSound(soundTypeWood);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.bambooCrate");
		setBlockTextureName("erebus:bambooCrate");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBambooCrate();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (isCrate(world, x, y, z)) {
			// BOTTOM
			if (isCrate(world, x, y + 1, z) && isCrate(world, x + 1, y + 1, z) && isCrate(world, x + 1, y + 1, z + 1) && isCrate(world, x, y + 1, z + 1))
				if (isCrate(world, x + 1, y, z) && isCrate(world, x + 1, y, z + 1) && isCrate(world, x, y, z + 1)) {
					world.setBlockMetadataWithNotify(x, y, z, 1, 3); // BTL
					return;
				}

			if (isCrate(world, x, y + 1, z) && isCrate(world, x - 1, y + 1, z) && isCrate(world, x - 1, y + 1, z + 1) && isCrate(world, x, y + 1, z + 1))
				if (isCrate(world, x - 1, y, z) && isCrate(world, x - 1, y, z + 1) && isCrate(world, x, y, z + 1)) {
					world.setBlockMetadataWithNotify(x, y, z, 2, 3); // BTR
					return;
				}

			if (isCrate(world, x, y + 1, z) && isCrate(world, x + 1, y + 1, z) && isCrate(world, x + 1, y + 1, z - 1) && isCrate(world, x, y + 1, z - 1))
				if (isCrate(world, x + 1, y, z) && isCrate(world, x + 1, y, z - 1) && isCrate(world, x, y, z - 1)) {
					world.setBlockMetadataWithNotify(x, y, z, 3, 3); // BBL
					return;
				}

			if (isCrate(world, x, y + 1, z) && isCrate(world, x - 1, y + 1, z) && isCrate(world, x - 1, y + 1, z - 1) && isCrate(world, x, y + 1, z - 1))
				if (isCrate(world, x - 1, y, z) && isCrate(world, x - 1, y, z - 1) && isCrate(world, x, y, z - 1)) {
					world.setBlockMetadataWithNotify(x, y, z, 4, 3); // BBR
					return;
				}

			// TOP
			if (isCrate(world, x, y - 1, z) && isCrate(world, x + 1, y - 1, z) && isCrate(world, x + 1, y - 1, z + 1) && isCrate(world, x, y - 1, z + 1))
				if (isCrate(world, x + 1, y, z) && isCrate(world, x + 1, y, z + 1) && isCrate(world, x, y, z + 1)) {
					world.setBlockMetadataWithNotify(x, y, z, 5, 3); // TTL
					return;
				}

			if (isCrate(world, x, y - 1, z) && isCrate(world, x - 1, y - 1, z) && isCrate(world, x - 1, y - 1, z + 1) && isCrate(world, x, y - 1, z + 1))
				if (isCrate(world, x - 1, y, z) && isCrate(world, x - 1, y, z + 1) && isCrate(world, x, y, z + 1)) {
					world.setBlockMetadataWithNotify(x, y, z, 6, 3); // TTR
					return;
				}

			if (isCrate(world, x, y - 1, z) && isCrate(world, x + 1, y - 1, z) && isCrate(world, x + 1, y - 1, z - 1) && isCrate(world, x, y - 1, z - 1))
				if (isCrate(world, x + 1, y, z) && isCrate(world, x + 1, y, z - 1) && isCrate(world, x, y, z - 1)) {
					world.setBlockMetadataWithNotify(x, y, z, 7, 3); // TBL
					return;
				}

			if (isCrate(world, x, y - 1, z) && isCrate(world, x - 1, y - 1, z) && isCrate(world, x - 1, y - 1, z - 1) && isCrate(world, x, y - 1, z - 1))
				if (isCrate(world, x - 1, y, z) && isCrate(world, x - 1, y, z - 1) && isCrate(world, x, y, z - 1)) {
					world.setBlockMetadataWithNotify(x, y, z, 8, 3); // TBR
					return;
				}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (neighbour == this)
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 1; j++)
					for (int k = -1; k <= 1; k++)
						if (world.getBlock(x + i, y + k, z + j) == this)
							onBlockAdded(world, x + i, y + k, z + j);
	}

	private boolean isCrate(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == this;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			if (world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == this) {
				int meta = world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
				if (meta != 0)
					return false;
				if (world.getBlock(x + dir.getOpposite().offsetX, y + dir.getOpposite().offsetY, z + dir.getOpposite().offsetZ) == this)
					return false;
			}
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntityBambooCrate tileCrate = Utils.getTileEntity(world, x, y, z, TileEntityBambooCrate.class);
		if (tileCrate != null)
			if (world.getBlockMetadata(x, y, z) == 0) {
				ItemStack current = player.inventory.getCurrentItem();
				if (current != null && current.getItem() == Item.getItemFromBlock(this))
					return false;
				player.openGui(Erebus.instance, CommonProxy.GuiID.BAMBOO_CRATE.ordinal(), world, x, y, z);
			} else
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						for (int k = -1; k <= 1; k++)
							if (world.getBlock(x + i, y + k, z + j) == this)
								if (world.getBlockMetadata(x + i, y + k, z + j) == 1) {
									player.openGui(Erebus.instance, CommonProxy.GuiID.COLOSSAL_CRATE.ordinal(), world, x + i, y + k, z + j);
									return true;
								}
		return true;
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
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		resetCrates(world, x, y, z, world.getBlockMetadata(x, y, z));

		TileEntityBambooCrate tile = Utils.getTileEntity(world, x, y, z, TileEntityBambooCrate.class);
		if (tile != null)
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack is = tile.getStackInSlot(i);
				if (is != null)
					Utils.dropStack(world, x, y, z, is);
			}
		super.breakBlock(world, x, y, z, block, meta);
	}

	private void resetCrates(World world, int x, int y, int z, int meta) {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				for (int k = -1; k <= 1; k++)
					if (world.getBlock(x + i, y + k, z + j) == this && world.getBlockMetadata(x + i, y + k, z + j) != 0)
						world.setBlockMetadataWithNotify(x + i, y + k, z + j, 0, 3);
	}
}