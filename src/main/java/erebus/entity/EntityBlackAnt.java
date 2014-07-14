package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIEatMushroom;

public class EntityBlackAnt extends EntityMob {
	private EntityAINearestAttackableTarget aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.4D, false);
	private EntityAIPanic aiPanic = new EntityAIPanic(this, 0.8D);
	private EntityAIEatMushroom aiEatMushroom = new EntityAIEatMushroom(this, 0.6D, 5);
	public boolean setAttributes;
	public boolean isEating;
	public EntityBlackAnt(World world) {
		super(world);
		stepHeight = 1.0F;
		setAttributes = false;
		setSize(1.3F, 0.55F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiEatMushroom);
		tasks.addTask(2, aiPanic);
		tasks.addTask(3, new EntityAILookIdle(this));
		tasks.addTask(4, new EntityAIWander(this, 0.6D));
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, new Byte((byte) 0));
		dataWatcher.addObject(21, new Byte((byte) 0));
		dataWatcher.addObject(22, new Byte((byte) 0));
		dataWatcher.addObject(23, new Byte((byte) 0));
		dataWatcher.addObject(24, new Byte((byte) 0));
		dataWatcher.addObject(25, new Byte((byte) 0));
		dataWatcher.addObject(26, new Byte((byte) 0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}
	
	@Override
	public int getTotalArmorValue() {
		if (getCanZombie()==7)
			return 10;
		return 0;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
    protected boolean canDespawn() {
        return true;
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
	protected float getSoundPitch() {
		if (getCanZombie()==7)
			return 0.5F;
		return 1F;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}
	
	public void setIsEating(boolean isEating) {
		this.isEating = isEating;
	}
	
	public void setShroomsMunched(Block block, int meta, byte mushrooms) {
		
		if (block != null && block == ModBlocks.erebusPlantSmall && meta <= 4) {
			Utils.dropStack(worldObj, (int)posX, (int)posY, (int)posZ, new ItemStack(block, 1, meta));
			
			switch (meta) {
			case 0:
				dataWatcher.updateObject(20, Byte.valueOf(mushrooms));
				break;
			case 1:
				dataWatcher.updateObject(21, Byte.valueOf(mushrooms));
				break;
			case 2:
				dataWatcher.updateObject(22, Byte.valueOf(mushrooms));
				break;
			case 3:
				dataWatcher.updateObject(23, Byte.valueOf(mushrooms));
				break;
			case 4:
				dataWatcher.updateObject(24, Byte.valueOf(mushrooms));
				break;
			}
		}
		
		if (block != null && block == Blocks.red_mushroom)
			dataWatcher.updateObject(25, Byte.valueOf(mushrooms));

		if (block != null && block == Blocks.brown_mushroom)
			dataWatcher.updateObject(26, Byte.valueOf(mushrooms));
	}
	
	@Override
	public void onDeathUpdate() {
		super.onDeathUpdate();
		if (!worldObj.isRemote && isDead) {
			if(dataWatcher.getWatchableObjectByte(20) == 1)
				entityDropItem(new ItemStack(ModBlocks.erebusPlantSmall, 1, 0), 0.0F);
			if(dataWatcher.getWatchableObjectByte(21) == 1)
				entityDropItem(new ItemStack(ModBlocks.erebusPlantSmall, 1, 1), 0.0F);
			if(dataWatcher.getWatchableObjectByte(22) == 1)
				entityDropItem(new ItemStack(ModBlocks.erebusPlantSmall, 1, 2), 0.0F);
			if(dataWatcher.getWatchableObjectByte(23) == 1)
				entityDropItem(new ItemStack(ModBlocks.erebusPlantSmall, 1, 3), 0.0F);
			if(dataWatcher.getWatchableObjectByte(24) == 1)
				entityDropItem(new ItemStack(ModBlocks.erebusPlantSmall, 1, 4), 0.0F);
			if(dataWatcher.getWatchableObjectByte(25) == 1)
				entityDropItem(new ItemStack(Blocks.red_mushroom, 1, 0), 0.0F);
			if(dataWatcher.getWatchableObjectByte(26) == 1)
				entityDropItem(new ItemStack(Blocks.brown_mushroom, 1, 0), 0.0F);	
		}
	}
	
	public int getCanZombie() {
		int mushCount =
				dataWatcher.getWatchableObjectByte(20)
				+dataWatcher.getWatchableObjectByte(21)
				+dataWatcher.getWatchableObjectByte(22)
				+dataWatcher.getWatchableObjectByte(23)
				+dataWatcher.getWatchableObjectByte(24)
				+dataWatcher.getWatchableObjectByte(25)
				+dataWatcher.getWatchableObjectByte(26);
		return mushCount;	
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(worldObj.isRemote && getCanZombie() >= 6)
			worldObj.spawnParticle("reddust", posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());
		if(worldObj.isRemote && getCanZombie() == 7 && !setAttributes)
			setSize(2F, 1.2F);
		if(!worldObj.isRemote && getCanZombie() == 7 && !setAttributes) {
			tasks.removeTask(aiPanic);
			tasks.removeTask(aiEatMushroom);
			targetTasks.addTask(0, aiNearestAttackableTarget);
			tasks.addTask(2, aiAttackOnCollide);
			setAttributes = true;
		}
	}
}
