package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.entity.EntityUmberGolemDungeonTypes;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemDungeonIdols extends Item {

	@SideOnly(Side.CLIENT)
	private static IIcon[] icons;

	public ItemDungeonIdols() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
		setUnlocalizedName("erebus.idols");
	}

	public static ItemStack createStack(IDOL idol) {
		return createStack(idol, 1);
	}

	public static ItemStack createStack(IDOL idol, int size) {
		return new ItemStack(ModItems.idols, size, idol.ordinal());
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

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (is.getItemDamage() >= 4) {
			if (!world.isRemote) {
				byte spawn = (byte) (is.getItemDamage() - 4);
				EntityUmberGolemDungeonTypes entityUmberGolem = new EntityUmberGolemDungeonTypes(world);
				entityUmberGolem.setType(spawn);
				entityUmberGolem.setHealth(entityUmberGolem.getMaxHealth()); // hack because of stupid attributes setting
				entityUmberGolem.setPosition(x + 0.5D, y + 1, z + 0.5D);
				world.spawnEntityInWorld(entityUmberGolem);
			}
			if (!player.capabilities.isCreativeMode)
				--is.stackSize;
			return true;
		}
		return false;
	}

	public enum IDOL {
		Mud,
		Iron,
		Gold,
		Jade,
		MudUmbergolem,
		IronUmbergolem,
		GoldUmbergolem,
		JadeUmbergolem;
	}
}