package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.entity.EntityScorpion;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		ItemStack ammoStack = findAmmo(player);
		if (player.capabilities.isCreativeMode || !findAmmo(player).isEmpty()) {
			world.playSound(null, player.getPosition(), SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.PLAYERS,  0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
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
		}
			if (!player.capabilities.isCreativeMode && findAmmo(player) != null) {
				ammoStack.shrink(1);
	            if (ammoStack.getCount() <= 0)
	                player.inventory.deleteStack(ammoStack);
	            stack.damageItem(10, player);
			}
			player.swingArm(hand);
			return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

	private ItemStack findAmmo(EntityPlayer player) {
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (isAmmo(itemstack))
				return itemstack;
		}
		return null;
	}

	protected boolean isAmmo(@Nullable ItemStack stack) {
		return !stack.isEmpty() && stack.getItem() == Items.FIRE_CHARGE;
	}
}