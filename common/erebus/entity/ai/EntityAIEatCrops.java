package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntityAIEatCrops extends EntityAIEatBlock {

	public EntityAIEatCrops(EntityLiving entity, double movespeed) {
		super(entity, Block.crops, 7, new ItemStack(Item.seeds), movespeed);
	}

	@Override
	public boolean continueExecuting() {
		return entity instanceof EntityAnimal ? !((EntityAnimal) entity).isInLove() && !entity.isChild() : super.continueExecuting();
	}

	@Override
	protected boolean isEntityReady() {
		return false;
	}

	@Override
	protected void afterEaten() {
	}

	@Override
	protected void prepareToEat() {
		// TODO Auto-generated method stub
		
	}
}