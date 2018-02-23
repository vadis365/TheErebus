package erebus.entity;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class EntityBeetle extends EntityAnimal {
	private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityBeetle.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityBeetle.class, DataSerializers.BOOLEAN);

	int shagCount;

	public EntityBeetle(World world) {
		super(world);
		setSize(1.6F, 0.9F);
		setPathPriority(PathNodeType.WATER, -8F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SKIN_TYPE, rand.nextInt(51));
		dataManager.register(TAMED, false);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.6D));
		tasks.addTask(2, new EntityAIMate(this, 0.5D));
		tasks.addTask(3, new EntityAITempt(this, 0.5D, ModItems.TURNIP, false));
		tasks.addTask(5, new EntityAIWander(this, 0.5D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);;
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
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
		return 3;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public int getTotalArmorValue() {
		return 4;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BEETLE_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.BEETLE_HURT;
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
		if (shagCount > 0)
			shagCount--;
	}

	@Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (FluidUtil.getFluidHandler(stack) != null) {
			FluidStack fluidStack = getFluid(stack);
			if (fluidStack != null)
				return false;
			if (!stack.isEmpty() && stack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode) {
				stack.shrink(1);
				ItemStack newStack = FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid("beetle_juice"), Fluid.BUCKET_VOLUME));
				player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
				if (!player.inventory.addItemStackToInventory(newStack))
					player.dropItem(newStack, false);
				return true;
			}
		}
		if (!stack.isEmpty() && stack.getItem() == ModItems.TURNIP && !shagging()) {
			stack.shrink(1);
			setTame(true);
			shagCount = 600;
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), ModSounds.BEETLE_LARVA_MUNCH, SoundCategory.NEUTRAL, 1.0F, 0.75F);
			return true;
		}
		if (!stack.isEmpty() && stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BEETLE_TAMING_AMULET.ordinal()) {
			stack.shrink(1);
			setTame(true);
			return true;
		}

		return super.processInteract(player, hand);
	}

	@Nullable
	public FluidStack getFluid(final ItemStack container) {
		return FluidUtil.getFluidContained(container);
	}

	public boolean shagging() {
		return shagCount > 0;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (recentlyHit) {
			int chance = rand.nextInt(3) + rand.nextInt(1 + looting);
			int amount;
			for (amount = 0; amount < chance; ++amount)
				entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.PLATE_EXO.ordinal()), 0.0F);
		}
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return !stack.isEmpty() && stack.getItem() == ModItems.TURNIP;
	}

	public EntityBeetleLarva spawnBabyAnimal(EntityAgeable entityageable) {
		EntityBeetleLarva entityBeetleLarva = new EntityBeetleLarva(getEntityWorld());
		entityBeetleLarva.setLarvaType((byte) 1);
		return entityBeetleLarva;
	}

	@Override
	protected boolean canDespawn() {
		if (getIsTame())
			return false;
		else
			return true;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}

	public void setSkin(int skinType) {
		dataManager.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return dataManager.get(SKIN_TYPE).intValue();
	}

	public void setTame(boolean hasMated) {
		dataManager.set(TAMED, hasMated);
	}

	public boolean getIsTame() {
		return dataManager.get(TAMED);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("beetleSkin", getSkin());
		nbt.setBoolean("isTamed", getIsTame());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setSkin(nbt.getInteger("beetleSkin"));
		setTame(nbt.getBoolean("isTamed"));
	}

}
