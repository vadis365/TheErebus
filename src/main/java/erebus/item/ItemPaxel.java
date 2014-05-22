package erebus.item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class ItemPaxel extends ItemTool {

	public ItemPaxel(ToolMaterial material) {
		super(1.0F, material, null);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean isToolEffective(Block block, int metadata) {
		try {
			List tool = (List) ReflectionHelper.findField(ForgeHooks.class, "toolClasses").get(Item.pickaxeIron);
			if (tool != null)
				return ((HashSet<List>) ReflectionHelper.findField(ForgeHooks.class, "toolEffectiveness").get(null)).contains(Arrays.asList(block, metadata, tool.get(0)));

			tool = (List) ReflectionHelper.findField(ForgeHooks.class, "toolClasses").get(Item.axeIron);
			if (tool != null)
				return ((HashSet<List>) ReflectionHelper.findField(ForgeHooks.class, "toolEffectiveness").get(null)).contains(Arrays.asList(block, metadata, tool.get(0)));

			tool = (List) ReflectionHelper.findField(ForgeHooks.class, "toolClasses").get(Item.shovelIron);
			if (tool != null)
				return ((HashSet<List>) ReflectionHelper.findField(ForgeHooks.class, "toolEffectiveness").get(null)).contains(Arrays.asList(block, metadata, tool.get(0)));
		} catch (Exception e) {
		}
		return false;
	}
}