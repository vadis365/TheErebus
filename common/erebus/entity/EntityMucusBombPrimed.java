package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;

public class EntityMucusBombPrimed extends Entity {
	/** How long the fuse is */
	public int fuse=80;
	private EntityLivingBase mucusBombPlacedBy;

	public EntityMucusBombPrimed(World world) {
		super(world);
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.yOffset = this.height / 2.0F;
	}

	public EntityMucusBombPrimed(World world, double x, double y, double z, EntityLivingBase entity) {
		this(world);
		this.setPosition(x, y, z);
		float f = (float) (Math.random() * Math.PI * 2.0D);
		this.motionX = (double) (-((float) Math.sin((double) f)) * 0.02F);
		this.motionY = 0.20000000298023224D;
		this.motionZ = (double) (-((float) Math.cos((double) f)) * 0.02F);
		this.fuse = 80;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.mucusBombPlacedBy = entity;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if (this.fuse-- <= 0) {
			this.setDead();

			if (!this.worldObj.isRemote) {
				this.explode();
			}
			if (this.worldObj.isRemote) {
				spawnSonicParticles();
			}
			
		} else {
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);	
		}
	}

	private void explode() {
		//float f = 4.0F;
		//this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, f, true);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setByte("Fuse", (byte) this.fuse);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		this.fuse = nbt.getByte("Fuse");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}

	public EntityLivingBase getTntPlacedBy() {
		return this.mucusBombPlacedBy;
	}
	@SideOnly(Side.CLIENT)
	public void spawnSonicParticles() {
		for (int a = 0; a < 360; a += 6) {
			double ang = a * Math.PI / 180D;
			Erebus.proxy.spawnCustomParticle("repellent", worldObj, this.posX + -MathHelper.sin((float) ang) * 1.0, this.posY + 0.5D, this.posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.3, 0D, MathHelper.cos((float) ang) * 0.3);
		}

		for (int a = 0; a < 360; a += 4) {
			double ang = a * Math.PI / 180D;
			Erebus.proxy.spawnCustomParticle("slime", worldObj, this.posX + -MathHelper.sin((float) ang) * 1.0, this.posY + 1D, this.posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.3, 0.1D, MathHelper.cos((float) ang) * 0.3);
			Erebus.proxy.spawnCustomParticle("slime", worldObj, this.posX + -MathHelper.sin((float) ang) * 1.0, this.posY + 0.5D, this.posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.4, 0D, MathHelper.cos((float) ang) * 0.4);
		}
	}
}
