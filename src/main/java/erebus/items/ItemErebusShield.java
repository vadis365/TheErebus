package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemErebusShield extends ItemShield {

	private IShieldType shieldType;

	public ItemErebusShield() {

		addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
			@Override
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
				return stack.getItem().getItemUseAction(stack) == EnumAction.BLOCK && entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (getItemUseAction(stack) == EnumAction.BLOCK) {
			return super.onItemRightClick(world, player, hand);
		} else {
			return new ActionResult<>(EnumActionResult.PASS, stack);
		}
	}

	public IShieldType getShieldType() {
		return shieldType;
	}

	public void setShieldType(IShieldType shieldType) {
		this.shieldType = shieldType;

		setMaxDamage(shieldType.getDurability());
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return ModTabs.GEAR;
	}
	
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (new TextComponentTranslation(getUnlocalizedNameInefficiently(stack) + ".name")).getFormattedText().trim();
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
        shieldType.addInformation(stack, world, list, flag);
    }

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		shieldType.onUpdate(this, stack, world, entity, slot, selected);
	}

	@Override
	public boolean isRepairable() {
		return !shieldType.getRepairItem().isEmpty();
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return !repair.isEmpty() && ItemStack.areItemsEqual(repair, shieldType.getRepairItem());
	}

	public boolean damageShield(int toDamage, ItemStack stack, EntityLivingBase entity) {
		int damage = stack.getItemDamage() + toDamage;

		if (damage >= getMaxDamage(stack)) {
			stack.shrink(1);
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				player.addStat(StatList.getObjectBreakStats(stack.getItem()));
			}
			if (stack.getCount() < 0) {
				stack.setCount(0);
			}
		} else {
			stack.setItemDamage(damage);
		}
		return true;
	}

	public boolean repairShield(int toRepair, ItemStack stack) {
		if (stack.getItemDamage() > 0) {
			stack.setItemDamage(Math.max(0, stack.getItemDamage() - toRepair));
			return true;
		} else {
			return false;
		}
	}
}
