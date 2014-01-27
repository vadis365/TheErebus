package erebus.world.loot;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class LootUtil {

	public static final WeightedLootList lootFuel = new WeightedLootList(new LootItemStack(Item.coal).setAmount(1, 16).setWeight(32), new LootItemStack(Item.coal).setDamage(1).setAmount(1, 16).setWeight(30), new LootItemStack(Block.coalBlock).setAmount(1, 6).setWeight(10), new LootItemStack(
	Item.blazeRod).setAmount(1, 8).setWeight(18), new LootItemStack(Item.bucketLava).setWeight(15), new LootItemStack(Block.sapling).setAmount(1, 20).setDamage(0, 3).setWeight(10), new LootItemStack(Item.stick).setAmount(1, 32).setWeight(8));

	public static void addLore(ItemStack is, String lore) {
		NBTTagCompound tag = is.stackTagCompound;
		if (tag == null)
			tag = new NBTTagCompound("tag");
		if (!tag.hasKey("display"))
			tag.setCompoundTag("display", new NBTTagCompound());

		NBTTagList loreTag = tag.getCompoundTag("display").getTagList("Lore");
		if (lore == null)
			loreTag = new NBTTagList();
		loreTag.appendTag(new NBTTagString(lore, lore));

		tag.getCompoundTag("display").setTag("Lore", loreTag);
		is.setTagCompound(tag);
	}

	public static void generateLoot(IInventory inventory, Random rand, WeightedLootList lootList, int minAmount, int maxAmount) {
		int amount = rand.nextInt(Math.max(1, maxAmount - minAmount + 1)) + minAmount;

		for (int a = 0; a < amount; a++)
			inventory.setInventorySlotContents(rand.nextInt(inventory.getSizeInventory()), lootList.generateIS(rand));
	}
}