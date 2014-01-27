package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemErebusMaterial;

public class EntityAntlion extends EntityMob implements IEntityAdditionalSpawnData {
	private boolean areAttributesSetup = false;

	public EntityAntlion(World world) {
		super(world);
		isImmuneToFire = true;
		experienceValue = 17;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.7D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.7D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
		setIsBoss(false);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		areAttributesSetup = true;
		updateBossAttributes();
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public int getTotalArmorValue() {
		if (isBoss())
			return 15;
		else
			return 8;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	/*
	 * @Override protected String getLivingSound() { return
	 * "erebus:AntlionSound"; }
	 * 
	 * @Override protected String getHurtSound() { return "erebus:Antlionhurt";
	 * }
	 */
	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected int getDropItemId() {
		return Block.sand.blockID;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = rand.nextInt(4) + rand.nextInt(1 + par2);
		int var4;
		for (var4 = 0; var4 < var3; ++var4)
			entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate), 0.0F);
	}

	@Override
	protected void dropRareDrop(int par1) {
		if (isBoss())
			dropItem(ModBlocks.ghostSand.blockID, 4);
	}

	@Override
	public boolean getCanSpawnHere() {
		return isOnSand() && super.getCanSpawnHere();
	}

	public boolean isOnSand() {
		return worldObj.getBlockId(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)) == Block.sand.blockID;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (findPlayerToAttack() != null)
			entityToAttack = findPlayerToAttack();
		else
			entityToAttack = null;
		if (isBoss()) { // a hack to keep boss antlions alive even on peaceful
			int difficulty = worldObj.difficultySetting;
			if (difficulty == 0)
				worldObj.difficultySetting = 1;

			worldObj.difficultySetting = difficulty;
		} else
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
		if (!worldObj.isRemote && getEntityToAttack() == null && isOnSand() && !isBoss())
			yOffset = -1;
		else
			yOffset = 0;
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown))
			return false;
		return super.attackEntityFrom(source, damage);
	}

	@Override
	public void despawnEntity() {
		if (!isBoss())
			super.despawnEntity();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("AntlionType", isBoss());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setIsBoss(nbt.getBoolean("AntlionType"));
	}

	public void setIsBoss(boolean isBoss) {
		dataWatcher.updateObject(16, Byte.valueOf((byte) (isBoss ? 1 : 0)));
		worldObj.setEntityState(this, (byte) 16);

		if (!isBoss)
			setSize(2.0F, 0.9F);
		else
			setSize(2.75F, 1.2F);

		if (areAttributesSetup)
			updateBossAttributes();
	}

	public void updateBossAttributes() {
		if (isBoss()) {
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(60.0D);
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D);
			getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
			getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.75D);
		} else {
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D);
			getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
			getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.5D);
		}
	}

	public boolean isBoss() {
		return dataWatcher.getWatchableObjectByte(16) == 1;
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeBoolean(isBoss());
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		setIsBoss(data.readBoolean());
	}
}