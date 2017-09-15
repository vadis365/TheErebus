package erebus.proxy;

import erebus.ModColourManager;
import erebus.ModItems;
import erebus.client.fx.ParticleBubbleGas;
import erebus.client.fx.ParticleRepellent;
import erebus.client.fx.ParticleSonic;
import erebus.client.render.entity.RenderAntlion;
import erebus.client.render.entity.RenderAntlionMiniBoss;
import erebus.client.render.entity.RenderBedBug;
import erebus.client.render.entity.RenderBeetle;
import erebus.client.render.entity.RenderBeetleLarva;
import erebus.client.render.entity.RenderBlackWidow;
import erebus.client.render.entity.RenderBombardierBeetle;
import erebus.client.render.entity.RenderBotFly;
import erebus.client.render.entity.RenderBotFlyLarva;
import erebus.client.render.entity.RenderCentipede;
import erebus.client.render.entity.RenderChameleonTick;
import erebus.client.render.entity.RenderCicada;
import erebus.client.render.entity.RenderCropWeevil;
import erebus.client.render.entity.RenderDragonfly;
import erebus.client.render.entity.RenderFireAnt;
import erebus.client.render.entity.RenderFireAntSoldier;
import erebus.client.render.entity.RenderFly;
import erebus.client.render.entity.RenderGasVent;
import erebus.client.render.entity.RenderGlowWorm;
import erebus.client.render.entity.RenderGooBall;
import erebus.client.render.entity.RenderGrasshopper;
import erebus.client.render.entity.RenderHoneyPotAnt;
import erebus.client.render.entity.RenderJumpingSpider;
import erebus.client.render.entity.RenderLocust;
import erebus.client.render.entity.RenderMidgeSwarm;
import erebus.client.render.entity.RenderMoneySpider;
import erebus.client.render.entity.RenderMoth;
import erebus.client.render.entity.RenderPondSkater;
import erebus.client.render.entity.RenderPrayingMantis;
import erebus.client.render.entity.RenderScorpion;
import erebus.client.render.entity.RenderScytodes;
import erebus.client.render.entity.RenderSolifuge;
import erebus.client.render.entity.RenderSolifugeSmall;
import erebus.client.render.entity.RenderTarantula;
import erebus.client.render.entity.RenderTarantulaBaby;
import erebus.client.render.entity.RenderVelvetWorm;
import erebus.client.render.entity.RenderWasp;
import erebus.client.render.entity.RenderWebSling;
import erebus.client.render.entity.RenderWoodlouse;
import erebus.client.render.entity.RenderWoodlouseBall;
import erebus.client.render.entity.RenderZombieAnt;
import erebus.client.render.entity.RenderZombieAntSoldier;
import erebus.client.render.item.RenderErebusShield;
import erebus.client.render.tile.TileEntityGaeanKeystoneRenderer;
import erebus.core.handler.GogglesClientTickHandler;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityAntlionMiniBoss;
import erebus.entity.EntityBedBug;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBombardierBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityBotFlyLarva;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityCicada;
import erebus.entity.EntityCropWeevil;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFireAntSoldier;
import erebus.entity.EntityFly;
import erebus.entity.EntityGasVent;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGooBall;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityHoneyPotAnt;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMidgeSwarm;
import erebus.entity.EntityMoneySpider;
import erebus.entity.EntityMoth;
import erebus.entity.EntityPondSkater;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntitySolifugeSmall;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityTarantulaBaby;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;
import erebus.entity.EntityWebSling;
import erebus.entity.EntityWoodlouse;
import erebus.entity.EntityWoodlouseBall;
import erebus.entity.EntityZombieAnt;
import erebus.entity.EntityZombieAntSoldier;
import erebus.tileentity.TileEntityBambooShield;
import erebus.tileentity.TileEntityExoPlateShield;
import erebus.tileentity.TileEntityGaeanKeystone;
import erebus.tileentity.TileEntityJadeShield;
import erebus.tileentity.TileEntityReinExoShield;
import erebus.tileentity.TileEntityRhinoExoShield;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleBreaking;
import net.minecraft.client.particle.ParticleCloud;
import net.minecraft.client.particle.ParticleDigging;
import net.minecraft.client.particle.ParticleEnchantmentTable;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.particle.ParticleHeart;
import net.minecraft.client.particle.ParticleLava;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemAndBlockRenderers() {
		MinecraftForge.EVENT_BUS.register(new GogglesClientTickHandler());
	}

	@Override
	public void setCustomStateMap(Block block, StateMap stateMap) {
		ModelLoader.setCustomStateMapper(block, stateMap);
	}

	@Override
	public void registerItemAndBlockColourRenderers() {
		ModColourManager.registerColourHandlers();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGaeanKeystone.class, new TileEntityGaeanKeystoneRenderer());
	}

	@Override
	public void registerEnitityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityGasVent.class, RenderGasVent::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFly.class, RenderFly::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, RenderWasp::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonfly.class, RenderDragonfly::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCentipede.class, RenderCentipede::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, RenderBeetle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetleLarva.class, RenderBeetleLarva::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBombardierBeetleLarva.class, RenderBeetleLarva::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBedBug.class, RenderBedBug::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBombardierBeetle.class, RenderBombardierBeetle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackWidow.class, RenderBlackWidow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWebSling.class, RenderWebSling::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBotFly.class, RenderBotFly::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBotFlyLarva.class, RenderBotFlyLarva::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAnt.class, RenderFireAnt::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAntSoldier.class, RenderFireAntSoldier::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlion.class, RenderAntlion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlionMiniBoss.class, RenderAntlionMiniBoss::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHoneyPotAnt.class, RenderHoneyPotAnt::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrasshopper.class, RenderGrasshopper::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLocust.class, RenderLocust::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityJumpingSpider.class, RenderJumpingSpider::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPrayingMantis.class, RenderPrayingMantis::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPondSkater.class, RenderPondSkater::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMidgeSwarm.class, RenderMidgeSwarm::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMoth.class, RenderMoth::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCropWeevil.class, RenderCropWeevil::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityChameleonTick.class, RenderChameleonTick::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieAnt.class, RenderZombieAnt::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieAntSoldier.class, RenderZombieAntSoldier::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMoneySpider.class, RenderMoneySpider::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityScytodes.class, RenderScytodes::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantula.class, RenderTarantula::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantulaBaby.class, RenderTarantulaBaby::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySolifuge.class, RenderSolifuge::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySolifugeSmall.class, RenderSolifugeSmall::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityVelvetWorm.class, RenderVelvetWorm::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGooBall.class, RenderGooBall::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodlouse.class, RenderWoodlouse::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodlouseBall.class, RenderWoodlouseBall::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCicada.class, RenderCicada::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityScorpion.class, RenderScorpion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGlowWorm.class, RenderGlowWorm::new);
	}

	@Override
	public void postInit() {
		// shield rendering
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.BAMBOO));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExoPlateShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.EXO_PLATE));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJadeShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.JADE));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReinExoShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.REIN_EXO));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRhinoExoShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.RHINO_EXO));

		// item models
		ForgeHooksClient.registerTESRItemStack(ModItems.BAMBOO_SHIELD, 0, TileEntityBambooShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.EXOSKELETON_SHIELD, 0, TileEntityExoPlateShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.JADE_SHIELD, 0, TileEntityJadeShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.REIN_EXOSKELETON_SHIELD, 0, TileEntityReinExoShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.RHINO_EXOSKELETON_SHIELD, 0, TileEntityRhinoExoShield.class);
	}

	@Override
	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		Particle fx = null;

		if (particleName.equals("repellent"))
			fx = new ParticleRepellent(world, x, y, z, 0.0F, 0.0F, 0.0F);

		if (particleName.equals("sonic"))
			fx = new ParticleSonic(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("bubblegas")) {
			fx = new ParticleBubbleGas(world, x, y, z, vecX, vecY, vecZ);
			fx.setRBGColorF(0.306F, 0.576F, 0.192F);
		}

		if (particleName.equals("bubblegasAcid")) {
			fx = new ParticleBubbleGas(world, x, y, z, vecX, vecY, vecZ);
			fx.setRBGColorF(0.490F, 0.7451F, 0.6863F);
		}

		if (particleName.equals("swampflame")) {
			fx = new ParticleFlame.Factory().createParticle(EnumParticleTypes.FLAME.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setParticleTextureIndex(96);
		}

		if (particleName.equals("portal")) {
			fx = new ParticlePortal.Factory().createParticle(EnumParticleTypes.PORTAL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setRBGColorF(0F, 1F, 0F);
		}
		if (particleName.equals("cloud"))
			fx = new ParticleCloud.Factory().createParticle(EnumParticleTypes.CLOUD.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("spell"))
			fx = new ParticleSpell.Factory().createParticle(EnumParticleTypes.SPELL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("heart"))
			fx = new ParticleHeart.Factory().createParticle(EnumParticleTypes.HEART.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("smoke"))
			fx = new ParticleSmokeNormal.Factory().createParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("poison")) {
			fx = new ParticleSpell.Factory().createParticle(EnumParticleTypes.SPELL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setRBGColorF(0.306F, 0.576F, 0.192F);
		}

		if (particleName.equals("flame"))
			fx = new ParticleFlame.Factory().createParticle(EnumParticleTypes.FLAME.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("enchantmenttable"))
			fx = new ParticleEnchantmentTable.EnchantmentTable().createParticle(EnumParticleTypes.FLAME.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
		
		if (particleName.equals("lava"))
			fx = new ParticleLava.Factory().createParticle(EnumParticleTypes.LAVA.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

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

		if (fx != null)
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}
}