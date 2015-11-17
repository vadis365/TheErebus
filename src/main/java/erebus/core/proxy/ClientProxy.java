package erebus.core.proxy;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockPetrifiedChest;
import erebus.block.silo.TileEntitySiloTank;
import erebus.client.fx.EntityBubbleGasFX;
import erebus.client.fx.EntityRepellentFX;
import erebus.client.fx.EntitySonicFX;
import erebus.client.gui.GuiAntInventory;
import erebus.client.gui.GuiColossalCrate;
import erebus.client.gui.GuiComposter;
import erebus.client.gui.GuiErebusBasic;
import erebus.client.gui.GuiPetrifiedChest;
import erebus.client.gui.GuiPetrifiedWorkbench;
import erebus.client.gui.GuiSmoothieMaker;
import erebus.client.gui.GuiUmberFurnace;
import erebus.client.model.entity.ModelAnimatedBlock;
import erebus.client.model.entity.ModelAnimatedChest;
import erebus.client.render.block.BlockBambooCropRender;
import erebus.client.render.block.BlockComposterRender;
import erebus.client.render.block.BlockDoorRenderer;
import erebus.client.render.block.BlockDoublePlantRender;
import erebus.client.render.block.BlockGlowshroomRender;
import erebus.client.render.block.BlockGlowshroomStalkRender;
import erebus.client.render.block.BlockHollowLogRender;
import erebus.client.render.block.BlockPlantedFlowerRender;
import erebus.client.render.block.BlockSiloRoofRender;
import erebus.client.render.block.BlockSiloSupportsRender;
import erebus.client.render.block.BlockSwampVentRenderer;
import erebus.client.render.block.BlockVelocityBlockRender;
import erebus.client.render.entity.RenderAnimatedBlock;
import erebus.client.render.entity.RenderAnimatedChest;
import erebus.client.render.entity.RenderAntlion;
import erebus.client.render.entity.RenderAntlionBoss;
import erebus.client.render.entity.RenderAntlionMiniBoss;
import erebus.client.render.entity.RenderBeetle;
import erebus.client.render.entity.RenderBeetleLarva;
import erebus.client.render.entity.RenderBlackAnt;
import erebus.client.render.entity.RenderBlackWidow;
import erebus.client.render.entity.RenderBloodSnail;
import erebus.client.render.entity.RenderBombardierBeetle;
import erebus.client.render.entity.RenderBotFly;
import erebus.client.render.entity.RenderBotFlyLarva;
import erebus.client.render.entity.RenderCentipede;
import erebus.client.render.entity.RenderChameleonTick;
import erebus.client.render.entity.RenderCicada;
import erebus.client.render.entity.RenderCrushling;
import erebus.client.render.entity.RenderCrushroom;
import erebus.client.render.entity.RenderDragonfly;
import erebus.client.render.entity.RenderEntityPreservedBlock;
import erebus.client.render.entity.RenderEntityTarantulaEgg;
import erebus.client.render.entity.RenderErebusLightningBolt;
import erebus.client.render.entity.RenderExtractedBlock;
import erebus.client.render.entity.RenderFireAnt;
import erebus.client.render.entity.RenderFireAntSoldier;
import erebus.client.render.entity.RenderFly;
import erebus.client.render.entity.RenderGasVent;
import erebus.client.render.entity.RenderGlowWorm;
import erebus.client.render.entity.RenderGooBall;
import erebus.client.render.entity.RenderGrasshopper;
import erebus.client.render.entity.RenderHoneyPotAnt;
import erebus.client.render.entity.RenderJumpingSpider;
import erebus.client.render.entity.RenderLavaWebSpider;
import erebus.client.render.entity.RenderLeech;
import erebus.client.render.entity.RenderLocust;
import erebus.client.render.entity.RenderMagmaCrawler;
import erebus.client.render.entity.RenderMidgeSwarm;
import erebus.client.render.entity.RenderMoneySpider;
import erebus.client.render.entity.RenderMosquito;
import erebus.client.render.entity.RenderMoth;
import erebus.client.render.entity.RenderMucusBombPrimed;
import erebus.client.render.entity.RenderPoisonJet;
import erebus.client.render.entity.RenderPondSkater;
import erebus.client.render.entity.RenderPrayingMantis;
import erebus.client.render.entity.RenderPunchroom;
import erebus.client.render.entity.RenderRhinoBeetle;
import erebus.client.render.entity.RenderScorpion;
import erebus.client.render.entity.RenderScytodes;
import erebus.client.render.entity.RenderSnapper;
import erebus.client.render.entity.RenderSolifuge;
import erebus.client.render.entity.RenderSolifugeSmall;
import erebus.client.render.entity.RenderSporeBall;
import erebus.client.render.entity.RenderTarantula;
import erebus.client.render.entity.RenderTarantulaBaby;
import erebus.client.render.entity.RenderTarantulaMiniboss;
import erebus.client.render.entity.RenderThrownSand;
import erebus.client.render.entity.RenderTitanBeetle;
import erebus.client.render.entity.RenderUmberGolem;
import erebus.client.render.entity.RenderUmberGolemDungeonType;
import erebus.client.render.entity.RenderVelvetWorm;
import erebus.client.render.entity.RenderWasp;
import erebus.client.render.entity.RenderWebSling;
import erebus.client.render.entity.RenderWheatWeevil;
import erebus.client.render.entity.RenderWisp;
import erebus.client.render.entity.RenderWoodlouse;
import erebus.client.render.entity.RenderWorkerBee;
import erebus.client.render.entity.RenderZombieAnt;
import erebus.client.render.item.BambooBridgeItemRenderer;
import erebus.client.render.item.BambooCrateItemRenderer;
import erebus.client.render.item.BambooPoleItemRenderer;
import erebus.client.render.item.BambooTorchItemRenderer;
import erebus.client.render.item.ExtenderThingyItemRenderer;
import erebus.client.render.item.ItemAltarBaseRenderer;
import erebus.client.render.item.ItemAntlionEggRenderer;
import erebus.client.render.item.ItemBoneBlockRenderer;
import erebus.client.render.item.ItemGlowingJarRenderer;
import erebus.client.render.item.ItemOfferingAltarRenderer;
import erebus.client.render.item.ItemPetrifiedWoodChestRenderer;
import erebus.client.render.item.ItemSmoothieMakerRenderer;
import erebus.client.render.item.ItemTarantulaEggRenderer;
import erebus.client.render.item.ItemUmberFurnaceRenderer;
import erebus.client.render.item.ItemUmberGolemStatueRenderer;
import erebus.client.render.item.ItemWandOfAnimationRenderer;
import erebus.client.render.item.ItemWandOfPreservationRenderer;
import erebus.client.render.item.PortalActivatorRenderer;
import erebus.client.render.item.PreservedBlockitemRenderer;
import erebus.client.render.item.RenderMaxSpeedBow;
import erebus.client.render.item.ScorpionPincerItemRenderer;
import erebus.client.render.item.WarHammerItemRenderer;
import erebus.client.render.item.WaspDaggerItemRenderer;
import erebus.client.render.item.WaspSwordItemRenderer;
import erebus.client.render.item.WebSlingerItemRenderer;
import erebus.client.render.item.WoodlouseBallItemRenderer;
import erebus.client.render.tileentity.TileEntityAntlionEggRenderer;
import erebus.client.render.tileentity.TileEntityBambooBridgeRenderer;
import erebus.client.render.tileentity.TileEntityBambooPoleRenderer;
import erebus.client.render.tileentity.TileEntityBoneBlockRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarHealingRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarLightningRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarRepairRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarXPRenderer;
import erebus.client.render.tileentity.TileEntityExtenderThingyRenderer;
import erebus.client.render.tileentity.TileEntityGaeanKeystoneRenderer;
import erebus.client.render.tileentity.TileEntityGlowGemRenderer;
import erebus.client.render.tileentity.TileEntityGlowingJarRenderer;
import erebus.client.render.tileentity.TileEntityLadderRenderer;
import erebus.client.render.tileentity.TileEntityOfferingAltarRenderer;
import erebus.client.render.tileentity.TileEntityPetrifiedWoodChestRenderer;
import erebus.client.render.tileentity.TileEntityPreservedBlockRenderer;
import erebus.client.render.tileentity.TileEntityRenderBambooCrate;
import erebus.client.render.tileentity.TileEntitySmoothieMakerRenderer;
import erebus.client.render.tileentity.TileEntityTarantulaEggRenderer;
import erebus.client.render.tileentity.TileEntityUmberGolemStatueRenderer;
import erebus.core.handler.GogglesClientTickHandler;
import erebus.core.handler.KeyBindingHandler;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityAntlionBoss;
import erebus.entity.EntityAntlionMiniBoss;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackAnt;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBloodSnail;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBombardierBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityBotFlyLarva;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityCicada;
import erebus.entity.EntityCrushling;
import erebus.entity.EntityCrushroom;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityExtractedBlock;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFireAntSoldier;
import erebus.entity.EntityFly;
import erebus.entity.EntityGasVent;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGooBall;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityHoneyPotAnt;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityLavaWebSpider;
import erebus.entity.EntityLeech;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMagmaCrawler;
import erebus.entity.EntityMidgeSwarm;
import erebus.entity.EntityMoneySpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityMoth;
import erebus.entity.EntityMucusBombPrimed;
import erebus.entity.EntityPoisonJet;
import erebus.entity.EntityPondSkater;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityPreservedBlock;
import erebus.entity.EntityPunchroom;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySnapper;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntitySolifugeSmall;
import erebus.entity.EntitySporeBall;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityTarantulaBaby;
import erebus.entity.EntityTarantulaEgg;
import erebus.entity.EntityTarantulaMiniboss;
import erebus.entity.EntityThrownSand;
import erebus.entity.EntityTitanBeetle;
import erebus.entity.EntityUmberGolem;
import erebus.entity.EntityUmberGolemDungeonTypes;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;
import erebus.entity.EntityWaspDagger;
import erebus.entity.EntityWebSling;
import erebus.entity.EntityWheatWeevil;
import erebus.entity.EntityWisp;
import erebus.entity.EntityWoodlouse;
import erebus.entity.EntityWoodlouseBall;
import erebus.entity.EntityWorkerBee;
import erebus.entity.EntityZombieAnt;
import erebus.entity.effect.EntityErebusLightningBolt;
import erebus.inventory.ContainerAnimatedBambooCrate;
import erebus.inventory.ContainerBambooCrate;
import erebus.inventory.ContainerExtenderThingy;
import erebus.inventory.ContainerHoneyComb;
import erebus.inventory.ContainerSilo;
import erebus.tileentity.TileEntityAntlionEgg;
import erebus.tileentity.TileEntityBambooBridge;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.tileentity.TileEntityBambooPole;
import erebus.tileentity.TileEntityBones;
import erebus.tileentity.TileEntityComposter;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarHealing;
import erebus.tileentity.TileEntityErebusAltarLightning;
import erebus.tileentity.TileEntityErebusAltarRepair;
import erebus.tileentity.TileEntityErebusAltarXP;
import erebus.tileentity.TileEntityExtenderThingy;
import erebus.tileentity.TileEntityGaeanKeystone;
import erebus.tileentity.TileEntityGlowGem;
import erebus.tileentity.TileEntityGlowingJar;
import erebus.tileentity.TileEntityHoneyComb;
import erebus.tileentity.TileEntityLadder;
import erebus.tileentity.TileEntityOfferingAltar;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import erebus.tileentity.TileEntityPreservedBlock;
import erebus.tileentity.TileEntitySmoothieMaker;
import erebus.tileentity.TileEntityTarantulaEgg;
import erebus.tileentity.TileEntityUmberFurnace;
import erebus.tileentity.TileEntityUmberGolemStatue;

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
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlion.class, new RenderAntlion());
		RenderingRegistry.registerEntityRenderingHandler(EntityWaspDagger.class, new WaspDaggerItemRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBlock.class, new RenderAnimatedBlock(new ModelAnimatedBlock(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedChest.class, new RenderAnimatedChest(new ModelAnimatedChest(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBambooCrate.class, new RenderAnimatedBlock(new ModelAnimatedBlock(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGlowWorm.class, new RenderGlowWorm());
		RenderingRegistry.registerEntityRenderingHandler(EntityScytodes.class, new RenderScytodes());
		RenderingRegistry.registerEntityRenderingHandler(EntityMoneySpider.class, new RenderMoneySpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityUmberGolem.class, new RenderUmberGolem());
		RenderingRegistry.registerEntityRenderingHandler(EntityPrayingMantis.class, new RenderPrayingMantis());
		RenderingRegistry.registerEntityRenderingHandler(EntityJumpingSpider.class, new RenderJumpingSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAnt.class, new RenderFireAnt());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhinoBeetle.class, new RenderRhinoBeetle());
		RenderingRegistry.registerEntityRenderingHandler(EntityWebSling.class, new RenderWebSling());
		RenderingRegistry.registerEntityRenderingHandler(EntityErebusLightningBolt.class, new RenderErebusLightningBolt());
		RenderingRegistry.registerEntityRenderingHandler(EntityExtractedBlock.class, new RenderExtractedBlock());
		RenderingRegistry.registerEntityRenderingHandler(EntityWorkerBee.class, new RenderWorkerBee());
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonfly.class, new RenderDragonfly());
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodSnail.class, new RenderBloodSnail());
		RenderingRegistry.registerEntityRenderingHandler(EntityTitanBeetle.class, new RenderTitanBeetle());
		RenderingRegistry.registerEntityRenderingHandler(EntityBotFlyLarva.class, new RenderBotFlyLarva());
		RenderingRegistry.registerEntityRenderingHandler(EntityCrushling.class, new RenderCrushling());
		RenderingRegistry.registerEntityRenderingHandler(EntityWheatWeevil.class, new RenderWheatWeevil());
		RenderingRegistry.registerEntityRenderingHandler(EntityGooBall.class, new RenderGooBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodlouse.class, new RenderWoodlouse());
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodlouseBall.class, new WoodlouseBallItemRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityCicada.class, new RenderCicada());
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAntSoldier.class, new RenderFireAntSoldier());
		RenderingRegistry.registerEntityRenderingHandler(EntityMucusBombPrimed.class, new RenderMucusBombPrimed());
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaWebSpider.class, new RenderLavaWebSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlionMiniBoss.class, new RenderAntlionMiniBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityChameleonTick.class, new RenderChameleonTick());
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
		RenderingRegistry.registerEntityRenderingHandler(EntityPreservedBlock.class, new RenderEntityPreservedBlock());
		RenderingRegistry.registerEntityRenderingHandler(EntityHoneyPotAnt.class, new RenderHoneyPotAnt());
		RenderingRegistry.registerEntityRenderingHandler(EntityBombardierBeetleLarva.class, new RenderBeetleLarva());

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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPreservedBlock.class, new TileEntityPreservedBlockRenderer());

		RenderingRegistry.registerBlockHandler(new BlockBambooCropRender());
		RenderingRegistry.registerBlockHandler(new BlockHollowLogRender());
		RenderingRegistry.registerBlockHandler(new BlockPlantedFlowerRender());
		RenderingRegistry.registerBlockHandler(new BlockGlowshroomStalkRender());
		RenderingRegistry.registerBlockHandler(new BlockGlowshroomRender());
		RenderingRegistry.registerBlockHandler(new BlockSiloRoofRender());
		RenderingRegistry.registerBlockHandler(new BlockSiloSupportsRender());
		RenderingRegistry.registerBlockHandler(new BlockComposterRender());
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
		MinecraftForgeClient.registerItemRenderer(ModItems.wandOfAnimation, new ItemWandOfAnimationRenderer());
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
		MinecraftForgeClient.registerItemRenderer(ModItems.wandOfPreservation, new ItemWandOfPreservationRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.preservedBlock), new PreservedBlockitemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.maxSpeedBow, new RenderMaxSpeedBow());
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
	public GuiContainer getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GuiID guiID = GuiID.values()[ID];
		TileEntity tile = world.getTileEntity(x, y, z);
		Entity entity = world.getEntityByID(x);

		switch (guiID) {
			case ANIMATED_BAMBOO_CRATE:
				if (entity != null && entity instanceof EntityAnimatedBambooCrate)
					return new GuiErebusBasic(new ContainerAnimatedBambooCrate(player.inventory, (IInventory) entity), new ResourceLocation("erebus:textures/gui/container/bambooCrate.png"), (IInventory) entity, 168);
			case ANT_INVENTORY:
				if (entity != null && entity instanceof EntityBlackAnt)
					return new GuiAntInventory(player.inventory, entity);
			case BAMBOO_CRATE:
				return new GuiErebusBasic(new ContainerBambooCrate(player.inventory, (TileEntityBambooCrate) tile), new ResourceLocation("erebus:textures/gui/container/bambooCrate.png"), (TileEntityBambooCrate) tile, 168);
			case COLOSSAL_CRATE:
				List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
				for (int[] place : places) {
					TileEntity te = world.getTileEntity(x + place[0], y + place[1], z + place[2]);
					if (te != null && te instanceof TileEntityBambooCrate)
						list.add((TileEntityBambooCrate) te);
					else
						return null;
				}
				return new GuiColossalCrate(player.inventory, list);
			case COMPOSTER:
				return new GuiComposter(player.inventory, (TileEntityComposter) tile);
			case EXTENDER_THINGY:
				return new GuiErebusBasic(new ContainerExtenderThingy(player.inventory, (TileEntityExtenderThingy) tile), new ResourceLocation("erebus:textures/gui/container/extenderThingy.png"), (TileEntityExtenderThingy) tile, 176, 136);
			case HONEY_COMB:
				return new GuiErebusBasic(new ContainerHoneyComb(player.inventory, (TileEntityHoneyComb) tile), new ResourceLocation("erebus:textures/gui/container/honeyCombGui.png"), (TileEntityHoneyComb) tile, 168);
			case PETRIFIED_CHEST:
				IInventory inventory = BlockPetrifiedChest.getInventory(world, x, y, z);
				return new GuiPetrifiedChest(player.inventory, inventory);
			case PETRIFIED_CRAFT:
				return new GuiPetrifiedWorkbench(player.inventory, world, x, y, z);
			case SILO_INVENTORY:
				return new GuiErebusBasic(new ContainerSilo(player.inventory, (TileEntitySiloTank) tile), new ResourceLocation("erebus:textures/gui/container/siloGui.png"), (TileEntitySiloTank) tile, 256, 256);
			case SMOOTHIE_MAKER:
				return new GuiSmoothieMaker(player.inventory, (TileEntitySmoothieMaker) tile);
			case UMBER_FURNACE:
				return new GuiUmberFurnace(player.inventory, (TileEntityUmberFurnace) tile);
			default:
				return null;
		}
	}
}