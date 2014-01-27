package erebus.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMaxSpeedBow extends Item {

	private final int weaponEnchantibility;

	public ItemMaxSpeedBow(int id) {
		super(id);
		maxStackSize = 1;
		setMaxDamage(301);
		weaponEnchantibility = 0;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack is, World world, EntityPlayer player, int par4) {
		int var6 = getMaxItemUseDuration(is) - par4;

		ArrowLooseEvent event = new ArrowLooseEvent(player, is, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
			return;
		var6 = event.charge;

		boolean var5 = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, is) > 0;

		if (var5 || player.inventory.hasItem(Item.arrow.itemID)) {
			float var7 = var6 / 20.0F;
			var7 = (var7 * var7 + var7 * 2.0F) / 1.0F;

			if (var7 < 0.1D)
				return;

			if (var7 > 1.0F)
				var7 = 1.0F;

			EntityArrow var8 = new EntityArrow(world, player, var7 * 2.0F);

			if (var7 == 1.0F)
				var8.setIsCritical(true);

			int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, is);

			if (var9 > 0)
				var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);

			int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, is);

			if (var10 > 0)
				var8.setKnockbackStrength(var10);

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, is) > 0)
				var8.setFire(100);

			is.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

			if (var5)
				var8.canBePickedUp = 2;
			else
				player.inventory.consumeInventoryItem(Item.arrow.itemID);

			if (!world.isRemote)
				world.spawnEntityInWorld(var8);
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
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (!player.isSneaking()) {
			if (player.capabilities.isCreativeMode || player.inventory.hasItem(Item.arrow.itemID)) {
				EntityArrow arrow = new EntityArrow(world, player, 2.0F);

				if (world.rand.nextInt(4) == 0)
					arrow.setIsCritical(true);

				arrow.setDamage(arrow.getDamage() * 0.75D);

				is.damageItem(1, player);
				world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1.0F * 0.5F);

				if (!player.capabilities.isCreativeMode)
					player.inventory.consumeInventoryItem(Item.arrow.itemID);

				if (!world.isRemote)
					world.spawnEntityInWorld(arrow);
			}
		} else
			player.setItemInUse(is, getMaxItemUseDuration(is));

		return is;
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
	public boolean hasEffect(ItemStack is) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack is) {
		return EnumRarity.rare;
	}
}