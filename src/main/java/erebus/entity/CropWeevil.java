package erebus.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class CropWeevil extends Weevil{

	public CropWeevil(EntityType<? extends Weevil> type, Level level) {
		super(type, level);
	}

	public static boolean canSpawnHereAlt(EntityType<CropWeevil> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}
}
