package erebus.item;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashMultimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSmoothie extends ItemFood {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public ItemSmoothie() {
		super(3, 0.5F, false);
		setMaxDamage(0);
		setAlwaysEdible();
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return ItemMaterials.DATA.smoothieGlass.makeStack();
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.drink;
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onEaten(stack, world, player);
		return getContainerItem(stack);
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
	public int func_150905_g(ItemStack stack) {
		return SmoothieType.values()[stack.getItemDamage()].getHealAmount();
	}

	@Override
	public float func_150906_h(ItemStack stack) {
		return SmoothieType.values()[stack.getItemDamage()].getSaturationModifier();
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String type = SmoothieType.values()[stack.getItemDamage()].toString().toLowerCase();
		return super.getUnlocalizedName() + "_" + type;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean isComplex) {
		SmoothieType type = SmoothieType.values()[stack.getItemDamage()];

		String extraEffect = type.getExtraEffect();
		if (extraEffect != null)
			list.add(StatCollector.translateToLocal("erebus.smoothie.effect." + extraEffect));

		PotionEffect[] effects = type.getPotionEffects();
		if (effects != null && effects.length > 0) {
			HashMultimap<String, AttributeModifier> attributes = HashMultimap.create();

			for (PotionEffect effect : effects) {
				String str = StatCollector.translateToLocal(effect.getEffectName()).trim();
				Potion potion = Potion.potionTypes[effect.getPotionID()];
				Map<IAttribute, AttributeModifier> map = potion.func_111186_k();

				if (map != null && map.size() > 0)
					for (Entry<IAttribute, AttributeModifier> entry : map.entrySet()) {
						AttributeModifier modifier = entry.getValue();
						attributes.put(entry.getKey().getAttributeUnlocalizedName(), new AttributeModifier(modifier.getName(), potion.func_111183_a(effect.getAmplifier(), modifier), modifier.getOperation()));
					}

				if (effect.getAmplifier() > 0)
					str = str + " " + StatCollector.translateToLocal("potion.potency." + effect.getAmplifier()).trim();
				if (effect.getDuration() > 20)
					str = str + " (" + Potion.getDurationString(effect) + ")";

				if (potion.isBadEffect())
					list.add(EnumChatFormatting.RED + str);
				else
					list.add(EnumChatFormatting.GRAY + str);
			}

			if (!attributes.isEmpty()) {
				list.add("");
				list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("potion.effects.whenDrank"));

				for (Entry<String, AttributeModifier> entry : attributes.entries()) {
					AttributeModifier modifier = entry.getValue();
					double amount0 = modifier.getAmount();
					double amount1;

					if (modifier.getOperation() != 1 && modifier.getOperation() != 2)
						amount1 = modifier.getAmount();
					else
						amount1 = modifier.getAmount() * 100.0D;

					if (amount0 > 0.0D)
						list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus." + modifier.getOperation(), new Object[] { ItemStack.field_111284_a.format(amount1), StatCollector.translateToLocal("attribute.name." + entry.getKey()) }));
					else if (amount0 < 0.0D) {
						amount1 *= -1.0D;
						list.add(EnumChatFormatting.RED + StatCollector.translateToLocalFormatted("attribute.modifier.take." + modifier.getOperation(), new Object[] { ItemStack.field_111284_a.format(amount1), StatCollector.translateToLocal("attribute.name." + entry.getKey()) }));
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < SmoothieType.values().length; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;

		return icons[meta];
	}

	@Override
	public void registerIcons(IIconRegister icon) {
		icons = new IIcon[SmoothieType.values().length];

		for (int i = 0; i < icons.length; i++)
			icons[i] = icon.registerIcon("erebus:smoothie_" + SmoothieType.values()[i].toString().toLowerCase());
	}

	public static enum SmoothieType {

		GREEN_TEA_GRASSHOPPER(5, 0.4F, new PotionEffect(Potion.jump.id, 1000, 2)),
		MONEY_HONEY(3, 0.2F, new PotionEffect(Potion.regeneration.id, 200, 2)),
		NOTHING_IN_THE_MIDDLE(1, 0.0F, new PotionEffect(Potion.invisibility.id, 500, 1)),
		GREEN_GIANT(2, 0.1F),
		SEEDY_GOODNESS(1, 1.1F, new PotionEffect(Potion.digSpeed.id, 500, 1)),
		GIVIN_ME_THE_BLUES(3, 0.2F, new PotionEffect(Potion.moveSlowdown.id, 500, 2)),
		HOT_HOT_BABY(2, 0.1F, new PotionEffect(Potion.damageBoost.id, 1000, 1)),
		DONT_MEDDLE_WITH_THE_NETTLE(2, 0.1F, new PotionEffect(Potion.resistance.id, 1000, 1)),
		LIQUID_GOLD(0, 0.0F, new PotionEffect(Potion.field_76443_y.id, 1000, 1)),
		BRYUFS_BREW(2, 0.0F, new PotionEffect(Potion.damageBoost.id, 1000, 2), new PotionEffect(Potion.jump.id, 1000, 2), new PotionEffect(Potion.moveSpeed.id, 1000, 2), new PotionEffect(Potion.nightVision.id, 1000, 1));

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

		public ItemStack makeStack() {
			return makeStack(1);
		}

		public ItemStack makeStack(int size) {
			return new ItemStack(ModItems.smoothie, size, ordinal());
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
					player.curePotionEffects(new ItemStack(Items.milk_bucket));
				case GIVIN_ME_THE_BLUES:
					player.extinguish();
				case HOT_HOT_BABY:
					player.setFire(5);
				case LIQUID_GOLD:
					player.heal(0.5F);
				case BRYUFS_BREW:
					player.heal(1.5F);
				default:
			}
		}
	}
}