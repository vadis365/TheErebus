package erebus.blocks;

import java.util.Random;

import erebus.ModBlocks.IHasCustomItem;
import erebus.ModTabs;
import erebus.items.block.ItemDoorErebus;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDoorErebus extends BlockDoor implements IHasCustomItem {

	public BlockDoorErebus(IBlockState state, String harvestType, int harvestLevel) {
		super(state.getMaterial());
		setHarvestLevel(harvestType, harvestLevel);
		setCreativeTab(ModTabs.BLOCKS);
		setHardness(3.0F);
		setSoundType(SoundType.WOOD);
	}
	
	public BlockDoorErebus(IBlockState state) {
		super(state.getMaterial());
		setCreativeTab(ModTabs.BLOCKS);
		setHardness(3.0F);
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
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