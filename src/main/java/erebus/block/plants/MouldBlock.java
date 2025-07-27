package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

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
