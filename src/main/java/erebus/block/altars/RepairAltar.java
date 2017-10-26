package erebus.block.altars;

import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltarRepair;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RepairAltar extends AltarAbstract {

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarRepair();
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		TileEntityErebusAltarRepair te = Utils.getTileEntity(world, pos, TileEntityErebusAltarRepair.class);
		te.setActive(false);
		te.setcanBeUsed(true);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		TileEntityErebusAltarRepair te = Utils.getTileEntity(world, pos, TileEntityErebusAltarRepair.class);
		double offsetY = 0.9D;
		if (entity instanceof EntityItem && entity.getEntityBoundingBox().minY >= pos.getY() + offsetY && te.active) {
			te.setCollisions(te.getCollisions() + 1);
			ItemStack is = ((EntityItem) entity).getItem();
			entity.posY = pos.getY() + 1.6D;
			int repairDamage = is.getItemDamage();
			if (is.isItemStackDamageable() && repairDamage > 0) {
				if (te.notUsed)
					te.setSpawnTicks(160);
				if (te.getSpawnTicks() == 60 && te.getCollisions() == 101) {
					world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.2F, 1.0F);
					is.getItem().setDamage(is, -repairDamage);
				}
				if (te.getSpawnTicks() % 2 == 0 && te.getCollisions() < 101)
					if (world.isRemote)
						te.sparky(world, pos);
			}
			if (te.getCollisions() > 101)
				te.setSpawnTicks(0);
		}
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarRepair te = Utils.getTileEntity(world, pos, TileEntityErebusAltarRepair.class);
		ItemStack stack = player.getHeldItem(hand);
		if (!stack.isEmpty())
			if (stack.getItem() == ModItems.WAND_OF_ANIMATION) {
				stack.damageItem(1, player);
				if (!te.active) {
					te.setActive(true);
					te.setSpawnTicks(12000);
					return true;
				}
				if (te.active) {
					te.setActive(false);
					return true;
				}
			}
		return false;
	}
}
