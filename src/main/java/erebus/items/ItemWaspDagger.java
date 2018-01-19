package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModMaterials;
import erebus.entity.EntityWaspDagger;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWaspDagger extends ItemSword {

	public ItemWaspDagger() {
		super(ModMaterials.WEAPON_WASP_DAGGER);
		setMaxStackSize(16);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.waspdagger_1").getFormattedText());
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.waspdagger_2").getFormattedText());
	}

	@Override
	 public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!player.capabilities.isCreativeMode)
			stack.shrink(1);
		world.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote) {
			EntityWaspDagger dagger = new EntityWaspDagger(world, player);
			double direction = Math.toRadians(player.rotationYaw);
			dagger.shoot(player, player.rotationPitch, player.rotationYaw, 0F, 1F, 0F);
			dagger.setPosition(player.posX + -Math.sin(direction) * 1.5D, player.posY + player.eyeHeight, player.posZ + Math.cos(direction) * 1.5D);
			world.spawnEntity(dagger);
		}
		player.swingArm(hand);
		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(2, player);
		return true;
	}
}
