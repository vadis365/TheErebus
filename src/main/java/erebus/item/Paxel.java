package erebus.item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class Paxel extends ItemTool {

	public Paxel(ToolMaterial material) {
		super(1.0F, material, null);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass) {
		return 2;
	}

	@Override
	public float func_150893_a(ItemStack is, Block par2Block) {
		float oldSpeedPickaxe = ((ItemTool) Items.iron_pickaxe).efficiencyOnProperMaterial;
		float oldSpeedAxe = ((ItemTool) Items.iron_axe).efficiencyOnProperMaterial;
		float oldSpeedShovel = ((ItemTool) Items.iron_shovel).efficiencyOnProperMaterial;
		((ItemTool) Items.iron_pickaxe).efficiencyOnProperMaterial = efficiencyOnProperMaterial;
		((ItemTool) Items.iron_axe).efficiencyOnProperMaterial = efficiencyOnProperMaterial;
		((ItemTool) Items.iron_shovel).efficiencyOnProperMaterial = efficiencyOnProperMaterial;
		float pickaxeSpeed = Items.iron_pickaxe.func_150893_a(is, par2Block);
		float axeSpeed = Items.iron_axe.func_150893_a(is, par2Block);
		float shovelSpeed = Items.iron_shovel.func_150893_a(is, par2Block);
		((ItemTool) Items.iron_pickaxe).efficiencyOnProperMaterial = oldSpeedPickaxe;
		((ItemTool) Items.iron_axe).efficiencyOnProperMaterial = oldSpeedAxe;
		((ItemTool) Items.iron_shovel).efficiencyOnProperMaterial = oldSpeedShovel;
		if (pickaxeSpeed > 1.0F)
			return pickaxeSpeed;
		if (axeSpeed > 1.0F)
			return axeSpeed;
		if (shovelSpeed > 1.0F)
			return shovelSpeed;
		return 1.0F;
	}

	@Override
	public float getDigSpeed(ItemStack is, Block block, int meta) {
		if (isToolEffective(block, meta))
			return efficiencyOnProperMaterial;
		return func_150893_a(is, block);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean isToolEffective(Block block, int metadata) {
		try {
			List tool = (List) ReflectionHelper.findField(ForgeHooks.class, "toolClasses").get(Items.iron_pickaxe);
			if (tool != null)
				return ((HashSet<List>) ReflectionHelper.findField(ForgeHooks.class, "toolEffectiveness").get(null)).contains(Arrays.asList(block, metadata, tool.get(0)));

			tool = (List) ReflectionHelper.findField(ForgeHooks.class, "toolClasses").get(Items.iron_axe);
			if (tool != null)
				return ((HashSet<List>) ReflectionHelper.findField(ForgeHooks.class, "toolEffectiveness").get(null)).contains(Arrays.asList(block, metadata, tool.get(0)));

			tool = (List) ReflectionHelper.findField(ForgeHooks.class, "toolClasses").get(Items.iron_shovel);
			if (tool != null)
				return ((HashSet<List>) ReflectionHelper.findField(ForgeHooks.class, "toolEffectiveness").get(null)).contains(Arrays.asList(block, metadata, tool.get(0)));
		} catch (Exception e) {
		}
		return false;
	}
}