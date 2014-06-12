package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ErebusMaterial.DATA;

public class EntityCentipede extends EntityMob {

	public int skin = rand.nextInt(3);

	public EntityCentipede(World world) {
		super(world);
		setSize(1.0F, 0.8F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0D); //getAttackStrength()// atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D); // followRange
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	public double getAttackStrength() {
		switch (worldObj.difficultySetting) {
			default:
				return 2.0D;
			case EASY:
				return 2.0D;
			case NORMAL:
				return 2.0D;
			case HARD:
				return 4.0D;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 8;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:centipedesound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:centipedehurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {  // playStepSound
		playSound("erebus:centipedewalk", 0.5F, 1.0F);
	}
	
	protected String getTunnelingSound() {
		return "erebus:canDigsound";
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(2) + looting, DATA.bioVelocity.ordinal()), 0.0F);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(2), DATA.poisonGland.ordinal()), 0.0F);
	}

	@Override
	protected void dropRareDrop(int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, DATA.supernaturalvelocity.ordinal()), 0.0F);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY) {
			byte duration = 0;
			if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
				duration = 7;
			else if (worldObj.difficultySetting == EnumDifficulty.HARD)
				duration = 15;
			if (duration > 0)
				player.addPotionEffect(new PotionEffect(Potion.poison.id, duration * 20, 0));
		}
	}
	
	public boolean isOnSandOrDirt() {
		return worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)) == Blocks.sand
				||worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)) == Blocks.dirt
				||worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)) == Blocks.grass;
			
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (findPlayerToAttack() != null)
			entityToAttack = findPlayerToAttack();
		else
			entityToAttack = null;

		if (!worldObj.isRemote && getEntityToAttack() != null && isOnSandOrDirt())
			yOffset = -1.0F;
		 // TODO need to set another flag  just to stop yOffset digging to the Earth's core
		else
			yOffset = 0F;
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer player = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return player;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown))
			return false;
		return super.attackEntityFrom(source, damage);
	}
	
	@Override
	public void onLivingUpdate() {
	            if(yOffset ==-1.0F)
	            	tunnel();
	            
	        super.onLivingUpdate();
	    }

	public boolean canCentipedeDestroy(World world, int x, int y, int z) {
		return world.getBlock( x, y, z) == Blocks.sand || world.getBlock( x, y, z) == Blocks.dirt|| world.getBlock( x, y, z) == Blocks.grass;		
	}

	private void tunnel() {
			boolean canDig = false;
			Block block = this.worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
				if (block != null)
					if (this.canCentipedeDestroy(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ))) {
						canDig = true;
						worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY+1), MathHelper.floor_double(posZ), block);
	                    worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), Blocks.air);
	                    }
	    if (canDig)
	    	this.worldObj.playSoundEffect((int)posX, (int)posY, (int)posZ, getTunnelingSound(), 1.0F, 1.0F);
	}
}
