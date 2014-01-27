package erebus.item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.ForgeHooks;
import erebus.core.helper.ReflectionHelper;

public class ItemPaxel extends ItemTool {

	public ItemPaxel(int id, EnumToolMaterial material) {
		super(id, 1.0F, material, new Block[0]);
	}

	@Override
	public boolean canHarvestBlock(Block block) {
		return Item.axeIron.canHarvestBlock(block) || Item.pickaxeIron.canHarvestBlock(block) || Item.shovelIron.canHarvestBlock(block);
	}

	@Override
	public float getStrVsBlock(ItemStack is, Block par2Block) {
		float oldSpeedPickaxe = ((ItemTool) Item.pickaxeIron).efficiencyOnProperMaterial;
		float oldSpeedAxe = ((ItemTool) Item.axeIron).efficiencyOnProperMaterial;
		float oldSpeedShovel = ((ItemTool) Item.shovelIron).efficiencyOnProperMaterial;
		((ItemTool) Item.pickaxeIron).efficiencyOnProperMaterial = efficiencyOnProperMaterial;
		((ItemTool) Item.axeIron).efficiencyOnProperMaterial = efficiencyOnProperMaterial;
		((ItemTool) Item.shovelIron).efficiencyOnProperMaterial = efficiencyOnProperMaterial;
		float pickaxeSpeed = Item.pickaxeIron.getStrVsBlock(is, par2Block);
		float axeSpeed = Item.axeIron.getStrVsBlock(is, par2Block);
		float shovelSpeed = Item.shovelIron.getStrVsBlock(is, par2Block);
		((ItemTool) Item.pickaxeIron).efficiencyOnProperMaterial = oldSpeedPickaxe;
		((ItemTool) Item.axeIron).efficiencyOnProperMaterial = oldSpeedAxe;
		((ItemTool) Item.shovelIron).efficiencyOnProperMaterial = oldSpeedShovel;
		if (pickaxeSpeed > 1.0F)
			return pickaxeSpeed;
		if (axeSpeed > 1.0F)
			return axeSpeed;
		if (shovelSpeed > 1.0F)
			return shovelSpeed;
		return 1.0F;
	}

	@Override
	public float getStrVsBlock(ItemStack is, Block block, int meta) {
		if (isToolEffective(block, meta))
			return efficiencyOnProperMaterial;
		return getStrVsBlock(is, block);
	}

	public static boolean isToolEffective(Block block, int metadata) {
		List toolClass = (List) ReflectionHelper.getField(ForgeHooks.class, HashMap.class, null, "toolClasses").get(Item.pickaxeIron);
		if (toolClass != null)
			return ReflectionHelper.getField(ForgeHooks.class, HashSet.class, null, "toolEffectiveness").contains(Arrays.asList(block, metadata, toolClass.get(0)));

		toolClass = (List) ReflectionHelper.getField(ForgeHooks.class, HashMap.class, null, "toolClasses").get(Item.axeIron);
		if (toolClass != null)
			return ReflectionHelper.getField(ForgeHooks.class, HashSet.class, null, "toolEffectiveness").contains(Arrays.asList(block, metadata, toolClass.get(0)));

		toolClass = (List) ReflectionHelper.getField(ForgeHooks.class, HashMap.class, null, "toolClasses").get(Item.shovelIron);
		if (toolClass != null)
			return ReflectionHelper.getField(ForgeHooks.class, HashSet.class, null, "toolEffectiveness").contains(Arrays.asList(block, metadata, toolClass.get(0)));

		return false;
	}
}
