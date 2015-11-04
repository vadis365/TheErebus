package erebus.core.helper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.oredict.OreDictionary;

public class Utils {

	public static boolean rightClickItemAt(World world, int x, int y, int z, int side, ItemStack stack) {
		if (world.isRemote || stack == null || stack.getItem() == null)
			return false;
		EntityPlayer player = getPlayer(world);
		player.setCurrentItemOrArmor(0, stack);
		try {
			return stack.getItem().onItemUse(stack, player, world, x, y, z, side, 0, 0, 0);
		} finally {
			player.setCurrentItemOrArmor(0, null);
		}
	}

	public static EntityPlayer getPlayer(World world) {
		if (world.isRemote || !(world instanceof WorldServer))
			return null;
		return FakePlayerFactory.get((WorldServer) world, new GameProfile(UUID.nameUUIDFromBytes(Reference.MOD_ID.getBytes()), "[" + Reference.CHANNEL + "]"));
	}

	public static final int getFlowerMetadata(Object obj) {
		int meta = -1;
		if (obj instanceof ItemStack)
			meta = ((ItemStack) obj).getItemDamage();
		else if (obj instanceof Integer)
			meta = (Integer) obj;

		if (meta >= 2 && meta <= 8 || meta == 14)
			meta++;
		else if (meta >= 9 && meta <= 13)
			meta += 2;

		return meta;
	}

	public static final void breakBlockWithParticles(World world, int x, int y, int z, int meta) {
		playBreakParticles(world, x, y, z, meta);
		world.setBlockToAir(x, y, z);
	}

	public static final void breakBlockWithParticles(World world, int x, int y, int z) {
		breakBlockWithParticles(world, x, y, z, world.getBlockMetadata(x, y, z));
	}

	public static void playBreakParticles(World world, int x, int y, int z, int meta) {
		world.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(world.getBlock(x, y, z)) + (meta << 12));
	}

	public static void playBreakParticles(World world, int x, int y, int z) {
		world.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(world.getBlock(x, y, z)) + (world.getBlockMetadata(x, y, z) << 12));
	}

	public static final void dropStack(World world, int x, int y, int z, ItemStack is) {
		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
			float f = 0.7F;
			double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, is);
			entityitem.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(entityitem);
		}
	}

	public static final int getColour(int R, int G, int B) {
		return new Color(R, G, B).getRGB() & 0x00ffffff;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T getTileEntity(IBlockAccess world, int x, int y, int z, Class<T> cls) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!cls.isInstance(tile))
			return null;
		return (T) tile;
	}

	public static void dropStackNoRandom(World world, int x, int y, int z, ItemStack stack) {
		if (!world.isRemote && stack != null && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
			EntityItem entityItem = new EntityItem(world, x + 0.5, y, z + 0.5, stack);
			entityItem.motionX = 0;
			entityItem.motionY = 0;
			entityItem.motionZ = 0;
			entityItem.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(entityItem);
		}
	}

	public static int[] getSlotsFromSide(IInventory iinventory, int side) {
		if (iinventory == null)
			return null;

		if (iinventory instanceof ISidedInventory)
			return ((ISidedInventory) iinventory).getAccessibleSlotsFromSide(side);
		else {
			int[] slots = new int[iinventory.getSizeInventory()];
			for (int i = 0; i < slots.length; i++)
				slots[i] = i;
			return slots;
		}
	}

	/**
	 * Extracts 1 item from the first found stack
	 *
	 * @param iinventory
	 * @param side
	 * @param maxStackSize
	 * @return extracted stack
	 */
	public static ItemStack extractFromInventory(IInventory iinventory, int side) {
		return extractFromInventory(iinventory, side, 1);
	}

	/**
	 * Extracts a stack with size the same or smaller of @param maxStackSize
	 *
	 * @param iinventory
	 * @param side
	 * @param maxStackSize
	 * @return extracted stack
	 */
	public static ItemStack extractFromInventory(IInventory iinventory, int side, int maxStackSize) {
		IInventory invt = getInventory(iinventory);
		return extractFromSlots(invt, side, maxStackSize, getSlotsFromSide(invt, side));
	}

	private static ItemStack extractFromSlots(IInventory iinventory, int side, int maxStackSize, int[] slots) {
		for (int slot : slots) {
			ItemStack invtStack = iinventory.getStackInSlot(slot);
			if (invtStack != null)
				if (iinventory instanceof ISidedInventory ? ((ISidedInventory) iinventory).canExtractItem(slot, invtStack, side) : true) {
					ItemStack copy = invtStack.copy();
					if (maxStackSize <= 0)
						iinventory.setInventorySlotContents(slot, null);
					else {
						int amount = Math.min(maxStackSize, invtStack.stackSize);
						invtStack.stackSize -= amount;
						copy.stackSize = amount;
						if (invtStack.stackSize <= 0)
							iinventory.setInventorySlotContents(slot, null);
					}
					return copy;
				}
		}
		return null;
	}

	public static boolean addEntitytoInventory(IInventory iinventory, EntityItem entity) {
		if (entity == null)
			return false;

		boolean flag = addItemStackToInventory(iinventory, entity.getEntityItem());
		if (flag)
			entity.setDead();
		else if (entity.getEntityItem().stackSize <= 0)
			entity.setDead();
		return flag;
	}

	public static boolean addItemStackToInventory(IInventory iinventory, ItemStack stack) {
		return addItemStackToInventory(iinventory, stack, 0);
	}

	public static boolean addItemStackToInventory(IInventory iinventory, ItemStack stack, int side) {
		if (iinventory == null)
			return false;

		if (stack == null || stack.stackSize <= 0)
			return false;

		IInventory invt = getInventory(iinventory);
		return addToSlots(invt, stack, side, getSlotsFromSide(invt, side));
	}

	private static boolean addToSlots(IInventory iinventory, ItemStack stack, int side, int[] slots) {
		for (int slot : slots) {
			if (iinventory instanceof ISidedInventory) {
				if (!((ISidedInventory) iinventory).canInsertItem(slot, stack, side))
					continue;
			} else if (!iinventory.isItemValidForSlot(slot, stack))
				continue;

			if (iinventory.getStackInSlot(slot) == null) {
				iinventory.setInventorySlotContents(slot, stack.copy());
				stack.stackSize = 0;
				return true;
			} else {
				ItemStack invtStack = iinventory.getStackInSlot(slot);
				if (invtStack.stackSize < Math.min(invtStack.getMaxStackSize(), iinventory.getInventoryStackLimit()) && areStacksTheSame(invtStack, stack, false)) {
					invtStack.stackSize += stack.stackSize;
					if (invtStack.stackSize > invtStack.getMaxStackSize()) {
						stack.stackSize = invtStack.stackSize - invtStack.getMaxStackSize();
						invtStack.stackSize = invtStack.getMaxStackSize();
					} else
						stack.stackSize = 0;
					return true;
				}
			}
		}
		return false;
	}

	public static boolean areStacksSameOre(ItemStack stack1, ItemStack stack2) {
		if (stack1 == null || stack2 == null)
			return false;
		if (areStacksTheSame(stack1, stack2, false))
			return true;

		List<String> ores1 = getOreNames(stack1);
		List<String> ores2 = getOreNames(stack2);

		if (ores1.isEmpty() || ores2.isEmpty())
			return false;
		for (String ore2 : ores2)
			if (ores1.contains(ore2))
				return isIntercheageableOreName(ore2);
		return false;
	}

	private static final String[] orePrefixes = new String[] { "dust", "ingot", "ore", "block", "gem", "nugget", "shard", "plate", "gear", "stickWood" };

	private static boolean isIntercheageableOreName(String name) {
		for (String prefix : orePrefixes)
			if (name.startsWith(prefix))
				return true;
		return false;
	}

	public static List<String> getOreNames(ItemStack stack) {
		List<String> list = new ArrayList<String>();
		for (int id : OreDictionary.getOreIDs(stack))
			list.add(OreDictionary.getOreName(id));

		return list;
	}

	public static boolean isItemOre(ItemStack stack, String ore) {
		int oreID = OreDictionary.getOreID(ore);
		for (int id : OreDictionary.getOreIDs(stack))
			if (id == oreID)
				return true;
		return false;
	}

	public static boolean areStacksTheSame(ItemStack stack1, ItemStack stack2, boolean matchSize) {
		if (stack1 == null || stack2 == null)
			return false;

		if (stack1.getItem() == stack2.getItem())
			if (stack1.getItemDamage() == stack2.getItemDamage() || isWildcard(stack1.getItemDamage()) || isWildcard(stack2.getItemDamage()))
				if (!matchSize || stack1.stackSize == stack2.stackSize) {
					if (stack1.hasTagCompound() && stack2.hasTagCompound())
						return stack1.getTagCompound().equals(stack2.getTagCompound());
					return stack1.hasTagCompound() == stack2.hasTagCompound();
				}
		return false;
	}

	private static boolean isWildcard(int meta) {
		return meta == OreDictionary.WILDCARD_VALUE;
	}

	public static IInventory getInventory(IInventory inventory) {
		if (inventory instanceof TileEntityChest) {
			TileEntityChest chest = (TileEntityChest) inventory;
			TileEntityChest adjacent = null;
			if (chest.adjacentChestXNeg != null)
				adjacent = chest.adjacentChestXNeg;
			if (chest.adjacentChestXNeg != null)
				adjacent = chest.adjacentChestXNeg;
			if (chest.adjacentChestXPos != null)
				adjacent = chest.adjacentChestXPos;
			if (chest.adjacentChestZNeg != null)
				adjacent = chest.adjacentChestZNeg;
			if (chest.adjacentChestZPos != null)
				adjacent = chest.adjacentChestZPos;

			if (adjacent != null)
				return new InventoryLargeChest("", inventory, adjacent);
		}
		return inventory;
	}

	public static void dropInventoryContents(TileEntity tile) {
		if (tile == null || !(tile instanceof IInventory))
			return;
		IInventory iinventory = (IInventory) tile;
		for (int i = 0; i < iinventory.getSizeInventory(); i++) {
			ItemStack stack = iinventory.getStackInSlot(i);
			if (stack != null && stack.getItem() != null && stack.stackSize > 0) {
				dropStack(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, stack.copy());
				iinventory.setInventorySlotContents(i, null);
			}
		}
		tile.markDirty();
	}

	public static boolean inventoryContains(IInventory iinventory, ItemStack stack, boolean ignoreSize) {
		return inventoryContains(iinventory, stack, ignoreSize, getSlotsFromSide(iinventory, 0));
	}

	public static boolean inventoryContains(IInventory iinventory, ItemStack stack, boolean ignoreSize, int... slots) {
		if (stack == null)
			return false;
		iinventory = getInventory(iinventory);

		int totalSize = 0;
		for (int slot : slots) {
			ItemStack invtStack = iinventory.getStackInSlot(slot);
			if (areStacksTheSame(invtStack, stack, false)) {
				if (ignoreSize)
					return true;
				totalSize += invtStack.stackSize;
			}
			if (totalSize >= stack.stackSize)
				return true;
		}

		return false;
	}

	public static boolean deleteFromInventory(IInventory iinventory, int side, ItemStack stack) {
		return deleteFromSlots(iinventory, stack, getSlotsFromSide(iinventory, side));
	}

	public static boolean deleteFromSlots(IInventory iinventory, ItemStack stack, int... slots) {
		iinventory = getInventory(iinventory);

		if (!inventoryContains(iinventory, stack, false))
			return false;
		int totalDel = 0;
		for (int slot : slots) {
			ItemStack invtStack = iinventory.getStackInSlot(slot);
			if (areStacksTheSame(invtStack, stack, false) || areStacksSameOre(invtStack, stack))
				if (invtStack.stackSize >= stack.stackSize) {
					invtStack.stackSize -= stack.stackSize;
					if (invtStack.stackSize <= 0)
						iinventory.setInventorySlotContents(slot, getContainer(stack));
					return true;
				} else {
					totalDel += invtStack.stackSize;
					iinventory.setInventorySlotContents(slot, getContainer(stack));
				}
			if (totalDel == stack.stackSize)
				return true;
		}
		return false;
	}

	public static ItemStack getContainer(ItemStack stack) {
		return stack.getItem().hasContainerItem(stack) ? stack.getItem().getContainerItem(stack) : null;
	}

	public static final LinkedHashMap<Short, Short> getEnchantments(ItemStack stack) {
		LinkedHashMap<Short, Short> map = new LinkedHashMap<Short, Short>();
		NBTTagList list = stack.getItem() == Items.enchanted_book ? Items.enchanted_book.func_92110_g(stack) : stack.getEnchantmentTagList();

		if (list != null)
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound tag = list.getCompoundTagAt(i);
				map.put(tag.getShort("id"), tag.getShort("lvl"));
			}
		return map;
	}
}