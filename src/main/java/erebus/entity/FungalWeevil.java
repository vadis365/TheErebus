package erebus.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class FungalWeevil extends Weevil {

	public FungalWeevil(EntityType<? extends Weevil> type, Level level) {
		super(type, level);
	}

	public static boolean canSpawnHereAlt(EntityType<FungalWeevil> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}
/*
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
	*/

	/* TODO LOOT TABLES
	@Override
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
*/
}
