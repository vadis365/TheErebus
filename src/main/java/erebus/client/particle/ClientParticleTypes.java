package erebus.client.particle;

import erebus.registries.client.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ClientParticleTypes {
	public static void spawnParticles(byte particleType, double xPos, double yPos, double zPos, double vecX, double vecY, double vecZ) {
        Level level = Minecraft.getInstance().level;
		if (level != null) {
			switch (ParticleType.values[particleType]) {
				case BEETLE_LARVA_SQUISH:
					for (int count = 0; count <= 200; ++count)
						level.addParticle(ParticleTypes.ITEM_SLIME, xPos + (level.getRandom().nextDouble() - 0.5D), yPos + level.getRandom().nextDouble(), zPos + (level.getRandom().nextDouble() - 0.5D), 0, 0, 0);
					break;
				case CRUSHROOM_BLAM:
					for (int a = 0; a < 360; a += 4) {
						double ang = a * Math.PI / 180D;
						level.addParticle(ParticleTypes.ELECTRIC_SPARK, xPos + -Math.sin((float) ang) * 3, yPos + 0.1D, zPos + Math.cos((float) ang) * 3, 0, 0, 0);
					}
					break;
				case TARANTULA_BLAM:
					for (int a = 0; a < 360; a += 4) {
						double ang = a * Math.PI / 180D;
						level.addParticle(ParticleTypes.CLOUD, xPos + -Math.sin((float) ang) * 3, yPos, zPos + Math.cos((float) ang) * 3, -Math.sin((float) ang) * 0.5, 0.1D, Math.cos((float) ang) * 0.5);
					}
					break;
				case ANTLION_BLAM:
					for (int a = 0; a < 360; a += 4) {
						double ang = a * Math.PI / 180D;
						for (int count = 0; count <= 20; ++count)
							level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SAND.defaultBlockState()), xPos + -Math.sin((float) ang) * 3.5D, yPos + 0.5D, zPos + Math.cos((float) ang) * 3.5D, -Math.sin((float) ang) * 0.8, 0.0D, Math.cos((float) ang) * 0.8);
						level.addParticle(ParticleTypes.CLOUD, xPos + -Math.sin((float) ang) * 4.5D, yPos, zPos + Math.cos((float) ang) * 4.5D, -Math.sin((float) ang), 0.1D, Math.cos((float) ang));
					}
					break;
				case BOSS_DEATH:
					float f = (level.getRandom().nextFloat() - 0.5F) * 8.0F;
					float f1 = (level.getRandom().nextFloat() - 0.5F) * 4.0F;
					float f2 = (level.getRandom().nextFloat() - 0.5F) * 8.0F;
					level.addParticle(ParticleTypes.EXPLOSION, xPos + f, yPos + 2.0D + f1, zPos + f2, 0.0D, 0.0D, 0.0D);
					break;
	/*	case ANTLION_RUMBLE:
			for (int a = 0; a < 360; a += 4) {
				double ang = a * Math.PI / 180D;
				level.addParticle(new BlockParticleOption (ParticleTypes.BLOCK, Blocks.SAND.defaultBlockState()), xPos + -Math.sin((float) ang) * 3.5D, yPos + 0.125D, zPos + Math.cos((float) ang) * 3.5D, -Math.sin((float) ang) * 0.8, 0.3D, Math.cos((float) ang) * 0.8);
			}
			break;
	*/
				case HAMMER_BLAM:
					for (int a = 0; a < 360; a += 4) {
						double ang = a * Math.PI / 180D;
						for (int count = 0; count <= 4; ++count)
							level.addParticle(ParticleTypes.ELECTRIC_SPARK, xPos + -Math.sin((float) ang) * 1D * count * 0.5, yPos, zPos + Math.cos((float) ang) * 1 * count * 0.5, -Math.sin((float) ang) * 0.5D, 0.01D, Math.cos((float) ang) * 0.5D);
						level.addParticle(ParticleTypes.CLOUD, xPos + -Math.sin((float) ang) * 2D, yPos, zPos + Math.cos((float) ang) * 2D, -Math.sin((float) ang) * 0.5D, 0.01D, Math.cos((float) ang) * 0.5D);
					}
					break;
				case GAS_VENT_SWAMP:
					for (double yy = yPos; yy < yPos + 2D; yy += 0.5D) {
						double d0 = xPos - 0.075F;
						double d1 = yy;
						double d2 = zPos - 0.075F;
						double d3 = xPos + 0.075F;
						double d4 = zPos + 0.075F;
						double d5 = xPos;
						double d6 = yy + 0.25F;
						double d7 = zPos;
						level.addParticle(ModParticles.SWAMP_VENT.get(), d0, d1, d2, 0.0D, 0.05D, 0.0D);
						level.addParticle(ModParticles.SWAMP_VENT.get(), d0, d1, d4, 0.0D, 0.05D, 0.0D);
						level.addParticle(ModParticles.SWAMP_VENT.get(), d3, d1, d2, 0.0D, 0.05D, 0.0D);
						level.addParticle(ModParticles.SWAMP_VENT.get(), d3, d1, d4, 0.0D, 0.05D, 0.0D);
						level.addParticle(ModParticles.SWAMP_VENT.get(), d5, d6, d7, 0.0D, 0.05D, 0.0D);
					}
					break;
				case GAS_VENT_VOLCANIC:
					for (double yy = yPos; yy < yPos + 2D; yy += 0.5D) {
						double d0 = xPos - 0.075F;
						double d1 = yy;
						double d2 = zPos - 0.075F;
						double d3 = xPos + 0.075F;
						double d4 = zPos + 0.075F;
						double d5 = xPos;
						double d6 = yy + 0.25F;
						double d7 = zPos;
						level.addParticle(ModParticles.GNEISS_VENT.get(), d0, d1, d2, 0.0D, 0.05D, 0.0D);
						level.addParticle(ModParticles.GNEISS_VENT.get(), d0, d1, d4, 0.0D, 0.05D, 0.0D);
						level.addParticle(ModParticles.GNEISS_VENT.get(), d3, d1, d2, 0.0D, 0.05D, 0.0D);
						level.addParticle(ModParticles.GNEISS_VENT.get(), d3, d1, d4, 0.0D, 0.05D, 0.0D);
						level.addParticle(ModParticles.GNEISS_VENT.get(), d5, d6, d7, 0.0D, 0.05D, 0.0D);
					}
					break;
				case WASP_DAGGER:
					for (int i = 0; i < 8; i++)
						level.addParticle(DustParticleOptions.REDSTONE, xPos, yPos, zPos, 0.0D, 0.0D, 0.0D);
					break;
				case ELECTRIC:
					float vx = (level.getRandom().nextFloat() * 0.5f - 0.25f) * 0.00125f;
					float vy = (level.getRandom().nextFloat() * 0.5f - 0.25f) * 0.00125f;
					float vz = (level.getRandom().nextFloat() * 0.5f - 0.25f) * 0.00125f;
					level.addParticle(ParticleTypes.ELECTRIC_SPARK, xPos, yPos, zPos, vx, vy, vz);
					break;
				default:
			}
		}
	}

	public enum ParticleType {
		BEETLE_LARVA_SQUISH,
		SPRAY_CAN,
		CRUSHROOM_BLAM,
		TARANTULA_BLAM,
		BOSS_DEATH,
		ANTLION_BLAM,
		HAMMER_BLAM,
		GAS_VENT_SWAMP,
		GAS_VENT_VOLCANIC,
		WASP_DAGGER,
		ELECTRIC;

		static final ParticleType[] values = values();
	}

	public static void spawnAntlionParticles(int blockType, double xPos, double yPos, double zPos, double offSetRadius, boolean reverse, double velX, double velY, double velZ) {
		Level level = Minecraft.getInstance().level;
		for (int a = 0; a < 360; a += 4) {
			double ang = a * Math.PI / 180D;
			if(reverse)
				level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Block.stateById(blockType)), xPos + -Math.sin((float) ang) * offSetRadius, yPos, zPos + Math.cos((float) ang) * offSetRadius, -Math.sin((float) ang) * -1D, 1D, Math.cos((float) ang) * -1D);
			else
				level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Block.stateById(blockType)), xPos + -Math.sin((float) ang) * offSetRadius, yPos, zPos + Math.cos((float) ang) * offSetRadius, -Math.sin((float) ang) * 0.8D, 0.3D, Math.cos((float) ang) * 0.8D);
		}
		
	}

}
