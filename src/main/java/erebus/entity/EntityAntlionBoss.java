package erebus.entity;

import java.util.List;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIAntlionBossAttack;
import erebus.items.ItemMaterials;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import erebus.world.feature.structure.AntlionMazeDungeon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityAntlionBoss extends EntityMob {
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	private static final DataParameter<BlockPos> SPAWN_ORIGIN = EntityDataManager.<BlockPos>createKey(EntityAntlionBoss.class, DataSerializers.BLOCK_POS);
	private static final DataParameter<Byte> IN_PYRAMID = EntityDataManager.<Byte>createKey(EntityAntlionBoss.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> BLAM = EntityDataManager.<Byte>createKey(EntityAntlionBoss.class, DataSerializers.BYTE);
	private int blamCount;
	public int deathTicks;

	public EntityAntlionBoss(World world) {
		super(world);
		setSize(6.0F, 2.0F);
		isImmuneToFire = true;
		stepHeight = 1.0F;
		experienceValue = 100;
	}
	
	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAIAntlionBossAttack<EntityPlayer>(this, EntityPlayer.class, 0.6D, true));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SPAWN_ORIGIN, new BlockPos(0, 0, 0));
		dataManager.register(IN_PYRAMID, (byte) 0);
		dataManager.register(BLAM, (byte) 3);
	}

	@Override
    public boolean isNonBoss() {
        return false;
    }

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 400D : 400D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 6D : 6D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.75D);
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
	public void onUpdate() {
		super.onUpdate();
		if (blamCount <= 75)
			blamCount++;

		if (blamCount == 5)
			setBlam(5, (byte) 3);

		if (blamCount < 75 && blamCount > 10)
			if (getBlam() >= 1 && getBlam() <= 2)
				if (blamCount % 5 == 0 && blamCount % 10 != 0)
					setBlam(blamCount, (byte) 2);
				else if (blamCount % 10 == 0) {
					setBlam(blamCount, (byte) 1);
					areaOfEffect2();
				}

		if (blamCount == 75)
			setBlam(75, (byte) 3);

		if (getBlam() == 1)
			spawnRumbleParticles();
		destroyBlocksInAABB(getEntityBoundingBox());
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.IN_WALL) || source.equals(DamageSource.DROWN) || source instanceof EntityDamageSourceIndirect)
			return false;
		return super.attackEntityFrom(source, damage);
	}

	public void spawnBlamParticles() {
		if (!getEntityWorld().isRemote && onGround)
			Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.ANTLION_BLAM, (float) posX, (float)posY, (float)posZ));
	}

	public void spawnRumbleParticles() {
		if (!getEntityWorld().isRemote)
			Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.ANTLION_RUMBLE, (float) posX, (float)posY, (float)posZ));
	}

	public void setBlam(int count, byte action) {
		blamCount = count;
		dataManager.set(BLAM, action);
	}

	public byte getBlam() {
		return dataManager.get(BLAM);
	}

	public void setInPyramid(byte state) {
		dataManager.set(IN_PYRAMID, state);
	}

	public byte getInPyramid() {
		return dataManager.get(IN_PYRAMID);
	}

	public int getSpawnPointX() {
		return dataManager.get(SPAWN_ORIGIN).getX();
	}

	public int getSpawnPointY() {
		return dataManager.get(SPAWN_ORIGIN).getY();
	}

	public int getSpawnPointZ() {
		return dataManager.get(SPAWN_ORIGIN).getZ();
	}

	public void setSpawnPoint(int x, int y, int z) {
		dataManager.set(SPAWN_ORIGIN, new BlockPos(x, y, z));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("inPyramid", getInPyramid());
		nbt.setInteger("spawnPointX", getSpawnPointX());
		nbt.setInteger("spawnPointY", getSpawnPointY());
		nbt.setInteger("spawnPointZ", getSpawnPointZ());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setInPyramid(nbt.getByte("inPyramid"));
		setSpawnPoint(nbt.getInteger("spawnPointX"), nbt.getInteger("spawnPointY"), nbt.getInteger("spawnPointZ"));
        if(hasCustomName())
        	bossInfo.setName(this.getDisplayName());
	}

	@Override
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        bossInfo.setName(this.getDisplayName());
    }

	@Override
    public void addTrackingPlayer(EntityPlayerMP player) {
		super.addTrackingPlayer(player);
        bossInfo.addPlayer(player);
    }

	@Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        bossInfo.removePlayer(player);
    }

	protected Entity areaOfEffect2() {
		List<?> list = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().grow(16D, 1D, 16D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity != null)
				if (entity instanceof EntityLivingBase && !(entity instanceof EntityAntlionBoss)) {
					float Knockback = (-3.0F + getEntityWorld().rand.nextInt(4)) * 0.1F;
					entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
					entity.addVelocity(-MathHelper.sin(rotationYaw * -3.141593F + getEntityWorld().rand.nextInt(3) + 0.141593F / 180.0F) * Knockback, 0.01D, MathHelper.cos(rotationYaw * -3.141593F + getEntityWorld().rand.nextInt(3) + 0.141593F / 180.0F) * Knockback);
					getEntityWorld().playSound(null, getPosition(), ModSounds.ANTLION_SLAM, SoundCategory.HOSTILE, 1.0F, 1.0F);
				}
		}
		return null;
	}

	@Override
	protected void onDeathUpdate() {
		++deathTicks;

		if (deathTicks % 25 == 1) {
			getEntityWorld().playSound(null, getPosition(), ModSounds.ANTLION_GROWL, SoundCategory.HOSTILE, 1.0F, getEntityWorld().rand.nextFloat() * 0.1F + 0.9F);
			getEntityWorld().playSound(null, getPosition(), ModSounds.ANTLION_GROWL, SoundCategory.HOSTILE, 1.0F, getEntityWorld().rand.nextFloat() * 0.1F + 0.3F);
			getEntityWorld().playSound(null, getPosition(), ModSounds.ANTLION_GROWL, SoundCategory.HOSTILE, 1.0F, getEntityWorld().rand.nextFloat() * 0.1F + 0.1F);
		}

		if (deathTicks >= 180 && deathTicks <= 200)
			if (!getEntityWorld().isRemote)
				Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.BOSS_DEATH, (float) posX, (float)posY, (float)posZ));

		int i;
		int j;

		if (!getEntityWorld().isRemote)
			if (deathTicks > 150 && deathTicks % 5 == 0) {
				i = 1000;

				while (i > 0) {
					j = EntityXPOrb.getXPSplit(i);
					i -= j;
					getEntityWorld().spawnEntity(new EntityXPOrb(getEntityWorld(), posX, posY, posZ, j));
				}
			}
		move(MoverType.SELF, 0.0D, 0.310000000149011612D, 0.0D);
		spawnRumbleParticles();
		renderYawOffset = prevRenderYawOffset += 0.03F;
		limbSwingAmount = 0.5F;
		if (deathTicks == 200 && !getEntityWorld().isRemote) {
			i = 2000;

			while (i > 0) {
				j = EntityXPOrb.getXPSplit(i);
				i -= j;
				getEntityWorld().spawnEntity(new EntityXPOrb(getEntityWorld(), posX, posY, posZ, j));
			}
			if (getInPyramid() == 1) {
				AntlionMazeDungeon.setTeleporter(getEntityWorld(), getSpawnPointX(), getSpawnPointY(), getSpawnPointZ(), 7, getSpawnPointX(), getSpawnPointY() + 12, getSpawnPointZ());
				AntlionMazeDungeon.setTeleporter(getEntityWorld(), getSpawnPointX() + 1, getSpawnPointY(), getSpawnPointZ(), 6, getSpawnPointX() + 1, getSpawnPointY() + 12, getSpawnPointZ());
				AntlionMazeDungeon.setTeleporter(getEntityWorld(), getSpawnPointX(), getSpawnPointY(), getSpawnPointZ() + 1, 9, getSpawnPointX(), getSpawnPointY() + 12, getSpawnPointZ() + 1);
				AntlionMazeDungeon.setTeleporter(getEntityWorld(), getSpawnPointX() + 1, getSpawnPointY(), getSpawnPointZ() + 1, 8, getSpawnPointX() + 1, getSpawnPointY() + 12, getSpawnPointZ() + 1);
			}
			getEntityWorld().setBlockState(getPosition(), ModBlocks.ANTLION_EGG.getDefaultState());
			Utils.dropStackNoRandom(getEntityWorld(), getPosition().up(), ItemMaterials.EnumErebusMaterialsType.SOUL_CRYSTAL.createStack());
			Utils.dropStackNoRandom(getEntityWorld(), getPosition().up(), new ItemStack(ModItems.WAR_HAMMER));
			setDead();
		}
	}

	public void destroyBlocksInAABB(AxisAlignedBB AABB) {
		if (getEntityWorld().isRemote)
			return;
		int i = MathHelper.floor(AABB.minX - 1);
		int j = MathHelper.floor(AABB.minY - 0.2);
		int k = MathHelper.floor(AABB.minZ - 1);
		int l = MathHelper.floor(AABB.maxX + 1);
		int i1 = MathHelper.floor(AABB.maxY);
		int j1 = MathHelper.floor(AABB.maxZ + 1);

		for (int k1 = i; k1 <= l; ++k1)
			for (int l1 = j; l1 <= i1; ++l1)
				for (int i2 = k; i2 <= j1; ++i2) {
					BlockPos pos = new BlockPos(k1, l1, i2);
					Block block = getEntityWorld().getBlockState(pos).getBlock();
					Block blockBelow = getEntityWorld().getBlockState(pos.down()).getBlock();
					if (block == Blocks.SAND && blockBelow != ModBlocks.TEMPLE_BRICK_UNBREAKING) {
						getEntityWorld().setBlockToAir(pos);
						getEntityWorld().playEvent(null, 2001, pos, Block.getIdFromBlock(block));
					}
				}
	}

}