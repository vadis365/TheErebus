package erebus.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import erebus.ModTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBucketAntiVenom extends ItemBucketMilk {

	public ItemBucketAntiVenom() {
		setMaxStackSize(1);
		setContainerItem(Items.bucket);
		setCreativeTab(ModTabs.specials);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		List<Potion> toRemove = new ArrayList<Potion>();
		for (PotionEffect effect : (Collection<PotionEffect>) player.getActivePotionEffects()) {
			Potion potion = Potion.potionTypes[effect.getPotionID()];
			if (potion.isBadEffect())
				toRemove.add(potion);
		}
		for (Potion potion : toRemove)
			player.removePotionEffect(potion.getId());

		if (!player.capabilities.isCreativeMode) {
			stack.stackSize--;
			if (stack.stackSize <= 0)
				return new ItemStack(Items.bucket);
			else
				player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
		}
		return stack;
	}
}