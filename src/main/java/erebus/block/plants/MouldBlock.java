package erebus.block.plants;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;

public class MouldBlock extends WallPlantsAbstract {

	public static final MapCodec<MouldBlock> CODEC = simpleCodec(MouldBlock::new);

	public MouldBlock(Properties properties) {
		super(properties);
	}

    @Override
	protected @NotNull MapCodec<MouldBlock> codec() {
		return CODEC;
	}

    @Override
	public boolean shouldScheduleTick() {
		return false;
	}
}
