package erebus.block;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import erebus.utils.Utils;

public class BlockPetrifiedChest extends BlockContainer {

	public BlockPetrifiedChest(int id) {
		super(id, Material.rock);
		setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
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
	public int getRenderType() {
		return 22;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		if (world.getBlockId(x, y, z - 1) == blockID)
			setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
		else if (world.getBlockId(x, y, z + 1) == blockID)
			setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
		else if (world.getBlockId(x - 1, y, z) == blockID)
			setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		else if (world.getBlockId(x + 1, y, z) == blockID)
			setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
		else
			setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		unifyAdjacentChests(world, x, y, z);
		int l = world.getBlockId(x, y, z - 1);
		int i1 = world.getBlockId(x, y, z + 1);
		int j1 = world.getBlockId(x - 1, y, z);
		int k1 = world.getBlockId(x + 1, y, z);

		if (l == blockID)
			unifyAdjacentChests(world, x, y, z - 1);

		if (i1 == blockID)
			unifyAdjacentChests(world, x, y, z + 1);

		if (j1 == blockID)
			unifyAdjacentChests(world, x - 1, y, z);

		if (k1 == blockID)
			unifyAdjacentChests(world, x + 1, y, z);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is) {
		int l = world.getBlockId(x, y, z - 1);
		int i1 = world.getBlockId(x, y, z + 1);
		int j1 = world.getBlockId(x - 1, y, z);
		int k1 = world.getBlockId(x + 1, y, z);
		byte b0 = 0;
		int l1 = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l1 == 0)
			b0 = 2;

		if (l1 == 1)
			b0 = 5;

		if (l1 == 2)
			b0 = 3;

		if (l1 == 3)
			b0 = 4;

		if (l != blockID && i1 != blockID && j1 != blockID && k1 != blockID)
			world.setBlockMetadataWithNotify(x, y, z, b0, 3);
		else {
			if ((l == blockID || i1 == blockID) && (b0 == 4 || b0 == 5)) {
				if (l == blockID)
					world.setBlockMetadataWithNotify(x, y, z - 1, b0, 3);
				else
					world.setBlockMetadataWithNotify(x, y, z + 1, b0, 3);

				world.setBlockMetadataWithNotify(x, y, z, b0, 3);
			}

			if ((j1 == blockID || k1 == blockID) && (b0 == 2 || b0 == 3)) {
				if (j1 == blockID)
					world.setBlockMetadataWithNotify(x - 1, y, z, b0, 3);
				else
					world.setBlockMetadataWithNotify(x + 1, y, z, b0, 3);

				world.setBlockMetadataWithNotify(x, y, z, b0, 3);
			}
		}

		if (is.hasDisplayName())
			((TileEntityPetrifiedWoodChest) world.getBlockTileEntity(x, y, z)).setChestGuiName(is.getDisplayName());
	}

	public void unifyAdjacentChests(World world, int x, int y, int z) {
		if (!world.isRemote) {
			int l = world.getBlockId(x, y, z - 1);
			int i1 = world.getBlockId(x, y, z + 1);
			int j1 = world.getBlockId(x - 1, y, z);
			int k1 = world.getBlockId(x + 1, y, z);
			int l1;
			int i2;
			byte b0;
			int j2;

			if (l != blockID && i1 != blockID) {
				if (j1 != blockID && k1 != blockID) {
					b0 = 3;

					if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
						b0 = 3;

					if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
						b0 = 2;

					if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
						b0 = 5;

					if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
						b0 = 4;
				} else {
					l1 = world.getBlockId(j1 == blockID ? x - 1 : x + 1, y, z - 1);
					i2 = world.getBlockId(j1 == blockID ? x - 1 : x + 1, y, z + 1);
					b0 = 3;

					if (j1 == blockID)
						j2 = world.getBlockMetadata(x - 1, y, z);
					else
						j2 = world.getBlockMetadata(x + 1, y, z);

					if (j2 == 2)
						b0 = 2;

					if ((Block.opaqueCubeLookup[l] || Block.opaqueCubeLookup[l1]) && !Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[i2])
						b0 = 3;

					if ((Block.opaqueCubeLookup[i1] || Block.opaqueCubeLookup[i2]) && !Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[l1])
						b0 = 2;
				}
			} else {
				l1 = world.getBlockId(x - 1, y, l == blockID ? z - 1 : z + 1);
				i2 = world.getBlockId(x + 1, y, l == blockID ? z - 1 : z + 1);
				b0 = 5;

				if (l == blockID)
					j2 = world.getBlockMetadata(x, y, z - 1);
				else
					j2 = world.getBlockMetadata(x, y, z + 1);

				if (j2 == 4)
					b0 = 4;

				if ((Block.opaqueCubeLookup[j1] || Block.opaqueCubeLookup[l1]) && !Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[i2])
					b0 = 5;

				if ((Block.opaqueCubeLookup[k1] || Block.opaqueCubeLookup[i2]) && !Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[l1])
					b0 = 4;
			}

			world.setBlockMetadataWithNotify(x, y, z, b0, 3);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		int l = 0;

		if (world.getBlockId(x - 1, y, z) == blockID)
			++l;

		if (world.getBlockId(x + 1, y, z) == blockID)
			++l;

		if (world.getBlockId(x, y, z - 1) == blockID)
			++l;

		if (world.getBlockId(x, y, z + 1) == blockID)
			++l;

		return l > 1 ? false : isThereANeighborChest(world, x - 1, y, z) ? false : isThereANeighborChest(world, x + 1, y, z) ? false : isThereANeighborChest(world, x, y, z - 1) ? false : !isThereANeighborChest(world, x, y, z + 1);
	}

	private boolean isThereANeighborChest(World world, int x, int y, int z) {
		return world.getBlockId(x, y, z) != blockID ? false : world.getBlockId(x - 1, y, z) == blockID ? true : world.getBlockId(x + 1, y, z) == blockID ? true : world.getBlockId(x, y, z - 1) == blockID ? true : world.getBlockId(x, y, z + 1) == blockID;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
		super.onNeighborBlockChange(world, x, y, z, par5);
		TileEntityPetrifiedWoodChest tileentitychest = (TileEntityPetrifiedWoodChest) world.getBlockTileEntity(x, y, z);

		if (tileentitychest != null)
			tileentitychest.updateContainingBlockInfo();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TileEntityPetrifiedWoodChest tile = (TileEntityPetrifiedWoodChest) world.getBlockTileEntity(x, y, z);
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.isRemote)
			return true;
		else {
			player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_PETRIFIED_CHEST, world, x, y, z);
			return true;
		}
	}

	public static IInventory getInventory(World world, int x, int y, int z) {
		Object object = world.getBlockTileEntity(x, y, z);
		int blockID = ModBlocks.petrifiedWoodChest.blockID;

		if (object == null)
			return null;
		else if (world.isBlockSolidOnSide(x, y + 1, z, DOWN))
			return null;
		else if (isOcelotBlockingChest(world, x, y, z))
			return null;
		else if (world.getBlockId(x - 1, y, z) == blockID && (world.isBlockSolidOnSide(x - 1, y + 1, z, DOWN) || isOcelotBlockingChest(world, x - 1, y, z)))
			return null;
		else if (world.getBlockId(x + 1, y, z) == blockID && (world.isBlockSolidOnSide(x + 1, y + 1, z, DOWN) || isOcelotBlockingChest(world, x + 1, y, z)))
			return null;
		else if (world.getBlockId(x, y, z - 1) == blockID && (world.isBlockSolidOnSide(x, y + 1, z - 1, DOWN) || isOcelotBlockingChest(world, x, y, z - 1)))
			return null;
		else if (world.getBlockId(x, y, z + 1) == blockID && (world.isBlockSolidOnSide(x, y + 1, z + 1, DOWN) || isOcelotBlockingChest(world, x, y, z + 1)))
			return null;
		else {
			if (world.getBlockId(x - 1, y, z) == blockID)
				object = new InventoryLargeChest("container.petrifiedChestDouble", (TileEntityPetrifiedWoodChest) world.getBlockTileEntity(x - 1, y, z), (IInventory) object);

			if (world.getBlockId(x + 1, y, z) == blockID)
				object = new InventoryLargeChest("container.petrifiedChestDouble", (IInventory) object, (TileEntityPetrifiedWoodChest) world.getBlockTileEntity(x + 1, y, z));

			if (world.getBlockId(x, y, z - 1) == blockID)
				object = new InventoryLargeChest("container.petrifiedChestDouble", (TileEntityPetrifiedWoodChest) world.getBlockTileEntity(x, y, z - 1), (IInventory) object);

			if (world.getBlockId(x, y, z + 1) == blockID)
				object = new InventoryLargeChest("container.petrifiedChestDouble", (IInventory) object, (TileEntityPetrifiedWoodChest) world.getBlockTileEntity(x, y, z + 1));

			return (IInventory) object;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityPetrifiedWoodChest();
	}

	public static boolean isOcelotBlockingChest(World world, int x, int y, int z) {
		Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB(x, y + 1, z, x + 1, y + 2, z + 1)).iterator();
		EntityOcelot entityocelot;

		do {
			if (!iterator.hasNext())
				return false;

			EntityOcelot entityocelot1 = (EntityOcelot) iterator.next();
			entityocelot = entityocelot1;
		} while (!entityocelot.isSitting());

		return true;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory(getInventory(world, x, y, z));
	}
}