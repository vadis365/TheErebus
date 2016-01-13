package erebus.item.bambucket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
		if (!player.capabilities.isCreativeMode) {
			stack.stackSize--;
			if(!player.getEntityData().hasKey("antivenomDuration") || player.getEntityData().getInteger("antivenomDuration") < 180)
				player.getEntityData().setInteger("antivenomDuration", 180);
			player.inventory.addItemStackToInventory(new ItemStack(ModItems.bambucket));
		}
		return stack;
	}
}