package erebus.entity;

import erebus.ModItems;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityHoneyPotAnt extends EntityTameable {

	public EntityHoneyPotAnt(World world) {
		super(world);
		setSize(0.9F, 0.4F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		tasks.addTask(4, new EntityAIPanic(this, 0.7F));
		tasks.addTask(5, new EntityAILookIdle(this));
		tasks.addTask(6, new EntityAITempt(this, 0.6D, ModItems.antTamingAmulet, false));
		tasks.addTask(7, new EntityAITempt(this, 0.6D, Items.sugar, false));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(28, new Float(0.0F));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		if (getBrightness(1.0F) >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	protected boolean canDespawn() {
		return !isTamed();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:fireantsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:fireanthurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (worldObj.isRemote)
			setSize(0.9F + getHoneyBelly(), 0.4F);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();
		if (stack == null)
			return super.interact(player);

		Item item = stack.getItem();

		if (item == Items.sugar) {
			if (isTamed() && getHoneyBelly() < 0.8F) {
				if (!worldObj.isRemote) {
					setHoneyBelly(getHoneyBelly() + 0.1F);
					if (!player.capabilities.isCreativeMode)
						stack.stackSize--;
				}
				return true;
			}
		} else if (item == ModItems.nectarCollector) {
			if (getHoneyBelly() > 0 && isTamed()) {
				if (!worldObj.isRemote) {
					entityDropItem(ItemMaterials.DATA.nectar.makeStack((int) (getHoneyBelly() * 10)), 0.0F);
					stack.damageItem(1, player);
					setHoneyBelly(0);
				}
				return true;
			}
		} else if (item == ModItems.antTamingAmulet)
			if (!isTamed()) {
				player.swingItem();
				setTamed(true);
				playTameEffect(true);
				return true;
			}

		return super.interact(player);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (isTamed()) {
			if (getHoneyBelly() > 0)
				entityDropItem(ItemMaterials.DATA.nectar.makeStack((int) (getHoneyBelly() * 10)), 0.0F);
		} else
			entityDropItem(ItemMaterials.DATA.nectar.makeStack(1), 0.0F);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setFloat("size", getHoneyBelly());
		nbt.setByte("tamed", (byte) (isTamed() ? 1 : 0));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setHoneyBelly(nbt.getFloat("size"));
		setTamed(nbt.getByte("tamed") == (byte) 1);
	}

	public void setHoneyBelly(float scaledSize) {
		dataWatcher.updateObject(28, scaledSize);
		setSize(0.9F + scaledSize, 0.4F);
	}

	@Override
	public void setTamed(boolean tamed) {
		dataWatcher.updateObject(16, (byte) (tamed ? 1 : 0));
	}

	public float getHoneyBelly() {
		return dataWatcher.getWatchableObjectFloat(28);
	}

	@Override
	public boolean isTamed() {
		return dataWatcher.getWatchableObjectByte(16) != 0;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable baby) {
		return null;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}
}