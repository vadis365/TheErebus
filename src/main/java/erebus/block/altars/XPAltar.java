package erebus.block.altars;

import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltarXP;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XPAltar extends AltarAbstract {

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarXP();
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		TileEntityErebusAltarXP te = Utils.getTileEntity(world, pos, TileEntityErebusAltarXP.class);
		te.setActive(false);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		TileEntityErebusAltarXP te = Utils.getTileEntity(world,pos, TileEntityErebusAltarXP.class);
		double offsetY = 0.9D;
		if (entity instanceof EntityItem && entity.getEntityBoundingBox().minY >= pos.getY() + offsetY && te.active) {
			ItemStack stack = ((EntityItem) entity).getItem();

			if (stack.getItem() == ModItems.MATERIALS) {
				te.setUses(te.getUses() + stack.getCount());
				entity.setDead();
				if (!world.isRemote)
					world.spawnEntity(new EntityXPOrb(world, pos.getX() + 0.5D, pos.getY() + 1.8D, pos.getZ() + 0.5D, stack.getCount() * 5));
				if (te.getUses() > 165)
					te.setSpawnTicks(0);
				if (te.getExcess() > 0)
					Utils.dropStackNoRandom(world, pos.up(), stack.copy());
			}
		}
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarXP te = Utils.getTileEntity(world, pos, TileEntityErebusAltarXP.class);
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