package erebus.entity;

import java.util.UUID;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.core.proxy.CommonProxy;
import erebus.entity.ai.EntityAIBlockFollowOwner;
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
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityAnimatedBlock extends EntityMobBlock implements IEntityAdditionalSpawnData {

	public Block blockID;
	public int blockMeta;
	private int lastX = 0, lastY = 0, lastZ = 0;
	protected final EntityAIWander aiWander = new EntityAIWander(this, 0.5D);
	protected final EntityAINearestAttackableTarget aiAttackNearestTarget = new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true);
	protected final EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityMob.class, 0.5D, false);

	public EntityAnimatedBlock(World world) {
		super(world);
		setSize(1.0F, 1.5F);
		setBlock(Blocks.stone, 0);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, aiAttackOnCollide);
		tasks.addTask(3, aiWander);
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, aiAttackNearestTarget);
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
		dataWatcher.addObject(16, new Byte((byte) 0));
		dataWatcher.addObject(17, new Byte((byte) 0));
		dataWatcher.addObject(18, "");
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
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
	protected void func_145780_a(int x, int y, int z, Block block) {
		worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem() {
		return null;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && isDead)
			Utils.dropStack(worldObj, (int) posX, (int) posY, (int) posZ, new ItemStack(blockID, 1, blockMeta));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (worldObj.isRemote)
			if (worldObj.getSunBrightness(1.0F) < 0.5F)
				lightUp(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
			else
				switchOff();
	}

	@SideOnly(Side.CLIENT)
	private void lightUp(World world, int x, int y, int z) {
		world.setLightValue(EnumSkyBlock.Block, x, y, z, blockID.getLightValue());
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				for (int k = -1; k < 2; k++)
					if (x + i != lastX || y + j != lastY || z + k != lastZ || isDead) {
						world.updateLightByType(EnumSkyBlock.Block, lastX + i, lastY + j, lastZ + k);
						lastX = x;
						lastY = y;
						lastZ = z;
					}
	}

	@SideOnly(Side.CLIENT)
	private void switchOff() {
		worldObj.updateLightByType(EnumSkyBlock.Block, lastX, lastY, lastZ);
		worldObj.updateLightByType(EnumSkyBlock.Block, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
	}

	@Override
	public void setDead() {
		super.setDead();
		if (worldObj.isRemote)
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
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		if (!worldObj.isRemote && is != null && is.getItem() == ModItems.wandOfAnimation) {
			setDead();
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), blockID, blockMeta, 3);
			worldObj.playSoundEffect(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), "erebus:altaroffering", 0.2F, 1.0F);
			return true;
		} else if (blockID == ModBlocks.petrifiedCraftingTable && is == null) {
			player.openGui(Erebus.instance, CommonProxy.GuiID.PETRIFIED_CRAFT.ordinal(), player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
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
		if (blockID == ModBlocks.petrifiedCraftingTable)
			tasks.addTask(1, new EntityAIBlockFollowOwner(this, 1.0D, 10.0F, 2.0F));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
		data.setInteger("blockID", Block.getIdFromBlock(blockID));
		data.setInteger("blockMeta", blockMeta);
		if (getOwnerName() == null)
			data.setString("OwnerUUID", "");
		else
			data.setString("OwnerUUID", getOwnerName());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		blockID = Block.getBlockById(data.getInteger("blockID"));
		blockMeta = data.getInteger("blockMeta");
		setCanBeTempted();

		String s = "";

		if (data.hasKey("OwnerUUID", 8))
			s = data.getString("OwnerUUID");
		else {
			String s1 = data.getString("Owner");
			s = PreYggdrasilConverter.func_152719_a(s1);
		}

		if (s.length() > 0)
			setOwnerName(s);
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

	public String getOwnerName() {
		return dataWatcher.getWatchableObjectString(18);
	}

	public void setOwnerName(String name) {
		dataWatcher.updateObject(18, name);
	}

	public EntityLivingBase getOwner() {
		try {
			UUID uuid = UUID.fromString(getOwnerName());
			return uuid == null ? null : worldObj.func_152378_a(uuid);
		} catch (IllegalArgumentException illegalargumentexception) {
			return null;
		}
	}

	public boolean belongsTo(EntityLivingBase entity) {
		return entity == getOwner();
	}
}