package erebus.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RolledNewspaper extends ItemSimpleFoiled
{

	public RolledNewspaper()
	{
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
	{
		list.add(EnumChatFormatting.BLUE + "+10 Attack Damage");
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		ItemStack stack = new ItemStack(item, 1, 0);
		stack.addEnchantment(Enchantment.baneOfArthropods, 5);
		list.add(stack);
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player)
	{
		is.damageItem(1, player);
		entity.attackEntityFrom(DamageSource.causeMobDamage(player), 10);
		return true;
	}

	@Override
	public void onCreated(ItemStack is, World world, EntityPlayer player)
	{
		is.addEnchantment(Enchantment.baneOfArthropods, 5);
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int id, boolean map)
	{
		if (!is.isItemEnchanted())
		{
			is.addEnchantment(Enchantment.baneOfArthropods, 5);
		}
	}
}