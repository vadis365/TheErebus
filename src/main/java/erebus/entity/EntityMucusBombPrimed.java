package erebus.entity;

import java.util.List;

import erebus.Erebus;
import erebus.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMucusBombPrimed extends Entity {

	private static final DataParameter<Integer> FUSE = EntityDataManager.<Integer>createKey(EntityMucusBombPrimed.class, DataSerializers.VARINT);

	private int fuse;

	public EntityMucusBombPrimed(World world) {
		super(world);
		fuse = 80;
		preventEntitySpawning = true;
		isImmuneToFire = true;
		setSize(0.98F, 0.98F);
	}

	public EntityMucusBombPrimed(World world, double x, double y, double z) {
		this(world);
		setPosition(x, y, z);
		float f = (float) (Math.random() * (Math.PI * 2D));
		motionX = (double) (-((float) Math.sin((double) f)) * 0.02F);
		motionY = 0.20000000298023224D;
		motionZ = (double) (-((float) Math.cos((double) f)) * 0.02F);
		setFuse(80);
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
	}

	@Override
	protected void entityInit() {
		dataManager.register(FUSE, Integer.valueOf(80));
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (!hasNoGravity())
			motionY -= 0.03999999910593033D;

		move(MoverType.SELF, motionX, motionY, motionZ);
		motionX *= 0.9800000190734863D;
		motionY *= 0.9800000190734863D;
		motionZ *= 0.9800000190734863D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
			motionY *= -0.5D;
		}

		--fuse;

		if (fuse <= 0) {
			setDead();

			if (!getEntityWorld().isRemote)
				explode();
			if (getEntityWorld().isRemote)
				spawnGooeyTypeParticles();

		} else {
			handleWaterMovement();
			getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	@SuppressWarnings("unchecked")
	private void explode() {
		List<Entity> list = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX - 0.5D, posY - 0.5D, posZ - 0.5D, posX + 0.5D, posY + 0.5D, posZ + 0.5D).grow(4D, 4D, 4D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
			if (entity != null)
				if (entity instanceof EntityLivingBase) {
					entity.addVelocity(-MathHelper.sin(entity.rotationYaw * 3.141593F / 180.0F) * 2.0D, 1D, MathHelper.cos(entity.rotationYaw * 3.141593F / 180.0F) * 2.0D);
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10 * 20, 3));
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 10 * 20, 0));
					getEntityWorld().playSound(null, getPosition(), ModSounds.BEETLE_LARVA_SPLAT, SoundCategory.BLOCKS, 1.0F, 0.5F);
					getEntityWorld().playSound(null, getPosition(), ModSounds.SQUISH, SoundCategory.BLOCKS, 1.0F, 0.7F);
				}
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setShort("Fuse", (short) getFuse());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		setFuse(compound.getShort("Fuse"));
	}

	public float getEyeHeight() {
		return 0.0F;
	}

	public void setFuse(int fuseIn) {
		dataManager.set(FUSE, fuseIn);
		fuse = fuseIn;
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (FUSE.equals(key))
			fuse = getFuseDataManager();
	}

	public int getFuseDataManager() {
		return dataManager.get(FUSE);
	}

	public int getFuse() {
		return fuse;
	}

	@SideOnly(Side.CLIENT)
	public void spawnGooeyTypeParticles() {
		for (int a = 0; a < 360; a += 6) {
			double ang = a * Math.PI / 180D;
			Erebus.PROXY.spawnCustomParticle("repellent", getEntityWorld(), posX + -MathHelper.sin((float) ang) * 1.0, posY + 0.5D, posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.3, 0D, MathHelper.cos((float) ang) * 0.3);
		}

		for (int a = 0; a < 360; a += 4) {
			double ang = a * Math.PI / 180D;
			Erebus.PROXY.spawnCustomParticle("slime", getEntityWorld(), posX + -MathHelper.sin((float) ang) * 1.0, posY + 1D, posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.3, 0.1D, MathHelper.cos((float) ang) * 0.3);
			Erebus.PROXY.spawnCustomParticle("slime", getEntityWorld(), posX + -MathHelper.sin((float) ang) * 1.0, posY + 0.5D, posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.4, 0D, MathHelper.cos((float) ang) * 0.4);
		}
	}
}