package erebus.entity;

import java.util.Calendar;

import erebus.client.render.entity.AnimationMathHelper;
import erebus.item.ItemMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMidgeSwarm extends EntityMob {
	private ChunkCoordinates currentFlightTarget;
	private float heightOffset = 0.5F;
	public float wingFloat;
	private final AnimationMathHelper mathWings = new AnimationMathHelper();

	public EntityMidgeSwarm(World world) {
		super(world);
		setSize(1.0F, 1.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(8.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 2F;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:mosquitoflying";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:flyhurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean isAIEnabled() {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		wingFloat = mathWings.swing(1.5F, 0.2F);
		motionY *= 0.6000000238418579D;
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote) {
			heightOffset = 0.5F + (float) rand.nextGaussian() * 5.0F;
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

			if (getEntityToAttack() == null)
				flyAbout();
		}
		super.onLivingUpdate();
	}

	protected void flyAbout() {
		if (currentFlightTarget != null && (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1))
			currentFlightTarget = null;

		if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 4.0F)
			currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));

		double var1 = currentFlightTarget.posX + 0.5D - posX;
		double var3 = currentFlightTarget.posY + 0.1D - posY;
		double var5 = currentFlightTarget.posZ + 0.5D - posZ;
		motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
		motionY += (Math.signum(var3) * 0.699999988079071D - motionY) * 0.10000000149011612D;
		motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
		float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
		moveForward = 0.5F;
		rotationYaw += var8;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected void updateFallState(double par1, boolean par3) {
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable())
			return false;
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(boundingBox.minY);

		if (var1 >= 127)
			return false;
		else {
			int duration = MathHelper.floor_double(posX);
			int var3 = MathHelper.floor_double(posZ);
			int var4 = worldObj.getBlockLightValue(duration, var1, var3);
			byte var5 = 4;
			Calendar var6 = worldObj.getCurrentDate();

			if ((var6.get(2) + 1 != 10 || var6.get(5) < 20) && (var6.get(2) + 1 != 11 || var6.get(5) > 3)) {
				if (rand.nextBoolean())
					return false;
			} else
				var5 = 7;

			return var4 > rand.nextInt(var5) ? false : super.getCanSpawnHere();
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount) {
			entityDropItem(ItemMaterials.DATA.flyWing.makeStack(), 0.0F);
			if (rand.nextInt(5) == 0)
				entityDropItem(ItemMaterials.DATA.compoundEyes.makeStack(), 0.0F);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity))
			if (entity instanceof EntityLivingBase) {
				byte duration = 0;
				if (worldObj.difficultySetting.ordinal() > 1)
					if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
						duration = 7;
					else if (worldObj.difficultySetting == EnumDifficulty.HARD)
						duration = 15;
				if (duration > 0)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, duration * 20, 0));
				return true;
			}
		return false;
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (par2 < 1.2F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			attackEntityAsMob(entity);
	}
}
