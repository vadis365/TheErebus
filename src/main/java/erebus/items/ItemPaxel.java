package erebus.items;

import erebus.ModTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import javax.annotation.Nullable;

public abstract class ItemPaxel extends ItemTool {

	protected ItemPaxel(ToolMaterial material) {
		super(1.0F, 1.0F, material, null);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState blockState) {
		if ("pickaxe".equals(toolClass) || "axe".equals(toolClass) || "shovel".equals(toolClass))
			return ToolMaterial.IRON.getHarvestLevel();
		return -1;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		if (isToolEffective(state))
			return efficiencyOnProperMaterial;
		return 1.0F;
	}

	public boolean isToolEffective(IBlockState state) {
		return state.getBlock().isToolEffective("pickaxe", state) || state.getBlock().isToolEffective("axe", state) || state.getBlock().isToolEffective("shovel", state);
	}
}