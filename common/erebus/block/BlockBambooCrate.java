package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import erebus.ErebusMod;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.utils.Utils;

public class BlockBambooCrate extends BlockContainer {

	public BlockBambooCrate(int id) {
		super(id, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBambooCrate();
	}

	@Override
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:bambooCrate");
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
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		if (neighbourID == blockID)
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 1; j++)
					for (int k = -1; k <= 1; k++)
						if (world.getBlockId(x + i, y + k, z + j) == blockID)
							onBlockAdded(world, x + i, y + k, z + j);
	}

	private boolean isCrate(World world, int x, int y, int z) {
		return world.getBlockId(x, y, z) == blockID;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			if (world.getBlockId(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == blockID) {
				int meta = world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
				if (meta != 0)
					return false;
				if (world.getBlockId(x + dir.getOpposite().offsetX, y + dir.getOpposite().offsetY, z + dir.getOpposite().offsetZ) == blockID)
					return false;
			}
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntityBambooCrate tileCrate = (TileEntityBambooCrate) world.getBlockTileEntity(x, y, z);
		if (tileCrate != null)
			if (world.getBlockMetadata(x, y, z) == 0) {
				ItemStack current = player.inventory.getCurrentItem();
				if (current != null && current.itemID == blockID)
					return false;
				player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_BAMBOO_CRATE, world, x, y, z);
			} else
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						for (int k = -1; k <= 1; k++)
							if (world.getBlockId(x + i, y + k, z + j) == blockID)
								if (world.getBlockMetadata(x + i, y + k, z + j) == 1) {
									player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_COLOSSAL_CRATE, world, x + i, y + k, z + j);
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
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		resetCrates(world, x, y, z, world.getBlockMetadata(x, y, z));

		TileEntityBambooCrate tile = (TileEntityBambooCrate) world.getBlockTileEntity(x, y, z);
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

	private void resetCrates(World world, int x, int y, int z, int meta) {
		if (meta != 0)
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 1; j++)
					for (int k = -1; k <= 1; k++)
						if (world.getBlockId(x + i, y + k, z + j) == blockID && world.getBlockMetadata(x + i, y + k, z + j) != 0)
							world.setBlockMetadataWithNotify(x + i, y + k, z + j, 0, 3);
	}
}