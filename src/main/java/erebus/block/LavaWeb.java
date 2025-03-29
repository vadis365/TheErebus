package erebus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class LavaWeb extends WebBlock {

	public LavaWeb(Properties properties) {
		super(properties);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		Vec3 vec3 = new Vec3(0.25, 0.05F, 0.25);
		if (entity instanceof LivingEntity livingentity && livingentity.hasEffect(MobEffects.WEAVING))
			vec3 = new Vec3(0.5, 0.25, 0.5);

		if (entity instanceof LivingEntity livingentity)
			((LivingEntity) entity).setRemainingFireTicks(100);

		entity.makeStuckInBlock(state, vec3);
	}

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockPos blockpos = pos.above();
        if (level.getBlockState(blockpos).isAir() && !level.getBlockState(blockpos).isSolidRender(level, blockpos)) {
            if (random.nextInt(50) == 0 && level.isClientSide()) {
                double x = (double)pos.getX() + random.nextDouble();
                double y = (double)pos.getY() + 1.0;
                double z = (double)pos.getZ() + random.nextDouble();
                level.addParticle(ParticleTypes.LAVA, x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }
}
