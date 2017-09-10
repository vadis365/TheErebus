package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityHoneyPotAnt extends EntityTameable {

	protected static final DataParameter<Float> HONEY_BELLY = EntityDataManager.<Float>createKey(EntityHoneyPotAnt.class, DataSerializers.FLOAT);

	public EntityHoneyPotAnt(World world) {
		super(world);
		setSize(0.9F, 0.4F);
		setPathPriority(PathNodeType.WATER, -8F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(HONEY_BELLY, new Float(0.0F));
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		tasks.addTask(4, new EntityAIPanic(this, 0.7F));
		tasks.addTask(5, new EntityAILookIdle(this));
		tasks.addTask(6, new EntityAITempt(this, 0.6D, ModItems.ANT_TAMING_AMULET, false));
		tasks.addTask(7, new EntityAITempt(this, 0.6D, Items.SUGAR, false));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
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
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.FIRE_ANT_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.FIRE_ANT_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (getEntityWorld().isRemote)
			setSize(0.9F + getHoneyBelly(), 0.4F);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.isEmpty())
			return super.processInteract(player, hand);

		Item item = stack.getItem();

		if (item == Items.SUGAR) {
			if (isTamed() && getHoneyBelly() < 0.8F) {
				if (!getEntityWorld().isRemote) {
					setHoneyBelly(getHoneyBelly() + 0.1F);
					if (!player.capabilities.isCreativeMode)
						stack.shrink(1);
				}
				return true;
			}
		} else if (item == ModItems.NECTAR_COLLECTOR) {
			if (getHoneyBelly() > 0 && isTamed()) {
				if (!getEntityWorld().isRemote) {
					entityDropItem(new ItemStack(ModItems.MATERIALS, (int) (getHoneyBelly() * 10), ItemMaterials.EnumErebusMaterialsType.NECTAR.ordinal()), 0.0F);
					stack.damageItem(1, player);
					setHoneyBelly(0);
				}
				return true;
			}
		} else if (item == ModItems.ANT_TAMING_AMULET)
			if (!isTamed()) {
				player.swingArm(hand);
				setTamed(true);
				playTameEffect(true);
				return true;
			}

		return super.processInteract(player, hand);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (isTamed()) {
			if (getHoneyBelly() > 0)
				entityDropItem(new ItemStack(ModItems.MATERIALS, (int) (getHoneyBelly() * 10), ItemMaterials.EnumErebusMaterialsType.NECTAR.ordinal()), 0.0F);
		} else
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, ItemMaterials.EnumErebusMaterialsType.NECTAR.ordinal()), 0.0F);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setFloat("size", getHoneyBelly());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setHoneyBelly(nbt.getFloat("size"));
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (HONEY_BELLY.equals(key)) {
			float size = getHoneyBelly();
			setNewSize(0.9F + size, 0.4F);
		}
		super.notifyDataManagerChange(key);
	}

	protected void setNewSize(float width, float height) {
		if (this.width != width || this.height != height) {
			float prevWidth = this.width;
			this.width = width;
			this.height = height;
			AxisAlignedBB axisalignedbb = getEntityBoundingBox();
			setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) width, axisalignedbb.minY + (double) height, axisalignedbb.minZ + (double) width));

			if (width > prevWidth && !firstUpdate && !getEntityWorld().isRemote) {
				move(MoverType.SELF, (double) (prevWidth - width), 0.0D, (double) (prevWidth - width));
			}
		}
	}

	public void setHoneyBelly(float scaledSize) {
		dataManager.set(HONEY_BELLY, scaledSize);
	}

	public float getHoneyBelly() {
		return dataManager.get(HONEY_BELLY);
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