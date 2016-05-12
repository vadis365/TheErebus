package erebus.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.configs.ConfigHandler;
import erebus.item.ItemMaterials;

public class EntityWisp extends EntityMob {
	private ChunkCoordinates currentFlightTarget;
	private float heightOffset = 0.5F;
	public int lastX;
	public int lastY;
	public int lastZ;
	private float particleSpawnTick;
	public float particleSize;
	private boolean triggerOnce;

	public EntityWisp(World world) {
		super(world);
		setSize(0.5F, 0.5F);
		experienceValue = 2;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(30, new Byte((byte) 0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 5D : 5D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(8.0D);
	}

	@Override
	protected void fall(float distance) {
	}

	@Override
	protected void updateFallState(double distanceFallen, boolean onGround) {
	}

	@Override
	public boolean isOnLadder() {
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		particleSpawnTick++;
		if (particleSpawnTick <= 50)
			particleSize = particleSpawnTick / 25;
		else
			particleSize = 2 - (particleSpawnTick - 50) / 25;
		if (particleSpawnTick > 100)
			particleSpawnTick = 0;
		heightOffset = 1.5F + (float) rand.nextGaussian() * 5.0F;
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

	protected void flyAbout() {
		if (currentFlightTarget != null && (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1))
			currentFlightTarget = null;
		if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 4.0F)
			currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));
		double var1 = currentFlightTarget.posX + 0.5D - posX;
		double var3 = currentFlightTarget.posY + 0.1D - posY;
		double var5 = currentFlightTarget.posZ + 0.5D - posZ;
		motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
		motionY += (Math.signum(var3) * 0.8D - motionY) * 0.10000000149011612D;
		motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
		float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
		moveForward = 0.5F;
		rotationYaw += var8;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote)
			findNearEntity();
		if (worldObj.isRemote && isGlowing())
			lightUp(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
		if (worldObj.isRemote && !isGlowing())
			switchOff();
		if (!worldObj.isRemote)
			if (worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)) == Blocks.water)
				motionY += 0.1D;
	}

	@SideOnly(Side.CLIENT)
	private void lightUp(World world, int x, int y, int z) {
		if (!ConfigHandler.INSTANCE.bioluminescence)
			return;
		world.setLightValue(EnumSkyBlock.Block, x, y, z, 9);
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				for (int k = -1; k < 2; k++)
					if (x + i != lastX || y + j != lastY || z + k != lastZ || isDead) {
						world.updateLightByType(EnumSkyBlock.Block, lastX + i, lastY + j, lastZ + k);
						lastX = x;
						lastY = y;
						lastZ = z;
					}
		triggerOnce = true;
	}

	@SideOnly(Side.CLIENT)
	private void switchOff() {
		if (!ConfigHandler.INSTANCE.bioluminescence)
			return;
		if(triggerOnce) {
			worldObj.updateLightByType(EnumSkyBlock.Block, lastX, lastY, lastZ);
			worldObj.updateLightByType(EnumSkyBlock.Block, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
			triggerOnce = false;
		}
	}

	@SuppressWarnings("unchecked")
	protected Entity findNearEntity() {
		List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(posX - 0.5D, posY - 0.5D, posZ - 0.5D, posX + 0.5D, posY + 0.5D, posZ + 0.5D).expand(16D, 16D, 16D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
			if (entity != null && !getIsNearEntity())
				setIsNearEntity(true);
		}
		if (list.isEmpty() && getIsNearEntity())
			setIsNearEntity(false);
		return null;
	}

	public boolean isGlowing() {
		return worldObj.getSunBrightness(1.0F) < 0.5F && getIsNearEntity();
	}

	public void setIsNearEntity(boolean entityNear) {
		dataWatcher.updateObject(30, entityNear ? (byte) 1 : (byte) 0);
	}

	public boolean getIsNearEntity() {
		return dataWatcher.getWatchableObjectByte(30) == 1 ? true : false;
	}

	@Override
	public void setDead() {
		super.setDead();
		if (worldObj.isRemote)
			switchOff();
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.BIO_LUMINESCENCE.makeStack(), 0.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {

			if (entity instanceof EntityLivingBase) {
				byte duration = 0;

				if (worldObj.difficultySetting.ordinal() > EnumDifficulty.EASY.ordinal() && rand.nextInt(19) == 0)
					if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
						duration = 5;
					else if (worldObj.difficultySetting == EnumDifficulty.HARD)
						duration = 10;

				if (duration > 0)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, duration * 20, 0));
			}
			return true;
		} else
			return false;
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance < 2.0F) {
			super.attackEntity(entity, distance);
			attackEntityAsMob(entity);
		}
	}
}
