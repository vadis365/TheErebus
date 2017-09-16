package erebus.entity;

import erebus.ModItems;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityChameleonTick extends EntityMob {

	public Block blockID;
	public int blockMeta;
	public int animation;
	public boolean active = false;
	private final EntityAINearestAttackableTarget aiAttackTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, true);
	private final EntityAIErebusAttackMelee aiAttackOnCollide = new EntityAIErebusAttackMelee(this, 0.65D, false);

	public EntityChameleonTick(World world) {
		super(world);
		setSize(1.0F, 1.0F);
		setBlock(Blocks.GRASS, 0);
		setPathPriority(PathNodeType.WATER, -8F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
	}

	public void setBlock(Block blockID, int blockMeta) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 30D : 30D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.CAMO_POWDER.ordinal()), 0.0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		IBlockState state = getEntityWorld().getBlockState(getPosition().add(0, -1, 0));
		Block newblockID = state.getBlock();
		int newBlockMeta = newblockID.getMetaFromState(state);

		if (onGround && newblockID != null && newblockID != blockID && state.isBlockNormalCube()) {
			blockID = newblockID;
			blockMeta = newBlockMeta;
		}

		if (findPlayerToAttack() != null && (!findPlayerToAttack().isSpectator() && !findPlayerToAttack().isCreative())) {
			setAttackTarget(findPlayerToAttack());
			if (!active)
				active = true;
			animation++;
			if (animation >= 10)
				animation = 10;

		} else {
			setAttackTarget(null);
			if (active)
				active = false;
			animation--;
			if (animation <= 0)
				animation = 0;
		}

		if (!getEntityWorld().isRemote && animation == 9 && active)
			setAIs(true);

		if (!getEntityWorld().isRemote && !active) {
			stationaryEntity();
			if (animation == 1)
				setAIs(false);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity))
			return super.attackEntityAsMob(entity);
		else
			return false;
	}

	public void stationaryEntity() {
		posX = MathHelper.floor(posX) + 0.5;
		posY = MathHelper.floor(posY);
		posZ = MathHelper.floor(posZ) + 0.5;
		rotationYaw = prevRotationYaw = 0F;
		renderYawOffset = prevRenderYawOffset = 0F;

		if (getEntityWorld().getBlockState(getPosition().down()) == null)
			posY -= 1;
	}

	public void setAIs(boolean active) {
		if (!active) {
			getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			tasks.removeTask(aiAttackOnCollide);
			tasks.removeTask(aiAttackTarget);
		}
		if (active) {
			getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.65D);
			tasks.addTask(1, aiAttackOnCollide);
			targetTasks.addTask(1, aiAttackTarget);
		}
	}

	protected EntityPlayer findPlayerToAttack() {
		return getEntityWorld().getClosestPlayerToEntity(this, 8.0D);
	}
}