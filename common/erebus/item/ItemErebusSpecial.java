package erebus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemErebusSpecial extends Item {

	public static final String[] iconPaths = new String[] { "rhinoRidingKit", "beetleTamingAmulet", "umberGolemCore", "umberGolemHead", "umberGolemLClaw", "umberGolemRClaw", "umberGolemLegs" };

	public static final short dataRhinoRidingKit = 0, dataBeetleTamingAmulet = 1, dataGolemCore = 2, dataGolemHead = 3, dataGolemLClaw = 4, dataGolemRClaw = 5, dataGolemLegs = 6;

	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public ItemErebusSpecial(int id) {
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		return is;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int a = 0; a < iconPaths.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		int i = is.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}
}
