package erebus.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBotFlyLarva extends EntityMob {
	public EntityBotFlyLarva(World world) {
		super(world);
		setSize(0.5F, 0.2F);
		isImmuneToFire = true;
		tasks.addTask(0, new EntityAIWander(this, 0.3D));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6000000238418579D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
    public boolean canBeCollidedWith() {
        return false;
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
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (!player.capabilities.isCreativeMode && !worldObj.isRemote)
			if (player.riddenByEntity==null) {
				mountEntity(player);
				setPosition(player.posX, player.posY+ ridingEntity.getYOffset(), player.posZ);
			}
		setRotation(player.renderYawOffset, player.rotationPitch);
	}
	
	@Override
	public double getYOffset() {
		if (ridingEntity != null && ridingEntity instanceof EntityPlayer)
			return -2D;
		else if (ridingEntity != null)
			return ridingEntity.height * 0.75D - 1.0D;
		else
			return yOffset;
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		playSound("mob.silverfish.step", 0.15F, 1.0F);
	}

	@Override
	public void onUpdate() {
		renderYawOffset = rotationYaw;
		if (!worldObj.isRemote)
			if (ridingEntity != null && ridingEntity instanceof EntityPlayer && rand.nextInt(100) == 0) {
				byte duration = 15;
				((EntityLivingBase) ridingEntity).addPotionEffect(new PotionEffect(Potion.weakness.id, duration * 20, 0));
				((EntityLivingBase) ridingEntity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, duration * 20, 0));
				((EntityLivingBase) ridingEntity).addPotionEffect(new PotionEffect(Potion.hunger.id, duration * 20, 0));
		}
		super.onUpdate();
	}
	
	@Override
	public void setDead() {
		super.setDead();
		worldObj.playSoundEffect(posX, posY, posZ, getDeathSound(), 1.0F, 0.7F);
		if (!worldObj.isRemote)
			entityDropItem(new ItemStack(Item.slimeBall), 0.0F);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown))
			return false;
		return super.attackEntityFrom(source, damage);
	}
}
