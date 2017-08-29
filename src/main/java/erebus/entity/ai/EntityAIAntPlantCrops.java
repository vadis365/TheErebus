package erebus.entity.ai;

import erebus.core.helper.Utils;
import erebus.entity.EntityBlackAnt;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;

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
	public boolean continueExecuting() {
		return !blackAnt.canCollectFromSilo && !isAntInvSlotEmpty();
	}

	@Override
	protected boolean canEatBlock(Block block, int blockMeta) {
		if (block == null)
			return false;

		if (block == Blocks.dirt || block == Blocks.grass)
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
		PathEntity pathentity = blackAnt.worldObj.getEntityPathToXYZ(blackAnt, cropX, cropY + 1, cropZ, 16.0F, true, false, false, true);
		if (pathentity != null) {
			blackAnt.setPathToEntity(pathentity);
			blackAnt.getNavigator().setPath(pathentity, 0.5D);
		}

		if (blackAnt.getDistance(cropX, cropY, cropZ) < 1.5D)
			blackAnt.getMoveHelper().setMoveTo(cropX + 0.5D, cropY + 1, cropZ + 0.5D, 0.5D);
	}

	@Override
	protected void prepareToEat() {
	}

	@Override
	protected void eatingInterupted() {
	}

	@Override
	protected void afterEaten() {
		if (!blackAnt.worldObj.isRemote) {

			if (getTargetBlock() != Blocks.farmland) {
				Utils.rightClickItemAt(blackAnt.worldObj, cropX, cropY, cropZ, 1, new ItemStack(Items.wooden_hoe));
				blackAnt.worldObj.playSoundEffect((double) cropX + 0.5F, (double) cropY + 0.5F, (double) cropZ + 0.5F, Blocks.farmland.stepSound.getStepResourcePath(), (Blocks.farmland.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.farmland.stepSound.getPitch() * 0.8F);
			}

			if (!isFilterSlotEmpty() && !isAntInvSlotEmpty()) {
				Item filterItem = getFilterSlotStack().getItem();
				Item invItem = getAntInvSlotStack().getItem();
				int invItemMeta = getAntInvSlotStack().getItemDamage();
				int filterItemMeta = getFilterSlotStack().getItemDamage();

				if (filterItem == invItem && filterItemMeta == invItemMeta) {
					Utils.rightClickItemAt(blackAnt.worldObj, cropX, cropY, cropZ, 1, new ItemStack(invItem, invItemMeta));
					blackAnt.setInventorySlotContents(INVENTORY_SLOT, new ItemStack(invItem, getAntInvSlotStack().stackSize - 1, invItemMeta));
					if (getAntInvSlotStack().stackSize < 1)
						blackAnt.setInventorySlotContents(INVENTORY_SLOT, null);
				}
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