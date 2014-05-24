package erebus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.ConfigHandler;

public class ItemMetalIngots extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public ItemMetalIngots() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("erebus.metalIngot");
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return super.getUnlocalizedName() + is.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int i) {
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item id, CreativeTabs tabs, List list) {
		if (ConfigHandler.copper)
			list.add(new ItemStack(id, 1, 0));
		if (ConfigHandler.lead)
			list.add(new ItemStack(id, 1, 1));
		if (ConfigHandler.silver)
			list.add(new ItemStack(id, 1, 2));
		if (ConfigHandler.tin)
			list.add(new ItemStack(id, 1, 3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icons = new IIcon[4];

		for (int i = 0; i < icons.length; i++)
			icons[i] = reg.registerIcon("erebus:metalIngot" + i);
	}
}