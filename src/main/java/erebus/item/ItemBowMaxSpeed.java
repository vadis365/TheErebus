package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
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
		itemIcon = icon.registerIcon("erebus:maxspeedbow");
		iconArray = new IIcon[bowAnimationIcon.length];

		for (int iconIndex = 0; iconIndex < iconArray.length; ++iconIndex) {
			iconArray[iconIndex] = icon.registerIcon("erebus:maxspeedbow" + bowAnimationIcon[iconIndex]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if (usingItem != null) {
			int time = 36000 - useRemaining;
			if (time < 3)
				return iconArray[0];
			if (time < 6)
				return iconArray[1];
			if (time < 9)
				return iconArray[2];
			return iconArray[2];
		}
		return getIcon(stack, renderPass);
	}

	@Override
	public IIcon getItemIconForUseDuration(int iconIndex) {
		return iconArray[iconIndex];
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount) {
		int maxUseDuration = getMaxItemUseDuration(stack) - inUseCount;

		ArrowLooseEvent event = new ArrowLooseEvent(player, stack, maxUseDuration);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
			return;
		maxUseDuration = event.charge;

		boolean canShoot = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

		if (canShoot || player.inventory.hasItem(Items.arrow)) {
			float power = maxUseDuration / 20.0F;
			power = (power * power + power * 2.0F) / 1.0F;

			if (power < 0.1D)
				return;

			if (power > 1.0F)
				power = 1.0F;

			EntityArrow entityarrow = new EntityArrow(world, player, power * 2.0F);

			if (power == 1.0F)
				entityarrow.setIsCritical(true);

			int powerEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

			if (powerEnchant > 0)
				entityarrow.setDamage(entityarrow.getDamage() + (double) powerEnchant * 0.5D + 0.5D);

			int punchEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

			if (punchEnchant > 0)
				entityarrow.setKnockbackStrength(punchEnchant);

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
				entityarrow.setFire(100);

			stack.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + power * 0.5F);

			if (canShoot)
				entityarrow.canBePickedUp = 2;
			else
				player.inventory.consumeInventoryItem(Items.arrow);

			if (!world.isRemote)
				world.spawnEntityInWorld(entityarrow);
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

	/*
		@Override
		public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
			if (!player.isSneaking()) {
				if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.arrow)) {
					EntityArrow arrow = new EntityArrow(world, player, 2.0F);
	
					if (world.rand.nextInt(4) == 0)
						arrow.setIsCritical(true);
	
					arrow.setDamage(arrow.getDamage() * 0.75D);
	
					is.damageItem(1, player);
					world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1.0F * 0.5F);
	
					if (!player.capabilities.isCreativeMode)
						player.inventory.consumeInventoryItem(Items.arrow);
	
					if (!world.isRemote)
						world.spawnEntityInWorld(arrow);
				}
			} else
				player.setItemInUse(is, getMaxItemUseDuration(is));
	
			return is;
		}
	*/
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