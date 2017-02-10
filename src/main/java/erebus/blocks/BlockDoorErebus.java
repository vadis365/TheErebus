package erebus.blocks;

import erebus.Erebus;
import erebus.ModBlocks.IHasCustomItem;
import erebus.ModTabs;
import erebus.items.block.ItemDoorErebus;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDoorErebus extends BlockDoor implements IHasCustomItem {

	BlockDoorErebus() {
		super(Material.WOOD);
		setHarvestLevel("axe", 0);
		setCreativeTab(ModTabs.BLOCKS);
		Erebus.proxy.setCustomStateMap(this, new StateMap.Builder().ignore(new IProperty[] { POWERED }).build());
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : Item.getItemFromBlock(this);
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this));
	}

	@Override
	public ItemBlock getItemBlock() {
		return new ItemDoorErebus(this);
	}
}