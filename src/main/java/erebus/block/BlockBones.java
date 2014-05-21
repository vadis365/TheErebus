package erebus.block;

import java.util.ArrayList;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.tileentity.TileEntityBones;

public class BlockBones extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon a, b;

	public BlockBones(int id) {
		super(id, Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.6875F, 1.0F);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBones();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.doesBlockHaveSolidTopSurface(x, y - 1, z) || BlockFence.isIdAFence(world.getBlockId(x, y - 1, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? b : side == 1 ? a : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:blockBonesBreak");
		a = reg.registerIcon("erebus:blockBonesBreak");
		b = reg.registerIcon("erebus:blockBonesBreak");
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is) {
		byte b0 = 0;
		int l1 = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (l1 == 0)
			b0 = 2;
		if (l1 == 1)
			b0 = 5;
		if (l1 == 2)
			b0 = 3;
		if (l1 == 3)
			b0 = 4;
		world.setBlockMetadataWithNotify(x, y, z, b0, 3);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int count = 1 + world.rand.nextInt(3);
		for (int i = 0; i < count; i++) {
			int id = -1;
			int damage = 0;
			if (world.rand.nextInt(3) == 0)
				id = Item.bone.itemID;
			else {
				id = ModItems.erebusMaterials.itemID;
				damage = 2;
			}
			if (id > 0)
				ret.add(new ItemStack(id, 1, damage));
		}
		return ret;
	}
}
