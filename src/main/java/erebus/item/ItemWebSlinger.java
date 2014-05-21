package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.entity.EntityWebSling;

public class ItemWebSlinger extends Item {
	public ItemWebSlinger(int id) {
		super(id);
		maxStackSize = 1;
		setMaxDamage(128);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(Item.itemsList[Block.web.blockID].itemID)) {
			world.playSoundAtEntity(player, "erebus:webslingthrow", 1.0F, 1.0F);
			if (!world.isRemote) {
				EntityWebSling var11 = new EntityWebSling(world, player);
				var11.posY = player.posY + player.height / 2.0F;
				var11.setType((byte) 0);
				world.spawnEntityInWorld(var11);
			}
		}
		if (!player.capabilities.isCreativeMode) {
			player.inventory.consumeInventoryItem(Item.itemsList[Block.web.blockID].itemID);
			stack.damageItem(1, player);
		}
		return stack;
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
}