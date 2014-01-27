package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.tileentity.TileEntityErebusAltarXP;
import erebus.utils.Utils;

public class BlockErebusAltarXP extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon a, b;
	private int item;
	private int meta;

	public BlockErebusAltarXP(int id) {
		super(id, Material.rock);
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
		return new TileEntityErebusAltarXP();
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
		blockIcon = reg.registerIcon("erebus:blockErebusAltarBreak");
		a = reg.registerIcon("erebus:blockErebusAltarBreak");
		b = reg.registerIcon("erebus:blockErebusAltarBreak");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityErebusAltarXP te = (TileEntityErebusAltarXP) world.getBlockTileEntity(x, y, z);
		te.setActive(false);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox(i + f, j, k + f, i + 1 - f, j + 1 - f, k + 1 - f);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		TileEntityErebusAltarXP te = (TileEntityErebusAltarXP) world.getBlockTileEntity(x, y, z);
		double offsetY = 0.9D;
		if (entity instanceof EntityItem && entity.boundingBox.minY >= y + offsetY && te.active) {
			ItemStack is = ((EntityItem) entity).getEntityItem();
			int metadata = is.getItemDamage();
			setItemOffering(is.itemID, metadata);
			if (item == ModItems.erebusMaterials.itemID) {
				te.setUses(te.getUses() + is.stackSize);
				entity.setDead();
				if (!world.isRemote)
					world.spawnEntityInWorld(new EntityXPOrb(world, x + 0.5D, y + 1.8D, z + 0.5D, is.stackSize * 5));
				if (te.getUses() > 165)
					te.setSpawnTicks(0);
				if (te.getExcess() > 0)
					Utils.dropStack(world, (int) (x + 0.5D), (int) (y + 1.0D), (int) (z + 0.5D), new ItemStack(item, te.getExcess(), meta));
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarXP te = (TileEntityErebusAltarXP) world.getBlockTileEntity(x, y, z);
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().itemID == ModItems.wandOfAnimation.itemID && !te.active) {
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setSpawnTicks(12000);
				te.setActive(true);
				return true;
			}
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().itemID == ModItems.wandOfAnimation.itemID && te.active) {
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setActive(false);
				return true;
			}
		return false;
	}

	private void setItemOffering(int itemID, int metadata) {
		item = itemID;
		meta = metadata;
	}
}
