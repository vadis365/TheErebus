package erebus.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import erebus.ModItems;
import erebus.ModItems.ISubItemsItem;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMaterials extends Item implements ISubItemsItem {

	public ItemMaterials() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.ITEMS);
		setUnlocalizedName("materials");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (EnumType type : EnumType.values())
			list.add(type.createStack());
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		meta = Math.min(Math.max(meta, 0), EnumType.values().length - 1);
		return super.getUnlocalizedName() + "." + EnumType.values()[meta].name().toLowerCase();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() == EnumType.WHETSTONE_POWDER.ordinal();
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumType type : EnumType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumType implements IErebusEnum {

		PLATE_EXO,
		JADE,
		SHARD_BONE,
		BAMBOO,
		COMPOUND_EYES,
		COMPOUND_LENS,
		FLY_WING,
		PETRIFIED_WOOD,
		BIO_VELOCITY,
		ELASTIC_FIBRE,
		WASP_STING,
		BAMBOO_SHOOT,
		RED_GEM,
		BIO_LUMINESCENCE,
		SUPERNATURAL_VELOCITY,
		ALTAR_FRAGMENT,
		REINFORCED_PLATE_EXO,
		GLIDER_WING,
		SCORPION_PINCER,
		CAMO_POWDER,
		NECTAR,
		HONEY_DRIP,
		POISON_GLAND,
		MUD_BRICK,
		WHETSTONE_POWDER,
		DRAGONFLY_WING,
		BLUEBELL_PETAL,
		PAPYRUS,
		ENHANCED_GLIDER_WING,
		REPELLENT,
		MUCUS_CHARGE,
		NETTLE_LEAVES,
		NETTLE_FLOWERS,
		DARK_FRUIT_SEEDS,
		MOSS_BALL,
		GLOWSHROOM,
		PLATE_EXO_RHINO,
		RHINO_BEETLE_HORN,
		ANT_PHEROMONES,
		GAEAN_GEM,
		CRIMSON_HEART,
		RESIN,
		AMBER_STAR,
		INGOT_ALUMINIUM,
		INGOT_COPPER,
		INGOT_LEAD,
		INGOT_SILVER,
		INGOT_TIN,
		GNEISS_ROCK,
		HIDE_SHROOM,
		BEETLE_RIDING_KIT,
		BEETLE_TAMING_AMULET,
		UMBERGOLEM_CORE,
		UMBERGOLEM_HEAD,
		UMBERGOLEM_CLAW,
		UMBERGOLEM_LEGS,
		JADE_BERRIES,
		BOGMAW_ROOT,
		HYDROFUGE,
		WATER_REPELLENT,
		SMOOTHIE_GLASS,
		MAGMA_CRAWLER_EYE,
		STEW_POT,
		TITAN_STEW,
		FORCE_KEY,
		SOUL_CRYSTAL,
		PLATE_ZOMBIE_ANT,
		STAG_BEETLE_MANDIBLES,
		TERPSISHROOM;

		@Override
		public ItemStack createStack() {
			return createStack(1);
		}

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModItems.MATERIALS, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}