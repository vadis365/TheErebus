package erebus.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModItems.ISubItemsItem;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import erebus.blocks.BlockPlantedGiantFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFlowerSeeds extends Item implements IPlantable, ISubItemsItem {

	public ItemFlowerSeeds() {
		setHasSubtypes(true);
		setCreativeTab(ModTabs.PLANTS);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, stack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up())) {
			worldIn.setBlockState(pos.up(), ModBlocks.PLANTED_FLOWER.getDefaultState().withProperty(BlockPlantedGiantFlower.TYPE, BlockPlantedGiantFlower.EnumFlowerType.values()[stack.getItemDamage()]), 11);
			stack.shrink(1);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return ModBlocks.PLANTED_FLOWER.getDefaultState();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.PLANTS)
			for (EnumSeedType type : EnumSeedType.values())
				list.add(type.createStack(1));
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		return "item.erebus.flower_seed_" + EnumSeedType.values()[stack.getItemDamage()].getName();
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumSeedType type : EnumSeedType.values())
			models.add("flower_seed_" + type.getName());
		return models;
	}

	public enum EnumSeedType implements IErebusEnum {
		BLACK,
		RED,
		BROWN,
		BLUE,
		PURPLE,
		CYAN,
		LIGHT_GRAY,
		GRAY,
		PINK,
		YELLOW,
		LIGHT_BLUE,
		MAGENTA,
		ORANGE,
		WHITE,
		RAINBOW;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModItems.FLOWER_SEED, size, ordinal());
		}
	}
}