package erebus.entity;

import erebus.Erebus;
import erebus.ModBiomes;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.biomes.decorators.BiomeDecoratorFungalForest;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFungalWeevil extends EntityCreature {

	public EntityFungalWeevil(World world) {
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
	public void onLivingUpdate() {
		if (getEntityWorld().isRemote)
			Erebus.PROXY.spawnCustomParticle("spores", getEntityWorld(), posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble(), 1.0D + rand.nextDouble());
		if (!getEntityWorld().isRemote) {
			if (rand.nextInt(200) == 0) {
				if (getEntityWorld().isAirBlock(getPosition()) && getEntityWorld().getBiome(getPosition()) == ModBiomes.FUNGAL_FOREST && Blocks.BROWN_MUSHROOM.canPlaceBlockAt(getEntityWorld(), getPosition())) {
					int mush = rand.nextInt(3);
					if (mush == 0)
						getEntityWorld().setBlockState(getPosition(), Blocks.BROWN_MUSHROOM.getDefaultState());
					if (mush == 1)
						getEntityWorld().setBlockState(getPosition(), Blocks.RED_MUSHROOM.getDefaultState());
					else
						getEntityWorld().setBlockState(getPosition(), BiomeDecoratorFungalForest.MUSHROOMS[rand.nextInt(BiomeDecoratorFungalForest.MUSHROOMS.length)].getDefaultState(), 3);
				}
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int mush = rand.nextInt(7);
		if (mush == 0)
			entityDropItem(new ItemStack(Blocks.BROWN_MUSHROOM), 0F);
		if (mush == 1)
			entityDropItem(new ItemStack(Blocks.RED_MUSHROOM), 0F);
		else
			entityDropItem(new ItemStack(BiomeDecoratorFungalForest.MUSHROOMS[rand.nextInt(BiomeDecoratorFungalForest.MUSHROOMS.length)]), 0F);
	}
}