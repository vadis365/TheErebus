package erebus.entity;

import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCentipede extends EntityMob {

	public EntityCentipede(World world) {
		super(world);
		setSize(1.0F, 0.8F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(30, new Integer(rand.nextInt(3)));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(getAttackStrength());
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	public double getAttackStrength() {
		switch (worldObj.difficultySetting) {
			default:
				return 2.0D;
			case EASY:
				return 2.0D;
			case NORMAL:
				return 2.0D;
			case HARD:
				return 4.0D;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 8;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:centipedesound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:centipedehurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) { // playStepSound
		playSound("erebus:centipedewalk", 0.5F, 1.0F);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount) {
			entityDropItem(ItemMaterials.DATA.bioVelocity.makeStack(), 0.0F);
			entityDropItem(ItemMaterials.DATA.poisonGland.makeStack(), 0.0F);
		}
	}

	@Override
	protected void dropRareDrop(int looting) {
		entityDropItem(ItemMaterials.DATA.supernaturalvelocity.makeStack(), 0.0F);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY) {
			byte duration = 0;
			if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
				duration = 7;
			else if (worldObj.difficultySetting == EnumDifficulty.HARD)
				duration = 15;
			if (duration > 0)
				player.addPotionEffect(new PotionEffect(Potion.poison.id, duration * 20, 0));
		}
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
