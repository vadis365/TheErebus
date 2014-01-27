package erebus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.ConfigurationHandler;

public class ItemMetalIngots extends Item {

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemMetalIngots(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return "item.metalIngot" + is.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tabs, List list) {
		if (ConfigurationHandler.copper)
			list.add(new ItemStack(id, 1, 0));
		if (ConfigurationHandler.lead)
			list.add(new ItemStack(id, 1, 1));
		if (ConfigurationHandler.silver)
			list.add(new ItemStack(id, 1, 2));
		if (ConfigurationHandler.tin)
			list.add(new ItemStack(id, 1, 3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icons = new Icon[4];

		for (int i = 0; i < icons.length; i++)
			icons[i] = reg.registerIcon("erebus:metalIngot" + i);
	}
}
