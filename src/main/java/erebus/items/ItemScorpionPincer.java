package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.entity.EntityScorpion;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemScorpionPincer extends ItemSword {

	public ItemScorpionPincer() {
		super(ModMaterials.WEAPON_SCOPION_PINCER);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.scorpionpincer").getFormattedText());
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
	 public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!player.capabilities.isCreativeMode && player.inventory.hasItem(Items.FIRE_CHARGE))
			stack.damageItem(10, player);
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.FIRE_CHARGE)) {
			world.playSoundAtEntity(player, "mob.ghast.fireball", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				Vec3d look = player.getLookVec();
				double direction = Math.toRadians(player.renderYawOffset);
				double directionY = Math.toRadians(player.cameraPitch);
				EntitySmallFireball fireball = new EntitySmallFireball(world, player, 1, 1, 1);
				fireball.setPosition(player.posX + -Math.sin(direction) * 1, player.posY + Math.cos(directionY) * 1.2D, player.posZ + Math.cos(direction) * 1);
				fireball.accelerationX = look.x * 0.1;
				fireball.accelerationY = look.y * 0.1;
				fireball.accelerationZ = look.z * 0.1;
				world.spawnEntity(fireball);
			}
			player.inventory.consumeInventoryItem(Items.FIRE_CHARGE);
		}
		player.swingItem();
		return new ActionResult(EnumActionResult.PASS, stack);
	}
}