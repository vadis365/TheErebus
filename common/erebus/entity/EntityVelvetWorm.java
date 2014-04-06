package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityVelvetWorm extends EntityMob {

public int skin = rand.nextInt(2);
	
	public EntityVelvetWorm(World world) {
		super(world);
		setSize(2F, 0.7F);
		getNavigator().setAvoidsWater(false);
		experienceValue = 15; 
		fireResistance = 10;
		isImmuneToFire = false;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, new Integer(0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(getAttackStrength()); // atkDmg
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(getEntityToAttack() !=null) {
			if(getInflateSize()<100)
				setInflateSize(getInflateSize()+2);
		
		if(getEntityToAttack() == null)
			setInflateSize(0);
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
	public void setInWeb() {
	}
	
	protected String getWebSlingThrowSound() {
		return "erebus:webslingthrow";
	}

	public double getAttackStrength() {
		switch (worldObj.difficultySetting) {
			default:
				return 4.0D;
			case 1:
				return 4.0D;
			case 2:
				return 5.0D;
			case 3:
				return 6.0D;
		}
	}

	@Override
	protected void dropFewItems(boolean hit, int looting) {
		int chanceFiftyFifty = rand.nextInt(2) + 1;

		dropItem(Item.slimeBall.itemID, chanceFiftyFifty + looting);
	}
	
	@Override
	protected Entity findPlayerToAttack() {
		float f = getBrightness(1.0F);
		if (f < 0.5F) {
			double d0 = 16.0D;
			return worldObj.getClosestVulnerablePlayerToEntity(this, d0);
		} else
			return null;
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance < 2.0F) {
			setInflateSize(0);
			super.attackEntity(entity, distance);
			attackEntityAsMob(entity);
		}

		if (distance >= 2.0F & distance < 16.0F)
			if (entity instanceof EntityPlayer && getInflateSize()>=100) {
				worldObj.playSoundAtEntity(this, getWebSlingThrowSound(), 1.0F, 1.0F);
				setInflateSize(0);
				EntityGooBall gooBall = new EntityGooBall(worldObj, this);
				gooBall.posY = posY + height / 2.0F + 0.3D;
				worldObj.spawnEntityInWorld(gooBall);
			}
		}

	public void setInflateSize(int size) {
		dataWatcher.updateObject(20, Integer.valueOf(size));
	}
	
	public int getInflateSize() {
		return  dataWatcher.getWatchableObjectInt(20);
	}
}
