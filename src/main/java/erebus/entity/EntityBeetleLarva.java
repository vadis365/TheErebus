package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import erebus.ModAchievements;
import erebus.ModItems;
import erebus.entity.ai.EntityAIEatWoodenItem;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;

public class EntityBeetleLarva extends EntityAnimal {

	public EntityAIEatWoodenItem aiEatWoodItem = new EntityAIEatWoodenItem(this, 0.48D, 10);
	private final EntityAIWander aiWander = new EntityAIWander(this, 0.48D);
	public boolean isEating;
	public boolean isSquashed;

	public EntityBeetleLarva(World world) {
		super(world);
		setSize(0.9F, 0.5F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiEatWoodItem);
		tasks.addTask(2, new EntityAITempt(this, 0.48D, Items.stick, false));
		tasks.addTask(3, aiWander);
		tasks.addTask(4, new EntityAILookIdle(this));
		tasks.addTask(5, new EntityAIPanic(this, 0.48D));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(28, new Float(1.0F));
		dataWatcher.addObject(29, new Byte((byte) 0));
	}

	public void setLarvaSize(float byteSize) {
		dataWatcher.updateObject(28, new Float(byteSize));
		setSize(0.9F * byteSize, 0.5F * byteSize);
	}

	public void setTame(byte isBred) {
		dataWatcher.updateObject(29, Byte.valueOf(isBred));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean isChild() {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		if (getTame() != 0 && getTame() != 4)
			return false;
		else
			return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0F);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
		return super.getCanSpawnHere();
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		byte duration = 0;
		if (!worldObj.isRemote && player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY && player.boundingBox.maxX >= boundingBox.minX && player.boundingBox.minX <= boundingBox.maxX && player.boundingBox.maxZ >= boundingBox.minZ && player.boundingBox.minZ <= boundingBox.maxZ && player.lastTickPosY > player.posY) {
			if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
				duration = 7;
			else if (worldObj.difficultySetting == EnumDifficulty.HARD)
				duration = 15;
			if (duration > 0)
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, duration * 20, 0));
			setisSquashed(true);
			setDead();
			onDeathUpdate();
			player.triggerAchievement(ModAchievements.beetleSpecial);
		}
	}

	@Override
	protected String getLivingSound() {
		String actionSound = "erebus:beetlelarvasound";
		if (isEating)
			actionSound = "erebus:beetlelarvamunch";
		return actionSound;
	}

	@Override
	protected String getHurtSound() {
		return "erebus:beetlelarvahurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected String getJumpedOnSound() {
		return "erebus:beetlelarvasplat";
	}

	protected static String getHasMunched() {
		return "erebus:beetlelarvamunch";
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		float i;
		if (worldObj.isRemote) {
			i = getLarvaSize();
			setSize(0.9F * i, 0.5F * i);
		}
		if (!worldObj.isRemote)
			if (getLarvaSize() > 1.8F) {
				setDead();
				spawnBeetle();
			}
	}

	private void spawnBeetle() {
		if (getTame() == 0) {
			EntityBeetle entityBeetle = new EntityBeetle(worldObj);
			entityBeetle.setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(entityBeetle);
		}
		if (getTame() == 1) {
			EntityBeetle entityBeetle = new EntityBeetle(worldObj);
			entityBeetle.setPosition(posX, posY, posZ);
			entityBeetle.setTame((byte) 1);
			worldObj.spawnEntityInWorld(entityBeetle);
		} else if (getTame() == 2) {
			EntityRhinoBeetle entityRhinoBeetle = new EntityRhinoBeetle(worldObj);
			entityRhinoBeetle.setPosition(posX, posY, posZ);
			entityRhinoBeetle.setTameState((byte) 1);
			worldObj.spawnEntityInWorld(entityRhinoBeetle);
		} else if (getTame() == 3) {
			EntityTitanBeetle entityTitanBeetle = new EntityTitanBeetle(worldObj);
			entityTitanBeetle.setPosition(posX, posY, posZ);
			entityTitanBeetle.setTameState((byte) 1);
			worldObj.spawnEntityInWorld(entityTitanBeetle);
		} else if (getTame() == 4) {
			EntityBombardierBeetle entityBombardierBeetle = new EntityBombardierBeetle(worldObj);
			entityBombardierBeetle.setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(entityBombardierBeetle);
		}
	}

	@Override
	public void onDeathUpdate() {
		super.onDeathUpdate();
		if (isSquashed) {
			PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.BEETLE_LARVA_SQUISH));
			worldObj.playSoundEffect(posX, posY, posZ, getJumpedOnSound(), 1.0F, 0.5F);
			worldObj.playSoundEffect(posX, posY, posZ, getDeathSound(), 1.0F, 0.7F);
			if (!worldObj.isRemote) {
				if (rand.nextInt(200) == 0)
					entityDropItem(new ItemStack(Items.diamond), 0.0F);
				entityDropItem(new ItemStack(Items.slime_ball), 0.0F);
			}
		}
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (isBurning())
			entityDropItem(new ItemStack(ModItems.food, 1, 1), 0.0F);
		else
			entityDropItem(new ItemStack(ModItems.food, 1, 0), 0.0F);
	}

	private boolean isStick(ItemStack stack) {
		int stick = OreDictionary.getOreID("stickWood");
		for (int id : OreDictionary.getOreIDs(stack))
			if (id == stick)
				return true;
		return false;
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();
		if (!worldObj.isRemote && isStick(stack) && getTame() != 4) {
			setLarvaSize(getLarvaSize() + 0.1F);
			stack.stackSize--;
			return true;
		}
		return super.interact(player);
	}

	public void setIsEating(boolean eating) {
		isEating = eating;
	}

	public void setisSquashed(boolean squashed) {
		isSquashed = squashed;
	}

	public void setMoveTasks(boolean task) {
		if (!task)
			tasks.removeTask(aiWander);
		else
			tasks.addTask(2, aiWander);
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setFloat("size", getLarvaSize());
		nbt.setByte("tame", getTame());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setLarvaSize(nbt.getFloat("size"));
		setTame(nbt.getByte("tame"));
	}

	public float getLarvaSize() {
		return dataWatcher.getWatchableObjectFloat(28);
	}

	public byte getTame() {
		return dataWatcher.getWatchableObjectByte(29);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return null;
	}

	@Override
	public void onDeath(DamageSource dmgSrc) {
		super.onDeath(dmgSrc);

		if (dmgSrc instanceof EntityDamageSource) {
			Entity killer = ((EntityDamageSource) dmgSrc).getSourceOfDamage();
			if (killer instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) killer;
				player.triggerAchievement(ModAchievements.beetle);

				for (EntityItem entityDrop : capturedDrops)
					if (entityDrop != null) {
						ItemStack stack = entityDrop.getEntityItem();
						if (stack != null && stack.getItem() == Items.diamond) {
							player.triggerAchievement(ModAchievements.diamond);
							break;
						}
					}
			}
		}
	}
}