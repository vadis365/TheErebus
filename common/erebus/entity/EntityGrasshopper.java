package erebus.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import erebus.ModItems;
import erebus.entity.ai.EntityAIEatCrops;
import erebus.network.PacketHandler;
import erebus.network.packet.PacketParticle;

public class EntityGrasshopper extends EntityCreature {

	protected EntityLiving theEntity;
	private ChunkCoordinates currentJumpTarget;
	private final EntityAIWander aiWander = new EntityAIWander(this, 0.6D);
	public EntityAIEatCrops aiEatCrops = new EntityAIEatCrops(this, 0.6D);
	public boolean isEating;
	public boolean canJump = true;

	public EntityGrasshopper(World world) {
		super(world);
		stepHeight = 1.0F;
		jumpMovementFactor = 0.1F;
		setSize(1.3F, 0.5F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(2, aiWander);
		tasks.addTask(3, aiEatCrops);
		tasks.addTask(4, new EntityAIPanic(this, 0.8D));
		tasks.addTask(5, new EntityAILookIdle(this));

	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // Max
		// Health
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:grasshoppersound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:grasshopperhurt";
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
		if (isBurning())
			entityDropItem(new ItemStack(ModItems.erebusFood, 1, 3), 0.0F);
		else
			entityDropItem(new ItemStack(ModItems.erebusFood, 1, 2), 0.0F);
	}

	public boolean randJump() {
		return rand.nextInt(50) == 0;
	}

	@Override
	protected void jump() {
		motionY = 0.61999998688697815D;
	}

	public void setIsEating(boolean isEating) {
		this.isEating = isEating;
	}

	public void setMoveTasks(boolean par1) {
		if (par1 == false)
			tasks.removeTask(aiWander);

		if (par1 == true)
			tasks.addTask(2, aiWander);
	}

	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}

	@Override
	protected void fall(float par1) {
	}

	protected void jumpMovevement() {
		if (currentJumpTarget != null && (!worldObj.isAirBlock(currentJumpTarget.posX, currentJumpTarget.posY, currentJumpTarget.posZ) || currentJumpTarget.posY < 1))
			currentJumpTarget = null;
		if (currentJumpTarget == null || rand.nextInt(30) == 0 || currentJumpTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 4.0F)
			currentJumpTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));
		double d0 = currentJumpTarget.posX + 0.5D - posX;
		double d2 = currentJumpTarget.posZ + 0.5D - posZ;
		motionX += (Math.signum(d0) * 0.5D - motionX) * 0.10000000149011612D;
		motionZ += (Math.signum(d2) * 0.5D - motionZ) * 0.10000000149011612D;
		float f = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float f1 = MathHelper.wrapAngleTo180_float(f - rotationYaw);
		moveForward = 0.2F;
		rotationYaw += f1;
		if (rand.nextInt(100) == 0 && worldObj.isBlockNormalCube(MathHelper.floor_double(posX), (int) posY + 1, MathHelper.floor_double(posZ))) {
			motionY = 0;
			setPositionAndUpdate(posX, posY, posZ);
		}
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote && onGround && randJump() && !isEating && canJump)
			jump();
		if (!worldObj.isRemote && motionY < 0 && !onGround && !isEating)
			jumpMovevement();
		super.onLivingUpdate();
	}

	public void munchBlock() {
		if (isEating && worldObj.getWorldTime() % 5 == 0)
			PacketDispatcher.sendPacketToAllAround(
			posX,
			posY,
			posZ,
			64D,
			dimension,
			PacketHandler.buildPacket(2, PacketParticle.BEETLE_LARVA_AND_GRASSHOPPER_EAT, entityId, aiEatCrops.PlantX, aiEatCrops.PlantY, aiEatCrops.PlantZ, worldObj.getBlockId(aiEatCrops.PlantX, aiEatCrops.PlantY, aiEatCrops.PlantZ),
			Byte.valueOf((byte) worldObj.getBlockMetadata(aiEatCrops.PlantX, aiEatCrops.PlantY, aiEatCrops.PlantZ))));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}
}
