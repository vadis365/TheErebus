package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBotFlyLarva extends EntityMob {
	boolean mountFlag;
	public EntityBotFlyLarva(World world) {
		super(world);
		setSize(0.3F, 0.7F);
		isImmuneToFire = true;
		mountFlag=false;
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
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.isSneaking())
			player.setSneaking(false);
		byte var2 = 0;
		if (!worldObj.isRemote && player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY)
			if (worldObj.difficultySetting > 1)
				if (worldObj.difficultySetting == 2)
					var2 = 7;
				else if (worldObj.difficultySetting == 3)
					var2 = 15;
		if (var2 > 0 && rand.nextInt(200) == 0) {
			player.addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 10, 0));

		}
		//if (!player.capabilities.isCreativeMode && !worldObj.isRemote)
		if (!mountFlag) {
			mountEntity(player);
			setPosition(player.posX, player.posY+ ridingEntity.getYOffset(), player.posZ);
			setRotation(player.renderYawOffset, player.rotationPitch);
			mountFlag=true;
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
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown))
			return false;
		return super.attackEntityFrom(source, damage);
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (par2 < 1.2F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			entity.setFire(5);
			attackEntityAsMob(entity);
		}
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
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
