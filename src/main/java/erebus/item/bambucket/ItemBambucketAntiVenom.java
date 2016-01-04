package erebus.item.bambucket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;

public class ItemBambucketAntiVenom extends ItemBambucketDrinkable {

	public ItemBambucketAntiVenom() {
		super("erebus:bambucketAntivenom");
		setTextureName("erebus:bambucket_antivenom");
		setUnlocalizedName("erebus.bambucketAntiVenom");
	}

	@Override
	@SuppressWarnings("unchecked")
	public ItemStack applyEffects(ItemStack stack, World world, EntityPlayer player) {
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
			if(!player.getEntityData().hasKey("antivenomDuration") || player.getEntityData().getInteger("antivenomDuration") < 180)
				player.getEntityData().setInteger("antivenomDuration", 180);
			player.inventory.addItemStackToInventory(new ItemStack(ModItems.bambucket));
		}
		return stack;
	}
}