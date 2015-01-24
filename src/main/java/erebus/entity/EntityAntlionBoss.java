package erebus.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.entity.ai.EntityAIAntlionBossAttack;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;

public class EntityAntlionBoss extends EntityMob implements IBossDisplayData{
	private int blamCount;
	public EntityAntlionBoss(World world) {
		super(world);
		this.isImmuneToFire = true;
		this.stepHeight = 1.0F;
		experienceValue = 3965;
		this.setSize(6.0F, 2.0F);
		tasks.addTask(0, new EntityAIAntlionBossAttack(this, EntityPlayer.class, 0.6D, true));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(21, new Byte((byte) 3));
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
		if(blamCount <= 75) {
			blamCount++;
		}
		
		if(blamCount == 5) {
			setBlam(5, (byte) 3);
		}
		
		if(blamCount < 75 && blamCount > 10){
			if(dataWatcher.getWatchableObjectByte(21) >= 1 && dataWatcher.getWatchableObjectByte(21) <= 2){
			if(blamCount %5 == 0 && blamCount %10 != 0) {
			setBlam(blamCount, (byte) 2);
			}
			else if(blamCount %10 == 0){
				setBlam(blamCount, (byte) 1);
				areaOfEffect2();
			}
		}
		}
		
		if(blamCount == 75) {
			setBlam(75, (byte) 3);
		}
		
		if(dataWatcher.getWatchableObjectByte(21) == 1) {
			spawnRumbleParticles();
		}
		destroyBlocksInAABB(getBoundingBox());
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown)||source instanceof EntityDamageSourceIndirect)
			return false;
		return super.attackEntityFrom(source, damage);
	}
	
	public void spawnBlamParticles() {
		if(onGround)
			PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.ANTLION_BLAM));
	}
	
	public void spawnRumbleParticles() {
		PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.ANTLION_RUMBLE));
	}
	
	public void setBlam(int count, byte action) {
		blamCount = count;
		dataWatcher.updateObject(21, action);
	}

	protected Entity areaOfEffect2() {
		List list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).expand(16D, 1D, 16D));
			for (int i = 0; i < list.size(); i++) {
				Entity entity = (Entity) list.get(i);
				if (entity != null)
					if (entity instanceof EntityLivingBase && !(entity instanceof EntityAntlionBoss)){
						float Knockback = (-3.0F + worldObj.rand.nextInt(4))*0.1F;
						entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
						entity.addVelocity(-MathHelper.sin(rotationYaw * -3.141593F + (float)worldObj.rand.nextInt(3) + 0.141593F / 180.0F)* Knockback, 0.01D, MathHelper.cos(rotationYaw * -3.141593F + (float)worldObj.rand.nextInt(3)+0.141593F / 180.0F)* Knockback);
						worldObj.playSoundAtEntity(entity, "erebus:antlionslam", 1.0F, 1.0F);
					}
			}	
		return null;
	}
	
    public void destroyBlocksInAABB(AxisAlignedBB AABB) {
    	if(worldObj.isRemote)
    		return;
        int i = MathHelper.floor_double(AABB.minX -1);
        int j = MathHelper.floor_double(AABB.minY -0.2);
        int k = MathHelper.floor_double(AABB.minZ - 1);
        int l = MathHelper.floor_double(AABB.maxX + 1);
        int i1 = MathHelper.floor_double(AABB.maxY);
        int j1 = MathHelper.floor_double(AABB.maxZ + 21);

        for (int k1 = i; k1 <= l; ++k1)
            for (int l1 = j; l1 <= i1; ++l1)
                for (int i2 = k; i2 <= j1; ++i2) {
                    Block block = worldObj.getBlock(k1, l1, i2);
                    Block blockBelow = worldObj.getBlock(k1, l1-1, i2);
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