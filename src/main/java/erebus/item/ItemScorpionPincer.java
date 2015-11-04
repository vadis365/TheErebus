package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.entity.EntityScorpion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemScorpionPincer extends ItemSword {

	public ItemScorpionPincer() {
		super(ModMaterials.weaponScorpionPincer);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean isComplex) {
		tooltip.add(StatCollector.translateToLocal("tooltip.erebus.scorpionpincer"));
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
		if (!player.capabilities.isCreativeMode && player.inventory.hasItem(Items.fire_charge))
			is.damageItem(10, player);
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.fire_charge)) {
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
			player.inventory.consumeInventoryItem(Items.fire_charge);
		}
		player.swingItem();
		return is;
	}
}