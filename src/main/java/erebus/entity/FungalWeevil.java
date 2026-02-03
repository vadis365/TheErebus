package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBiomeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FungalWeevil extends Weevil {

	public FungalWeevil(EntityType<? extends Weevil> type, Level level) {
		super(type, level);
	}

	public static boolean canSpawnHereAlt(EntityType<FungalWeevil> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
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
				if (level().isEmptyBlock(blockPosition()) && level().getBiome(blockPosition()).is(ModBiomeTags.IS_FUNGAL_FOREST) && state.is(BlockTags.DIRT)) {
					// TODO Replace this to pull one of the random mushrooms from its loot table drop and plant it as a block
					level().setBlockAndUpdate(blockPosition(), getMushroomToPlace());
				}
			}
		}
		super.tick();
	}

    public BlockState getMushroomToPlace() {
        return switch (random.nextInt(7)) {
            case 1 -> Blocks.RED_MUSHROOM.defaultBlockState();
            case 2 -> ModBlocks.DARK_CAPPED_MUSHROOM.get().defaultBlockState();
            case 3 -> ModBlocks.DUTCH_CAP_MUSHROOM.get().defaultBlockState();
            case 4 -> ModBlocks.GRANDMAS_SHOES_MUSHROOM.get().defaultBlockState();
            case 5 -> ModBlocks.KAIZERS_FINGERS_MUSHROOM.get().defaultBlockState();
            case 6 -> ModBlocks.SARCASTIC_CZECH_MUSHROOM.get().defaultBlockState();
            default -> Blocks.BROWN_MUSHROOM.defaultBlockState();
        };
    }
}
