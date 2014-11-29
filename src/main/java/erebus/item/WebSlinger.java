package erebus.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.entity.EntityWebSling;

public class WebSlinger extends Item {
	public WebSlinger() {
		setMaxDamage(128);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(Item.getItemFromBlock(Blocks.web))) {
			world.playSoundAtEntity(player, "erebus:webslingthrow", 1.0F, 1.0F);
			if (!world.isRemote) {
				EntityWebSling web = new EntityWebSling(world, player);
				web.posY = player.posY + player.height / 2.0F;
				web.setType(getShootType());
				world.spawnEntityInWorld(web);
			}
		}
		if (!player.capabilities.isCreativeMode) {
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.web));
			stack.damageItem(1, player);
		}
		return stack;
	}

	protected byte getShootType() {
		return (byte) 0;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
		stack.damageItem(2, player);
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is) {
		return EnumAction.bow;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 10;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}
}