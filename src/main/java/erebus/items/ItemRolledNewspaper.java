package erebus.items;

import erebus.ModMaterials;
import erebus.ModTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRolledNewspaper extends ItemSword {
	public ItemRolledNewspaper() {
		super(ModMaterials.WEAPON_ROLLED_NEWSPAPER);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		ItemStack is = new ItemStack(item);
		is.addEnchantment(Enchantment.getEnchantmentByLocation("bane_of_arthropods"), 5);
		list.add(is);
	}

	@Override
	public void onCreated(ItemStack is, World world, EntityPlayer player) {
		is.addEnchantment(Enchantment.getEnchantmentByLocation("bane_of_arthropods"), 5);
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int id, boolean map) {
		if (!is.isItemEnchanted())
			is.addEnchantment(Enchantment.getEnchantmentByLocation("bane_of_arthropods"), 5);
	}
}