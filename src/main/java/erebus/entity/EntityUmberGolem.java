package erebus.entity;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.blocks.BlockUmberGolemStatue;
import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityUmberGolem extends EntityCreature {

	public EntityUmberGolem(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1.0F, 1.0F);
		experienceValue = 0;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAITempt(this, 0.5D, ModItems.WAND_OF_ANIMATION, false));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackMelee(this, 0.5D, false));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMob.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 75D : 75D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return !canDespawn() && super.canBeLeashedTo(player);
    }

	/*
	 * protected String getLivingSound() { return "erebus:umbergolemsound"; } protected String getHurtSound() { return "erebus:umbergolemhurt"; }
	 */

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int looting) {
		entityDropItem(new ItemStack(Blocks.STONE, 5, 0), 0.0F);
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	@Override
	public boolean isOnLadder() {
		return collidedHorizontally;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return Attack(entity);
	}

	protected boolean Attack(Entity entity) {
		if (!getEntityWorld().isRemote) {
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
				motionY = 0.4000000059604645D;
			}
			int Knockback = 1;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 5D : 5D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
			entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F, 0.4D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F);
			getEntityWorld().playSound(null, getPosition(), SoundEvents.ENTITY_PLAYER_BIG_FALL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, getEntityWorld().getDifficulty().ordinal() * 50, 0));
			return true;
		}
		return true;
	}

	@Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack is = player.inventory.getCurrentItem();
		if (!getEntityWorld().isRemote && !is.isEmpty() && is.getItem() == ModItems.WAND_OF_ANIMATION) {
			setDead();
			EnumFacing facing = this.getHorizontalFacing();
			getEntityWorld().setBlockState(getPosition(), ModBlocks.UMBER_GOLEM_STATUE.getDefaultState().withProperty(BlockUmberGolemStatue.FACING, facing), 3);
			getEntityWorld().playSound(null, getPosition(), ModSounds.ALTAR_OFFERING, SoundCategory.BLOCKS, 0.2F, 1.0F);
			return true;
		} else
			return false;
	}
}
