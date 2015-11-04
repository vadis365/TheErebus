package erebus.entity;

import erebus.ModBiomes;
import erebus.world.biomes.decorators.BiomeDecoratorFungalForest;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCrushling extends EntityAnimal {

	public EntityCrushling(World world) {
		super(world);
		setSize(0.3F, 0.5F);
		stepHeight = 0.0F;
		getNavigator().setBreakDoors(true);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 10.0F, 0.4D, 0.5D));
		tasks.addTask(2, new EntityAIMoveIndoors(this));
		tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		tasks.addTask(4, new EntityAIOpenDoor(this, true));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.4D));
		tasks.addTask(6, new EntityAIPanic(this, 0.6D));
		tasks.addTask(7, new EntityAITempt(this, 0.4D, Items.wheat, false));
		tasks.addTask(8, new EntityAIWander(this, 0.4D));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(10, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		worldObj.spawnParticle("reddust", posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());

		if (rand.nextInt(200) == 0) {
			int x = MathHelper.floor_double(posX);
			int y = MathHelper.floor_double(posY);
			int z = MathHelper.floor_double(posZ);

			if (worldObj.isAirBlock(x, y, z) && worldObj.getBiomeGenForCoords(x, z).biomeID == ModBiomes.fungalForestID && Blocks.brown_mushroom.canPlaceBlockAt(worldObj, x, y, z)) {
				int mush = rand.nextInt(3);
				if (mush != 0)
					worldObj.setBlock(x, y, z, Blocks.brown_mushroom);
				else
					worldObj.setBlock(x, y, z, BiomeDecoratorFungalForest.mushrooms[rand.nextInt(BiomeDecoratorFungalForest.mushrooms.length)], 0, 3);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:sporelingliving";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:sporelinghurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:sporelingdeath";
	}

	@Override
	protected Item getDropItem() {
		return Items.bone;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable child) {
		return null;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}
}