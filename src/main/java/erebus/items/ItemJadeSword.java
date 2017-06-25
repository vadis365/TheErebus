package erebus.items;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemJadeSword extends ItemSword {

	public ItemJadeSword() {
		super(ModMaterials.TOOL_JADE);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.JADE.ordinal();
	}
/*
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {  
			WorldGenErebusHugeTree worldGenTree = new WorldGenErebusHugeTree(true, true, EnumWood.MAHOGANY);
			worldGenTree.prepare(20 + itemRand.nextInt(5));
			worldGenTree.generate(world, itemRand, pos.up());
		}
		return EnumActionResult.SUCCESS;
	}
*/
}

