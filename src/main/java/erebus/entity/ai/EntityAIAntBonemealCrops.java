package erebus.entity.ai;

import erebus.core.helper.Utils;
import erebus.entity.EntityBlackAnt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;

public class EntityAIAntBonemealCrops extends EntityAIAntsBlock {

	EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
	public static final int CROP_ID_SLOT = 1;
	public static final int INVENTORY_SLOT = 2;

	public EntityAIAntBonemealCrops(EntityLivingBase entity, double moveSpeed, int eatSpeed) {
		super((EntityLiving) entity, null, 0, null, moveSpeed, eatSpeed);
	}

	@Override
	public boolean shouldExecute() {
		return !blackAnt.canCollectFromSilo;
	}

	@Override
	public boolean continueExecuting() {
		return !blackAnt.canCollectFromSilo && !isAntInvSlotEmpty();
	}

	@Override
	protected boolean canEatBlock(Block block, int blockMeta) {
		if (block == null)
			return false;

		if (block instanceof BlockCrops && blockMeta < 7)
			return true;
		else if (block.hasTileEntity(blockMeta))
			return false;

		return false;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		PathEntity pathentity = blackAnt.worldObj.getEntityPathToXYZ(blackAnt, cropX, cropY, cropZ, 16.0F, true, false, false, true);

		if (pathentity != null) {
			blackAnt.setPathToEntity(pathentity);
			blackAnt.getNavigator().setPath(pathentity, 0.5D);
		}

		if (blackAnt.getDistance(cropX, cropY, cropZ) < 1.5D)
			blackAnt.getMoveHelper().setMoveTo(cropX + 0.5D, cropY, cropZ + 0.5D, 0.5D);
	}

	@Override
	protected void prepareToEat() {
	}

	@Override
	protected void eatingInterupted() {
	}

	@Override
	protected void afterEaten() {
		EntityPlayer player = Utils.getPlayer(blackAnt.worldObj);

		if (!blackAnt.worldObj.isRemote)
			if (!isFilterSlotEmpty() && !isAntInvSlotEmpty()) {
				Item filterItem = getFilterSlotStack().getItem();
				Item invItem = getAntInvSlotStack().getItem();
				int invItemMeta = getAntInvSlotStack().getItemDamage();
				int filterItemMeta = getFilterSlotStack().getItemDamage();

				if (filterItem == invItem && filterItemMeta == invItemMeta) {
					Utils.rightClickItemAt(blackAnt.worldObj, cropX, cropY, cropZ, 1, new ItemStack(invItem, invItemMeta));
					ItemDye.applyBonemeal(getAntInvSlotStack(), blackAnt.worldObj, cropX, cropY, cropZ, player);
					if (getAntInvSlotStack().stackSize < 1)
						blackAnt.setInventorySlotContents(INVENTORY_SLOT, null);
				}
			}
	}

	public boolean isFilterSlotEmpty() {
		return getFilterSlotStack() == null;
	}

	public ItemStack getFilterSlotStack() {
		return blackAnt.getStackInSlot(CROP_ID_SLOT);
	}

	public boolean isAntInvSlotEmpty() {
		return getAntInvSlotStack() == null;
	}

	public ItemStack getAntInvSlotStack() {
		return blackAnt.getStackInSlot(INVENTORY_SLOT);
	}
}