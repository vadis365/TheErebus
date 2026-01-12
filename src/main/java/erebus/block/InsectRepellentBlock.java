package erebus.block;

import erebus.registries.client.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        double d = 0.0625D;

        for(int c = 0; c < 6; c++) {
            double x = pos.getX() + random.nextFloat();
            double y = pos.getY() + random.nextFloat();
            double z = pos.getZ() + random.nextFloat();

            if(c == 0 && !level.getBlockState(pos.above()).isCollisionShapeFullBlock(level, pos.above())) {
                y = pos.getY() + 1 + d;
            }

            if(c == 1 && !level.getBlockState(pos.below()).isCollisionShapeFullBlock(level, pos.below())) {
                y = pos.getY() - d;
            }

            if(c == 2 && !level.getBlockState(pos.east()).isCollisionShapeFullBlock(level, pos.east())) {
                z = pos.getZ() + 1 + d;
            }

            if(c == 3 && !level.getBlockState(pos.west()).isCollisionShapeFullBlock(level, pos.west())) {
                z = pos.getZ() - d;
            }

            if(c == 4 && !level.getBlockState(pos.north()).isCollisionShapeFullBlock(level, pos.north())) {
                x = pos.getX() - d;
            }

            if(c == 5 && !level.getBlockState(pos.south()).isCollisionShapeFullBlock(level, pos.south())) {
                x = pos.getX() + 1 + d;
            }

            if(x < pos.getX() || x > pos.getX() + 1 || y < 0 || y > pos.getY() + 1 || z < pos.getZ() || z > pos.getZ() + 1) {
                level.addParticle(ModParticles.REPELLENT.get(), x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }

        super.animateTick(state, level, pos, random);
    }

    @Override
    protected void entityInside(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, Entity entity, @NonNull InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if(entity.is(EntityTypeTags.ARTHROPOD)) {
            entity.push(new Vec3(
                    Mth.sin((float) (entity.getYRot() * Math.PI / 180.0F)) * 0.1F,
                    0.1F,
                    Mth.cos((float) (entity.getYRot() * Math.PI / 180.0F)) * 0.1F

            ));
        }
    }
}
