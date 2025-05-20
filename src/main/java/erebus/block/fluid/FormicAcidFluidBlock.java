package erebus.block.fluid;

import erebus.client.particle.ClientParticles;
import erebus.registries.client.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class FormicAcidFluidBlock extends LiquidBlock  {

	public FormicAcidFluidBlock(FlowingFluid fluid, Properties properties) {
		super(fluid, properties);
	}

	@Override
	public void entityInside(@Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Entity entity) {
		if (!level.isClientSide)
			if (entity instanceof LivingEntity)
				entity.hurt(entity.damageSources().generic(), 2F);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(@Nonnull BlockState stateIn, Level level, BlockPos pos, @Nonnull RandomSource rand) {
		if (level.isEmptyBlock(pos.above())) {

			float xx = (float) pos.getX() + 0.5F;
			float yy = pos.getY() + 1.0F;
			float zz = (float) pos.getZ() + 0.5F;
			float fixedOffset = 0.25F;
			float randomOffset = rand.nextFloat() * 0.6F - 0.3F;

			level.addParticle(ModParticles.SWAMP_VENT.get(), xx - fixedOffset, (double) pos.getY() + 0.75D, zz + randomOffset, 0.0D, 0.0D, 0.0D);
			level.addParticle(ModParticles.SWAMP_VENT.get(), xx + fixedOffset, (double) pos.getY() + 0.75D, zz + randomOffset, 0.0D, 0.0D, 0.0D);
			level.addParticle(ModParticles.SWAMP_VENT.get(), xx + randomOffset, (double) pos.getY() + 0.75D, zz - fixedOffset, 0.0D, 0.0D, 0.0D);
			level.addParticle(ModParticles.SWAMP_VENT.get(), xx + randomOffset, (double) pos.getY() + 0.75D, zz + fixedOffset, 0.0D, 0.0D, 0.0D);

			ClientParticles.spawnCustomParticle("swampflame", xx - fixedOffset, yy, zz + randomOffset, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("swampflame", xx + fixedOffset, yy, zz + randomOffset, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("swampflame", xx + randomOffset, yy, zz - fixedOffset, 0.0D, 0.0D, 0.0D);
			ClientParticles.spawnCustomParticle("swampflame", xx + randomOffset, yy, zz + fixedOffset, 0.0D, 0.0D, 0.0D);
		}
	}
}