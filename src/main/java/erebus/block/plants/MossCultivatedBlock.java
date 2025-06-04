package erebus.block.plants;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;

import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import net.minecraft.world.level.block.state.BlockState;

public class MossCultivatedBlock extends WallPlantsAbstract {
	// REMEMBER TO SET THE CULTIVATED BLOCKS NOT TO RANDOM TICK ON REG!
	public static final MapCodec<MossCultivatedBlock> CODEC = simpleCodec(MossCultivatedBlock::new);

	protected MossCultivatedBlock(Properties properties) {
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
	
    @Override
	public boolean isValidBlock(BlockState state) {
		return state.is(WoodBlocks.LOG_ROTTEN.get());
	}
}
