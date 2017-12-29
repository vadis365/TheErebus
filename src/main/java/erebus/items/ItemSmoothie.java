package erebus.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;

import erebus.ModItems;
import erebus.ModItems.ISubItemsItem;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSmoothie extends ItemFood implements ISubItemsItem {

	public ItemSmoothie() {
		super(3, 0.5F, false);
		setMaxDamage(0);
		setAlwaysEdible();
		setHasSubtypes(true);
		setCreativeTab(ModTabs.ITEMS);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack();
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
		super.onItemUseFinish(stack, world, entityLiving);
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (stack.getCount() >= 0)
				if (!player.inventory.addItemStackToInventory(getContainerItem(stack)))
					player.dropItem(getContainerItem(stack), false);
		}
		return stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			SmoothieType type = SmoothieType.values()[stack.getItemDamage()];
			PotionEffect[] effects = type.getPotionEffects();
			if (effects != null && effects.length > 0)
				for (PotionEffect effect : effects)
					player.addPotionEffect(new PotionEffect(effect));
			type.onDrunk(player);
		}
	}

	@Override
	public int getHealAmount(ItemStack stack) {
		return SmoothieType.values()[stack.getItemDamage()].getHealAmount();
	}

	@Override
	public float getSaturationModifier(ItemStack stack) {
		return SmoothieType.values()[stack.getItemDamage()].getSaturationModifier();
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		meta = Math.min(Math.max(meta, 0), SmoothieType.values().length - 1);
		return super.getUnlocalizedName() + "." + SmoothieType.values()[meta].name().toLowerCase();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		SmoothieType type = SmoothieType.values()[stack.getItemDamage()];

		String extraEffect = type.getExtraEffect();
		if (extraEffect != null)
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("erebus.smoothie.effect." + extraEffect).getFormattedText());

		PotionEffect[] effects = type.getPotionEffects();
		if (effects != null && effects.length > 0) {
			HashMultimap<String, AttributeModifier> attributes = HashMultimap.create();

			for (PotionEffect effect : effects) {
				String str = new TextComponentTranslation(effect.getEffectName()).getFormattedText().trim();
				Potion potion = effect.getPotion();
				
				Map<IAttribute, AttributeModifier> map = potion.getAttributeModifierMap();

				if (map != null && map.size() > 0)
					for (Entry<IAttribute, AttributeModifier> entry : map.entrySet()) {
						AttributeModifier modifier = entry.getValue();
						attributes.put(entry.getKey().getName(), new AttributeModifier(modifier.getName(), potion.getAttributeModifierAmount(effect.getAmplifier(), modifier), modifier.getOperation()));
					}

				if (effect.getAmplifier() > 0) {
					str = str + " " + (potion.isBadEffect() ? TextFormatting.RED : TextFormatting.BLUE) + new TextComponentTranslation("potion.potency." + effect.getAmplifier()).getFormattedText().trim();
				
				}
				if (effect.getDuration() > 20) {
					str = str + (potion.isBadEffect() ? TextFormatting.RED : TextFormatting.BLUE) + " (" + StringUtils.ticksToElapsedTime(effect.getDuration()) + ")";
				}

				if (potion.isBadEffect())
					list.add(TextFormatting.RED + str);
				else
					list.add(TextFormatting.BLUE + str);
			}

			if (!attributes.isEmpty()) {
				list.add("");
				list.add(TextFormatting.DARK_PURPLE + new TextComponentTranslation("potion.whenDrank").getFormattedText());

				for (Entry<String, AttributeModifier> entry : attributes.entries()) {
					AttributeModifier modifier = entry.getValue();
					double amount0 = modifier.getAmount();
					double amount1;

					if (modifier.getOperation() != 1 && modifier.getOperation() != 2)
						amount1 = modifier.getAmount();
					else
						amount1 = modifier.getAmount() * 100.0D;

					if (amount0 > 0.0D)
						list.add(TextFormatting.BLUE + new TextComponentTranslation("attribute.modifier.plus." + modifier.getOperation(), new Object[] { ItemStack.DECIMALFORMAT.format(amount1), new TextComponentTranslation("attribute.name." + entry.getKey()) }).getFormattedText());
					else if (amount0 < 0.0D) {
						amount1 *= -1.0D;
						list.add(TextFormatting.RED + new TextComponentTranslation("attribute.modifier.take." + modifier.getOperation(), new Object[] { ItemStack.DECIMALFORMAT.format(amount1), new TextComponentTranslation("attribute.name." + entry.getKey()) }).getFormattedText());
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.ITEMS) {
			for (SmoothieType type : SmoothieType.values())
				list.add(type.createStack(1));
		}
	}
	
	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (SmoothieType type : SmoothieType.values())
			models.add(type.getName());
		return models;
	}

	public static enum SmoothieType implements IErebusEnum {

		GREEN_TEA_GRASSHOPPER(5, 0.4F, new PotionEffect(MobEffects.JUMP_BOOST, 1000, 2)),
		MONEY_HONEY(3, 0.2F, new PotionEffect(MobEffects.REGENERATION, 200, 2)),
		NOTHING_IN_THE_MIDDLE(1, 0.0F, new PotionEffect(MobEffects.INVISIBILITY, 500, 1)),
		GREEN_GIANT(2, 0.1F),
		SEEDY_GOODNESS(1, 1.1F, new PotionEffect(MobEffects.HASTE, 500, 1)),
		GIVIN_ME_THE_BLUES(3, 0.2F, new PotionEffect(MobEffects.SLOWNESS, 500, 2)),
		HOT_HOT_BABY(2, 0.1F, new PotionEffect(MobEffects.STRENGTH, 1000, 1)),
		DONT_MEDDLE_WITH_THE_NETTLE(2, 0.1F, new PotionEffect(MobEffects.RESISTANCE, 1000, 1)),
		LIQUID_GOLD(0, 0.0F, new PotionEffect(MobEffects.REGENERATION, 1000, 1)),
		BRYUFS_BREW(2, 0.0F, new PotionEffect(MobEffects.STRENGTH, 1000, 2), new PotionEffect(MobEffects.JUMP_BOOST, 1000, 2), new PotionEffect(MobEffects.SPEED, 1000, 2), new PotionEffect(MobEffects.NIGHT_VISION, 1000, 1));

		private final int healAmount;
		private final float saturationModifier;
		private final PotionEffect[] effects;

		SmoothieType(int healAmount, float saturationModifier, PotionEffect... effects) {
			this.healAmount = healAmount;
			this.saturationModifier = saturationModifier;
			this.effects = effects;
		}

		public PotionEffect[] getPotionEffects() {
			return effects;
		}

		public float getSaturationModifier() {
			return saturationModifier;
		}

		public int getHealAmount() {
			return healAmount;
		}
		
		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModItems.SMOOTHIE, size, ordinal());
		}

		@Override
		public String getName() {
			return "smoothie_" + name().toLowerCase(Locale.ENGLISH);
		}

		public String getExtraEffect() {
			switch (this) {
				case GREEN_GIANT:
					return "milk";
				case GIVIN_ME_THE_BLUES:
					return "extinguish";
				case HOT_HOT_BABY:
					return "set_fire";
				case LIQUID_GOLD:
					return "heal";
				case BRYUFS_BREW:
					return "heal";
				default:
					return null;
			}
		}

		public void onDrunk(EntityPlayer player) {
			switch (this) {
				case GREEN_GIANT:
					player.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
					break;
				case GIVIN_ME_THE_BLUES:
					player.extinguish();
					break;
				case HOT_HOT_BABY:
					player.setFire(5);
					break;
				case LIQUID_GOLD:
					player.heal(0.5F);
					break;
				case BRYUFS_BREW:
					player.heal(1.5F);
					break;
				default:
			}
		}
	}
}