package erebus.item;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemErebusFood extends ItemFood {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	static {
		registerPotionEffect();
	}

	@SuppressWarnings("unchecked")
	private static void registerPotionEffect() {
		try {
			Field f = ReflectionHelper.findField(PotionHelper.class, "potionRequirements", "field_77927_l");
			f.setAccessible(true);

			HashMap<Integer, String> potionRequirements = (HashMap<Integer, String>) f.get(null);
			potionRequirements.put(Potion.jump.getId(), "0 & 1 & !2 & 3");

			Field f2 = ReflectionHelper.findField(PotionHelper.class, "potionAmplifiers", "field_77928_m");
			f2.setAccessible(true);
			HashMap<Integer, String> potionAmplifiers = (HashMap<Integer, String>) f2.get(null);
			potionAmplifiers.put(Potion.jump.getId(), "5");

			Field f3 = ReflectionHelper.findField(Potion.class, "liquidColor", "field_76414_N");
			f3.setAccessible(true);
			f3.set(Potion.jump, 0x22FF4C);
		} catch (Exception e) {
		}
	}

	public ItemErebusFood() {
		super(3, 0.5F, false);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
	}

	public PotionEffect getPotionEffect(ItemStack is, World world, EntityPlayer player) {
		switch (FoodType.values()[is.getItemDamage()]) {
			case BEETLE_LARVA_RAW:
				return new PotionEffect(Potion.confusion.id, 300, 2);
			case MELONADE_SPARKLY:
				return new PotionEffect(Potion.regeneration.id, 200, 0);
			case LARVAE_ON_STICK:
				return new PotionEffect(Potion.confusion.id, 100, 1);
			case TITAN_CHOP_COOKED:
				return new PotionEffect(Potion.damageBoost.id, 600, 1);
			default:
				return null;
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		switch (FoodType.values()[stack.getItemDamage()]) {
			case MELONADE:
			case MELONADE_SPARKLY:
				return EnumAction.drink;
			default:
				return EnumAction.eat;
		}
	}

	@Override
	public int func_150905_g(ItemStack stack) {
		return FoodType.values()[stack.getItemDamage()].getHealAmount();
	}

	@Override
	public float func_150906_h(ItemStack stack) {
		return FoodType.values()[stack.getItemDamage()].getSaturationModifier();
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		FoodType type = FoodType.values()[stack.getItemDamage()];
		stack.stackSize--;
		player.getFoodStats().func_151686_a(this, stack);

		String sound = type == FoodType.CABBAGE ? "erebus:cabbagefart" : "random.burp";
		float volume = type == FoodType.CABBAGE ? 1 : 0.5F;
		world.playSoundAtEntity(player, sound, volume, world.rand.nextFloat() * 0.1F + 0.9F);
		onFoodEaten(stack, world, player);

		return hasContainerItem(stack) ? getContainerItem(stack) : stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		PotionEffect effect = this.getPotionEffect(stack, world, player);
		if (!world.isRemote && effect != null)
			player.addPotionEffect(effect);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icons = new IIcon[FoodType.values().length];
		int i = 0;
		for (FoodType type : FoodType.values())
			icons[i++] = reg.registerIcon("erebus:food_" + type.toString().toLowerCase());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < FoodType.values().length; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + FoodType.values()[stack.getItemDamage()].toString().toLowerCase();
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		switch (FoodType.values()[stack.getItemDamage()]) {
			case LARVAE_ON_STICK:
				return new ItemStack(Items.stick);
			case BAMBOO_SOUP:
				return new ItemStack(Items.bowl);
			case MELONADE:
			case MELONADE_SPARKLY:
				return ItemMaterials.DATA.smoothieGlass.makeStack();
			case TITAN_STEW_COOKED:
				return ItemMaterials.DATA.stewPot.makeStack();
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
	public String getPotionEffect(ItemStack stack) {
		return stack != null && stack.getItemDamage() == FoodType.GRASSHOPPER_LEG_RAW.ordinal() ? "+0+1-2+3&4-4+13" : super.getPotionEffect(stack);
	}

	@Override
	public boolean isPotionIngredient(ItemStack stack) {
		return stack != null && stack.getItemDamage() == FoodType.GRASSHOPPER_LEG_RAW.ordinal();
	}

	public static enum FoodType {

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
		SWAMPBERRIES(1, 0.1F),
		CABBAGE(1, 0.3F),
		TITAN_STEW_COOKED(20, 4.0F);

		private final int healAmount;
		private final float saturationModifier;

		FoodType(int healAmount, float saturationModifier) {
			this.healAmount = healAmount;
			this.saturationModifier = saturationModifier;
		}

		public float getSaturationModifier() {
			return saturationModifier;
		}

		public int getHealAmount() {
			return healAmount;
		}

		public ItemStack makeStack() {
			return makeStack(1);
		}

		public ItemStack makeStack(int size) {
			return new ItemStack(ModItems.food, size, ordinal());
		}
	}
}