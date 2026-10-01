package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBiomeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;

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
            ClientParticles.spawnParticles(ClientParticles.ParticleType.SPORES, getX() + (random.nextDouble() - 0.5D) * getBbWidth(), getBoundingBox().minY + random.nextDouble() * getBbHeight() - 0.25D, getZ() + (random.nextDouble() - 0.5D) * getBbWidth(), 1.0D + random.nextDouble(), 1.0D + random.nextDouble(), 1.0D + random.nextDouble());
        if (level() instanceof ServerLevel server && isAlive()) {
            if (random.nextInt(200) == 0) {
                BlockPos pos = blockPosition();
                if (server.isInWorldBounds(pos) && server.getWorldBorder().isWithinBounds(pos)
                        && server.isEmptyBlock(pos) && server.getBiome(pos).is(ModBiomeTags.IS_FUNGAL_FOREST)
                        && EventHooks.canEntityGrief(server, this)) {
                    BlockState mushroom = getMushroomToPlace();
                    if (mushroom.canSurvive(server, pos)) server.setBlockAndUpdate(pos, mushroom);
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
