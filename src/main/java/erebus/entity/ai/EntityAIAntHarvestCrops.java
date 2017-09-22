package erebus.entity.ai;

import erebus.entity.EntityBlackAnt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class EntityAIAntHarvestCrops extends EntityAIAntsBlock {

	EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
	private IBlockState blockMunched;
	private int metaData;

	public EntityAIAntHarvestCrops(EntityLivingBase entity, double moveSpeed, int eatSpeed) {
		super((EntityLiving) entity, null, 0, null, moveSpeed, eatSpeed);
	}

	@Override
	protected boolean canEatBlock(IBlockState state) {
		Block block = state.getBlock();

		if (block == null)
			return false;

		if (block instanceof BlockCrops && block.getMetaFromState(state) >= 7)
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
	public boolean shouldExecute() {
		return !blackAnt.getMoveHelper().isUpdating() && super.shouldExecute();
	}

	@Override
	protected void moveToLocation() {
		blackAnt.getNavigator().tryMoveToXYZ(cropX + 0.5D, cropY + 1, cropZ + 0.5D, 0.5D);
	}

	@Override
	protected void prepareToEat() {
		blockMunched = getTargetBlock();
		metaData = getTargetBlock().getBlock().getMetaFromState(getTargetBlock());
	}

	@Override
	protected void eatingInterupted() {
		entity.getNavigator().clearPathEntity();
	}

	@Override
	protected void afterEaten() {
		BlockPos pos = new BlockPos(cropX, cropY, cropZ);
		blackAnt.getEntityWorld().setBlockToAir(pos);
		blackAnt.getEntityWorld().setBlockState(pos.down(), Blocks.DIRT.getDefaultState());
		blackAnt.setBlockHarvested(blockMunched.getBlock(), metaData);
		entity.getNavigator().clearPathEntity();
	}
}