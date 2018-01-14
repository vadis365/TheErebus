package erebus.proxy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModColourManager;
import erebus.block.silo.TileEntitySiloTank;
import erebus.blocks.BlockPetrifiedChest;
import erebus.client.fx.ParticleBubbleGas;
import erebus.client.fx.ParticleRepellent;
import erebus.client.fx.ParticleSonic;
import erebus.client.gui.GuiAntInventory;
import erebus.client.gui.GuiColossalCrate;
import erebus.client.gui.GuiComposter;
import erebus.client.gui.GuiErebusBasic;
import erebus.client.gui.GuiLiquifier;
import erebus.client.gui.GuiPetrifiedChest;
import erebus.client.gui.GuiPetrifiedWorkbench;
import erebus.client.gui.GuiSmoothieMaker;
import erebus.client.gui.GuiUmberFurnace;
import erebus.client.render.entity.RenderAnimatedBlock;
import erebus.client.render.entity.RenderAnimatedChest;
import erebus.client.render.entity.RenderAntlion;
import erebus.client.render.entity.RenderAntlionBoss;
import erebus.client.render.entity.RenderAntlionMiniBoss;
import erebus.client.render.entity.RenderBedBug;
import erebus.client.render.entity.RenderBeetle;
import erebus.client.render.entity.RenderBeetleLarva;
import erebus.client.render.entity.RenderBlackAnt;
import erebus.client.render.entity.RenderBlackWidow;
import erebus.client.render.entity.RenderBogMaw;
import erebus.client.render.entity.RenderBombardierBeetle;
import erebus.client.render.entity.RenderBotFly;
import erebus.client.render.entity.RenderBotFlyLarva;
import erebus.client.render.entity.RenderCentipede;
import erebus.client.render.entity.RenderChameleonTick;
import erebus.client.render.entity.RenderCicada;
import erebus.client.render.entity.RenderCropWeevil;
import erebus.client.render.entity.RenderCrushroom;
import erebus.client.render.entity.RenderDragonfly;
import erebus.client.render.entity.RenderEntityPreservedBlock;
import erebus.client.render.entity.RenderEntityTarantulaEgg;
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
import erebus.client.render.entity.RenderLocust;
import erebus.client.render.entity.RenderMagmaCrawler;
import erebus.client.render.entity.RenderMidgeSwarm;
import erebus.client.render.entity.RenderMoneySpider;
import erebus.client.render.entity.RenderMoth;
import erebus.client.render.entity.RenderPoisonJet;
import erebus.client.render.entity.RenderPondSkater;
import erebus.client.render.entity.RenderPrayingMantis;
import erebus.client.render.entity.RenderPunchroom;
import erebus.client.render.entity.RenderRhinoBeetle;
import erebus.client.render.entity.RenderScorpion;
import erebus.client.render.entity.RenderScytodes;
import erebus.client.render.entity.RenderSolifuge;
import erebus.client.render.entity.RenderSolifugeSmall;
import erebus.client.render.entity.RenderSporeBall;
import erebus.client.render.entity.RenderStagBeetle;
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
import erebus.client.render.entity.RenderWoodlouse;
import erebus.client.render.entity.RenderWoodlouseBall;
import erebus.client.render.entity.RenderWorkerBee;
import erebus.client.render.entity.RenderZombieAnt;
import erebus.client.render.entity.RenderZombieAntSoldier;
import erebus.client.render.item.RenderErebusShield;
import erebus.client.render.item.RenderPortalActivator;
import erebus.client.render.item.RenderScorpionPincer;
import erebus.client.render.item.RenderWandOfAnimation;
import erebus.client.render.item.RenderWandOfPreservation;
import erebus.client.render.item.RenderWaspSword;
import erebus.client.render.tile.TileEntityBambooBridgeRenderer;
import erebus.client.render.tile.TileEntityBoneBlockRenderer;
import erebus.client.render.tile.TileEntityErebusAltarHealingRenderer;
import erebus.client.render.tile.TileEntityErebusAltarLightningRenderer;
import erebus.client.render.tile.TileEntityErebusAltarRenderer;
import erebus.client.render.tile.TileEntityErebusAltarRepairRenderer;
import erebus.client.render.tile.TileEntityErebusAltarXPRenderer;
import erebus.client.render.tile.TileEntityExtenderThingyRenderer;
import erebus.client.render.tile.TileEntityFluidJarRenderer;
import erebus.client.render.tile.TileEntityGaeanKeystoneRenderer;
import erebus.client.render.tile.TileEntityGlowingJarRenderer;
import erebus.client.render.tile.TileEntityLiquifierRenderer;
import erebus.client.render.tile.TileEntityOfferingAltarRenderer;
import erebus.client.render.tile.TileEntityPetrifiedWoodChestRenderer;
import erebus.client.render.tile.TileEntityPreservedBlockRenderer;
import erebus.client.render.tile.TileEntitySmoothieMakerRenderer;
import erebus.client.render.tile.TileEntityUmberGolemStatueRenderer;
import erebus.core.handler.GogglesClientTickHandler;
import erebus.core.handler.KeyBindingHandler;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityAntlionBoss;
import erebus.entity.EntityAntlionMiniBoss;
import erebus.entity.EntityBedBug;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackAnt;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBogMaw;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBombardierBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityBotFlyLarva;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityCicada;
import erebus.entity.EntityCropWeevil;
import erebus.entity.EntityCrushroom;
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
import erebus.entity.EntityLavaWebSpider;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMagmaCrawler;
import erebus.entity.EntityMidgeSwarm;
import erebus.entity.EntityMoneySpider;
import erebus.entity.EntityMoth;
import erebus.entity.EntityPoisonJet;
import erebus.entity.EntityPondSkater;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityPreservedBlock;
import erebus.entity.EntityPunchroom;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntitySolifugeSmall;
import erebus.entity.EntitySporeBall;
import erebus.entity.EntityStagBeetle;
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
import erebus.entity.EntityWebSling;
import erebus.entity.EntityWoodlouse;
import erebus.entity.EntityWoodlouseBall;
import erebus.entity.EntityWorkerBee;
import erebus.entity.EntityZombieAnt;
import erebus.entity.EntityZombieAntSoldier;
import erebus.inventory.ContainerAnimatedBambooCrate;
import erebus.inventory.ContainerBambooCrate;
import erebus.inventory.ContainerExtenderThingy;
import erebus.inventory.ContainerHoneyComb;
import erebus.inventory.ContainerSilo;
import erebus.tileentity.TileEntityBambooBridge;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.tileentity.TileEntityBones;
import erebus.tileentity.TileEntityComposter;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarHealing;
import erebus.tileentity.TileEntityErebusAltarLightning;
import erebus.tileentity.TileEntityErebusAltarRepair;
import erebus.tileentity.TileEntityErebusAltarXP;
import erebus.tileentity.TileEntityExtenderThingy;
import erebus.tileentity.TileEntityFluidJar;
import erebus.tileentity.TileEntityGaeanKeystone;
import erebus.tileentity.TileEntityGlowingJar;
import erebus.tileentity.TileEntityHoneyComb;
import erebus.tileentity.TileEntityLiquifier;
import erebus.tileentity.TileEntityOfferingAltar;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import erebus.tileentity.TileEntityPreservedBlock;
import erebus.tileentity.TileEntitySmoothieMaker;
import erebus.tileentity.TileEntityUmberFurnace;
import erebus.tileentity.TileEntityUmberGolemStatue;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleBreaking;
import net.minecraft.client.particle.ParticleCloud;
import net.minecraft.client.particle.ParticleDigging;
import net.minecraft.client.particle.ParticleEnchantmentTable;
import net.minecraft.client.particle.ParticleExplosionHuge;
import net.minecraft.client.particle.ParticleFirework;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.particle.ParticleHeart;
import net.minecraft.client.particle.ParticleLava;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.client.particle.ParticleSuspendedTown;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerKeyHandlers() {
		MinecraftForge.EVENT_BUS.register(new KeyBindingHandler());
	}

	@Override
	public void registerItemAndBlockRenderers() {
		MinecraftForge.EVENT_BUS.register(new GogglesClientTickHandler());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGaeanKeystone.class, new TileEntityGaeanKeystoneRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooBridge.class, new TileEntityBambooBridgeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtenderThingy.class, new TileEntityExtenderThingyRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltar.class, new TileEntityErebusAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarLightning.class, new TileEntityErebusAltarLightningRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarHealing.class, new TileEntityErebusAltarHealingRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarXP.class, new TileEntityErebusAltarXPRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarRepair.class, new TileEntityErebusAltarRepairRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOfferingAltar.class, new TileEntityOfferingAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBones.class, new TileEntityBoneBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmberGolemStatue.class, new TileEntityUmberGolemStatueRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPetrifiedWoodChest.class, new TileEntityPetrifiedWoodChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPreservedBlock.class, new TileEntityPreservedBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlowingJar.class, new TileEntityGlowingJarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmoothieMaker.class, new TileEntitySmoothieMakerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidJar.class, new TileEntityFluidJarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLiquifier.class, new TileEntityLiquifierRenderer());
	}

	@Override
	public void setCustomStateMap(Block block, StateMap stateMap) {
		ModelLoader.setCustomStateMapper(block, stateMap);
	}

	@Override
	public void registerItemAndBlockColourRenderers() {
		ModColourManager.registerColourHandlers();
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
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaWebSpider.class, RenderLavaWebSpider::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBlock.class, RenderAnimatedBlock::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedChest.class, RenderAnimatedChest::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBambooCrate.class, RenderAnimatedBlock::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTitanBeetle.class, RenderTitanBeetle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackAnt.class, RenderBlackAnt::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityStagBeetle.class, RenderStagBeetle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRhinoBeetle.class, RenderRhinoBeetle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityUmberGolemDungeonTypes.class, RenderUmberGolemDungeonType::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlionBoss.class, RenderAntlionBoss::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownSand.class, RenderThrownSand::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityUmberGolem.class, RenderUmberGolem::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmaCrawler.class, RenderMagmaCrawler::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBogMaw.class, RenderBogMaw::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPreservedBlock.class, RenderEntityPreservedBlock::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantulaMiniboss.class, RenderTarantulaMiniboss::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantulaEgg.class, RenderEntityTarantulaEgg::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonJet.class, RenderPoisonJet::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWorkerBee.class, RenderWorkerBee::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPunchroom.class, RenderPunchroom::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCrushroom.class, RenderCrushroom::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySporeBall.class, RenderSporeBall::new);

		TileEntityItemStackRenderer.instance = new RenderErebusShield(TileEntityItemStackRenderer.instance);
		TileEntityItemStackRenderer.instance = new RenderWaspSword(TileEntityItemStackRenderer.instance);
		TileEntityItemStackRenderer.instance = new RenderWandOfAnimation(TileEntityItemStackRenderer.instance);
		TileEntityItemStackRenderer.instance = new RenderPortalActivator(TileEntityItemStackRenderer.instance);
		TileEntityItemStackRenderer.instance = new RenderWandOfPreservation(TileEntityItemStackRenderer.instance);
		TileEntityItemStackRenderer.instance = new RenderScorpionPincer(TileEntityItemStackRenderer.instance);
	}

	@Override
	public void postInit() {
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.BAMBOO_BRIDGE), 0, TileEntityBambooBridge.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.BAMBOO_EXTENDER), 0, TileEntityExtenderThingy.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.ALTAR_BASE), 0, TileEntityErebusAltar.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.ALTAR_LIGHTNING), 0, TileEntityErebusAltarLightning.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.ALTAR_HEALING), 0, TileEntityErebusAltarHealing.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.ALTAR_XP), 0, TileEntityErebusAltarXP.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.ALTAR_REPAIR), 0, TileEntityErebusAltarRepair.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.ALTAR_OFFERING), 0, TileEntityOfferingAltar.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.BLOCK_OF_BONES), 0, TileEntityBones.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.UMBER_GOLEM_STATUE), 0, TileEntityUmberGolemStatue.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.PETRIFIED_WOOD_CHEST), 0, TileEntityPetrifiedWoodChest.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.PRESERVED_BLOCK), 0, TileEntityPreservedBlock.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.GLOWING_JAR), 0, TileEntityGlowingJar.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.SMOOTHIE_MAKER), 0, TileEntitySmoothieMaker.class);

		// shield rendering unused but keeping here for future reference on other stuff
		/*	
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJadeShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.JADE));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReinExoShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.REIN_EXO));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRhinoExoShield.class, new RenderErebusShield(RenderErebusShield.Shieldtype.RHINO_EXO));

		// item models
		ForgeHooksClient.registerTESRItemStack(ModItems.BAMBOO_SHIELD, 0, TileEntityBambooShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.EXOSKELETON_SHIELD, 0, TileEntityExoPlateShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.JADE_SHIELD, 0, TileEntityJadeShield.class);
		ForgeHooksClient.registerTESRItemStack(ModItems.REIN_EXOSKELETON_SHIELD, 0, TileEntityReinExoShield.class);
		
	*/
		}

	@Override
	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		Particle fx = null;

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

		if (particleName.equals("swampflame")) {
			fx = new ParticleFlame.Factory().createParticle(EnumParticleTypes.FLAME.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setParticleTextureIndex(96);
		}

		if (particleName.equals("swampflame_green")) {
			fx = new ParticleFlame.Factory().createParticle(EnumParticleTypes.FLAME.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			fx.setParticleTextureIndex(96);
			fx.setRBGColorF(1F, 1F, 0F);
		}

		if (particleName.equals("portal")) {
			fx = new ParticlePortal.Factory().createParticle(EnumParticleTypes.PORTAL.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);
			//fx.setRBGColorF(0F, 1F, 0F);
		}

		if (particleName.equals("bonemeal"))
			fx = new ParticleSuspendedTown.HappyVillagerFactory().createParticle(EnumParticleTypes.VILLAGER_HAPPY.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

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

		if (particleName.equals("sparks"))
			fx = new ParticleFirework.Factory().createParticle(EnumParticleTypes.FIREWORKS_SPARK.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (particleName.equals("huge_explode"))
			fx = new ParticleExplosionHuge.Factory().createParticle(EnumParticleTypes.EXPLOSION_HUGE.getParticleID(), world, x, y, z, vecX, vecY, vecZ, 0);

		if (fx != null)
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}
	
	@Override
	public GuiContainer getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GuiID guiID = GuiID.values()[ID];
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);
		Entity entity = world.getEntityByID(x);

		switch (guiID) {
			case ANIMATED_BAMBOO_CRATE:
				if (entity != null && entity instanceof EntityAnimatedBambooCrate)
					return new GuiErebusBasic(new ContainerAnimatedBambooCrate(player.inventory, (IInventory) entity), new ResourceLocation("erebus:textures/gui/container/bamboo_crate.png"), (IInventory) entity, 168);
			case ANT_INVENTORY:
				if (entity != null && entity instanceof EntityBlackAnt)
					return new GuiAntInventory(player.inventory, entity);
			case BAMBOO_CRATE:
				return new GuiErebusBasic(new ContainerBambooCrate(player.inventory, (TileEntityBambooCrate) tile), new ResourceLocation("erebus:textures/gui/container/bamboo_crate.png"), (TileEntityBambooCrate) tile, 168);
			case COLOSSAL_CRATE:
				List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
				for (int[] place : places) {
					TileEntity te = world.getTileEntity(pos.add(place[0], place[1],place[2]));
					if (te != null && te instanceof TileEntityBambooCrate)
						list.add((TileEntityBambooCrate) te);
					else
						return null;
				}
				return new GuiColossalCrate(player.inventory, list);
			case COMPOSTER:
				return new GuiComposter(player.inventory, (TileEntityComposter) tile);
			case EXTENDER_THINGY:
				return new GuiErebusBasic(new ContainerExtenderThingy(player.inventory, (TileEntityExtenderThingy) tile), new ResourceLocation("erebus:textures/gui/container/extender_thingy.png"), (TileEntityExtenderThingy) tile, 176, 136);
			case HONEY_COMB:
				return new GuiErebusBasic(new ContainerHoneyComb(player.inventory, (TileEntityHoneyComb) tile), new ResourceLocation("erebus:textures/gui/container/honey_comb_gui.png"), (TileEntityHoneyComb) tile, 168);
			case PETRIFIED_CHEST:
				return new GuiPetrifiedChest(player.inventory, getContainer(world, pos, false), player);
			case PETRIFIED_CRAFT:
				return new GuiPetrifiedWorkbench(player.inventory, world, pos);
			case SILO_INVENTORY:
				return new GuiErebusBasic(new ContainerSilo(player.inventory, (TileEntitySiloTank) tile), new ResourceLocation("erebus:textures/gui/container/silo_gui.png"), (TileEntitySiloTank) tile, 256, 256);
			case SMOOTHIE_MAKER:
				return new GuiSmoothieMaker(player.inventory, (TileEntitySmoothieMaker) tile);
			case UMBER_FURNACE:
				return new GuiUmberFurnace(player.inventory, (TileEntityUmberFurnace) tile);
			case LIQUIFIER:
				return new GuiLiquifier(player.inventory, (TileEntityLiquifier) tile);
			default:
				return null;
		}
	}

	@Nullable
	public ILockableContainer getContainer(World world, BlockPos pos, boolean allowBlocking) {
		TileEntity tileentity = world.getTileEntity(pos);
		IBlockState state = world.getBlockState(pos);
		Block blockIn = state.getBlock();

		if (!(tileentity instanceof TileEntityPetrifiedWoodChest)) {
			return null;
		} else {
			ILockableContainer ilockablecontainer = (TileEntityPetrifiedWoodChest) tileentity;

			if (!allowBlocking && ((BlockPetrifiedChest)blockIn).isBlocked(world, pos)) {
				return null;
			} else {
				for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
					BlockPos blockpos = pos.offset(enumfacing);
					Block block = world.getBlockState(blockpos).getBlock();

					if (block instanceof BlockPetrifiedChest) {
						if (((BlockPetrifiedChest)block).isBlocked(world, blockpos)) {
							return null;
						}

						TileEntity tileentity1 = world.getTileEntity(blockpos);

						if (tileentity1 instanceof TileEntityPetrifiedWoodChest) {
							if (enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH) {
								ilockablecontainer = new InventoryLargeChest("container.petrifiedChestDouble", ilockablecontainer, (TileEntityPetrifiedWoodChest) tileentity1);
							} else {
								ilockablecontainer = new InventoryLargeChest("container.petrifiedChestDouble", (TileEntityPetrifiedWoodChest) tileentity1, ilockablecontainer);
							}
						}
					}
				}

				return ilockablecontainer;
			}
		}
	}
}