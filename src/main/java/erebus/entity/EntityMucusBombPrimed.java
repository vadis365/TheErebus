package erebus.entity;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMucusBombPrimed extends Entity {

	public int fuse = 80;

	public EntityMucusBombPrimed(World world) {
		super(world);
		setSize(0.98F, 0.98F);
		yOffset = height / 2.0F;
		preventEntitySpawning = true;
	}

	public EntityMucusBombPrimed(World world, double x, double y, double z) {
		this(world);
		setPosition(x, y, z);
		float f = (float) (Math.random() * Math.PI * 2.0D);
		motionX = -((float) Math.sin(f)) * 0.02F;
		motionY = 0.20000000298023224D;
		motionZ = -((float) Math.cos(f)) * 0.02F;
		fuse = 80;
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
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
		return !isDead;
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY -= 0.03999999910593033D;
		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.9800000190734863D;
		motionY *= 0.9800000190734863D;
		motionZ *= 0.9800000190734863D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
			motionY *= -0.5D;
		}

		if (fuse-- <= 0) {
			setDead();

			if (!worldObj.isRemote)
				explode();
			if (worldObj.isRemote)
				spawnGooeyTypeParticles();

		} else
			worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
	}

	@SuppressWarnings("unchecked")
	private void explode() {
		List<Entity> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(posX - 0.5D, posY - 0.5D, posZ - 0.5D, posX + 0.5D, posY + 0.5D, posZ + 0.5D).expand(4D, 4D, 4D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
			if (entity != null)
				if (entity instanceof EntityLivingBase) {
					entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * 2.0D, 1D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * 2.0D);
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10 * 20, 3));
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 10 * 20, 0));
					worldObj.playSoundAtEntity(this, "erebus:beetlelarvasplat", 1.0F, 0.5F);
					worldObj.playSoundAtEntity(this, "erebus:squish", 1.0F, 0.7F);
				}
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setByte("Fuse", (byte) fuse);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		fuse = nbt.getByte("Fuse");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}

	@SideOnly(Side.CLIENT)
	public void spawnGooeyTypeParticles() {
		for (int a = 0; a < 360; a += 6) {
			double ang = a * Math.PI / 180D;
			Erebus.proxy.spawnCustomParticle("repellent", worldObj, posX + -MathHelper.sin((float) ang) * 1.0, posY + 0.5D, posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.3, 0D, MathHelper.cos((float) ang) * 0.3);
		}

		for (int a = 0; a < 360; a += 4) {
			double ang = a * Math.PI / 180D;
			Erebus.proxy.spawnCustomParticle("slime", worldObj, posX + -MathHelper.sin((float) ang) * 1.0, posY + 1D, posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.3, 0.1D, MathHelper.cos((float) ang) * 0.3);
			Erebus.proxy.spawnCustomParticle("slime", worldObj, posX + -MathHelper.sin((float) ang) * 1.0, posY + 0.5D, posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.4, 0D, MathHelper.cos((float) ang) * 0.4);
		}
	}
}