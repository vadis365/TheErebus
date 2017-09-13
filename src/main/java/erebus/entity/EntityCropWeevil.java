package erebus.entity;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.blocks.EnumWood;
import erebus.core.handler.configs.ConfigHandler;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityCropWeevil extends EntityCreature {

	public EntityCropWeevil(World world) {
		super(world);
		setSize(1.0F, 0.5F);
		setPathPriority(PathNodeType.WATER, -8F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 10.0F, 0.7D, 0.5D));
		tasks.addTask(2, new EntityAIAvoidEntity(this, EntityMob.class, 10.0F, 0.7D, 0.5D));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		tasks.addTask(4, new EntityAIPanic(this, 0.7F));
		tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public int getTotalArmorValue() {
		return 4;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BEETLE_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.BEETLE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		switch (rand.nextInt(5)) {
			case 0:
				entityDropItem(new ItemStack(ModBlocks.GIANT_FLOWER_STIGMA, 1 + rand.nextInt(3) + looting, rand.nextInt(14)), 0F);
				break;
			case 1:
				ItemStack seed = ForgeHooks.getGrassSeed(rand, rand.nextInt(3) + looting);
				if (seed != null)
					entityDropItem(seed, 0F);
				break;
			case 2:
				entityDropItem(new ItemStack(Items.PUMPKIN_SEEDS, 1 + rand.nextInt(3) + looting), 0F);
				break;

			case 3:
				entityDropItem(new ItemStack(Items.MELON_SEEDS, 1 + rand.nextInt(3) + looting), 0F);
				break;

			case 4:
				entityDropItem(new ItemStack(Items.DYE, 1 + rand.nextInt(3) + looting, 3), 0F);
				break;
		}

		if (rand.nextInt(10) == 0) {
			int dropRareishType = rand.nextInt(7);
			switch (dropRareishType) {
				case 0:
					entityDropItem(new ItemStack(ModItems.TURNIP, 1 + looting), 0F);
					break;
				case 1:
					entityDropItem(new ItemStack(Items.NETHER_WART, 1 + looting), 0F);
					break;

				case 2:
					entityDropItem(new ItemStack(Items.WHEAT, 1 + looting), 0F);
					break;

				case 3:
					entityDropItem(new ItemStack(Items.REEDS, 1 + looting), 0F);
					break;

				case 4:
					entityDropItem(new ItemStack(EnumWood.BAMBOO.getSapling(), (1 + looting)), 0F);
					break;

				case 5:
					entityDropItem(new ItemStack(Items.CARROT, 1 + looting), 0F);
					break;

				case 6:
					entityDropItem(new ItemStack(Items.POTATO, 1 + looting), 0F);
					break;
			}
		}
	}
}