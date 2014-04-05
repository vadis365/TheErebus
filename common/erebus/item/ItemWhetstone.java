package erebus.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWhetstone extends ItemSimpleFoiled {
	public static final byte maxTier = 4;

	public ItemWhetstone(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List textLines, boolean showAdvancedInfo) {
		textLines.add(EnumChatFormatting.LIGHT_PURPLE + "Level " + (1 + is.getItemDamage()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 5; i++)
			list.add(new ItemStack(id, 1, i));
	}
}