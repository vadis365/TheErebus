package erebus.block.plants;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

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

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}
}
