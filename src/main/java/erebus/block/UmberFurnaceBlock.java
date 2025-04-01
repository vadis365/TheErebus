package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.UmberFurnaceBlockEntity;
import erebus.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class UmberFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<UmberFurnaceBlock> CODEC = simpleCodec(UmberFurnaceBlock::new);

    public UmberFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec() {
        return CODEC;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof UmberFurnaceBlockEntity) {
            player.openMenu((MenuProvider) entity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new UmberFurnaceBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createFurnaceTicker(level, type, ModBlockEntities.UMBERFURNACE.get());
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double x = pos.getX() + 0.5;
            double y = pos.getY();
            double z = pos.getZ() + 0.5;

            if (random.nextDouble() < 0.1) {
                level.playLocalSound(x, y, z, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction facing = state.getValue(FACING);
            Direction.Axis axis = facing.getAxis();

            double ran = random.nextDouble() * 0.6 - 0.3;
            double xOffset = axis == Direction.Axis.X ? facing.getStepX() * 0.52 : ran;
            double yOffset = random.nextDouble() * 6.0 / 16.0;
            double zOffset = axis == Direction.Axis.Z ? facing.getStepZ() * 0.52 : ran;

            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
            level.addParticle(ParticleTypes.FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
        }
    }
}
