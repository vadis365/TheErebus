package erebus.entity;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAntlion extends EntityMob {

	public EntityAntlion(World world) {
		super(world);
		setSize(1.9F, 0.9F);
		stepHeight = 1F;
		isImmuneToFire = true;
		experienceValue = 17;
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 0.7D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAntlion.AIWander(this, 0.7D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFireAnt.class, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFireAntSoldier.class, false));
		//targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityBlackAnt.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 35D : 35D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
	}

	@Override
    public float getEyeHeight() {
        return this.height * 0.3F;
    }

	@Override
	public int getTotalArmorValue() {
		return 8;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.ANTLION_GROWL;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.ANTLION_GROWL;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	protected Item getDropItem() {
		return Item.getItemFromBlock(Blocks.SAND);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.PLATE_EXO.ordinal()), 0.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	public boolean isOnSand() {
		if(!getEntityWorld().isRemote) {
		int minX = MathHelper.floor(getEntityBoundingBox().minX);
		int minY = MathHelper.floor(getEntityBoundingBox().minY);
		int minZ = MathHelper.floor(getEntityBoundingBox().minZ);
		int maxX = MathHelper.floor(getEntityBoundingBox().maxX);
		int maxY = MathHelper.floor(getEntityBoundingBox().maxY);
		int maxZ = MathHelper.floor(getEntityBoundingBox().maxZ);

		for (int k1 = minX; k1 <= maxX; ++k1)
			for (int l1 = minY; l1 <= maxY; ++l1)
				for (int i2 = minZ; i2 <= maxZ; ++i2) {
					Block blockBelow = getEntityWorld().getBlockState(new BlockPos(k1, l1, i2).down()).getBlock();
					if (blockBelow != Blocks.SAND)
						return false;
				}
		}
		return true;
	}

/*
	public boolean isOnSpawner() {
		return isNotColliding() && getEntityWorld().getBlockState(getPosition().down()).getBlock() == ModBlocks.antlionSpawner;
	}

	public boolean isOnGneiss() {
		return isNotColliding() && getEntityWorld().getBlockState(getPosition().down()).getBlock() == ModBlocks.gneiss;
	}
	*/

	@Override
	public void onUpdate() {
		if (!getEntityWorld().isRemote && getAttackTarget() == null && isOnSand() && !isInsideOfMaterial(Material.SAND)) {
			setPosition(posX, posY - 1, posZ);
		}

		if (!getEntityWorld().isRemote && getAttackTarget() != null && isInsideOfMaterial(Material.SAND)) {
			Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.ANTLION_DIG, (float) posX, (float)posY + 1F, (float)posZ));
			setPosition(posX, posY + 1, posZ);
		}

		super.onUpdate();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.IN_WALL) || source.equals(DamageSource.DROWN))
			return false;
		return super.attackEntityFrom(source, damage);
	}
	
// AI

	public class AIWander extends EntityAIWander {
		
		private final EntityAntlion antlion;

		public AIWander(EntityAntlion antlion, double speedIn) {
			super(antlion, speedIn);
			this.antlion = antlion;
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return !antlion.isInsideOfMaterial(Material.SAND) && super.shouldExecute();
		}

		@Override
	    public boolean shouldContinueExecuting() {
	        return !antlion.isInsideOfMaterial(Material.SAND) && !this.entity.getNavigator().noPath();
	    }

	}
}