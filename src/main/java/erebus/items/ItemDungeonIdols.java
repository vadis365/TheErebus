package erebus.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import erebus.ModItems;
import erebus.ModItems.ISubItemsItem;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.entity.EntityUmberGolemDungeonTypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDungeonIdols extends Item implements ISubItemsItem {

	public ItemDungeonIdols() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.ITEMS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.ITEMS) {
			for (EnumIdolType type : EnumIdolType.values())
				list.add(type.createStack(1));
		}
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		int meta = stack.getItemDamage();
		meta = Math.min(Math.max(meta, 0), EnumIdolType.values().length - 1);
		return super.getTranslationKey() + "." + EnumIdolType.values()[meta].name().toLowerCase();
	}

	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getItemDamage() >= 4) {
			if (!world.isRemote) {
				byte spawn = (byte) (stack.getItemDamage() - 4);
				EntityUmberGolemDungeonTypes entityUmberGolem = new EntityUmberGolemDungeonTypes(world);
				entityUmberGolem.setType(spawn);
				entityUmberGolem.setHealth(entityUmberGolem.getMaxHealth()); // hack because of stupid attributes setting
				entityUmberGolem.setPosition(pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D);
				world.spawnEntity(entityUmberGolem);
			}
			if (!player.capabilities.isCreativeMode)
				stack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumIdolType type : EnumIdolType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumIdolType implements IErebusEnum {
		IDOL_MUD,
		IDOL_IRON,
		IDOL_GOLD,
		IDOL_JADE,
		IDOL_MUD_UMBERGOLEM,
		IDOL_IRON_UMBERGOLEM,
		IDOL_GOLD_UMBERGOLEM,
		IDOL_JADE_UMBERGOLEM;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModItems.IDOLS, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}