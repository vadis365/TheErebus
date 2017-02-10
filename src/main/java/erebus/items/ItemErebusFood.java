package erebus.items;

import erebus.ModItems;
import erebus.ModItems.ISubItemsItem;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemErebusFood extends ItemFood implements ISubItemsItem {

	public ItemErebusFood() {
		super(3, 0.5F, false);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.ITEMS);
	}

	public PotionEffect getPotionEffect(ItemStack is, World world, EntityPlayer player) {
		switch (EnumFoodType.values()[is.getItemDamage()]) {
			case BEETLE_LARVA_RAW:
				return new PotionEffect(MobEffects.NAUSEA, 300, 2);
			case MELONADE_SPARKLY:
				return new PotionEffect(MobEffects.REGENERATION, 200, 0);
			case LARVAE_ON_STICK:
				return new PotionEffect(MobEffects.NAUSEA, 100, 1);
			case TITAN_CHOP_COOKED:
				return new PotionEffect(MobEffects.STRENGTH, 600, 1);
			default:
				return null;
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		switch (EnumFoodType.values()[stack.getItemDamage()]) {
			case MELONADE:
			case MELONADE_SPARKLY:
				return EnumAction.DRINK;
			default:
				return EnumAction.EAT;
		}
	}

	@Override
	public int getHealAmount(ItemStack stack) {
		return EnumFoodType.values()[stack.getItemDamage()].getHealAmount();
	}

	@Override
	public float getSaturationModifier(ItemStack stack) {
		return EnumFoodType.values()[stack.getItemDamage()].getSaturationModifier();
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			EnumFoodType type = EnumFoodType.values()[stack.getItemDamage()];
			stack.setCount(stack.getCount() - 1);
			player.getFoodStats().addStats(this, stack);
			String sound = type == EnumFoodType.CABBAGE ? "erebus:cabbagefart" : "random.burp";
			float volume = type == EnumFoodType.CABBAGE ? 1 : 0.5F;
			// TODO fix sounds
			//world.playSoundAtEntity(player, sound, volume, world.rand.nextFloat() * 0.1F + 0.9F);
			onFoodEaten(stack, world, player);
		}
		return hasContainerItem(stack) ? getContainerItem(stack) : stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		PotionEffect effect = this.getPotionEffect(stack, world, player);
		if (!world.isRemote && effect != null)
			player.addPotionEffect(effect);
		if (!world.isRemote && stack.getItemDamage() == EnumFoodType.PRICKLY_PEAR_RAW.ordinal())
			player.attackEntityFrom(DamageSource.CACTUS, 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (EnumFoodType type : EnumFoodType.values())
			list.add(type.createStack(1));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		meta = Math.min(Math.max(meta, 0), EnumFoodType.values().length - 1);
		return super.getUnlocalizedName() + "." + EnumFoodType.values()[meta].name().toLowerCase();
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		switch (EnumFoodType.values()[stack.getItemDamage()]) {
			case LARVAE_ON_STICK:
				return new ItemStack(Items.STICK);
			case BAMBOO_SOUP:
				return new ItemStack(Items.BOWL);
			case MELONADE:
			case MELONADE_SPARKLY:
				return ItemMaterials.EnumType.SMOOTHIE_GLASS.createStack();
			case TITAN_STEW_COOKED:
				return ItemMaterials.EnumType.STEW_POT.createStack();
			default:
				return null;
		}
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return getContainerItem(stack) != null;
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return !hasContainerItem(stack) ? 64 : 1;
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumFoodType type : EnumFoodType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumFoodType implements IErebusEnum {

		BEETLE_LARVA_RAW(1, 0.1F),
		BEETLE_LARVA_COOKED(3, 0.4F),
		GRASSHOPPER_LEG_RAW(1, 0.1F),
		GRASSHOPPER_LEG_COOKED(4, 0.4F),
		TARANTULA_LEG_RAW(1, 0.1F),
		TARANTULA_LEG_COOKED(5, 0.4F),
		BAMBOO_SOUP(4, 0.3F),
		MELONADE(3, 0.2F),
		MELONADE_SPARKLY(5, 0.4F),
		LARVAE_ON_STICK(9, 0.5F),
		HONEY_SANDWICH(6, 0.6F),
		DARK_FRUIT(2, 0.3F),
		TITAN_CHOP_RAW(4, 0.3F),
		TITAN_CHOP_COOKED(8, 0.8F),
		SWAMP_BERRIES(1, 0.1F),
		CABBAGE(1, 0.3F),
		TITAN_STEW_COOKED(20, 4.0F),
		PRICKLY_PEAR_RAW(3, 0.3F),
		PRICKLY_PEAR_COOKED(4, 0.5F),
		DARK_FRUIT_PIE(8, 0.3F);

		private final int healAmount;
		private final float saturationModifier;

		EnumFoodType(int healAmount, float saturationModifier) {
			this.healAmount = healAmount;
			this.saturationModifier = saturationModifier;
		}

		public float getSaturationModifier() {
			return saturationModifier;
		}

		public int getHealAmount() {
			return healAmount;
		}

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModItems.EREBUS_FOOD, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}

		@Override
		public ItemStack createStack() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}