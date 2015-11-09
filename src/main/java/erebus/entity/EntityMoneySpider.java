package erebus.entity;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMoneySpider extends EntitySpider {

	public EntityMoneySpider(World world) {
		super(world);
		setSize(0.6F, 0.4F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(30, new Integer(rand.nextInt(3)));
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
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (recentlyHit && (rand.nextInt(10) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Items.gold_ingot, 1);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData) {
		return entityLivingData;
	}

	public void setSkin(int skinType) {
		dataWatcher.updateObject(30, new Integer(skinType));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("skin", getSkin());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("skin"))
			setSkin(nbt.getInteger("skin"));
		else
			setSkin(rand.nextInt(3));
	}

	public int getSkin() {
		return dataWatcher.getWatchableObjectInt(30);
	}
}