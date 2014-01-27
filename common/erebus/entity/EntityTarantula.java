package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpiderEffectsGroupData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModItems;

public class EntityTarantula extends EntityMob {
	public int skin = rand.nextInt(99);

	public EntityTarantula(World world) {
		super(world);
		setSize(1.3F, 0.6F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(5.0D);
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public int getTotalArmorValue() {
		return 4;
	}

	@Override
	public boolean isOnLadder() {
		return isBesideClimbableBlock();
	}

	@Override
	public void setInWeb() {
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potionEffect) {
		return potionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(potionEffect);
	}

	public boolean isBesideClimbableBlock() {
		return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean par1) {
		byte b0 = dataWatcher.getWatchableObjectByte(16);

		if (par1)
			b0 = (byte) (b0 | 1);
		else
			b0 &= -2;

		dataWatcher.updateObject(16, Byte.valueOf(b0));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote)
			setBesideClimbableBlock(isCollidedHorizontally);
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance < 2.0F) {
			super.attackEntity(entity, distance);
			attackEntityAsMob(entity);
		}
		if (distance > 2.0F && distance < 6.0F && rand.nextInt(10) == 0)
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
				motionY = 0.4000000059604645D;
			}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {

		if (super.attackEntityAsMob(entity)) {

			if (entity instanceof EntityLiving) {
				byte var2 = 0;

				if (worldObj.difficultySetting > 1 && rand.nextInt(19) == 0)
					if (worldObj.difficultySetting == 2)
						var2 = 5;
					else if (worldObj.difficultySetting == 3)
						var2 = 10;

				if (var2 > 0)
					((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
			}
			return true;
		} else
			return false;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int chanceFiftyFifty = rand.nextInt(1) + 1;

		int chance40x40x20 = rand.nextInt(4);
		int stringDrop = 0;

		switch (chance40x40x20) {
			case 0:
			case 1:
				stringDrop = 1;
				break;
			case 2:
			case 3:
				stringDrop = 2;
				break;
			case 4:
				stringDrop = 3;
				break;
		}

		int chance20x60x20 = rand.nextInt(4);
		int legDrop = 0;

		switch (chance20x60x20) {
			case 0:
				legDrop = 1;
				break;
			case 1:
			case 2:
			case 3:
				legDrop = 2;
				break;
			case 4:
				legDrop = 3;
				break;
		}

		dropItem(Item.silk.itemID, stringDrop + par2);

		if (isBurning())
			entityDropItem(new ItemStack(ModItems.erebusFood, legDrop + par2, 5), 0.0F);
		else
			entityDropItem(new ItemStack(ModItems.erebusFood, legDrop + par2, 4), 0.0F);

		dropItem(Item.spiderEye.itemID, chanceFiftyFifty + par2);
	}

	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData entityLivingData) {
		Object entityLivingData1 = super.onSpawnWithEgg(entityLivingData);

		if (worldObj.rand.nextInt(100) == 0) {
			EntityMoneySpider entityspidermoney = new EntityMoneySpider(worldObj);
			entityspidermoney.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			entityspidermoney.onSpawnWithEgg((EntityLivingData) null);
			worldObj.spawnEntityInWorld(entityspidermoney);
			entityspidermoney.mountEntity(this);
		}
		if (entityLivingData1 == null) {
			entityLivingData1 = new SpiderEffectsGroupData();
			if (worldObj.difficultySetting > 2 && worldObj.rand.nextFloat() < 0.1F * worldObj.getLocationTensionFactor(posX, posY, posZ))
				((SpiderEffectsGroupData) entityLivingData1).func_111104_a(worldObj.rand);
		}
		if (entityLivingData1 instanceof SpiderEffectsGroupData) {
			int i = ((SpiderEffectsGroupData) entityLivingData1).field_111105_a;
			if (i > 0 && Potion.potionTypes[i] != null)
				addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
		}
		return (EntityLivingData) entityLivingData1;
	}
}
