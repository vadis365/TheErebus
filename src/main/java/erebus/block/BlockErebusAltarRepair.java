package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.tileentity.TileEntityErebusAltarRepair;

public class BlockErebusAltarRepair extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon a, b;

	public BlockErebusAltarRepair(int id) {
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
		return new TileEntityErebusAltarRepair();
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
		TileEntityErebusAltarRepair te = (TileEntityErebusAltarRepair) world.getBlockTileEntity(x, y, z);
		te.setActive(false);
		te.setcanBeUsed(true);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox(x + f, y, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		TileEntityErebusAltarRepair te = (TileEntityErebusAltarRepair) world.getBlockTileEntity(x, y, z);
		double offsetY = 0.9D;
		if (entity instanceof EntityItem && entity.boundingBox.minY >= y + offsetY && te.active) {
			te.setCollisions(te.getCollisions() + 1);
			ItemStack is = ((EntityItem) entity).getEntityItem();
			entity.posY = y + 1.6D;
			int repairDamage = is.getItemDamage();
			if (is.isItemStackDamageable() && repairDamage > 0) {
				if (te.notUsed)
					te.setSpawnTicks(160);
				if (te.getSpawnTicks() == 60 && te.getCollisions() == 101) {
					world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "random.anvil_use", 0.2F, 1.0F);
					is.getItem().setDamage(is, -repairDamage);
				}
				if (te.getSpawnTicks() % 2 == 0 && te.getCollisions() < 101)
					te.sparky(world, x, y, z);
			}
			if (te.getCollisions() > 101)
				te.setSpawnTicks(0);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarRepair te = (TileEntityErebusAltarRepair) world.getBlockTileEntity(x, y, z);
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().itemID == ModItems.wandOfAnimation.itemID && !te.active) {
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setActive(true);
				te.setSpawnTicks(12000);
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
}
