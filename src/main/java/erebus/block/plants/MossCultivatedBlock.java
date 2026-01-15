package erebus.block.plants;

import erebus.registries.blocks.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class MossCultivatedBlock extends WallPlantsAbstract {
	// REMEMBER TO SET THE CULTIVATED BLOCKS NOT TO RANDOM TICK ON REG!
	public static final MapCodec<MossCultivatedBlock> CODEC = simpleCodec(MossCultivatedBlock::new);

	public MossCultivatedBlock(Properties properties) {
		super(properties);
	}

    @Override
	protected @NotNull MapCodec<MossCultivatedBlock> codec() {
		return CODEC;
	}

    @Override
	public boolean shouldScheduleTick() {
		return true;
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

    @Override
	public boolean isValidBlock(BlockState state) {
		return state.is(ModBlocks.LOG_ROTTEN.get());
	}
}
