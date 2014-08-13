package erebus.item.bambucket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.ModTabs;

public abstract class BambucketDrinkable extends Item
{

	public BambucketDrinkable(String texture)
	{
		setMaxDamage(0);
		setMaxStackSize(16);
		setHasSubtypes(true);
		setTextureName(texture);
		setCreativeTab(ModTabs.specials);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack)
	{
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack)
	{
		return new ItemStack(ModItems.bambucket);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.drink;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		return applyEffects(stack, world, player);
	}

	public abstract ItemStack applyEffects(ItemStack stack, World world, EntityPlayer player);
}