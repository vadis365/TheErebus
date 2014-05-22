package erebus.entity;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMoneySpider extends EntitySpider {
	public int skin = rand.nextInt(3);

	public EntityMoneySpider(World world) {
		super(world);
		setSize(0.6F, 0.4F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.800000011920929D);
	}

	@Override
	protected Item getDropItem() {
		return Items.gold_nugget;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		super.dropFewItems(par1, par2);

		if (par1 && (rand.nextInt(10) == 0 || rand.nextInt(1 + par2) > 0))
			dropItem(Items.gold_ingot, 1);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData) {
		return entityLivingData;
	}
}