package erebus.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWhetstone extends Item {
	public static final byte maxTier = 5;

	public ItemWhetstone(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List textLines, boolean showAdvancedInfo) {
		if(is.getItemDamage()>0) {
		textLines.add(EnumChatFormatting.LIGHT_PURPLE + "Sharpness Level " + (is.getItemDamage()));
		textLines.add(EnumChatFormatting.WHITE + "Crafted with an Axe or Sword.");
		textLines.add(EnumChatFormatting.WHITE + "Adds Sharpness enchantment.");
		}
		
		else {
			textLines.add(EnumChatFormatting.LIGHT_PURPLE + "Un-enchanted");
			textLines.add(EnumChatFormatting.WHITE + "Surround with");
			textLines.add(EnumChatFormatting.WHITE +  "Whetstone Powder");
			textLines.add(EnumChatFormatting.WHITE + "to increase levels");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 6; i++)
			list.add(new ItemStack(id, 1, i));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is, int pass) {
		return is.getItemDamage()>0;
	}
}