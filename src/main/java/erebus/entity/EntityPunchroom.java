package erebus.entity;

import erebus.item.ItemMaterials;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPunchroom extends EntityMob {
	private int shroomJumpDelay;
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;

	public EntityPunchroom(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1.0F, 1.0F);
		shroomJumpDelay = rand.nextInt(20) + 10;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	/*
	 * @Override protected String getLivingSound() { return ""; }
	 * @Override protected String getHurtSound() { return ""; }
	 * @Override protected String getDeathSound() { return ""; }
	 */

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (rand.nextInt(5) == 0)
			entityDropItem(ItemMaterials.DATA.elasticFibre.makeStack(1 + looting), 0.0F);
	}

	@Override
	public void onUpdate() {
		squishFactor += (squishAmount - squishFactor) * 0.5F;
		prevSquishFactor = squishFactor;
		boolean flag = onGround;
		super.onUpdate();
		if (onGround && !flag) {
			squishAmount = -0.5F;
			for (int j = 0; j < 8; ++j) {
				float f = rand.nextFloat() * (float) Math.PI * 2.0F;
				float f1 = rand.nextFloat() * 0.5F + 0.5F;
				float f2 = MathHelper.sin(f) * 0.5F * f1;
				float f3 = MathHelper.cos(f) * 0.5F * f1;
				worldObj.spawnParticle("cloud", posX + f2, boundingBox.minY, posZ + f3, 0.0D, 0.0D, 0.0D);
			}
		} else if (!onGround && flag)
			squishAmount = 1.0F;

		alterSquishAmount();
	}

	protected void alterSquishAmount() {
		squishAmount *= 0.8F;
	}

	@Override
	protected void updateEntityActionState() {
		despawnEntity();
		EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

		if (entityplayer != null) {
			faceEntity(entityplayer, 10.0F, 20.0F);
			setAttackTarget(entityplayer);
		}

		if (onGround && shroomJumpDelay-- <= 0) {
			shroomJumpDelay = getJumpDelay();
			if (entityplayer != null)
				shroomJumpDelay /= 3;
			isJumping = true;
			moveStrafing = 1.0F - rand.nextFloat() * 2.0F;
			moveForward = 1;
		} else {
			isJumping = false;
			if (onGround)
				moveStrafing = moveForward = 0.0F;
		}
	}

	protected int getJumpDelay() {
		return rand.nextInt(20) + 10;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		float knockback = 0.2F;
		if (!player.capabilities.isCreativeMode && !worldObj.isRemote) {

			if (player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY)
				if (worldObj.difficultySetting.ordinal() > 1)
					if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
						knockback = 0.4F;
					else if (worldObj.difficultySetting == EnumDifficulty.HARD)
						knockback = 0.6F;
			player.attackEntityFrom(DamageSource.causeMobDamage(this), 1F);
			player.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * knockback, 0.3D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * knockback);
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}
}
