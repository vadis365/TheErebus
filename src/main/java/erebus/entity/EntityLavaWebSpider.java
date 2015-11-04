package erebus.entity;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLavaWebSpider extends EntityMob {
	private int shouldDo;

	public EntityLavaWebSpider(World world) {
		super(world);
		setSize(3F, 1.5F);
		isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote)
			setBesideClimbableBlock(isCollidedHorizontally);
		if (worldObj.isRemote && worldObj.getWorldTime() % 5 == 0)
			lavaParticles(worldObj, posX, posY + 1.3D, posZ, rand);
	}

	@Override
	public void onLivingUpdate() {
		if (rand.nextInt(50) == 0) {
			int x = MathHelper.floor_double(posX);
			int y = MathHelper.floor_double(posY);
			int z = MathHelper.floor_double(posZ);

			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * -2D;
			double offSetZ = Math.cos(a) * -2D;

			if (worldObj.isAirBlock(x, y, z) && Blocks.fire.canPlaceBlockAt(worldObj, x + MathHelper.floor_double(offSetX), y, z + MathHelper.floor_double(offSetZ)))
				worldObj.setBlock(x + MathHelper.floor_double(offSetX), y, z + MathHelper.floor_double(offSetZ), Blocks.fire);
		}
		super.onLivingUpdate();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1D);
	}

	@Override
	protected Entity findPlayerToAttack() {
		float f = getBrightness(1.0F);
		if (f < 0.5F) {
			double d0 = 16.0D;
			return worldObj.getClosestVulnerablePlayerToEntity(this, d0);
		} else
			return null;
	}

	@Override
	protected String getLivingSound() {
		return "mob.spider.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.spider.say";
	}

	@Override
	protected String getDeathSound() {
		return "mob.spider.death";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	protected String getWebSlingThrowSound() {
		return "erebus:webslingthrow";
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (attackTime <= 0 && distance < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			entity.setFire(10);
			attackTime = 20;
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
		if (distance >= 5 & distance <= 16.0F)
			if (attackTime == 0) {
				++shouldDo;
				if (shouldDo == 1)
					attackTime = 60;
				else if (shouldDo <= 4)
					attackTime = 6;
				else {
					attackTime = 20;
					shouldDo = 0;
				}
				if (shouldDo > 1) {
					worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1009, (int) posX, (int) posY, (int) posZ, 0);
					for (int count = 0; count < 1; ++count) {
						EntityWebSling websling = new EntityWebSling(worldObj, this);
						websling.posY = posY + height / 2.0F + 0.5D;
						websling.setType((byte) 2);
						worldObj.spawnEntityInWorld(websling);
					}
				}
			}
	}

	@Override
	protected Item getDropItem() {
		return Items.fire_charge;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		super.dropFewItems(recentlyHit, looting);
		if (recentlyHit && (rand.nextInt(3) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Items.spider_eye, 1);
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
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && worldObj.isMaterialInBB(boundingBox, Material.lava);
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
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData) {
		Object entityLivingData1 = super.onSpawnWithEgg(entityLivingData);

		if (worldObj.rand.nextInt(100) == 0) {
			EntityMoneySpider entityspidermoney = new EntityMoneySpider(worldObj);
			entityspidermoney.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			entityspidermoney.onSpawnWithEgg((IEntityLivingData) null);
			worldObj.spawnEntityInWorld(entityspidermoney);
			entityspidermoney.mountEntity(this);
		}
		if (entityLivingData1 == null) {
			entityLivingData1 = new EntitySpider.GroupData();
			if (worldObj.difficultySetting == EnumDifficulty.HARD && worldObj.rand.nextFloat() < 0.1F * worldObj.func_147462_b(posX, posY, posZ))
				((EntitySpider.GroupData) entityLivingData1).func_111104_a(worldObj.rand);

			if (entityLivingData1 instanceof EntitySpider.GroupData) {
				int i = ((EntitySpider.GroupData) entityLivingData1).field_111105_a;
				if (i > 0 && Potion.potionTypes[i] != null)
					addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
			}
		}
		return (IEntityLivingData) entityLivingData1;
	}

	@SideOnly(Side.CLIENT)
	public void lavaParticles(World world, double x, double y, double z, Random rand) {
		Erebus.proxy.spawnCustomParticle("lava", worldObj, x, y, z, 0F, 0F, 0F);
	}
}