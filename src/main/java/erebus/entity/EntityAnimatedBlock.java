package erebus.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIBlockFollowOwner;
import erebus.proxy.CommonProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimatedBlock extends EntityMobBlock implements IEntityAdditionalSpawnData {
	protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimatedBlock.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public Block blockID;
	public int blockMeta;
	private int lastX = 0, lastY = 0, lastZ = 0;
	public EntityAIWander aiWander;
	public EntityAINearestAttackableTarget aiAttackNearestTarget;
	public EntityAIAttackMelee aiAttackOnCollide;

	public EntityAnimatedBlock(World world) {
		super(world);
		setSize(1.0F, 1.5F);
		setBlock(Blocks.STONE, 0);
		experienceValue = 0;
	}

	public void setBlock(Block blockID, int blockMeta) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
		setCanBeTempted();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(OWNER_UNIQUE_ID, Optional.<UUID>absent());
	}

	@Override
	protected void initEntityAI() {
		aiWander = new EntityAIWander(this, 0.5D);
		aiAttackNearestTarget = new EntityAINearestAttackableTarget(this, EntityMob.class, true);
		aiAttackOnCollide = new EntityAIAttackMelee(this, 0.5D, false);
		
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, aiAttackOnCollide);
		tasks.addTask(3, aiWander);
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, aiAttackNearestTarget);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 10.0D : 10D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	protected Item getDropItem() {
		return null;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote && isDead)
			Utils.dropStack(getEntityWorld(), getPosition(), new ItemStack(blockID, 1, blockMeta));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (getEntityWorld().isRemote)
			if (getEntityWorld().getSunBrightness(1.0F) < 0.5F)
				lightUp(getEntityWorld(), getPosition());
			else
				switchOff();
	}

	@SideOnly(Side.CLIENT)
	private void lightUp(World world, BlockPos pos) {
		world.setLightFor(EnumSkyBlock.BLOCK, pos, 9);
		for (int i = -2; i < 2; i++)
			for (int j = -2; j < 2; j++)
				for (int k = -2; k < 2; k++)
					if (pos.getX() + i != lastX || pos.getY() + j != lastY || pos.getZ() + k != lastZ || isDead) {
						world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lastX + i, lastY + j, lastZ + k));
						lastX = pos.getX();
						lastY = pos.getY();
						lastZ = pos.getZ();
					}
	}

	@SideOnly(Side.CLIENT)
	private void switchOff() {
		getEntityWorld().checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lastX, lastY, lastZ));
		getEntityWorld().checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ)));
	}

	@Override
	public void setDead() {
		super.setDead();
		if (getEntityWorld().isRemote)
			switchOff();
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack is = player.inventory.getCurrentItem();
		if (!getEntityWorld().isRemote && is != null && is.getItem() == ModItems.wandOfAnimation) {
			setDead();
			getEntityWorld().setBlockState(getPosition(), blockID.getStateFromMeta(blockMeta), 3);
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), ModSounds.ALTAR_OFFERING, SoundCategory.NEUTRAL, 0.2F, 1.0F);
			return true;
		} else if (blockID == ModBlocks.PETRIFIED_CRAFTING_TABLE && is == null) {
			player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.PETRIFIED_CRAFT.ordinal(), player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
			return true;
		} else
			return false;
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance > 0.0F && distance < 2.0F)
			attackEntityAsMob(entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		boolean atk = false;
		if (super.attackEntityAsMob(entity))
			if (entity instanceof EntityMob)
				atk = true;
			else
				atk = false;
		return atk;
	}

	public void setCanBeTempted() {
		if (blockID == ModBlocks.PETRIFIED_CRAFTING_TABLE)
			tasks.addTask(1, new EntityAIBlockFollowOwner(this, 1.0D, 10.0F, 2.0F));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("blockID", Block.getIdFromBlock(blockID));
		nbt.setInteger("blockMeta", blockMeta);
		if (this.getOwnerId() == null)
			nbt.setString("OwnerUUID", "");
		else
			nbt.setString("OwnerUUID", this.getOwnerId().toString());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		blockID = Block.getBlockById(nbt.getInteger("blockID"));
		blockMeta = nbt.getInteger("blockMeta");
		String s;
		if (nbt.hasKey("OwnerUUID", 8))
			s = nbt.getString("OwnerUUID");
		else {
			String s1 = nbt.getString("Owner");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty()) {
			try {
				this.setOwnerId(UUID.fromString(s));
			} catch (Throwable e) {
			}
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(Block.getIdFromBlock(blockID));
		buffer.writeInt(blockMeta);
	}

	@Override
	public void readSpawnData(ByteBuf buffer) {
		blockID = Block.getBlockById(buffer.readInt());
		blockMeta = buffer.readInt();
		setCanBeTempted();
	}

	@Nullable
	public UUID getOwnerId() {
		return (UUID) ((Optional) this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
	}

	public void setOwnerId(@Nullable UUID uuid) {
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uuid));
	}

	@Nullable
	public EntityLivingBase getOwner() {
		try {
			UUID uuid = this.getOwnerId();
			return uuid == null ? null : this.getEntityWorld().getPlayerEntityByUUID(uuid);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public boolean isOwner(EntityLivingBase entityIn) {
		return entityIn == this.getOwner();
	}

	public boolean belongsTo(EntityLivingBase entity) {
		return entity == getOwner();
	}
}