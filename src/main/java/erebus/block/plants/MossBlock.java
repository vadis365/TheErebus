package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class MossBlock extends WallPlantsAbstract {

	public static final MapCodec<MossBlock> CODEC = simpleCodec(MossBlock::new);

	public MossBlock(Properties properties) {
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

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}
}
