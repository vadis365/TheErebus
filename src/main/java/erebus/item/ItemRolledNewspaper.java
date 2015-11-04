package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModMaterials;
import erebus.ModTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemRolledNewspaper extends ItemSword {
	public ItemRolledNewspaper() {
		super(ModMaterials.rolledNewspaper);
		setTextureName("erebus:rolledNewspaper");
		setUnlocalizedName("erebus.rolledNewspaper");
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(item);
		stack.addEnchantment(Enchantment.baneOfArthropods, 5);
		list.add(stack);
	}

	@Override
	public void onCreated(ItemStack is, World world, EntityPlayer player) {
		is.addEnchantment(Enchantment.baneOfArthropods, 5);
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int id, boolean map) {
		if (!is.isItemEnchanted())
			is.addEnchantment(Enchantment.baneOfArthropods, 5);
	}
}