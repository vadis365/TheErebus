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

public class WitherWeb extends WebBlock {

	public WitherWeb(Properties properties) {
		super(properties);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		Vec3 vec3 = new Vec3(0.25, 0.05F, 0.25);
		if (entity instanceof LivingEntity livingentity && livingentity.hasEffect(MobEffects.WEAVING))
			vec3 = new Vec3(0.5, 0.25, 0.5);

		if (entity instanceof LivingEntity livingentity && !(livingentity instanceof BlackWidow))
			((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));

		entity.makeStuckInBlock(state, vec3);
	}

}
