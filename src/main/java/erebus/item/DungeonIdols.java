package erebus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class DungeonIdols extends Item {

	public enum IDOL {
		idolBronze,
		idolSilver,
		idolGold,
		idolJade;	
	}

	public static ItemStack createStack(IDOL idol) {
		return createStack(idol, 1);
	}

	public static ItemStack createStack(IDOL idol, int size) {
		return new ItemStack(ModItems.idols, size, idol.ordinal());
	}

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;

	public DungeonIdols() {
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		// TODO these will be used to activate the capstones on the dungeon pyramid
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		return is;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[IDOL.values().length];
		int i = 0;
		for (IDOL d : IDOL.values())
			icons[i++] = iconRegister.registerIcon("erebus:" + d.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < IDOL.values().length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}
}