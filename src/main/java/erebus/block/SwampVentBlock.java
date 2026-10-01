package erebus.block;

import erebus.registries.client.ModParticles;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SwampVentBlock extends Block {
    public SwampVentBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isEmptyBlock(pos.above())) return;
        var gas = ModEntities.SWAMP_VENT_GAS.get().create(level, EntitySpawnReason.TRIGGERED);
        if (gas == null) return;
        gas.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
        if (level.addFreshEntity(gas)) level.playSound(null, pos, SoundEvents.GHAST_SHOOT, SoundSource.BLOCKS,
                0.5F, 0.1F + (random.nextFloat() - random.nextFloat()) * 0.8F);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (!level.isEmptyBlock(pos.above())) return;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        level.addParticle(ModParticles.SWAMP_VENT.get(), x + 0.5F, y + 1F, z + 0.5F, 0.1D, 0.0D, 0.1D);
        if (random.nextInt(5) == 0) {
            level.playLocalSound(
                    x,
                    y,
                    z,
                    SoundEvents.BUBBLE_COLUMN_BUBBLE_POP,
                    SoundSource.BLOCKS,
                    0.2F + random.nextFloat() * 0.2F,
                    0.9F + random.nextFloat() * 0.15F,
                    false
            );
        }
    }
}
