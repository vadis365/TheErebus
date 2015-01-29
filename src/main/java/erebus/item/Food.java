package erebus.item;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

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
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.item.Materials.DATA;

@SuppressWarnings("unchecked")
public class Food extends ItemFood {
	public static enum FoodType {
		larvaRaw,
		larvaCooked,
		grasshopperLegRaw,
		grasshopperLegCooked,
		tarantulaLegRaw,
		tarantulaLegCooked,
		bambooSoup,
		melonade,
		melonadeSparkly,
		larvaeOnStick,
		honeySandwich,
		middleFruit,
		titanChop,
		titanChopCooked,
		swampBerries,
		cabbage,
		titanStewCooked;
	}

	static {
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

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;

	public Food() {
		super(3, 0.5F, false);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	public int getHealAmount(ItemStack is, World world, EntityPlayer player) {
		switch (FoodType.values()[is.getItemDamage()]) {
			case larvaRaw:
				return 1;
			case larvaCooked:
				return 3;
			case grasshopperLegRaw:
				return 1;
			case grasshopperLegCooked:
				return 4;
			case tarantulaLegRaw:
				return 1;
			case tarantulaLegCooked:
				return 5;
			case bambooSoup:
				return 4;
			case melonade:
				return 3;
			case melonadeSparkly:
				return 5;
			case larvaeOnStick:
				return 9;
			case honeySandwich:
				return 6;
			case middleFruit:
				return 2;
			case titanChop:
				return 4;
			case titanChopCooked:
				return 8;
			case swampBerries:
				return 1;
			case cabbage:
				return 1;
			case titanStewCooked:
				return 20;
			default:
				return 0;
		}
	}

	public float getSaturationModifier(ItemStack is, World world, EntityPlayer player) {
		switch (FoodType.values()[is.getItemDamage()]) {
			case larvaRaw:
				return 0.1F;
			case larvaCooked:
				return 0.4F;
			case grasshopperLegRaw:
				return 0.1F;
			case grasshopperLegCooked:
				return 0.4F;
			case tarantulaLegRaw:
				return 0.1F;
			case tarantulaLegCooked:
				return 0.4F;
			case bambooSoup:
				return 0.3F;
			case melonade:
				return 0.2F;
			case melonadeSparkly:
				return 0.4F;
			case larvaeOnStick:
				return 0.5F;
			case honeySandwich:
				return 0.6F;
			case middleFruit:
				return 0.3F;
			case titanChop:
				return 0.3F;
			case titanChopCooked:
				return 0.8F;
			case swampBerries:
				return 0.1F;
			case cabbage:
				return 0.1F;
			case titanStewCooked:
				return 1.0F;
			default:
				return 0.0F;
		}
	}

	public PotionEffect getPotionEffect(ItemStack is, World world, EntityPlayer player) {
		switch (FoodType.values()[is.getItemDamage()]) {
			case larvaRaw:
				return new PotionEffect(Potion.confusion.id, 300, 2);
			case melonadeSparkly:
				return new PotionEffect(Potion.regeneration.id, 200, 0);
			case larvaeOnStick:
				return new PotionEffect(Potion.confusion.id, 100, 1);
			case titanChopCooked:
				return new PotionEffect(Potion.damageBoost.id, 600, 1);
			default:
				return null;
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is) {
		switch (FoodType.values()[is.getItemDamage()]) {
			case melonade:
			case melonadeSparkly:
				return EnumAction.drink;
			default:
				return EnumAction.eat;
		}
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		is.stackSize--;
		player.getFoodStats().addStats(getHealAmount(is, world, player), getSaturationModifier(is, world, player));
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		onFoodEaten(is, world, player);

		Item item = null;

		switch (FoodType.values()[is.getItemDamage()]) {
			case bambooSoup:
				item = Items.bowl;
				break;
			case melonade:
			case melonadeSparkly:
				item = Items.glass_bottle;
				break;
			case cabbage:
				world.playSoundAtEntity(player, "erebus:cabbagefart", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
				break;
			case titanStewCooked:
				is = (Materials.createStack(DATA.stewPot));
				break;
			default:
				return is;
		}
		if (is.stackSize != 0)
			player.inventory.addItemStackToInventory(new ItemStack(item));

		return is.stackSize == 0 ? new ItemStack(item) : is;
	}

	@Override
	protected void onFoodEaten(ItemStack is, World world, EntityPlayer player) {
		PotionEffect effect = this.getPotionEffect(is, world, player);
		if (!world.isRemote && effect != null)
			player.addPotionEffect(effect);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[FoodType.values().length];
		int i = 0;
		for (FoodType type : FoodType.values())
			icons[i++] = iconRegister.registerIcon("erebus:" + type);
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes" })
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < FoodType.values().length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		int i = is.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		switch (FoodType.values()[stack.getItemDamage()]) {
			case bambooSoup:
				return new ItemStack(Items.bowl);
			case melonade:
			case melonadeSparkly:
				return new ItemStack(Items.glass_bottle);
			case titanStewCooked:
				return (Materials.createStack(DATA.stewPot));
			default:
				return null;
		}
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		switch (FoodType.values()[stack.getItemDamage()]) {
			case bambooSoup:
			case melonade:
			case melonadeSparkly:
			case titanStewCooked:
				return true;
			default:
				return false;
		}
	}

	@Override
    public int getItemStackLimit(ItemStack stack) {
		//could be made item specific I suppose
        if(!hasContainerItem(stack))
        	return 64;
        else
        	return 1;
    }

	@Override
	public String getPotionEffect(ItemStack stack) {
		return "+0+1-2+3&4-4+13";
	}

	@Override
	public boolean isPotionIngredient(ItemStack stack) {
		return stack != null && stack.getItemDamage() == FoodType.grasshopperLegRaw.ordinal();
	}

}