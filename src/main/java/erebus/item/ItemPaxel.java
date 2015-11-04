package erebus.item;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public abstract class ItemPaxel extends ItemTool {

	protected ItemPaxel(ToolMaterial material) {
		super(1.0F, material, null);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass) {
		if ("pickaxe".equals(toolClass) || "axe".equals(toolClass) || "shovel".equals(toolClass))
			return ToolMaterial.IRON.getHarvestLevel();
		return -1;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		if (isToolEffective(block, meta))
			return efficiencyOnProperMaterial;
		return 1.0F;
	}

	public boolean isToolEffective(Block block, int meta) {
		return block.isToolEffective("pickaxe", meta) || block.isToolEffective("axe", meta) || block.isToolEffective("shovel", meta);
	}
}