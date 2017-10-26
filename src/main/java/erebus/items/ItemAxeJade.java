package erebus.items;

import java.util.Set;

import com.google.common.collect.Sets;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemAxeJade extends ItemTool {
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });
	private static final float ATTACK_DAMAGE = 6.0F;
	private static final float ATTACK_SPEED = -3.0F;

	public ItemAxeJade() {
		super(ModMaterials.TOOL_JADE, EFFECTIVE_ON);
		setCreativeTab(ModTabs.GEAR);
		attackDamage = ATTACK_DAMAGE;
		attackSpeed = ATTACK_SPEED;
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.JADE.ordinal();
	}
}