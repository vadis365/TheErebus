package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial.DATA;

public class EntityChameleonTick extends EntityMobBlock implements IEntityAdditionalSpawnData {

	public int blockID, blockMeta;
	public int animation;
	public boolean active = false;
	private final EntityAINearestAttackableTarget aiAttackTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	private final EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.65D, false);

	public EntityChameleonTick(World world) {
		super(world);
		setSize(1.0F, 1.0F);
		setBlock(Block.grass.blockID, 0);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
	}

	public void setBlock(int blockID, int blockMeta) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
/*
	@Override
	protected String getLivingSound() {
		return "";
	}

	@Override
	protected String getHurtSound() {
		return "";
	}

	@Override
	protected String getDeathSound() {
		return "";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
	}
*/
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, DATA.camoPowder.ordinal()), 0.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		int newBlockID = worldObj.getBlockId(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ));
		int newBlockMeta = worldObj.getBlockMetadata(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ));
		
		if (onGround && newBlockID != 0 && newBlockID != blockID && worldObj.doesBlockHaveSolidTopSurface(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ))) {
			blockID = newBlockID;
			blockMeta = newBlockMeta;
		}
		
		if (findPlayerToAttack() != null) {
			entityToAttack = findPlayerToAttack();
			if(!active)
				active = true;
			animation++;
			if(animation >=10)
				animation = 10;
			
		} else {
			entityToAttack = null;
			if(active)
				active = false;
			animation--;
			if(animation <=0)
				animation = 0;
		}
		
		if(!worldObj.isRemote && animation==9 && active)
			setAIs(true);
		
		if(!worldObj.isRemote && !active){
			stationaryEntity();
			if(animation==1)
				setAIs(false);
		}	
	}
	
	public void stationaryEntity() {
		posX = MathHelper.floor_double(posX) + 0.5;
		posY = MathHelper.floor_double(posY);
		posZ = MathHelper.floor_double(posZ) + 0.5;
		rotationYaw = prevRotationYaw = 0F;
		renderYawOffset = prevRenderYawOffset = 0F;
		
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY) - 1;
		int z = MathHelper.floor_double(posZ);
		
		if (worldObj.getBlockId(x, y, z) == 0)
			posY -= 1;
	}
	
	public void setAIs(boolean active) {
		if (!active) {
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.0D);
			tasks.removeTask(aiAttackOnCollide);
			tasks.removeTask(aiAttackTarget);
		}
		if (active) {
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.65D);
			tasks.addTask(1, aiAttackOnCollide);
			targetTasks.addTask(1, aiAttackTarget);	
		}
	}
	
	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer player = worldObj.getClosestVulnerablePlayerToEntity(this, 8.0D);
		return player;
	}
	
	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance > 0.0F && distance < 2.0F)
			attackEntityAsMob(entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return super.attackEntityAsMob(entity);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
		data.setInteger("blockID", blockID);
		data.setInteger("blockMeta", blockMeta);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		blockID = data.getInteger("blockID");
		blockMeta = data.getInteger("blockMeta");
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeInt(blockID);
		data.writeInt(blockMeta);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		blockID = data.readInt();
		blockMeta = data.readInt();
	}
}