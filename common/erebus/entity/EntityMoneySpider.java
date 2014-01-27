package erebus.entity;

import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.800000011920929D);
	}

	@Override
	protected int getDropItemId() {
		return Item.goldNugget.itemID;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		super.dropFewItems(par1, par2);

		if (par1 && (rand.nextInt(10) == 0 || rand.nextInt(1 + par2) > 0))
			dropItem(Item.ingotGold.itemID, 1);
	}

	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData entityLivingData) {
		return entityLivingData;
	}
}
