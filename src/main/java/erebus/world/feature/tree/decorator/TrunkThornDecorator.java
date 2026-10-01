package erebus.world.feature.tree.decorator;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.tree.ModTreeDecorators;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class TrunkThornDecorator extends TreeDecorator {
    public static final TrunkThornDecorator INSTANCE = new TrunkThornDecorator();
    public static final MapCodec<TrunkThornDecorator> CODEC = MapCodec.unit(() -> INSTANCE);

    public static void placeThorn(Context context, BlockPos pos, BooleanProperty sideProperty) {
        var thorn = ModBlocks.THORNS.get().defaultBlockState().setValue(sideProperty, true);
        if (thorn.canSurvive(context.level(), pos)) context.setBlock(pos, thorn);
    }

    protected @NotNull TreeDecoratorType<?> type() {
        return ModTreeDecorators.TRUNK_THORN_DECORATOR.get();
    }

    public void place(TreeDecorator.Context context) {
        if (context.logs().isEmpty()) return;
        int baseY = context.logs().getFirst().getY();
        var footprint = new HashSet<BlockPos>();
        for (var log : context.logs()) if (log.getY() == baseY) footprint.add(log);
        RandomSource randomsource = context.random();
        context.logs().forEach((pos) -> {
            if (pos.getY() == baseY || !footprint.contains(new BlockPos(pos.getX(), baseY, pos.getZ()))) return;
            if (randomsource.nextInt(3) > 0) {
                BlockPos blockpos = pos.west();
                if (context.isAir(blockpos)) {
                    placeThorn(context, blockpos, VineBlock.EAST);
                }
            }

            if (randomsource.nextInt(3) > 0) {
                BlockPos blockpos1 = pos.east();
                if (context.isAir(blockpos1)) {
                    placeThorn(context, blockpos1, VineBlock.WEST);
                }
            }

            if (randomsource.nextInt(3) > 0) {
                BlockPos blockpos2 = pos.north();
                if (context.isAir(blockpos2)) {
                    placeThorn(context, blockpos2, VineBlock.SOUTH);
                }
            }

            if (randomsource.nextInt(3) > 0) {
                BlockPos blockpos3 = pos.south();
                if (context.isAir(blockpos3)) {
                    placeThorn(context, blockpos3, VineBlock.NORTH);
                }
            }
        });
    }
}
