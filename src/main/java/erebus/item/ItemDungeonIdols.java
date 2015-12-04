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
	private IIcon[] icons;

	public ItemDungeonIdols() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
		setUnlocalizedName("erebus.idols");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icons = new IIcon[IDOL.values().length];
		int i = 0;
		for (IDOL idol : IDOL.values())
			icons[i++] = reg.registerIcon("erebus:idol_" + idol.name().toLowerCase());
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
		int meta = stack.getItemDamage();
		IDOL idol = IDOL.values()[Math.min(Math.max(meta, 0), IDOL.values().length - 1)];
		return super.getUnlocalizedName() + "_" + idol.name().toLowerCase();
	}

	@Override
	public boolean onItemUse(ItemStack stacl, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (stacl.getItemDamage() >= 4) {
			if (!world.isRemote) {
				byte spawn = (byte) (stacl.getItemDamage() - 4);
				EntityUmberGolemDungeonTypes entityUmberGolem = new EntityUmberGolemDungeonTypes(world);
				entityUmberGolem.setType(spawn);
				entityUmberGolem.setHealth(entityUmberGolem.getMaxHealth()); // hack because of stupid attributes setting
				entityUmberGolem.setPosition(x + 0.5D, y + 1, z + 0.5D);
				world.spawnEntityInWorld(entityUmberGolem);
			}
			if (!player.capabilities.isCreativeMode)
				stacl.stackSize--;
			return true;
		}
		return false;
	}

	public enum IDOL {
		MUD,
		IRON,
		GOLD,
		JADE,
		MUD_UMBERGOLEM,
		IRON_UMBERGOLEM,
		GOLD_UMBERGOLEM,
		JADE_UMBERGOLEM;

		public ItemStack makeStack() {
			return makeStack(1);
		}

		public ItemStack makeStack(int size) {
			return new ItemStack(ModItems.idols, size, ordinal());
		}
	}
}