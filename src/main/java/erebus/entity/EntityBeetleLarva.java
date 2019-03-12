package erebus.entity;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIEatWoodenItem;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class EntityBeetleLarva extends EntityAnimal {
	private static final DataParameter<Byte> LARVA_TYPE = EntityDataManager.<Byte>createKey(EntityBeetleLarva.class, DataSerializers.BYTE);
	private static final DataParameter<Float> LARVA_SIZE = EntityDataManager.<Float>createKey(EntityBeetleLarva.class, DataSerializers.FLOAT);
	private static final DataParameter<Boolean> IS_SQUASHED = EntityDataManager.<Boolean>createKey(EntityBeetleLarva.class, DataSerializers.BOOLEAN);
	public boolean isEating;

	public EntityBeetleLarva(World world) {
		super(world);
		setSize(0.9F, 0.5F);
		setPathPriority(PathNodeType.WATER, -8F);
		stepHeight = 1F;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(LARVA_SIZE, 1F);
		dataManager.register(LARVA_TYPE, new Byte((byte) 0));
		dataManager.register(IS_SQUASHED, false);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWander(this, 0.48D));
		tasks.addTask(2, new EntityAIEatWoodenItem(this, 0.48D, 10));
		tasks.addTask(3, new EntityAITempt(this, 0.48D, Items.STICK, false));
		tasks.addTask(4, new EntityAILookIdle(this));
		tasks.addTask(5, new EntityAIPanic(this, 0.48D));
	}

	@Override
	public boolean isChild() {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		if (getLarvaType() != 0 && getLarvaType() != 4)
			return false;
		else
			return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 8D : 8D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		byte duration = 0;
		if(!getEntityWorld().isRemote && player.getEntityBoundingBox().maxY >= getEntityBoundingBox().minY && player.getEntityBoundingBox().minY <= getEntityBoundingBox().maxY && player.getEntityBoundingBox().maxX >= getEntityBoundingBox().minX && player.getEntityBoundingBox().minX <= getEntityBoundingBox().maxX && player.getEntityBoundingBox().maxZ >= getEntityBoundingBox().minZ && player.getEntityBoundingBox().minZ <= getEntityBoundingBox().maxZ && player.lastTickPosY > player.posY) {
			if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
				duration = 7;
			else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
				duration = 15;
			if (duration > 0)
				player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration * 20, 0));
			setisSquashed(true);
			setDead();
			onDeathUpdate();
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		SoundEvent actionSound = ModSounds.BEETLE_LARVA_SOUND;
		return actionSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.BEETLE_LARVA_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	protected SoundEvent getJumpedOnSound() {
		return ModSounds.BEETLE_LARVA_SPLAT;
	}

	protected SoundEvent getHasMunched() {
		return ModSounds.BEETLE_LARVA_MUNCH;
	}
	
	@Override
	public void updateAITasks() {
		super.updateAITasks();
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		float size;
		if (getEntityWorld().isRemote) {
			size = getLarvaSize();
			setSize(0.9F * size, 0.5F * size);
		}
		if (!getEntityWorld().isRemote)
			if (getLarvaSize() > 1.8F) {
				setDead();
				spawnBeetle();
			}
	}

	private void spawnBeetle() {
		if (getLarvaType() == 0) {
			EntityBeetle entityBeetle = new EntityBeetle(getEntityWorld());
			entityBeetle.setPosition(posX, posY, posZ);
			getEntityWorld().spawnEntity(entityBeetle);
		}
		if (getLarvaType() == 1) {
			EntityBeetle entityBeetle = new EntityBeetle(getEntityWorld());
			entityBeetle.setPosition(posX, posY, posZ);
			entityBeetle.setTame(true);
			getEntityWorld().spawnEntity(entityBeetle);
		} else if (getLarvaType() == 2) {
			EntityRhinoBeetle entityRhinoBeetle = new EntityRhinoBeetle(getEntityWorld());
			entityRhinoBeetle.setPosition(posX, posY, posZ);
			entityRhinoBeetle.setTameState((byte) 1);
			getEntityWorld().spawnEntity(entityRhinoBeetle);
		} else if (getLarvaType() == 3) {
			EntityTitanBeetle entityTitanBeetle = new EntityTitanBeetle(getEntityWorld());
			entityTitanBeetle.setPosition(posX, posY, posZ);
			entityTitanBeetle.setTameState((byte) 1);
			getEntityWorld().spawnEntity(entityTitanBeetle);
		} else if (getLarvaType() == 4) {
			EntityBombardierBeetle entityBombardierBeetle = new EntityBombardierBeetle(getEntityWorld());
			entityBombardierBeetle.setPosition(posX, posY, posZ);
			getEntityWorld().spawnEntity(entityBombardierBeetle);
		} else if (getLarvaType() == 5) {
			EntityStagBeetle entityStagBeetle = new EntityStagBeetle(getEntityWorld());
			entityStagBeetle.setPosition(posX, posY, posZ);
			entityStagBeetle.setTameState((byte) 1);
			getEntityWorld().spawnEntity(entityStagBeetle);
		}
	}

	@Override
	public void onDeathUpdate() {
		super.onDeathUpdate();
		if (getIsSquashed()) {
			if (!getEntityWorld().isRemote)
				Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.BEETLE_LARVA_SQUISH, (float) posX, (float)posY, (float)posZ));
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), getJumpedOnSound(), SoundCategory.NEUTRAL, 1.0F, 0.5F);
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), getDeathSound(), SoundCategory.NEUTRAL, 1.0F, 0.7F);
			if (!getEntityWorld().isRemote) {
				if (rand.nextInt(200) == 0)
					entityDropItem(new ItemStack(Items.DIAMOND), 0.0F);
				entityDropItem(new ItemStack(Items.SLIME_BALL), 0.0F);
			}
		}
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (recentlyHit) {
			if (isBurning())
				entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1, 1), 0.0F);
			else
				entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1, 0), 0.0F);
		}
	}

	private boolean isStick(ItemStack stack) {
		int stick = OreDictionary.getOreID("stickWood");
		for (int id : OreDictionary.getOreIDs(stack))
			if (id == stick)
				return true;
		return false;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.inventory.getCurrentItem();
		if (!getEntityWorld().isRemote && isStick(stack) && getLarvaType() != 4) {
			setLarvaSize(getLarvaSize() + 0.1F);
			stack.shrink(1);
			return true;
		}
		return super.processInteract(player, hand);
	}

	public void setIsEating(boolean eating) {
		isEating = eating;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setFloat("larvaSize", getLarvaSize());
		nbt.setByte("larvaType", getLarvaType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setLarvaSize(nbt.getFloat("larvaSize"));
		setLarvaType(nbt.getByte("larvaType"));
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (LARVA_SIZE.equals(key)) {
			float size = getLarvaSize();
			setNewSize( 0.9F * size, 0.5F * size);
		}
		super.notifyDataManagerChange(key);
	}

	protected void setNewSize(float width, float height) {
		if (this.width != width || this.height != height) {
			float f = this.width;
			this.width = width;
			this.height = height;
			AxisAlignedBB axisalignedbb = getEntityBoundingBox();
			setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) width, axisalignedbb.minY + (double) height, axisalignedbb.minZ + (double) width));

			if (width > f && !firstUpdate && !getEntityWorld().isRemote) {
				move(MoverType.SELF, (double) (f - width), 0.0D, (double) (f - width));
			}
		}
	}

	public void setLarvaSize(float size) {
		dataManager.set(LARVA_SIZE, size);
	}

	public void setLarvaType(byte isTamed) {
		dataManager.set(LARVA_TYPE, isTamed);
	}

	public void setisSquashed(boolean squashed) {
		dataManager.set(IS_SQUASHED, squashed);
	}

	private boolean getIsSquashed() {
		return dataManager.get(IS_SQUASHED);
	}

	public float getLarvaSize() {
		return dataManager.get(LARVA_SIZE);
	}

	public byte getLarvaType() {
		return dataManager.get(LARVA_TYPE);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return null;
	}
}