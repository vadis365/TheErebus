package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemWhetstone extends Item {

	public ItemWhetstone() {
		setMaxStackSize(1);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean showAdvancedInfo) {
		if (stack.getItemDamage() > 0) {
			list.add(EnumChatFormatting.LIGHT_PURPLE + StatCollector.translateToLocalFormatted("tooltip.erebus.whetstonesharpness", stack.getItemDamage()));
			list.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("tooltip.erebus.whetstone_1"));
		} else {
			list.add(EnumChatFormatting.LIGHT_PURPLE + StatCollector.translateToLocal("tooltip.erebus.whetstone_2"));
			list.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("tooltip.erebus.whetstone_3"));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 6; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is, int pass) {
		return is.getItemDamage() > 0;
	}
}