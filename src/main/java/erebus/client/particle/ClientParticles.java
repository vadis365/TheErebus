package erebus.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class ClientParticles {
 // TODO THIS IS A TEMP CLASS UNTIL ALL THE PARTICLE STUFF IS SORTED
	@SuppressWarnings("resource")
	public static void spawnCustomParticle(String particleName, double x, double y, double z, double vecX, double vecY, double vecZ) {
		Level level = Minecraft.getInstance().level;
		if (level != null) {
			Particle fx = null;
			if (particleName.equals("lava"))
				level.addParticle(ParticleTypes.LAVA, false, x, y, z, vecX, vecY, vecZ);

			if (particleName.equals("smoke"))
				level.addParticle(ParticleTypes.LARGE_SMOKE, false, x, y, z, vecX, vecY, vecZ);

			if (particleName.equals("flame"))
				level.addParticle(ParticleTypes.FLAME, false, x, y, z, vecX, vecY, vecZ);

			if (particleName.equals("swampflame")) {
				level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, false, x, y, z, vecX, vecY, vecZ);
			}

			if (particleName.equals("swampflame_green")) {
				level.addParticle(ParticleTypes.SMALL_FLAME, false, x, y, z, vecX, vecY, vecZ);
				//fx.setParticleTextureIndex(96);
				//fx.setRBGColorF(1F, 1F, 0F);
			}

			if (particleName.equals("heart"))
				level.addParticle(ParticleTypes.HEART, false, x, y, z, vecX, vecY, vecZ);
	/*	
		if (particleName.equals("repellent")) {
			fx = new ParticleRepellent(world, x, y, z, 0.0F, 0.0F, 0.0F);
			fx.setRBGColorF(0F, 1F, 0F);
		}

		if (particleName.equals("sonic")) {
			fx = new ParticleSonic(world, x, y, z, vecX, vecY, vecZ);
			fx.setRBGColorF(1F, 1F, 1F);
		}
		
		if (particleName.equals("sonicblue")) {
			fx = new ParticleSonic(world, x, y, z, vecX, vecY, vecZ);
			fx.setRBGColorF(0.490F, 0.7451F, 1F);
		}

		if (particleName.equals("bubblegas")) {
			fx = new ParticleBubbleGas(world, x, y, z, vecX, vecY, vecZ);
			fx.setRBGColorF(0.306F, 0.576F, 0.192F);
		}

		if (particleName.equals("bubblegasAcid")) {
			fx = new ParticleBubbleGas(world, x, y, z, vecX, vecY, vecZ);
			fx.setRBGColorF(0.490F, 0.7451F, 0.6863F);
		}

		if (particleName.equals("portal")) {
			fx = new ParticlePortal.Factory().createParticle(EnumParticleTypes.PORTAL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
		}
		
		if (particleName.equals("erebus_portal")) {
			fx = new ParticlePortal.Factory().createParticle(EnumParticleTypes.PORTAL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setRBGColorF(0.1F, 0.5F, 0.1F);
		}

		if (particleName.equals("bonemeal"))
			fx = new ParticleSuspendedTown.HappyVillagerFactory().createParticle(EnumParticleTypes.VILLAGER_HAPPY.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("cloud"))
			fx = new ParticleCloud.Factory().createParticle(EnumParticleTypes.CLOUD.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("spell"))
			fx = new ParticleSpell.Factory().createParticle(EnumParticleTypes.SPELL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("heart"))
			fx = new ParticleHeart.Factory().createParticle(EnumParticleTypes.HEART.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("poison")) {
			fx = new ParticleSpell.Factory().createParticle(EnumParticleTypes.SPELL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setRBGColorF(0.306F, 0.576F, 0.192F);
		}

		if (particleName.equals("enchantmenttable"))
			fx = new ParticleEnchantmentTable.EnchantmentTable().createParticle(EnumParticleTypes.FLAME.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
		
		if (particleName.equals("slime"))
			fx = new ParticleBreaking.SlimeFactory().createParticle(EnumParticleTypes.SLIME.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
		
		if (particleName.equals("antlion_dig"))
			fx = new ParticleDigging.Factory().createParticle(EnumParticleTypes.BLOCK_DUST.getParticleID(), world, x, y, z, vecX, vecY, vecZ, Block.getIdFromBlock(Blocks.SAND));

		if (particleName.equals("spores")) {
			fx = new ParticleRedstone.Factory().createParticle(EnumParticleTypes.REDSTONE.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setRBGColorF(1F, 1F, 1F);
		}

		if (particleName.equals("reddust")) {
			fx = new ParticleRedstone.Factory().createParticle(EnumParticleTypes.REDSTONE.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setRBGColorF(1F, 0F, 0F);
		}

		if (particleName.equals("sparks"))
			fx = new ParticleFirework.Factory().createParticle(EnumParticleTypes.FIREWORKS_SPARK.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("huge_explode"))
			fx = new ParticleExplosionHuge.Factory().createParticle(EnumParticleTypes.EXPLOSION_HUGE.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
*/
		}
	}
}
