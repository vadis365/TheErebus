package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModSounds;
import erebus.ModTabs;
import erebus.entity.EntityWebSling;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWebSlinger extends Item {
	public ItemWebSlinger() {
		setMaxDamage(128);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.webslinger").getFormattedText());
	}

	@Override
	 public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		ItemStack ammoStack = findAmmo(player);
		if (player.capabilities.isCreativeMode || !ammoStack.isEmpty()) {
			world.playSound(null, player.getPosition(), ModSounds.WEBSLING_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
			if (!world.isRemote) {
				EntityWebSling web = new EntityWebSling(world, player);
				web.posY = player.posY + player.height / 2.0F;
				web.setType(getShootType());
				web.shoot(player, player.rotationPitch, player.rotationYaw, 0F, 1.5F, 0F);
				world.spawnEntity(web);
			}
		}
		if (!player.capabilities.isCreativeMode && !ammoStack.isEmpty()) {
			ammoStack.shrink(1);
            if (ammoStack.getCount() <= 0)
                player.inventory.deleteStack(ammoStack);
            stack.damageItem(1, player);
		}
		player.swingArm(hand);
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

	private ItemStack findAmmo(EntityPlayer player) {
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (isAmmo(itemstack))
				return itemstack;
		}
		return ItemStack.EMPTY;
	}

	protected boolean isAmmo(@Nullable ItemStack stack) {
		return !stack.isEmpty() && stack.getItem() == Item.getItemFromBlock(Blocks.WEB);
	}

	protected byte getShootType() {
		return (byte) 0;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
		stack.damageItem(2, player);
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 10;
	}
}