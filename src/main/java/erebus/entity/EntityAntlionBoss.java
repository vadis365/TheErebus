package erebus.entity;

import java.util.List;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIAntlionBossAttack;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import erebus.world.feature.structure.AntlionMazeDungeon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAntlionBoss extends EntityMob implements IBossDisplayData {

	private int blamCount;
	public int deathTicks;

	public EntityAntlionBoss(World world) {
		super(world);
		isImmuneToFire = true;
		stepHeight = 1.0F;
		experienceValue = 100;
		setSize(6.0F, 2.0F);
		tasks.addTask(0, new EntityAIAntlionBossAttack<EntityPlayer>(this, EntityPlayer.class, 0.6D, true));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(21, new Byte((byte) 3));
		dataWatcher.addObject(23, new Byte((byte) 0));
		dataWatcher.addObject(24, new Integer(0));
		dataWatcher.addObject(25, new Integer(0));
		dataWatcher.addObject(26, new Integer(0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float fallAmount) {
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.75D);
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) { // playStepSound
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected String getLivingSound() {
		return "erebus:antliongrowl";
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (blamCount <= 75)
			blamCount++;

		if (blamCount == 5)
			setBlam(5, (byte) 3);

		if (blamCount < 75 && blamCount > 10)
			if (dataWatcher.getWatchableObjectByte(21) >= 1 && dataWatcher.getWatchableObjectByte(21) <= 2)
				if (blamCount % 5 == 0 && blamCount % 10 != 0)
					setBlam(blamCount, (byte) 2);
				else if (blamCount % 10 == 0) {
					setBlam(blamCount, (byte) 1);
					areaOfEffect2();
				}

		if (blamCount == 75)
			setBlam(75, (byte) 3);

		if (dataWatcher.getWatchableObjectByte(21) == 1)
			spawnRumbleParticles();
		destroyBlocksInAABB(getBoundingBox());
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown) || source instanceof EntityDamageSourceIndirect)
			return false;
		return super.attackEntityFrom(source, damage);
	}

	public void spawnBlamParticles() {
		if (onGround)
			PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.ANTLION_BLAM));
	}

	public void spawnRumbleParticles() {
		PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.ANTLION_RUMBLE));
	}

	public void setBlam(int count, byte action) {
		blamCount = count;
		dataWatcher.updateObject(21, action);
	}

	public void setInPyramid(byte state) {
		dataWatcher.updateObject(23, Byte.valueOf(state));
	}

	public byte getInPyramid() {
		return dataWatcher.getWatchableObjectByte(23);
	}

	public int getSpawnPointX() {
		return dataWatcher.getWatchableObjectInt(24);
	}

	public int getSpawnPointY() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	public int getSpawnPointZ() {
		return dataWatcher.getWatchableObjectInt(26);
	}

	public void setSpawnPoint(int x, int y, int z) {
		dataWatcher.updateObject(24, Integer.valueOf(x));
		dataWatcher.updateObject(25, Integer.valueOf(y));
		dataWatcher.updateObject(26, Integer.valueOf(z));
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
	}

	protected Entity areaOfEffect2() {
		List<?> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).expand(16D, 1D, 16D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity != null)
				if (entity instanceof EntityLivingBase && !(entity instanceof EntityAntlionBoss)) {
					float Knockback = (-3.0F + worldObj.rand.nextInt(4)) * 0.1F;
					entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
					entity.addVelocity(-MathHelper.sin(rotationYaw * -3.141593F + worldObj.rand.nextInt(3) + 0.141593F / 180.0F) * Knockback, 0.01D, MathHelper.cos(rotationYaw * -3.141593F + worldObj.rand.nextInt(3) + 0.141593F / 180.0F) * Knockback);
					worldObj.playSoundAtEntity(entity, "erebus:antlionslam", 1.0F, 1.0F);
				}
		}
		return null;
	}

	@Override
	protected void onDeathUpdate() {
		++deathTicks;

		if (deathTicks % 25 == 1) {
			worldObj.playSoundEffect(posX, posY, posZ, "erebus:antliongrowl", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			worldObj.playSoundEffect(posX, posY, posZ, "erebus:antliongrowl", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.3F);
			worldObj.playSoundEffect(posX, posY, posZ, "erebus:antliongrowl", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.1F);
		}

		if (deathTicks >= 180 && deathTicks <= 200)
			PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.BOSS_DEATH));

		int i;
		int j;

		if (!worldObj.isRemote)
			if (deathTicks > 150 && deathTicks % 5 == 0) {
				i = 1000;

				while (i > 0) {
					j = EntityXPOrb.getXPSplit(i);
					i -= j;
					worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX, posY, posZ, j));
				}
			}
		moveEntity(0.0D, 0.310000000149011612D, 0.0D);
		spawnRumbleParticles();
		renderYawOffset = prevRenderYawOffset += 0.03F;
		limbSwingAmount = 0.5F;
		if (deathTicks == 200 && !worldObj.isRemote) {
			i = 2000;

			while (i > 0) {
				j = EntityXPOrb.getXPSplit(i);
				i -= j;
				worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX, posY, posZ, j));
			}
			if (getInPyramid() == 1) {
				AntlionMazeDungeon.setTeleporter(worldObj, getSpawnPointX(), getSpawnPointY(), getSpawnPointZ(), 7, getSpawnPointX(), getSpawnPointY() + 12, getSpawnPointZ());
				AntlionMazeDungeon.setTeleporter(worldObj, getSpawnPointX() + 1, getSpawnPointY(), getSpawnPointZ(), 6, getSpawnPointX() + 1, getSpawnPointY() + 12, getSpawnPointZ());
				AntlionMazeDungeon.setTeleporter(worldObj, getSpawnPointX(), getSpawnPointY(), getSpawnPointZ() + 1, 9, getSpawnPointX(), getSpawnPointY() + 12, getSpawnPointZ() + 1);
				AntlionMazeDungeon.setTeleporter(worldObj, getSpawnPointX() + 1, getSpawnPointY(), getSpawnPointZ() + 1, 8, getSpawnPointX() + 1, getSpawnPointY() + 12, getSpawnPointZ() + 1);
			}
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), ModBlocks.antlionEgg);
			Utils.dropStackNoRandom(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY + 1.5), MathHelper.floor_double(posZ), new ItemStack(ModItems.soulCrystal));
			Utils.dropStackNoRandom(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY + 1.5), MathHelper.floor_double(posZ), new ItemStack(ModItems.warHammer));
			setDead();
		}
	}

	public void destroyBlocksInAABB(AxisAlignedBB AABB) {
		if (worldObj.isRemote)
			return;
		int i = MathHelper.floor_double(AABB.minX - 1);
		int j = MathHelper.floor_double(AABB.minY - 0.2);
		int k = MathHelper.floor_double(AABB.minZ - 1);
		int l = MathHelper.floor_double(AABB.maxX + 1);
		int i1 = MathHelper.floor_double(AABB.maxY);
		int j1 = MathHelper.floor_double(AABB.maxZ + 21);

		for (int k1 = i; k1 <= l; ++k1)
			for (int l1 = j; l1 <= i1; ++l1)
				for (int i2 = k; i2 <= j1; ++i2) {
					Block block = worldObj.getBlock(k1, l1, i2);
					Block blockBelow = worldObj.getBlock(k1, l1 - 1, i2);
					if (block == Blocks.sand && blockBelow != ModBlocks.templeBrickUnbreaking) {
						worldObj.setBlockToAir(k1, l1, i2);
						worldObj.playAuxSFXAtEntity(null, 2001, k1, l1, i2, Block.getIdFromBlock(Blocks.sand));
					}
				}
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}
}