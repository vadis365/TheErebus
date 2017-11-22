package erebus.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import erebus.ModItems.ISubItemsItem;
import erebus.ModTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWhetstone extends Item implements ISubItemsItem {

	public ItemWhetstone() {
		setMaxStackSize(1);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.ITEMS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		if (stack.getItemDamage() > 0) {
			list.add(TextFormatting.LIGHT_PURPLE + new TextComponentTranslation("tooltip.erebus.whetstonesharpness", stack.getItemDamage()).getFormattedText());
			list.add(TextFormatting.WHITE + new TextComponentTranslation("tooltip.erebus.whetstone_1").getFormattedText());
		} else {
			list.add(TextFormatting.LIGHT_PURPLE + new TextComponentTranslation("tooltip.erebus.whetstone_2").getFormattedText());
			list.add(TextFormatting.WHITE + new TextComponentTranslation("tooltip.erebus.whetstone_3").getFormattedText());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.ITEMS)
			for (int i = 0; i < 6; i++)
				list.add(new ItemStack(this, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() > 0;
	}
	
	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (int i = 0; i < 6; i++)
			models.add("whetstone");
		return models;
	}
}