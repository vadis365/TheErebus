package erebus.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.item.ItemDungeonIdols.IDOL;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityUmberGolemDungeonTypes extends EntityMob implements IEntityAdditionalSpawnData {
	Block block;
	int blockMeta;
	boolean hasBlock = false;
	float hardness;
	int blockX;
	int blockY;
	int blockZ;
	int breakTime = 0;

	public EntityUmberGolemDungeonTypes(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1.0F, 1.9F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.5D, false));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		experienceValue = 120;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, new Integer(0));
		dataWatcher.addObject(29, new Byte((byte) 0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	private double getAttackStrength() {
		switch (getType()) {
			case 0:
				return 4.0D;
			case 1:
				return 5.0D;
			case 2:
				return 6.0D;
			case 3:
				return 7.0D;
			default:
				return 4.0D;
		}
	}

	private double getGolemHealth() {
		switch (getType()) {
			case 0:
				return 100.0D;
			case 1:
				return 150.0D;
			case 2:
				return 200.0D;
			case 3:
				return 250.0D;
			default:
				return 100.0D;
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
	public void setInWeb() {
	}

	@Override
	public boolean allowLeashing() {
		return !canDespawn() && super.allowLeashing();
	}

	/*
	 * protected String getLivingSound() { return "erebus:umbergolemsound"; } protected String getHurtSound() { return "erebus:umbergolemhurt"; }
	 */

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.zombie.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int looting) {
		IDOL type = IDOL.values()[Math.max(0, Math.min(IDOL.values().length, getType()))];

		entityDropItem(new ItemStack(ModItems.idols, 1, type.ordinal()), 0.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getAttackTarget() != null) {
			float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
			if (!hasBlock) {
				if (getRangeAttackTimer() < 100 && distance > 3)
					setRangeAttackTimer(getRangeAttackTimer() + 2);
				if (getRangeAttackTimer() == 100 && distance > 3)
					shootMissile(getAttackTarget(), distance);
			}

			if (oneShotMoveCheat())
				getMoveHelper().setMoveTo(getAttackTarget().posX, getAttackTarget().posY, getAttackTarget().posZ, 0.5D);

			if (isCollidedHorizontally) {
				double direction = Math.toRadians(renderYawOffset);
				removeBlock((int) (posX + -Math.sin(direction) * 1.5D), (int) posY, (int) (posZ + Math.cos(direction) * 1.5D));
				removeBlock((int) (posX + -Math.sin(direction) * 1.5D), (int) posY + 1, (int) (posZ + Math.cos(direction) * 1.5D));
			}
		}
	}

	private boolean oneShotMoveCheat() {
		return !getNavigator().tryMoveToEntityLiving(getAttackTarget(), 0.5D);
	}

	protected void removeBlock(int x, int y, int z) {
		if (!hasBlock && worldObj.getBlock(x, y, z) != Blocks.air) {
			hasBlock = true;
			blockX = x;
			blockY = y;
			blockZ = z;
			breakTime = 0;
			block = worldObj.getBlock(blockX, blockY, blockZ);
			blockMeta = worldObj.getBlockMetadata(blockX, blockY, blockZ);
			hardness = block.getBlockHardness(worldObj, blockX, blockY, blockZ);
		}

		if (hardness <= 0.0F || block == null || !canBreakBlock(block, blockMeta) || !isInSamePos())
			hasBlock = false;

		if (hasBlock) {
			breakTime += 1;
			block = worldObj.getBlock(blockX, blockY, blockZ);
			blockMeta = worldObj.getBlockMetadata(blockX, blockY, blockZ);
			int i = (int) (breakTime / (hardness * 160.0F) * 10.0F);
			worldObj.destroyBlockInWorldPartially(getEntityId(), blockX, blockY, blockZ, i);

			if (worldObj.rand.nextInt(30) == 0)
				worldObj.playAuxSFXAtEntity(null, 2001, blockX, blockY, blockZ, Block.getIdFromBlock(worldObj.getBlock(blockX, blockY, blockZ)));

			if (breakTime >= hardness * 160.0F) {
				Utils.dropStack(worldObj, blockX, blockY, blockZ, new ItemStack(block, 1, blockMeta));
				worldObj.setBlockToAir(blockX, blockY, blockZ);
				breakTime = 0;
				hasBlock = false;
				worldObj.playAuxSFXAtEntity(null, 2001, blockX, blockY, blockZ, Block.getIdFromBlock(worldObj.getBlock(blockX, blockY, blockZ)));
			}
		}
	}

	protected boolean canBreakBlock(Block block, int blockMeta) {
		if (block == ModBlocks.gneiss)
			return false;
		if (block.hasTileEntity(blockMeta))
			return false;
		return true;
	}

	private boolean isInSamePos() {
		return (int) posX == (int) lastTickPosX && (int) posY == (int) lastTickPosY && (int) posZ == (int) lastTickPosZ;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		// TODO Add a different attack style for each type
		return Attack(entity);
	}

	protected boolean Attack(Entity entity) {
		if (!worldObj.isRemote && canEntityBeSeen(entity)) {
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
				motionY = 0.4000000059604645D;
			}
			int knockback = 1;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getAttackStrength());
			entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * knockback * 0.5F, 0.4D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * knockback * 0.5F);
			worldObj.playSoundAtEntity(entity, "game.player.hurt.fall.big", 1.0F, 1.0F);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, worldObj.difficultySetting.ordinal() * 50, 0));
			return true;
		}
		return true;
	}

	public EntityThrowable getMissileType() {
		switch (getType()) {
			case 0:
				return new EntityGooBall(worldObj, this);
			case 1:
			case 2:
				return new EntityWebSling(worldObj, this);
			case 3:
				return new EntityPoisonJet(worldObj, this);
			default:
				return new EntityGooBall(worldObj, this);
		}
	}

	public void shootMissile(EntityLivingBase entity, float distance) {
		setRangeAttackTimer(0);
		if (canEntityBeSeen(entity)) {
			EntityThrowable missile = getMissileType();
			if (getType() == 1)
				((EntityWebSling) missile).setType((byte) 0);
			if (getType() == 2)
				((EntityWebSling) missile).setType((byte) 2);
			missile.rotationPitch -= -20.0F;
			double targetX = entity.posX + entity.motionX - posX;
			double targetY = entity.posY + entity.getEyeHeight() - 1.100000023841858D - posY;
			double targetZ = entity.posZ + entity.motionZ - posZ;
			float target = MathHelper.sqrt_double(targetX * targetX + targetZ * targetZ);
			missile.setThrowableHeading(targetX, targetY + target * 0.1F, targetZ, 0.75F, 8.0F);
			worldObj.spawnEntityInWorld(missile);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("type", getType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setType(nbt.getByte("type"));
	}

	public void setType(byte isType) {
		dataWatcher.updateObject(29, Byte.valueOf(isType));
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(getGolemHealth());
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(getAttackStrength());
	}

	public byte getType() {
		return dataWatcher.getWatchableObjectByte(29);
	}

	public void setRangeAttackTimer(int size) {
		dataWatcher.updateObject(20, Integer.valueOf(size));
	}

	public int getRangeAttackTimer() {
		return dataWatcher.getWatchableObjectInt(20);
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		data.writeByte(getType());
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		setType(data.readByte());
	}
}
