package erebus.blocks;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockRedGem extends Block {

	public static final String[] iconPaths = new String[] { "redgem", "redlampOn", "redlampOff" };
	@SideOnly(Side.CLIENT)

	public BlockRedGem() {
		super(Material.GLASS);
		setHardness(0.3F);
		setLightLevel(1F);
		setSoundType(SoundType.GLASS);
		setUnlocalizedName("erebus.redGem");
		setCreativeTab(ModTabs.BLOCKS);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, NonNullList<ItemStack> list) {
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
	}

	@Override
	public int damageDropped(IBlockState state) {
		int meta = getMetaFromState(state);
		return meta == 1 || meta == 2 ? 1 : ItemMaterials.EnumType.RED_GEM.ordinal();
	}

	@Override
	public int quantityDropped(Random random) {
		return 1 + random.nextInt(2);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return getMetaFromState(state) == 0 ? ModItems.MATERIALS : Item.getItemFromBlock(this);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public int getLightValue(IBlockState state) {
		return 15;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		int meta = getMetaFromState(state);
		if (!world.isRemote && (meta == 1 || meta == 2))
			if (meta == 2 && !(world.isBlockIndirectlyGettingPowered(pos) == 0))
				world.scheduleBlockUpdate(pos, this, 0, 4);
			else if (meta != 2 && world.isBlockIndirectlyGettingPowered(pos) == 0)
				world.setBlockState(pos, getStateFromMeta(2), 2);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		int meta = getMetaFromState(state);
		if (!world.isRemote && meta == 2 && !(world.isBlockIndirectlyGettingPowered(pos) == 0))
			world.setBlockState(pos, getStateFromMeta(1), 2);
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		int meta = getMetaFromState(state);
		if (meta == 0 && side == EnumFacing.UP)
			return true;
		return meta == 1 || meta == 2;
	}
}