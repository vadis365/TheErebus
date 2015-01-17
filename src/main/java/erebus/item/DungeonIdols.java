package erebus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class DungeonIdols extends Item {

	public enum IDOL {
		Mud,
		Iron,
		Gold,
		Jade;
	}

	public static ItemStack createStack(IDOL idol) {
		return createStack(idol, 1);
	}

	public static ItemStack createStack(IDOL idol, int size) {
		return new ItemStack(ModItems.idols, size, idol.ordinal());
	}

	@SideOnly(Side.CLIENT)
	private static IIcon[] icons;

	public DungeonIdols() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("erebus.idols");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icons = new IIcon[IDOL.values().length];
		int i = 0;
		for (IDOL d : IDOL.values())
			icons[i++] = reg.registerIcon("erebus:idol" + d.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < IDOL.values().length; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + stack.getItemDamage();
	}
}