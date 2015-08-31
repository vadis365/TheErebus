package erebus.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ItemWhetstone extends Item {

	public static final byte maxTier = 5;

	public ItemWhetstone() {
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List textLines, boolean showAdvancedInfo) {
		if (is.getItemDamage() > 0) {
			textLines.add(EnumChatFormatting.LIGHT_PURPLE + "Sharpness Level " + is.getItemDamage());
			textLines.add(EnumChatFormatting.WHITE + "Crafted with an Axe or Sword.");
			textLines.add(EnumChatFormatting.WHITE + "Adds Sharpness enchantment.");
		} else {
			textLines.add(EnumChatFormatting.LIGHT_PURPLE + "Un-enchanted");
			textLines.add(EnumChatFormatting.WHITE + "Surround with");
			textLines.add(EnumChatFormatting.WHITE + "Whetstone Powder");
			textLines.add(EnumChatFormatting.WHITE + "to increase levels");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < 6; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is, int pass) {
		return is.getItemDamage() > 0;
	}
}