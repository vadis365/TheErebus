package erebus.entity;

import erebus.item.ItemMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLocust extends EntityMob {

	private float heightOffset = 0.5F;
	public boolean canJump = true;

	public EntityLocust(World world) {
		super(world);
		stepHeight = 1.0F;
		jumpMovementFactor = 0.05F;
		setSize(2F, 1F);
		getNavigator().setAvoidsWater(true);

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.5D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:locustsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:locusthurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4) {
		worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(ItemMaterials.DATA.elasticFibre.makeStack(), 0.0F);
	}

	public boolean randJump() {
		return rand.nextInt(50) == 0;
	}

	@Override
	protected void jump() {
		motionY = 0.61999998688697815D;
		setCanJump(false);
	}

	public void setCanJump(boolean par1) {
		canJump = par1;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote && onGround && randJump() && canJump)
			jump();
		if (!worldObj.isRemote && !canJump) {
			heightOffset = 0.5F + (float) rand.nextGaussian() * 5.0F;
			if (getEntityToAttack() != null && getEntityToAttack().posY + getEntityToAttack().getEyeHeight() > posY + getEyeHeight() + heightOffset) {
				motionY += (Math.signum(getEntityToAttack().posY + 1.D - posY) * 0.699999988079071D - motionY) * 0.10000000149011612D;
				moveForward = 0.5F;
			}
		}
		if (!canJump && !onGround && motionY < 0.0D)
			motionY *= 0.5D;
		if (onGround) {
			setCanJump(true);
			heightOffset = 0F;
		}
		super.onLivingUpdate();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLiving) {
				byte var2 = 0;
				if (worldObj.difficultySetting.ordinal() > EnumDifficulty.EASY.ordinal())
					if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
						var2 = 7;
					else if (worldObj.difficultySetting == EnumDifficulty.HARD)
						var2 = 15;
				if (var2 > 0)
					((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.hunger.id, var2 * 20, 0));
			}
			return true;
		} else
			return false;
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (par2 <= 3.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			attackEntityAsMob(entity);
	}
}