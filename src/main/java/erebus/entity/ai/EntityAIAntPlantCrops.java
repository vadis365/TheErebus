package erebus.entity.ai;

import erebus.core.helper.Utils;
import erebus.entity.EntityBlackAnt;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class EntityAIAntPlantCrops extends EntityAIAntsBlock {

	EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
	public static final int CROP_ID_SLOT = 1;
	public static final int INVENTORY_SLOT = 2;

	public EntityAIAntPlantCrops(EntityLivingBase entity, double moveSpeed, int eatSpeed) {
		super((EntityLiving) entity, null, 0, null, moveSpeed, eatSpeed);
	}

	@Override
	public boolean shouldExecute() {
		return !blackAnt.canCollectFromSilo;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !blackAnt.canCollectFromSilo && !isAntInvSlotEmpty();
	}

	@Override
	protected boolean canEatBlock(IBlockState state) {
		Block block = state.getBlock();
		if (block == null)
			return false;

		if (block == Blocks.DIRT || block == Blocks.GRASS)
			return true;
		if (block == Blocks.FARMLAND && entity.getEntityWorld().isAirBlock(new BlockPos(cropX, cropY + 1, cropZ)))
			return true;
		else if (block.hasTileEntity(state))
			return false;

		return false;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		blackAnt.getNavigator().tryMoveToXYZ(cropX + 0.5D, cropY + 1, cropZ + 0.5D, 0.5D);
	}

	@Override
	protected void prepareToEat() {
	}

	@Override
	protected void eatingInterupted() {
	}

	@Override
	protected void afterEaten() {
		BlockPos pos = new BlockPos(cropX, cropY, cropZ);
		if (!blackAnt.getEntityWorld().isRemote) {
			if (getTargetBlock() != Blocks.FARMLAND) {
				Utils.rightClickItemAt(blackAnt.getEntityWorld(), pos, EnumHand.MAIN_HAND, EnumFacing.UP, new ItemStack(Items.WOODEN_HOE));
				blackAnt.getEntityWorld().playSound((EntityPlayer)null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}

			if (!isFilterSlotEmpty() && !isAntInvSlotEmpty()) {
				Item filterItem = getFilterSlotStack().getItem();
				Item invItem = getAntInvSlotStack().getItem();
				int invItemMeta = getAntInvSlotStack().getItemDamage();
				int filterItemMeta = getFilterSlotStack().getItemDamage();

				if (filterItem == invItem && filterItemMeta == invItemMeta) {
					Utils.rightClickItemAt(blackAnt.getEntityWorld(), pos, EnumHand.MAIN_HAND, EnumFacing.UP, new ItemStack(invItem, 1, invItemMeta));
					blackAnt.setInventorySlotContents(INVENTORY_SLOT, new ItemStack(invItem, getAntInvSlotStack().getCount() - 1, invItemMeta));
					if (getAntInvSlotStack().getCount() < 1)
						blackAnt.setInventorySlotContents(INVENTORY_SLOT, ItemStack.EMPTY);
				}
			}
		}
	}

	public boolean isFilterSlotEmpty() {
		return getFilterSlotStack().isEmpty();
	}

	public ItemStack getFilterSlotStack() {
		return blackAnt.getStackInSlot(CROP_ID_SLOT);
	}

	public boolean isAntInvSlotEmpty() {
		return getAntInvSlotStack().isEmpty();
	}

	public ItemStack getAntInvSlotStack() {
		return blackAnt.getStackInSlot(INVENTORY_SLOT);
	}
}