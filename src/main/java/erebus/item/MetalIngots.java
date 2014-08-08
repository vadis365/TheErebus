package erebus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.configs.ConfigHandler;

public class MetalIngots extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public MetalIngots() {
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
		return icons[Math.min(icons.length - 1, i)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item id, CreativeTabs tabs, List list) {
		if (ConfigHandler.INSTANCE.copper)
			list.add(new ItemStack(id, 1, 0));
		if (ConfigHandler.INSTANCE.lead)
			list.add(new ItemStack(id, 1, 1));
		if (ConfigHandler.INSTANCE.silver)
			list.add(new ItemStack(id, 1, 2));
		if (ConfigHandler.INSTANCE.tin)
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