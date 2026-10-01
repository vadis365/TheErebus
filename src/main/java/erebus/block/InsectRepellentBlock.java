package erebus.block;

import erebus.registries.client.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class InsectRepellentBlock extends Block {

    public InsectRepellentBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Block.box(0, 0, 0, 16, 2, 16);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
                                     Direction side, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return side == Direction.DOWN && !canSurvive(state, level, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        for (Direction side : Direction.values()) {
            BlockPos neighbor = pos.relative(side);
            if (level.getBlockState(neighbor).isCollisionShapeFullBlock(level, neighbor)) continue;
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble();
            switch (side) {
                case DOWN -> y = pos.getY() - 0.0625;
                case UP -> y = pos.getY() + 1.0625;
                case NORTH -> z = pos.getZ() - 0.0625;
                case SOUTH -> z = pos.getZ() + 1.0625;
                case WEST -> x = pos.getX() - 0.0625;
                case EAST -> x = pos.getX() + 1.0625;
            }
            level.addParticle(ModParticles.REPELLENT.get(), x, y, z, 0, 0, 0);
        }
    }

    @Override
    protected void entityInside(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, Entity entity, @NonNull InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (!level.isClientSide() && entity instanceof Mob && entity.is(EntityTypeTags.ARTHROPOD)) {
            entity.push(new Vec3(
                    Mth.sin((float) (entity.getYRot() * Math.PI / 180.0F)) * 0.1F,
                    0.1F,
                    Mth.cos((float) (entity.getYRot() * Math.PI / 180.0F)) * 0.1F

            ));
        }
    }
}
