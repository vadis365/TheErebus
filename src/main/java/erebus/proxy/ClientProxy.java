package erebus.proxy;

import erebus.ModColourManager;
import erebus.ModItems;
import erebus.client.fx.ParticleBubbleGas;
import erebus.client.fx.ParticleRepellent;
import erebus.client.fx.ParticleSonic;
import erebus.client.render.entity.RenderDragonfly;
import erebus.client.render.entity.RenderFly;
import erebus.client.render.entity.RenderGasVent;
import erebus.client.render.entity.RenderWasp;
import erebus.client.render.item.RenderErebusShield;
import erebus.client.render.tile.TileEntityGaeanKeystoneRenderer;
import erebus.core.handler.GogglesClientTickHandler;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityFly;
import erebus.entity.EntityGasVent;
import erebus.entity.EntityWasp;
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
import net.minecraft.client.particle.ParticleEnchantmentTable;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.particle.ParticleHeart;
import net.minecraft.client.particle.ParticleLava;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.client.renderer.block.statemap.StateMap;
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

		if (fx != null)
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}
}