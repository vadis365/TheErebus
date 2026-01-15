package erebus.entity;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class CropWeevil extends Weevil{

	public CropWeevil(EntityType<? extends Weevil> type, Level level) {
		super(type, level);
	}

	public static boolean canSpawnHereAlt(EntityType<CropWeevil> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}
	
/* TODO LOOT TABLES
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		switch (rand.nextInt(5)) {
			case 0:
				entityDropItem(new ItemStack(ModBlocks.GIANT_FLOWER_STIGMA, 1 + rand.nextInt(3) + looting, rand.nextInt(14)), 0F);
				break;
			case 1:
				ItemStack seed = ForgeHooks.getGrassSeed(rand, rand.nextInt(3) + looting);
				if (seed != null)
					entityDropItem(seed, 0F);
				break;
			case 2:
				entityDropItem(new ItemStack(Items.PUMPKIN_SEEDS, 1 + rand.nextInt(3) + looting), 0F);
				break;

			case 3:
				entityDropItem(new ItemStack(Items.MELON_SEEDS, 1 + rand.nextInt(3) + looting), 0F);
				break;

			case 4:
				entityDropItem(new ItemStack(Items.DYE, 1 + rand.nextInt(3) + looting, 3), 0F);
				break;
		}

		if (rand.nextInt(10) == 0) {
			int dropRareishType = rand.nextInt(7);
			switch (dropRareishType) {
				case 0:
					entityDropItem(new ItemStack(ModItems.TURNIP, 1 + looting), 0F);
					break;
				case 1:
					entityDropItem(new ItemStack(Items.NETHER_WART, 1 + looting), 0F);
					break;

				case 2:
					entityDropItem(new ItemStack(Items.WHEAT, 1 + looting), 0F);
					break;

				case 3:
					entityDropItem(new ItemStack(Items.REEDS, 1 + looting), 0F);
					break;

				case 4:
					entityDropItem(new ItemStack(EnumWood.BAMBOO.getSapling(), (1 + looting)), 0F);
					break;

				case 5:
					entityDropItem(new ItemStack(Items.CARROT, 1 + looting), 0F);
					break;

				case 6:
					entityDropItem(new ItemStack(Items.POTATO, 1 + looting), 0F);
					break;
			}
		}
	}
*/

}
