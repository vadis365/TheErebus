package erebus.block.altars;

import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltarHealing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HealingAltar extends AltarAbstract {

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarHealing();
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		TileEntityErebusAltarHealing te = Utils.getTileEntity(world, pos, TileEntityErebusAltarHealing.class);
		te.setActive(false);
	}

	@Override
	 public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarHealing te = Utils.getTileEntity(world, pos, TileEntityErebusAltarHealing.class);
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