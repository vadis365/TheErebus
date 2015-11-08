package erebus.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;

public class ItemBowMaxSpeed extends ItemBow {

	private final int weaponEnchantibility;
	public static final String[] bowAnimationIcon = new String[] { "_standby", "_pulling_0", "_pulling_1", "_pulling_2" };

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public ItemBowMaxSpeed() {
		setMaxStackSize(1);
		setMaxDamage(301);
		weaponEnchantibility = 0;
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon("erebus:maxspeedbow" + "_standby");
		iconArray = new IIcon[bowAnimationIcon.length];

		for (int iconIndex = 0; iconIndex < iconArray.length; ++iconIndex) {
			iconArray[iconIndex] = icon.registerIcon("erebus:maxspeedbow" + bowAnimationIcon[iconIndex]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		int time = stack.getMaxItemUseDuration() - useRemaining;
		if (usingItem != null) {
			if (time >= 4)
				return getItemIconForUseDuration(3);
			else if (time > 2)
				return getItemIconForUseDuration(2);
			else if (time > 0)
				return getItemIconForUseDuration(1);
		}
		return this.itemIcon;
	}

	@Override
	public IIcon getItemIconForUseDuration(int iconIndex) {
		return iconArray[iconIndex];
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!player.isSneaking()) {
			ArrowNockEvent event = new ArrowNockEvent(player, stack);
			MinecraftForge.EVENT_BUS.post(event);

			if (event.isCanceled())
				return event.result;

			if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.arrow) || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0)
				player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		}
		return stack;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		int speed = 4;
		int ticks = 72000 - count;
		if (ticks > speed)
			player.stopUsingItem();
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount) {
		float pullSpeedModifier = 5F;
		float speedModifier = 1.5F;
		float soundPitchModifier = 1F;
		int minRelease = 0;
		int maxUseDuration = 72000 - inUseCount;

		ArrowLooseEvent event = new ArrowLooseEvent(player, stack, maxUseDuration);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
			return;

		maxUseDuration = event.charge;

		boolean canShoot = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

		if (canShoot || player.inventory.hasItem(Items.arrow)) {
			float power = maxUseDuration / pullSpeedModifier;
			power = (power * power + power * 2.0F) / 4.0F;

			if ((maxUseDuration < minRelease) || power < 0.1D)
				return;

			if (power > 1.0F)
				power = 1.0F;

			power *= speedModifier;

			EntityArrow entityArrow = new EntityArrow(world, player, power * 2.0F);

			if (power >= 1.0F)
				entityArrow.setIsCritical(true);

			int powerEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

			if (powerEnchant > 0)
				entityArrow.setDamage(entityArrow.getDamage() + powerEnchant + 0.5D);

			int punchEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

			if (punchEnchant > 0)
				entityArrow.setKnockbackStrength(punchEnchant * 2);

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
				entityArrow.setFire(200);

			stack.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, soundPitchModifier * (1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + power * 0.3F));

			if (canShoot)
				entityArrow.canBePickedUp = 2;
			else {
				entityArrow.canBePickedUp = 1;
				player.inventory.consumeInventoryItem(Items.arrow);
			}

			if (!world.isRemote)
				world.spawnEntityInWorld(entityArrow);
		}
	}

	public ItemStack onFoodEaten(ItemStack is, World world, EntityPlayer player) {
		return is;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is) {
		return EnumAction.bow;
	}

	@Override
	public int getItemEnchantability() {
		return weaponEnchantibility;
	}

	@Override
	public boolean isBookEnchantable(ItemStack is, ItemStack book) {
		return false;
	}

	@Override
	public boolean hasEffect(ItemStack is, int pass) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack is) {
		return EnumRarity.rare;
	}
}