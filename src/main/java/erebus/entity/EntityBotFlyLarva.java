package erebus.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBotFlyLarva extends EntityMob implements IEntityAdditionalSpawnData {
	private static final DataParameter<Byte> PARASITE_COUNT = EntityDataManager.<Byte>createKey(EntityBotFlyLarva.class, DataSerializers.BYTE);
	private static final DataParameter<String> INFESTED_PLAYER = EntityDataManager.<String>createKey(EntityBotFlyLarva.class, DataSerializers.STRING);
	
	public EntityBotFlyLarva(World world) {
		super(world);
		setSize(0.5F, 0.2F);
		isImmuneToFire = true;
		tasks.addTask(0, new EntityAIWander(this, 0.3D));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(PARASITE_COUNT, new Byte((byte) 1));
		dataManager.register(INFESTED_PLAYER, "");
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6000000238418579D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean getIsInvulnerable() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return SoundEvents.ENTITY_SILVERFISH_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SILVERFISH_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15F, 1.0F);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (!getEntityWorld().isRemote)
			if (!player.isBeingRidden()) {
				startRiding(player, true);
				setPersistanceOnPlayer(player.getCommandSenderEntity().getName()); // may not work
			}
	}

	@Override
	public double getYOffset() {
		if (getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer)
			return getRidingEntity().height -2.25F;
		else if (getRidingEntity() != null)
			return getRidingEntity().height * 0.75D - 1.0D;
		else
			return super.getYOffset();
	}

	@Override
	public void onUpdate() {

		if (getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer) {
			setRotation(getRidingEntity().rotationYaw, 0F);
			if (!getEntityWorld().isRemote) {
				if (getParasiteCount() > 0 && rand.nextInt(180 / getParasiteCount()) == 0) {
					byte duration = (byte) (getParasiteCount() * 5);
					((EntityLivingBase) getRidingEntity()).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, duration * 20, 0));
					((EntityLivingBase) getRidingEntity()).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, duration * 20, 0));
					((EntityLivingBase) getRidingEntity()).addPotionEffect(new PotionEffect(MobEffects.HUNGER, duration * 20, 0));
				}
				if (getParasiteCount() == 0)
					setDead();
			}
		}
		super.onUpdate();
	}

	public void setABitDead() {
		getEntityWorld().playSound((EntityPlayer)null, getPosition(), getDeathSound(), SoundCategory.HOSTILE, 1.0F, 0.7F);
		if (getEntityWorld().isRemote)
			getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
		if (!getEntityWorld().isRemote)
			entityDropItem(new ItemStack(Items.SLIME_BALL), 0.0F);
		setParasiteCount((byte) (getParasiteCount() - 1));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.IN_WALL) || source.equals(DamageSource.DROWN))
			return false;
		return super.attackEntityFrom(source, damage);
	}

	public void setParasiteCount(byte parasites) {
		dataManager.set(PARASITE_COUNT, parasites);
	}

	public byte getParasiteCount() {
		return dataManager.get(PARASITE_COUNT);
	}

	private void setPersistanceOnPlayer(String entityName) {
		dataManager.set(INFESTED_PLAYER, entityName);
	}

	public String getPersistanceOnPlayer() {
		return dataManager.get(INFESTED_PLAYER);
	}

	public EntityPlayer playerName() {
		return getEntityWorld().getPlayerEntityByName(getPersistanceOnPlayer());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setParasiteCount(nbt.getByte("parasites"));
		setPersistanceOnPlayer(nbt.getString("playerName"));
		if ((EntityPlayer) playerName() != null) {
			EntityPlayer player = (EntityPlayer) playerName();
			if (!getEntityWorld().isRemote)
				if (!player.isBeingRidden()) {
					startRiding(player, true);
				}
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("parasites", getParasiteCount());
		nbt.setString("playerName", getPersistanceOnPlayer());
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeByte(getParasiteCount());
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		setParasiteCount(additionalData.readByte());
	}

}
