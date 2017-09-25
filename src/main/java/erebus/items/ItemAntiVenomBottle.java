package erebus.items;

import erebus.ModTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemAntiVenomBottle extends Item {

	public ItemAntiVenomBottle() {
		setMaxStackSize(1);
		setCreativeTab(ModTabs.ITEMS);
		setContainerItem(Items.GLASS_BOTTLE);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
		EntityPlayer player = entity instanceof EntityPlayer ? (EntityPlayer) entity : null;
		if (player == null || !player.capabilities.isCreativeMode)
			stack.shrink(1);
		if (!player.getEntityWorld().isRemote) {
			if (!player.getEntityData().hasKey("anti_venom_duration") || player.getEntityData().getInteger("anti_venom_duration") < 180) {
				int currentDuration = player.getEntityData().getInteger("anti_venom_duration");
				player.getEntityData().setInteger("anti_venom_duration",
						currentDuration <= 120 ? currentDuration + 60 : 180);
			}
		}
		if (player != null)
			player.addStat(StatList.getObjectUseStats(this));

		if (player == null || !player.capabilities.isCreativeMode) {
			if (stack.isEmpty())
				return new ItemStack(Items.GLASS_BOTTLE);
			if (player != null)
				player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
		}
		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
}