package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityFirebrat extends EntityMob {

	public EntityFirebrat(World world) {
		super(world);
		setSize(0.3F, 0.7F);
		isImmuneToFire = true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6000000238418579D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D);
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected Entity findPlayerToAttack() {
		double d0 = 16.0D;
		return worldObj.getClosestVulnerablePlayerToEntity(this, d0);
	}

	@Override
	protected String getLivingSound() {
		return "mob.silverfish.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.silverfish.hit";
	}

	@Override
	protected String getDeathSound() {
		return "mob.silverfish.kill";
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (par2 < 1.2F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			entity.setFire(5);
			attackEntityAsMob(entity);
		}
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		playSound("mob.silverfish.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if (rand.nextInt(5) == 0)
			entityDropItem(new ItemStack(Item.blazePowder, 1, 0), 0.0F);
	}

	@Override
	public void onUpdate() {
		renderYawOffset = rotationYaw;
		super.onUpdate();
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		if (super.getCanSpawnHere()) {
			EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 5.0D);
			return entityplayer == null;
		} else
			return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
}
