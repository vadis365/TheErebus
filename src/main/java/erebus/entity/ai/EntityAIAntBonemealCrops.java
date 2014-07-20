package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import erebus.core.helper.Utils;
import erebus.entity.EntityBlackAnt;

public class EntityAIAntBonemealCrops extends EntityAIEatBlock {

	private final double moveSpeed;

	public EntityAIAntBonemealCrops(EntityLivingBase entity, double moveSpeed, int eatSpeed) {
		super((EntityLiving) entity, null, 0, null, moveSpeed, eatSpeed);
		this.moveSpeed = moveSpeed;
	}
	
	@Override
	public boolean shouldExecute() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		return blackAnt.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") && !blackAnt.canCollectFromSilo;
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
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		PathEntity pathentity = blackAnt.worldObj.getEntityPathToXYZ(blackAnt,
				cropX, cropY, cropZ, 16.0F, true, false, false, true);
		if (pathentity != null) {
			blackAnt.setPathToEntity(pathentity);
			blackAnt.getNavigator().setPath(pathentity, 0.5D);
		}
		if (blackAnt.getDistance(cropX, cropY, cropZ) < 1.5D)
			blackAnt.getMoveHelper().setMoveTo(cropX + 0.5D, cropY,
					cropZ + 0.5D, 0.5D);
	}

	@Override
	protected void prepareToEat() {
	}

	@Override
	protected void eatingInterupted() {
	}

	@Override
	protected void afterEaten() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		EntityPlayer player = Utils.getPlayer(blackAnt.worldObj);
		if (!blackAnt.worldObj.isRemote) {
			if (blackAnt.getStackInSlot(1) != null && blackAnt.getStackInSlot(2) != null) {
				if (blackAnt.getStackInSlot(1).getItem() == blackAnt.getStackInSlot(2).getItem()) {
					if (blackAnt.getStackInSlot(1).getItemDamage() == blackAnt.getStackInSlot(2).getItemDamage()) {
						Utils.rightClickItemAt(blackAnt.worldObj, cropX, cropY, cropZ, 1, new ItemStack(blackAnt.getStackInSlot(2).getItem(), blackAnt.getStackInSlot(2).getItemDamage()));
						ItemDye.applyBonemeal(blackAnt.getStackInSlot(2), blackAnt.worldObj, cropX, cropY, cropZ, player);
						blackAnt.setInventorySlotContents(2, new ItemStack(blackAnt.getStackInSlot(2).getItem(), blackAnt.getStackInSlot(2).stackSize - 1, blackAnt.getStackInSlot(2).getItemDamage()));
					if (blackAnt.getStackInSlot(2).stackSize < 1)
						blackAnt.setInventorySlotContents(2, null);
					}
				}
			}
		}
	}
}