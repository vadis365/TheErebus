package erebus.entity;

import erebus.ModItems;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityWheatWeevil extends EntityCreature {

	public EntityWheatWeevil(World world) {
		super(world);
		stepHeight = 0.0F;
		setSize(1.0F, 0.5F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 10.0F, 0.7D, 0.5D));
		tasks.addTask(2, new EntityAIAvoidEntity(this, EntityMob.class, 10.0F, 0.7D, 0.5D));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		tasks.addTask(4, new EntityAIPanic(this, 0.7F));
		tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
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
		return 2;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D); // MaxHealth
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:beetlesound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:beetlehurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		switch (rand.nextInt(5)) {
			case 0:
				entityDropItem(new ItemStack(ModItems.flowerSeeds, 1 + rand.nextInt(3) + looting, rand.nextInt(14)), 0F);
				break;
			case 1:
				ItemStack seed = ForgeHooks.getGrassSeed(worldObj);
				if (seed != null) {
					seed.stackSize = 1 + rand.nextInt(3) + looting;
					entityDropItem(seed, 0F);
				}
				break;
			case 2:
				entityDropItem(new ItemStack(Items.pumpkin_seeds, 1 + rand.nextInt(3) + looting), 0F);
				break;

			case 3:
				entityDropItem(new ItemStack(Items.melon_seeds, 1 + rand.nextInt(3) + looting), 0F);
				break;

			case 4:
				entityDropItem(new ItemStack(Items.dye, 1 + rand.nextInt(3) + looting, 3), 0F);
				break;
		}

		if (rand.nextInt(10) == 0) {
			int dropRareishType = rand.nextInt(7);
			switch (dropRareishType) {
				case 0:
					entityDropItem(new ItemStack(ModItems.turnip, 1 + looting), 0F);
					break;
				case 1:
					entityDropItem(new ItemStack(Items.nether_wart, 1 + looting), 0F);
					break;

				case 2:
					entityDropItem(new ItemStack(Items.wheat, 1 + looting), 0F);
					break;

				case 3:
					entityDropItem(new ItemStack(Items.reeds, 1 + looting), 0F);
					break;

				case 4:
					entityDropItem(ItemMaterials.DATA.bambooShoot.makeStack(1 + looting), 0F);
					break;

				case 5:
					entityDropItem(new ItemStack(Items.carrot, 1 + looting), 0F);
					break;

				case 6:
					entityDropItem(new ItemStack(Items.potato, 1 + looting), 0F);
					break;
			}
		}
	}
}