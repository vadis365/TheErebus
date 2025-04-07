package erebus.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

public class ClientParticlePackets {
	public static enum ParticleType {
		BEETLE_LARVA_SQUISH,
		SPRAY_CAN,
		CRUSHROOM_BLAM,
		TARANTULA_BLAM,
		BOSS_DEATH,
		ANTLION_BLAM,
		ANTLION_RUMBLE,
		ANTLION_DIG,
		HAMMER_BLAM,
		GAS_VENT_SWAMP,
		GAS_VENT_VOLCANIC,
		WASP_DAGGER;

		static final ParticleType[] values = values();
	}

	public static void spawnParticles(byte particleType, double xPos, double yPos, double zPos, double vecX, double vecY, double vecZ) {
        Level level = Minecraft.getInstance().level;
        switch (ParticleType.values[particleType]) {
		case BEETLE_LARVA_SQUISH:
			for (int count = 0; count <= 200; ++count)
				level.addParticle(ParticleTypes.ITEM_SLIME, false, xPos + (level.random.nextDouble() - 0.5D) , yPos + level.random.nextDouble(), zPos + (level.random.nextDouble() - 0.5D), 0, 0, 0);
			break;
		case CRUSHROOM_BLAM:
			for (int a = 0; a < 360; a += 4) {
			double ang = a * Math.PI / 180D;
			level.addParticle(ParticleTypes.ELECTRIC_SPARK, false, xPos + -Math.sin((float) ang) * 3, yPos + 0.1D, zPos + Math.cos((float) ang) * 3, 0, 0, 0);
			}
			break;
		case TARANTULA_BLAM:
			for (int a = 0; a < 360; a += 4) {
				double ang = a * Math.PI / 180D;
				level.addParticle(ParticleTypes.CLOUD, false, xPos + -Math.sin((float) ang) * 3, yPos, zPos + Math.cos((float) ang) * 3, -Math.sin((float) ang) * 0.5, 0.1D, Math.cos((float) ang) * 0.5);
			}
			break;
		case ANTLION_BLAM:
			for (int a = 0; a < 360; a += 4) {
				double ang = a * Math.PI / 180D;
				for (int count = 0; count <= 20; ++count)
					level.addParticle(ParticleTypes.DUST_PLUME, false, xPos + -Math.sin((float) ang) * 3.5D, yPos + 0.5D, zPos + Math.cos((float) ang) * 3.5D, -Math.sin((float) ang) * 0.8, 0.0D, Math.cos((float) ang) * 0.8);
				level.addParticle(ParticleTypes.CLOUD, false, xPos + -Math.sin((float) ang) * 4.5D, yPos, zPos + Math.cos((float) ang) * 4.5D, -Math.sin((float) ang) * 1D, 0.1D, Math.cos((float) ang) * 1D);
			}
			break;
		case BOSS_DEATH:
			float f = (level.random.nextFloat() - 0.5F) * 8.0F;
			float f1 = (level.random.nextFloat() - 0.5F) * 4.0F;
			float f2 = (level.random.nextFloat() - 0.5F) * 8.0F;
			level.addParticle(ParticleTypes.EXPLOSION, false, xPos + f, yPos + 2.0D + f1, zPos + f2, 0.0D, 0.0D, 0.0D);
			break;
		case ANTLION_RUMBLE:
			for (int a = 0; a < 360; a += 4) {
				double ang = a * Math.PI / 180D;
				level.addParticle(ParticleTypes.DUST_PLUME, false, xPos + -Math.sin((float) ang) * 3.5D, yPos + 0.125D, zPos + Math.cos((float) ang) * 3.5D, -Math.sin((float) ang) * 0.8, 0.3D, Math.cos((float) ang) * 0.8);
			}
			break;
		case ANTLION_DIG:
			for (int a = 0; a < 360; a += 4) {
				double ang = a * Math.PI / 180D;
				level.addParticle(ParticleTypes.DUST_PLUME, false, xPos + -Math.sin((float) ang) * 1.5D, yPos, zPos + Math.cos((float) ang) * 1.5D, -Math.sin((float) ang) * 0.8, 0.3D, Math.cos((float) ang) * 0.8);
			}
			break;
		case HAMMER_BLAM:
			for (int a = 0; a < 360; a += 4) {
				double ang = a * Math.PI / 180D;
				for (int count = 0; count <= 4; ++count)
					level.addParticle(ParticleTypes.ELECTRIC_SPARK, false, xPos + -Math.sin((float) ang)  * 1D * count * 0.5, yPos, zPos + Math.cos((float) ang) * 1 * count * 0.5, -Math.sin((float) ang) * 0.5D, 0.01D, Math.cos((float) ang) * 0.5D);
				level.addParticle(ParticleTypes.CLOUD, false, xPos + -Math.sin((float) ang) * 2D, yPos, zPos + Math.cos((float) ang) * 2D, -Math.sin((float) ang) * 0.5D, 0.01D, Math.cos((float) ang) * 0.5D);
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
				level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, false, d0, d1, d2, 0.0D, 0.05D, 0.0D);
				level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, false, d0, d1, d4, 0.0D, 0.05D, 0.0D);
				level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, false, d3, d1, d2, 0.0D, 0.05D, 0.0D);
				level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, false, d3, d1, d4, 0.0D, 0.05D, 0.0D);
				level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, false, d5, d6, d7, 0.0D, 0.05D, 0.0D);
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
				level.addParticle(ParticleTypes.FLAME, false, d0, d1, d2, 0.0D, 0.05D, 0.0D);
				level.addParticle(ParticleTypes.FLAME, false, d0, d1, d4, 0.0D, 0.05D, 0.0D);
				level.addParticle(ParticleTypes.FLAME, false, d3, d1, d2, 0.0D, 0.05D, 0.0D);
				level.addParticle(ParticleTypes.FLAME, false, d3, d1, d4, 0.0D, 0.05D, 0.0D);
				level.addParticle(ParticleTypes.FLAME, false, d5, d6, d7, 0.0D, 0.05D, 0.0D);
			}
			break;
		case WASP_DAGGER:
			for (int i = 0; i < 8; i++)
				level.addParticle(DustParticleOptions.REDSTONE, false, xPos, yPos, zPos, 0.0D, 0.0D, 0.0D);
			break;
		default:
        }
	}

}
