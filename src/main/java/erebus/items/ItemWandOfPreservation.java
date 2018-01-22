package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.entity.EntityPreservedBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
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

public class ItemWandOfPreservation extends Item {

	public ItemWandOfPreservation() {
		setMaxDamage(256);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.GEAR);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.wandofpreservation").getFormattedText());
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand); 
		if (player.capabilities.isCreativeMode || consumeBullet(player)) {
			stack.damageItem(1, player);
			world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS,  0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				EntityPreservedBlock amberStar = new EntityPreservedBlock(world, player);
				amberStar.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
				world.spawnEntity(amberStar);
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

	private boolean consumeBullet(EntityPlayer player) {
		for (int i = 0; i < player.inventory.mainInventory.size(); i++) {
			ItemStack stack = player.inventory.mainInventory.get(i);
			if (!stack.isEmpty() && stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.AMBER_STAR.ordinal()) {
				stack.shrink(1);
				player.inventory.markDirty();
				return true;
			}
		}
		return false;
	}
}