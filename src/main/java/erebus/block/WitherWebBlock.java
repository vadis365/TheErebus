package erebus.block;

import erebus.entity.BlackWidow;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class WitherWebBlock extends WebBlock {

	public WitherWebBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
		Vec3 vec3 = new Vec3(0.25, 0.05F, 0.25);
		if (entity instanceof LivingEntity livingentity && livingentity.hasEffect(MobEffects.WEAVING))
			vec3 = new Vec3(0.5, 0.25, 0.5);

		if (entity instanceof LivingEntity livingentity && !(livingentity instanceof BlackWidow))
			livingentity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));

		entity.makeStuckInBlock(state, vec3);
	}

}
