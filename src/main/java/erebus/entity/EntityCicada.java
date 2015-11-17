package erebus.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.item.ItemMaterials;
import erebus.lib.EnumWood;

public class EntityCicada extends EntityCreature {

	private int sonics;
	public ChunkCoordinates currentFlightTarget;
	public float wingFloat;
	public boolean cicadaFlying;
	AnimationMathHelper mathWings = new AnimationMathHelper();

	public EntityCicada(World world) {
		super(world);
		setSize(1.0F, 0.4F);
		stepHeight = 0.0F;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(8.0D);
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere() {
		AxisAlignedBB axisalignedbb = boundingBox.expand(1D, 1D, 1D);
		int n = MathHelper.floor_double(axisalignedbb.minX);
		int o = MathHelper.floor_double(axisalignedbb.maxX + 1.0D);
		int p = MathHelper.floor_double(axisalignedbb.minY);
		int q = MathHelper.floor_double(axisalignedbb.maxY + 1.0D);
		int n1 = MathHelper.floor_double(axisalignedbb.minZ);
		int o1 = MathHelper.floor_double(axisalignedbb.maxZ + 1.0D);
		for (int p1 = n; p1 < o; p1++)
			for (int q1 = p; q1 < q; q1++)
				for (int n2 = n1; n2 < o1; n2++) {
					Block o2 = worldObj.getBlock(p1, q1, n2);
					if (o2 == null)
						continue;
					if (o2 == EnumWood.Cypress.getLog())
						return true;
				}
		return false;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 10;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (recentlyHit) {
			int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
			int amount;
			for (amount = 0; amount < chance; ++amount)
				entityDropItem(ItemMaterials.DATA.repellent.makeStack(), 0.0F);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		findEnemyToAttack();
		if (sonics <= 20)
			sonics++;
		if (sonics > 20)
			sonics = 0;
		if (sonics > 10)
			entityToAttack = null;

		if (!isFlying())
			wingFloat = 0.0F;
		else
			wingFloat = mathWings.swing(4.0F, 0.1F);

		if (motionY < 0.0D)
			motionY *= 0.6D;

		if (!worldObj.isRemote) {
			if (rand.nextInt(100) == 0)
				if (cicadaFlying)
					setCicadaFlying(false);

			if (cicadaFlying)
				flyAbout();
			else
				land();
		}

		if (worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
			setDead();
	}

	public boolean isFlying() {
		return !onGround;
	}

	public void setCicadaFlying(boolean state) {
		cicadaFlying = state;
	}

	private void land() {
		// Nothing to see here - yet
	}

	@Override
	protected void fall(float par1) {
	}

	public void flyAbout() {
		if (currentFlightTarget != null)
			if (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1)
				currentFlightTarget = null;

		if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 10F)
			currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));

		flyToTarget();
	}

	public void flyToTarget() {
		if (currentFlightTarget != null) {
			double targetX = currentFlightTarget.posX + 0.5D - posX;
			double targetY = currentFlightTarget.posY + 1D - posY;
			double targetZ = currentFlightTarget.posZ + 0.5D - posZ;
			motionX += (Math.signum(targetX) * 0.5D - motionX) * 0.10000000149011612D;
			motionY += (Math.signum(targetY) * 0.699999988079071D - motionY) * 0.10000000149011612D;
			motionZ += (Math.signum(targetZ) * 0.5D - motionZ) * 0.10000000149011612D;
			float angle = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
			float rotation = MathHelper.wrapAngleTo180_float(angle - rotationYaw);
			moveForward = 0.5F;
			rotationYaw += rotation;
		}
	}

	protected Entity findEnemyToAttack() {
		List<?> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(posX + 0.5D, posY + 0.5D, posZ + 0.5D, posX + 0.5D, posY + 0.5D, posZ + 0.5D).expand(sonics * 0.2D, 0.5D, sonics * 0.2D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity instanceof EntityPlayer && !((EntityPlayer) entity).capabilities.isCreativeMode) {
				if (sonics == 20) {
					if (worldObj.isRemote)
						spawnSonicParticles();
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 8 * 20, 0));
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 8 * 20, 0));
					entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * 2.0D, 0D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * 2.0D);
					worldObj.playSoundAtEntity(this, "erebus:locustspawn", 1.0F, 6.0F);
					setCicadaFlying(true);
				}
				return canEntityBeSeen(entity) ? entity : null;
			}
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	public void spawnSonicParticles() {
		for (int a = 0; a < 360; a += 6) {
			double ang = a * Math.PI / 180D;
			Erebus.proxy.spawnCustomParticle("repellent", worldObj, posX + -MathHelper.sin((float) ang) * 1.0, posY + 0.5D, posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.3, 0D, MathHelper.cos((float) ang) * 0.3);
		}

		for (int a = 0; a < 360; a += 4) {
			double ang = a * Math.PI / 180D;
			Erebus.proxy.spawnCustomParticle("sonic", worldObj, posX + -MathHelper.sin((float) ang) * 1.0, posY + 0.5D, posZ + MathHelper.cos((float) ang) * 1.0, -MathHelper.sin((float) ang) * 0.3, 0D, MathHelper.cos((float) ang) * 0.3);
		}
	}

}
