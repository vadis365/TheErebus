package erebus.entity;

import erebus.registries.blocks.ModBlocks;
import erebus.client.particle.ClientParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FungalWeevil extends Weevil {

	public FungalWeevil(EntityType<? extends Weevil> type, Level level) {
		super(type, level);
	}

	public static boolean canSpawnHereAlt(EntityType<FungalWeevil> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Override
	public void tick() {
		if (level().isClientSide())
			ClientParticles.spawnCustomParticle("spores", getX() + (random.nextDouble() - 0.5D) * getBbWidth(), getBoundingBox().minY + random.nextDouble() * getBbHeight() - 0.25D, getZ() + (random.nextDouble() - 0.5D) * getBbWidth(), 1.0D + random.nextDouble(), 1.0D + random.nextDouble(), 1.0D + random.nextDouble());
		if (!level().isClientSide()) {
			if (random.nextInt(200) == 0) {
				BlockState state = level().getBlockState(blockPosition().below());
				// TODO Add all biomes to Tags as individuals just in case we need them for anything else later
				if (level().isEmptyBlock(blockPosition()) && level().getBiome(blockPosition()).is(ModTags.IS_FUNGAL_FOREST) && state.is(BlockTags.DIRT)) {
					// TODO Replace this to pull one of the random mushrooms from its loot table drop and plant it as a block
					level().setBlockAndUpdate(blockPosition(), getMushroomToPlace());
				}
			}
		}
		super.tick();
	}

    public BlockState getMushroomToPlace() {
    	switch (random.nextInt(7)) {
    	case 0 : return Blocks.BROWN_MUSHROOM.defaultBlockState();
    	case 1 : return Blocks.RED_MUSHROOM.defaultBlockState();
    	case 2 : return ModBlocks.DARK_CAPPED_MUSHROOM.get().defaultBlockState();
    	case 3 : return ModBlocks.DUTCH_CAP_MUSHROOM.get().defaultBlockState();
    	case 4 : return ModBlocks.GRANDMAS_SHOES_MUSHROOM.get().defaultBlockState();
    	case 5 : return ModBlocks.KAIZERS_FINGERS_MUSHROOM.get().defaultBlockState();
    	case 6 : return ModBlocks.SARCASTIC_CZECH_MUSHROOM.get().defaultBlockState();
    	default : return Blocks.BROWN_MUSHROOM.defaultBlockState();
    	}
    }

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
