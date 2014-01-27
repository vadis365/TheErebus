package erebus.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.entity.EntityScorpion;

public class ItemScorpionPincer extends ItemSword {

	public ItemScorpionPincer(int id) {
		super(id, ErebusMod.weaponScorpionPincer);
		maxStackSize = 1;
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		if (!(entity instanceof EntityScorpion)) {
			double knockback = 0.5D;
			double direction = Math.toRadians(player.renderYawOffset);
			entity.addVelocity(-Math.sin(direction) * knockback, 0.25D, Math.cos(direction) * knockback);
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode && player.inventory.hasItem(Item.fireballCharge.itemID))
			is.damageItem(10, player);
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(Item.fireballCharge.itemID)) {
			world.playSoundAtEntity(player, "mob.ghast.fireball", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				Vec3 look = player.getLookVec();
				double direction = Math.toRadians(player.renderYawOffset);
				double directionY = Math.toRadians(player.cameraPitch);
				EntitySmallFireball fireball = new EntitySmallFireball(world, player, 1, 1, 1);
				fireball.setPosition(player.posX + -Math.sin(direction) * 1, player.posY + Math.cos(directionY) * 1.2D, player.posZ + Math.cos(direction) * 1);
				fireball.accelerationX = look.xCoord * 0.1;
				fireball.accelerationY = look.yCoord * 0.1;
				fireball.accelerationZ = look.zCoord * 0.1;
				world.spawnEntityInWorld(fireball);
			}
			player.inventory.consumeInventoryItem(Item.fireballCharge.itemID);
		}
		player.swingItem();
		return is;
	}

}