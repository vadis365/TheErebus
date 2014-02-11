package erebus.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.item.ItemErebusMaterial;

public class EntityWorkerBee extends EntityCreature {
	private ChunkCoordinates currentFlightTarget;
	public float wingFloat;
	private final AnimationMathHelper mathWings = new AnimationMathHelper();
	private boolean beeFlying;

	public EntityWorkerBee(World world) {
		super(world);
		setSize(1.5F, 1.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected String getLivingSound() {
		return "erebus:WaspSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:WaspHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4) {
		worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataWaspSting), 0.0F);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, ItemErebusMaterial.dataExoPlate), 0.0F);
	}

	public boolean isFlying() {
		return !onGround;
	}

	@Override
	public void onUpdate() {
		if (!isFlying())
			wingFloat = 0.0F;
		else
			wingFloat = mathWings.swing(4.0F, 0.1F);

		if (motionY < 0.0D)
			motionY *= 0.5D;

		if (!worldObj.isRemote)
			if (rand.nextInt(200) == 0)
				if (!beeFlying)
					setBeeFlying(true);
				else
					setBeeFlying(false);

		if (!worldObj.isRemote) {
			if (beeFlying)
				flyAbout();
			else
				land();
		}
		super.onUpdate();
	}

	public void setBeeFlying(boolean state) {
		beeFlying = state;
	}

	protected void flyAbout() {
		if (currentFlightTarget != null && (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1))
			currentFlightTarget = null;

		if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 10.0F)
			currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));

		double var1 = currentFlightTarget.posX + 0.5D - posX;
		double var3 = currentFlightTarget.posY + 0.1D - posY;
		double var5 = currentFlightTarget.posZ + 0.5D - posZ;
		motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
		motionY += (Math.signum(var3) * 0.699999988079071D - motionY) * 0.10000000149011612D;
		motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
		float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
		moveForward = 0.5F;
		rotationYaw += var8;
	}

	private void land() {
		//Nothing to see here - yet
	}
}