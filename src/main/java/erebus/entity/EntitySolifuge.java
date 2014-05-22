package erebus.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ModItems;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemErebusMaterial.DATA;

public class EntitySolifuge extends EntityMob {

	public EntitySolifuge(World world) {
		super(world);
		setSize(2.0F, 1.0F);
		experienceValue = 10;
		isImmuneToFire = true;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }

	@Override
	protected void fall(float par1) {
	}

	@Override
	public void setInWeb() {
	}

	@Override
	protected String getLivingSound() {
		return "mob.spider.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.spider.say";
	}

	@Override
	protected String getDeathSound() {
		return "mob.spider.death";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1 + looting, DATA.bioVelocity.ordinal()), 0.0F);
	}

	@Override
	protected void dropRareDrop(int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, DATA.supernaturalvelocity.ordinal()), 0.0F);
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	
	@Override
	public void setDead() {
		super.setDead();
		if (!worldObj.isRemote) {
			for (int a = 0; a < 4; a++) {
				EntitySolifugeSmall entitySolifugeSmall = new EntitySolifugeSmall(worldObj);
				entitySolifugeSmall.setPosition(posX + (rand.nextFloat()*0.03D -rand.nextFloat()*0.03D), posY + 1, posZ +(rand.nextFloat()*0.03D-rand.nextFloat()*0.03D));
				entitySolifugeSmall.setPotionEffect(Byte.valueOf((byte)rand.nextInt(9)));
				worldObj.spawnEntityInWorld(entitySolifugeSmall);
			}
		}
	}	
}
