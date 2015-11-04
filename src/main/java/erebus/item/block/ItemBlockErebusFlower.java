package erebus.item.block;

import erebus.block.plants.BlockErebusFlower;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockErebusFlower extends ItemBlockGeneric {

	public ItemBlockErebusFlower(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		int meta = Math.min(Math.max(0, stack.getItemDamage()), BlockErebusFlower.FLOWER_TYPE.values().length - 1);
		BlockErebusFlower.FLOWER_TYPE type = BlockErebusFlower.FLOWER_TYPE.values()[meta];

		return StatCollector.translateToLocal(getUnlocalizedName() + "_" + type.toString().toLowerCase() + ".name");
	}
}