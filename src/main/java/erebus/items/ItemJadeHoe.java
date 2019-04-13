package erebus.items;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.blocks.BlockPetrifiedWoodRock;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.world.feature.decoration.WorldGenPetrifiedTrees;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemJadeHoe extends ItemHoe {

	public ItemJadeHoe() {
		super(ModMaterials.TOOL_JADE);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.JADE.ordinal();
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
		//	WorldGenSwampHut gen = new WorldGenSwampHut();
		//	gen.generateStructure(worldIn, itemRand, pos.up());

		//WorldGenPetrifiedTrees gen = new WorldGenPetrifiedTrees(16 + itemRand.nextInt(10), 3, ModBlocks.PETRIFIED_BARK_BROWN.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y), ModBlocks.PETRIFIED_LOG_INNER.getDefaultState(), true, ModBlocks.ORE_PETRIFIED_QUARTZ.getDefaultState());
		
		//WorldGenPetrifiedTrees gen = new WorldGenPetrifiedTrees(6 + itemRand.nextInt(5), 1, ModBlocks.PETRIFIED_BARK_BROWN.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y));
		
		//WorldGenPetrifiedTrees gen = new WorldGenPetrifiedTrees(11 + itemRand.nextInt(4), 2, ModBlocks.PETRIFIED_BARK_BROWN.getDefaultState().withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Y));
		
		//gen.generate(worldIn, itemRand, pos.up());
		}

		return EnumActionResult.SUCCESS;
	}

}