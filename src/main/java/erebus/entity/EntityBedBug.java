package erebus.entity;

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
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemMaterials;

public class EntityBedBug extends EntityMob {

	public EntityBedBug(World world) {
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
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(getAttackStrength());
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
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
		return "erebus:beetlesound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:beetlehurt";
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
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(3) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.PLATE_EXO.makeStack(), 0.0F);
	}
}
