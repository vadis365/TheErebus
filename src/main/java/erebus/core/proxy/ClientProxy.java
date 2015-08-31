package erebus.core.proxy;

import erebus.client.render.init.BlockRenderingRegistry;
import erebus.client.render.init.ItemRenderingRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.client.fx.EntityBubbleGasFX;
import erebus.client.fx.EntityRepellentFX;
import erebus.client.fx.EntitySonicFX;
import erebus.client.model.entity.*;
import erebus.client.render.block.*;
import erebus.client.render.entity.*;
import erebus.client.render.item.*;
import erebus.client.render.tileentity.*;
import erebus.core.handler.GogglesClientTickHandler;
import erebus.core.handler.KeyBindingHandler;
import erebus.entity.*;
import erebus.entity.effect.EntityErebusLightningBolt;
import erebus.tileentity.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	public enum BlockRenderIDs {
		BAMBOO_CROP,
		HOLLOW_LOG,
		PLANTED_FLOWER,
		GLOWSHROOM_STALK,
		GLOWSHROOM_CAPS,
		SILO_ROOF,
		SILO_SUPPORTS,
		COMPOSTER,
		KEYSTONE,
		DOUBLE_PLANTS,
		VELOCITY_BLOCK,
		DOOR,
		SWAMP_VENT;

		private final int ID;

		BlockRenderIDs() {
			ID = RenderingRegistry.getNextAvailableRenderId();
		}

		public int id() {
			return ID;
		}
	}

	@Override
	public void registerKeyHandlers() {
		FMLCommonHandler.instance().bus().register(new KeyBindingHandler());
	}

	@Override
	public void registerRenderInformation() {
		FMLCommonHandler.instance().bus().register(new GogglesClientTickHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new RenderBeetle());
		RenderingRegistry.registerEntityRenderingHandler(EntityFly.class, new RenderFly());
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantula.class, new RenderTarantula());
		RenderingRegistry.registerEntityRenderingHandler(EntityMosquito.class, new RenderMosquito());
		RenderingRegistry.registerEntityRenderingHandler(EntityVelvetWorm.class, new RenderVelvetWorm());
		RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp());
		RenderingRegistry.registerEntityRenderingHandler(EntityCentipede.class, new RenderCentipede());
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetleLarva.class, new RenderBeetleLarva());
		RenderingRegistry.registerEntityRenderingHandler(EntityBotFly.class, new RenderBotFly());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackWidow.class, new RenderBlackWidow());
		RenderingRegistry.registerEntityRenderingHandler(EntityScorpion.class, new RenderScorpion());
		RenderingRegistry.registerEntityRenderingHandler(EntityGrasshopper.class, new RenderGrasshopper());
		RenderingRegistry.registerEntityRenderingHandler(EntityLocust.class, new RenderLocust());
		RenderingRegistry.registerEntityRenderingHandler(EntitySolifuge.class, new RenderSolifuge());
		RenderingRegistry.registerEntityRenderingHandler(EntityMoth.class, new RenderMoth());
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlion.class, new RenderAntlion(new ModelAntlion(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWaspDagger.class, new WaspDaggerItemRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBlock.class, new RenderAnimatedBlock(new ModelAnimatedBlock(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedChest.class, new RenderAnimatedChest(new ModelAnimatedChest(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBambooCrate.class, new RenderAnimatedBlock(new ModelAnimatedBlock(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGlowWorm.class, new RenderGlowWorm(new ModelGlowWorm(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityScytodes.class, new RenderScytodes(new ModelScytodes(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMoneySpider.class, new RenderMoneySpider(new ModelScytodes(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityUmberGolem.class, new RenderUmberGolem(new ModelUmberGolem(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPrayingMantis.class, new RenderPrayingMantis(new ModelPrayingMantis(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJumpingSpider.class, new RenderJumpingSpider(new ModelJumpingSpider(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAnt.class, new RenderFireAnt(new ModelFireAnt(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRhinoBeetle.class, new RenderRhinoBeetle(new ModelRhinoBeetle(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWebSling.class, new RenderWebSling());
		RenderingRegistry.registerEntityRenderingHandler(EntityErebusLightningBolt.class, new RenderErebusLightningBolt());
		RenderingRegistry.registerEntityRenderingHandler(EntityExtractedBlock.class, new RenderExtractedBlock());
		RenderingRegistry.registerEntityRenderingHandler(EntityWorkerBee.class, new RenderWorkerBee());
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonfly.class, new RenderDragonfly());
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodSnail.class, new RenderBloodSnail());
		RenderingRegistry.registerEntityRenderingHandler(EntityTitanBeetle.class, new RenderTitanBeetle());
		RenderingRegistry.registerEntityRenderingHandler(EntityBotFlyLarva.class, new RenderBotFlyLarva());
		RenderingRegistry.registerEntityRenderingHandler(EntityCrushling.class, new RenderCrushling());
		RenderingRegistry.registerEntityRenderingHandler(EntityWheatWeevil.class, new RenderWheatWeevil(new ModelWheatWeevil(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGooBall.class, new RenderGooBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodlouse.class, new RenderWoodlouse(new ModelWoodlouse(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodlouseBall.class, new WoodlouseBallItemRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityCicada.class, new RenderCicada(new ModelCicada(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAntSoldier.class, new RenderFireAntSoldier(new ModelFireAntSoldier(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMucusBombPrimed.class, new RenderMucusBombPrimed());
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaWebSpider.class, new RenderLavaWebSpider(new ModelLavaWebSpider(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlionMiniBoss.class, new RenderAntlionMiniBoss(new ModelAntlion(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityChameleonTick.class, new RenderChameleonTick(new ModelChameleonTick(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySolifugeSmall.class, new RenderSolifugeSmall());
		RenderingRegistry.registerEntityRenderingHandler(EntityMidgeSwarm.class, new RenderMidgeSwarm());
		RenderingRegistry.registerEntityRenderingHandler(EntityPunchroom.class, new RenderPunchroom());
		RenderingRegistry.registerEntityRenderingHandler(EntitySporeBall.class, new RenderSporeBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityCrushroom.class, new RenderCrushroom());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackAnt.class, new RenderBlackAnt());
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieAnt.class, new RenderZombieAnt());
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantulaMiniboss.class, new RenderTarantulaMiniboss());
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantulaBaby.class, new RenderTarantulaBaby());
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantulaEgg.class, new RenderEntityTarantulaEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonJet.class, new RenderPoisonJet());
		RenderingRegistry.registerEntityRenderingHandler(EntityGasVent.class, new RenderGasVent());
		RenderingRegistry.registerEntityRenderingHandler(EntityPondSkater.class, new RenderPondSkater());
		RenderingRegistry.registerEntityRenderingHandler(EntityLeech.class, new RenderLeech());
		RenderingRegistry.registerEntityRenderingHandler(EntitySnapper.class, new RenderSnapper());
		RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp());
		RenderingRegistry.registerEntityRenderingHandler(EntityUmberGolemDungeonTypes.class, new RenderUmberGolemDungeonType());
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmaCrawler.class, new RenderMagmaCrawler());
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlionBoss.class, new RenderAntlionBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownSand.class, new RenderThrownSand());
		RenderingRegistry.registerEntityRenderingHandler(EntityBombardierBeetle.class, new RenderBombardierBeetle());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltar.class, new TileEntityErebusAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarLightning.class, new TileEntityErebusAltarLightningRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarHealing.class, new TileEntityErebusAltarHealingRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarXP.class, new TileEntityErebusAltarXPRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarRepair.class, new TileEntityErebusAltarRepairRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooCrate.class, new TileEntityRenderBambooCrate());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlowingJar.class, new TileEntityGlowingJarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLadder.class, new TileEntityLadderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooBridge.class, new TileEntityBambooBridgeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmberGolemStatue.class, new TileEntityUmberGolemStatueRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPetrifiedWoodChest.class, new TileEntityPetrifiedWoodChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBones.class, new TileEntityBoneBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooPole.class, new TileEntityBambooPoleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtenderThingy.class, new TileEntityExtenderThingyRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlowGem.class, new TileEntityGlowGemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGaeanKeystone.class, new TileEntityGaeanKeystoneRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOfferingAltar.class, new TileEntityOfferingAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTarantulaEgg.class, new TileEntityTarantulaEggRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmoothieMaker.class, new TileEntitySmoothieMakerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAntlionEgg.class, new TileEntityAntlionEggRenderer());

		RenderingRegistry.registerBlockHandler(new BlockBambooCropRender());
		RenderingRegistry.registerBlockHandler(new BlockHollowLogRender());
		RenderingRegistry.registerBlockHandler(new BlockPlantedFlowerRender());
		RenderingRegistry.registerBlockHandler(new BlockGlowshroomStalkRender());
		RenderingRegistry.registerBlockHandler(new BlockGlowshroomRender());
		RenderingRegistry.registerBlockHandler(new BlockSiloRoofRender());
		RenderingRegistry.registerBlockHandler(new BlockSiloSupportsRender());
		RenderingRegistry.registerBlockHandler(new BlockComposterRender());
		RenderingRegistry.registerBlockHandler(new BlockKeystoneRenderer());
		RenderingRegistry.registerBlockHandler(new BlockDoublePlantRender());
		RenderingRegistry.registerBlockHandler(new BlockVelocityBlockRender());
		RenderingRegistry.registerBlockHandler(new BlockDoorRenderer());
		RenderingRegistry.registerBlockHandler(new BlockSwampVentRenderer());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.bambooCrate), new BambooCrateItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.altarBase), new ItemAltarBaseRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.altarLightning), new ItemAltarBaseRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.altarHealing), new ItemAltarBaseRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.altarXP), new ItemAltarBaseRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.altarRepair), new ItemAltarBaseRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.waspSword, new WaspSwordItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.umberFurnace), new ItemUmberFurnaceRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.waspDagger, new WaspDaggerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.bambooTorch), new BambooTorchItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.wandOfAnimation, new WandOfAnimationItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.glowingJar), new ItemGlowingJarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.scorpionPincer, new ScorpionPincerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.umberGolemStatue), new ItemUmberGolemStatueRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.petrifiedWoodChest), new ItemPetrifiedWoodChestRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.bones), new ItemBoneBlockRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.webSlinger, new WebSlingerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.witherWebSlinger, new WebSlingerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.bambooBridge), new BambooBridgeItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.extenderThingy), new ExtenderThingyItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.bambooPole), new BambooPoleItemRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.jarOHoney), new ItemGlowingJarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.woodlouseBall, new WoodlouseBallItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.portalActivator, new PortalActivatorRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.offeringAltar), new ItemOfferingAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.tarantulaEgg), new ItemTarantulaEggRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.smoothieMaker), new ItemSmoothieMakerRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.antlionEgg), new ItemAntlionEggRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.warHammer, new WarHammerItemRenderer());
	}

	@Override
	public void registerBlockRendering() {
		BlockRenderingRegistry.init();
	}

	@Override
	public void registerItemRendering() {
		ItemRenderingRegistry.init();
	}

	@Override
	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		EntityFX fx = null;

		if (particleName.equals("repellent"))
			fx = new EntityRepellentFX(world, x, y, z, 0.0F, 0.0F, 0.0F);

		if (particleName.equals("sonic"))
			fx = new EntitySonicFX(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("bubblegas")) {
			fx = new EntityBubbleGasFX(world, x, y, z, vecX, vecY, vecZ);
			fx.setRBGColorF(0.306F, 0.576F, 0.192F);
		}

		if (particleName.equals("swampflame")) {
			fx = new EntityFlameFX(world, x, y, z, vecX, vecY, vecZ);
			fx.setParticleTextureIndex(96);
		}

		if (particleName.equals("portal"))
			fx = new EntityPortalFX(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("cloud"))
			fx = new EntityCloudFX(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("spell"))
			fx = new EntitySpellParticleFX(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("heart"))
			fx = new EntityHeartFX(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("smoke"))
			fx = new EntitySmokeFX(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("poison")) {
			fx = new EntitySpellParticleFX(world, x, y, z, vecX, vecY, vecZ);
			fx.setRBGColorF(0.306F, 0.576F, 0.192F);
		}

		if (particleName.equals("flame"))
			fx = new EntityFlameFX(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("enchantmenttable"))
			fx = new EntityEnchantmentTableParticleFX(world, x, y, z, vecX, vecY, vecZ);

		if (particleName.equals("lava"))
			fx = new EntityLavaFX(world, x, y, z);

		if (particleName.equals("slime"))
			fx = new EntityBreakingFX(world, x, y, z, vecX, vecY, vecZ, Items.slime_ball, 0);

		if (particleName.equals("sparks"))
			fx = new EntityFireworkSparkFX(world, x, y, z, vecX, vecY, vecZ, Minecraft.getMinecraft().effectRenderer);

		if (fx != null)
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
}