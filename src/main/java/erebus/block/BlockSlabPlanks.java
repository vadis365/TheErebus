package erebus.block;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSlabPlanks extends Block {

	private final EnumWood wood;

	public BlockSlabPlanks(EnumWood wood) {
		super(Material.wood);
		this.wood = wood;
		setHardness(2.0F);
		setLightOpacity(0);
		setHarvestLevel("axe", 0);
		setCreativeTab(ModTabs.blocks);
		setStepSound(Block.soundTypeWood);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		setBlockName(Reference.MOD_ID + ".slabPlanks" + wood.name());
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 2 || side == ForgeDirection.UP && meta == 1 || side == ForgeDirection.DOWN && meta == 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0)
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		else if (meta == 1)
			setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
		else if (meta == 2)
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity) {
		setBlockBoundsBasedOnState(world, x, y, z);
		super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if (side == 1)
			return 0;
		if (side == 0 || hitY > 0.5D)
			return 1;
		return 0;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		int size = 1;
		if (meta == 2)
			size = 2;

		ret.add(new ItemStack(this, size));

		return ret;
	}

	@Override
	public String getLocalizedName() {
		return StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".slab_" + wood.getUnlocalisedName() + ".name");
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.planks.getIcon(side, wood.ordinal());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return true;
		// if (side != 1 && side != 0 && !super.shouldSideBeRendered(world, x,
		// y, z, side))
		// return false;
		// else {
		// int i1 = x + Facing.offsetsXForSide[Facing.oppositeSide[side]];
		// int j1 = y + Facing.offsetsYForSide[Facing.oppositeSide[side]];
		// int k1 = z + Facing.offsetsZForSide[Facing.oppositeSide[side]];
		// boolean flag = (world.getBlockMetadata(i1, j1, k1) & 8) != 0;
		// return flag ? side == 0 ? true : side == 1 &&
		// super.shouldSideBeRendered(world, x, y, z, side) ? true :
		// !func_150003_a(world.getBlock(x, y, z)) || (world.getBlockMetadata(x,
		// y, z) & 8) == 0 : side == 1 ? true : side == 0 &&
		// super.shouldSideBeRendered(world, x, y, z, side) ? true :
		// !func_150003_a(world.getBlock(x, y, z)) || (world.getBlockMetadata(x,
		// y, z) & 8) != 0;
		// }
	}
}