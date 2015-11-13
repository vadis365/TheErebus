package erebus.entity;

import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.bioVelocity.makeStack(), 0.0F);
	}

	@Override
	protected void dropRareDrop(int looting) {
		entityDropItem(ItemMaterials.DATA.supernaturalvelocity.makeStack(), 0.0F);
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public void setDead() {
		super.setDead();

		if (!worldObj.isRemote && getHealth() <= 0.0F)
			for (int i = 0; i < 4; i++) {
				EntitySolifugeSmall entitySolifugeSmall = new EntitySolifugeSmall(worldObj);
				entitySolifugeSmall.setPosition(posX + (rand.nextFloat() * 0.03D - rand.nextFloat() * 0.03D), posY + 1, posZ + (rand.nextFloat() * 0.03D - rand.nextFloat() * 0.03D));
				entitySolifugeSmall.setPotionEffect(Byte.valueOf((byte) rand.nextInt(8)));
				worldObj.spawnEntityInWorld(entitySolifugeSmall);
			}
	}
}