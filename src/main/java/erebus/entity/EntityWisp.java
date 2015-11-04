package erebus.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.configs.ConfigHandler;
import erebus.item.ItemMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityWisp extends EntityMob {
	private ChunkCoordinates currentFlightTarget;
	private float heightOffset = 0.5F;
	public int lastX;
	public int lastY;
	public int lastZ;
	private float particleSpawnTick;
	public float particleSize;

	public EntityWisp(World world) {
		super(world);
		setSize(0.5F, 0.5F);
		experienceValue = 2;
		renderDistanceWeight = 64;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(8.0D);
	}

	/*
	 * protected String getLivingSound() { return "mob.zombie"; } protected String getHurtSound() { return "mob.zombiehurt"; } protected String getDeathSound() { return "mob.zombiedeath"; }
	 */

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
		if (worldObj.isRemote && isGlowing())
			lightUp(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
		if (worldObj.isRemote && !isGlowing())
			switchOff();
		if (!worldObj.isRemote)
			if (worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)) == Blocks.water)
				;
		motionY += 0.1D;
		super.onUpdate();
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
	}

	@SideOnly(Side.CLIENT)
	private void switchOff() {
		if (!ConfigHandler.INSTANCE.bioluminescence)
			return;
		worldObj.updateLightByType(EnumSkyBlock.Block, lastX, lastY, lastZ);
		worldObj.updateLightByType(EnumSkyBlock.Block, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
	}

	public boolean isGlowing() {
		return worldObj.getSunBrightness(1.0F) < 0.5F;
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
			entityDropItem(ItemMaterials.DATA.bioLuminescence.makeStack(), 0.0F);
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
