package erebus.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.item.ItemErebusMaterial.DATA;

public class EntityWasp extends EntityMob implements IEntityAdditionalSpawnData {

	private float heightOffset = 0.0F;
	public float wingFloat;
	AnimationMathHelper mathWings = new AnimationMathHelper();
	Class[] preys = { EntityGrasshopper.class, EntityBeetle.class, EntityBeetleLarva.class };
	private boolean areAttributesSetup = false;

	public EntityWasp(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(25, new Byte((byte) rand.nextInt(32)));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		areAttributesSetup = true;
		updateBossAttributes();
	}

	protected void updateBossAttributes() {
		if (worldObj != null && !worldObj.isRemote)
			if (getIsBoss() == 1) {
				setSize(3F, 2F);
				experienceValue = 25;
				getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.9D);
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(60.0D);
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(8.0D);
				getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
			} else {
				setSize(1.5F, 1.0F);
				experienceValue = 10;
				getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75D);
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
				getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
			}
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected String getLivingSound() {
		return "erebus:WaspSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:WaspHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, DATA.waspSting.ordinal()), 0.0F);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, DATA.plateExo.ordinal()), 0.0F);
	}

	public boolean isFlying() {
		return !onGround;
	}

	@Override
	public void onUpdate() {
		byte i;
		if (worldObj.isRemote) {
			i = getIsBoss();
			if (i == 1) {
				setSize(3F, 2F);
				setCustomNameTag("Hornet of Despair");
			} else
				setSize(1.5F, 1.0F);
		}
		if (!isFlying())
			wingFloat = 0.0F;
		if (isFlying())
			wingFloat = mathWings.swing(4.0F, 0.1F);
		if (findPlayerToAttack() != null)
			entityToAttack = findPlayerToAttack();
		else if (findEnemyToAttack() != null)
			entityToAttack = findEnemyToAttack();
		else
			entityToAttack = null;
		if (!worldObj.isRemote) {
			heightOffset = 1.0F + (float) rand.nextGaussian() * 5.0F;
			if (getEntityToAttack() != null && getEntityToAttack().posY + getEntityToAttack().getEyeHeight() > posY + getEyeHeight() + heightOffset) {
				double var1 = getEntityToAttack().posX + 0.5D - posX;
				double var3 = getEntityToAttack().posY + 1.D - posY;
				double var5 = getEntityToAttack().posZ + 0.5D - posZ;
				motionY += (0.350000011920929D - motionY) * 0.350000011920929D;
				motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
				motionY += (Math.signum(var3) * 0.699999988079071D - motionY) * 0.10000000149011612D;
				motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
				float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
				moveForward = 0.5F;
				rotationYaw += var8;
			}
		}
		if (motionY < 0.0D)
			motionY *= 0.5D;
		super.onUpdate();
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1 != null && canEntityBeSeen(var1) ? var1 : null;
	}

	protected Entity findEnemyToAttack() {
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(10D, 10D, 10D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity != null) {
				if (!(entity instanceof EntityLivingBase))
					continue;
				for (int j = 0; j < preys.length; j++)
					if (entity.getClass() == preys[j])
						return entity;
			}
		}
		return null;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte var2 = 0;
				if (worldObj.difficultySetting > 1)
					if (worldObj.difficultySetting == 2)
						var2 = 7;
					else if (worldObj.difficultySetting == 3)
						var2 = 15;
				if (var2 > 0)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
			}
			return true;
		}
		return false;
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (par2 < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			attackEntityAsMob(entity);
	}

	public byte getIsBoss() {
		return dataWatcher.getWatchableObjectByte(25);
	}

	public void setIsBoss(byte boss) {
		dataWatcher.updateObject(25, Byte.valueOf(boss));
		worldObj.setEntityState(this, (byte) 25);
		if (areAttributesSetup)
			updateBossAttributes();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("mobType", getIsBoss());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setIsBoss(nbt.getByte("mobType"));
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeByte(getIsBoss());
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		setIsBoss(data.readByte());
	}
}