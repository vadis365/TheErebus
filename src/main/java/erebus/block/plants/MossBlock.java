package erebus.block.plants;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;

public class MossBlock extends WallPlantsAbstract {

	public static final MapCodec<MossBlock> CODEC = simpleCodec(MossBlock::new);

	protected MossBlock(Properties properties) {
		super(properties);
	}

    @Override
	protected @NotNull MapCodec<MossBlock> codec() {
		return CODEC;
	}

    @Override
	public boolean shouldScheduleTick() {
		return false;
	}
}
