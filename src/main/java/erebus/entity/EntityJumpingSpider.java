package erebus.entity;

import erebus.item.ItemMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityJumpingSpider extends EntitySpider {

	public EntityJumpingSpider(World par1World) {
		super(par1World);
		setSize(0.7F, 0.5F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(30, new Integer(rand.nextInt(3)));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance < 2.0F) {
			super.attackEntity(entity, distance);
			attackEntityAsMob(entity);
		}
		if (distance > 2.0F && distance < 12.0F && rand.nextInt(10) == 0)
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 1.900000011920929D + motionX * 0.70000000298023224D;
				motionZ = d1 / f2 * 0.5D * 1.900000011920929D + motionZ * 0.70000000298023224D;
				motionY = 0.5000000059604645D;
			}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity)) {
			if (par1Entity instanceof EntityLivingBase) {
				byte b0 = 0;

				if (worldObj.difficultySetting.ordinal() > EnumDifficulty.EASY.ordinal())
					if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
						b0 = 7;
					else if (worldObj.difficultySetting == EnumDifficulty.HARD)
						b0 = 15;

				if (b0 > 0)
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 20, 0));
			}

			return true;
		} else
			return false;
	}

	@Override
	protected Item getDropItem() {
		return null;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.poisonGland.makeStack(), 0F);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
		return par1EntityLivingData;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
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