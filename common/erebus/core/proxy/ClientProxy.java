package erebus.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.client.fx.EntityRepellentFX;
import erebus.client.fx.EntitySonicFX;
import erebus.client.model.entity.ModelAnimatedBlock;
import erebus.client.model.entity.ModelAnimatedChest;
import erebus.client.model.entity.ModelAntlion;
import erebus.client.model.entity.ModelBombardierBeetle;
import erebus.client.model.entity.ModelCicada;
import erebus.client.model.entity.ModelFireAnt;
import erebus.client.model.entity.ModelFireAntSoldier;
import erebus.client.model.entity.ModelGlowWorm;
import erebus.client.model.entity.ModelJumpingSpider;
import erebus.client.model.entity.ModelLavaWebSpider;
import erebus.client.model.entity.ModelPrayingMantis;
import erebus.client.model.entity.ModelRhinoBeetle;
import erebus.client.model.entity.ModelScytodes;
import erebus.client.model.entity.ModelSporeling;
import erebus.client.model.entity.ModelUmberGolem;
import erebus.client.model.entity.ModelWheatWeevil;
import erebus.client.model.entity.ModelWoodlouse;
import erebus.client.render.block.BlockBambooCropRender;
import erebus.client.render.block.BlockHollowLogRender;
import erebus.client.render.block.BlockPlantedFlowerRender;
import erebus.client.render.entity.RenderAnimatedBlock;
import erebus.client.render.entity.RenderAnimatedChest;
import erebus.client.render.entity.RenderAntlion;
import erebus.client.render.entity.RenderBeetle;
import erebus.client.render.entity.RenderBeetleLarva;
import erebus.client.render.entity.RenderBlackWidow;
import erebus.client.render.entity.RenderBloodSnail;
import erebus.client.render.entity.RenderBombardierBeetle;
import erebus.client.render.entity.RenderBotFly;
import erebus.client.render.entity.RenderBotFlyLarva;
import erebus.client.render.entity.RenderCentipede;
import erebus.client.render.entity.RenderCicada;
import erebus.client.render.entity.RenderDragonfly;
import erebus.client.render.entity.RenderErebusLightningBolt;
import erebus.client.render.entity.RenderExtractedBlock;
import erebus.client.render.entity.RenderFireAnt;
import erebus.client.render.entity.RenderFireAntSoldier;
import erebus.client.render.entity.RenderFly;
import erebus.client.render.entity.RenderGlowWorm;
import erebus.client.render.entity.RenderGooBall;
import erebus.client.render.entity.RenderGrasshopper;
import erebus.client.render.entity.RenderJumpingSpider;
import erebus.client.render.entity.RenderLavaWebSpider;
import erebus.client.render.entity.RenderLocust;
import erebus.client.render.entity.RenderMoneySpider;
import erebus.client.render.entity.RenderMosquito;
import erebus.client.render.entity.RenderMoth;
import erebus.client.render.entity.RenderMucusBombPrimed;
import erebus.client.render.entity.RenderPrayingMantis;
import erebus.client.render.entity.RenderRhinoBeetle;
import erebus.client.render.entity.RenderScorpion;
import erebus.client.render.entity.RenderScytodes;
import erebus.client.render.entity.RenderSolifuge;
import erebus.client.render.entity.RenderSporeling;
import erebus.client.render.entity.RenderTarantula;
import erebus.client.render.entity.RenderTitanBeetle;
import erebus.client.render.entity.RenderUmberGolem;
import erebus.client.render.entity.RenderVelvetWorm;
import erebus.client.render.entity.RenderWasp;
import erebus.client.render.entity.RenderWebSling;
import erebus.client.render.entity.RenderWheatWeevil;
import erebus.client.render.entity.RenderWoodlouse;
import erebus.client.render.entity.RenderWorkerBee;
import erebus.client.render.item.BambooBridgeItemRenderer;
import erebus.client.render.item.BambooCrateItemRenderer;
import erebus.client.render.item.BambooPoleItemRenderer;
import erebus.client.render.item.BambooTorchItemRenderer;
import erebus.client.render.item.ExtenderThingyItemRenderer;
import erebus.client.render.item.ItemBoneBlockRenderer;
import erebus.client.render.item.ItemErebusAltarRenderer;
import erebus.client.render.item.ItemGlowingJarRenderer;
import erebus.client.render.item.ItemPetrifiedWoodChestRenderer;
import erebus.client.render.item.ItemUmberFurnaceRenderer;
import erebus.client.render.item.ItemUmberGolemStatueRenderer;
import erebus.client.render.item.ScorpionPincerItemRenderer;
import erebus.client.render.item.WandOfAnimationItemRenderer;
import erebus.client.render.item.WaspDaggerItemRenderer;
import erebus.client.render.item.WaspSwordItemRenderer;
import erebus.client.render.item.WebSlingerItemRenderer;
import erebus.client.render.item.WoodlouseBallItemRenderer;
import erebus.client.render.tileentity.TileEntityAltarRenderer;
import erebus.client.render.tileentity.TileEntityBambooBridgeRenderer;
import erebus.client.render.tileentity.TileEntityBambooPoleRenderer;
import erebus.client.render.tileentity.TileEntityBoneBlockRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarHealingRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarLightningRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarRepairRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarXPRenderer;
import erebus.client.render.tileentity.TileEntityExtenderThingyRenderer;
import erebus.client.render.tileentity.TileEntityGlowGemRenderer;
import erebus.client.render.tileentity.TileEntityGlowingJarRenderer;
import erebus.client.render.tileentity.TileEntityLadderRenderer;
import erebus.client.render.tileentity.TileEntityPetrifiedWoodChestRenderer;
import erebus.client.render.tileentity.TileEntityRenderBambooCrate;
import erebus.client.render.tileentity.TileEntitySpawnerRender;
import erebus.client.render.tileentity.TileEntityUmberGolemStatueRenderer;
import erebus.core.handler.ClientTickHandler;
import erebus.core.handler.KeyBindingHandler;
import erebus.core.handler.PortalOverlayHandler;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBloodSnail;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityBotFlyLarva;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityCicada;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityExtractedBlock;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFireAntSoldier;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGooBall;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityLavaWebSpider;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMoneySpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityMoth;
import erebus.entity.EntityMucusBombPrimed;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntitySporeling;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityTitanBeetle;
import erebus.entity.EntityUmberGolem;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;
import erebus.entity.EntityWaspDagger;
import erebus.entity.EntityWebSling;
import erebus.entity.EntityWheatWeevil;
import erebus.entity.EntityWoodlouse;
import erebus.entity.EntityWoodlouseBall;
import erebus.entity.EntityWorkerBee;
import erebus.entity.effect.EntityErebusLightningBolt;
import erebus.tileentity.TileEntityAltar;
import erebus.tileentity.TileEntityBambooBridge;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.tileentity.TileEntityBambooPole;
import erebus.tileentity.TileEntityBones;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarHealing;
import erebus.tileentity.TileEntityErebusAltarLightning;
import erebus.tileentity.TileEntityErebusAltarRepair;
import erebus.tileentity.TileEntityErebusAltarXP;
import erebus.tileentity.TileEntityExtenderThingy;
import erebus.tileentity.TileEntityGlowGem;
import erebus.tileentity.TileEntityGlowingJar;
import erebus.tileentity.TileEntityLadder;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import erebus.tileentity.TileEntitySpawner;
import erebus.tileentity.TileEntityUmberGolemStatue;

public class ClientProxy extends CommonProxy {

	public enum BlockRenderIDs {
		BAMBOO_CROP, HOLLOW_LOG, PLANTED_FLOWER;

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
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}

	@Override
	public void registerRenderInformation() {
		MinecraftForge.EVENT_BUS.register(new PortalOverlayHandler());

		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);

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
		RenderingRegistry.registerEntityRenderingHandler(EntityBombardierBeetle.class, new RenderBombardierBeetle(new ModelBombardierBeetle(), 0.3F));
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
		RenderingRegistry.registerEntityRenderingHandler(EntitySporeling.class, new RenderSporeling(new ModelSporeling(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWheatWeevil.class, new RenderWheatWeevil(new ModelWheatWeevil(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGooBall.class, new RenderGooBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodlouse.class, new RenderWoodlouse(new ModelWoodlouse(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodlouseBall.class, new WoodlouseBallItemRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityCicada.class, new RenderCicada(new ModelCicada(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAntSoldier.class, new RenderFireAntSoldier(new ModelFireAntSoldier(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMucusBombPrimed.class, new RenderMucusBombPrimed());
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaWebSpider.class, new RenderLavaWebSpider(new ModelLavaWebSpider(), 1.0F));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltar.class, new TileEntityErebusAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarLightning.class, new TileEntityErebusAltarLightningRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarHealing.class, new TileEntityErebusAltarHealingRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarXP.class, new TileEntityErebusAltarXPRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarRepair.class, new TileEntityErebusAltarRepairRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooCrate.class, new TileEntityRenderBambooCrate());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawner.class, new TileEntitySpawnerRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlowingJar.class, new TileEntityGlowingJarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLadder.class, new TileEntityLadderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooBridge.class, new TileEntityBambooBridgeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmberGolemStatue.class, new TileEntityUmberGolemStatueRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPetrifiedWoodChest.class, new TileEntityPetrifiedWoodChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBones.class, new TileEntityBoneBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooPole.class, new TileEntityBambooPoleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtenderThingy.class, new TileEntityExtenderThingyRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new TileEntityAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlowGem.class, new TileEntityGlowGemRenderer());
		
		RenderingRegistry.registerBlockHandler(BlockRenderIDs.BAMBOO_CROP.id(), new BlockBambooCropRender());
		RenderingRegistry.registerBlockHandler(BlockRenderIDs.HOLLOW_LOG.id(), new BlockHollowLogRender());
		RenderingRegistry.registerBlockHandler(BlockRenderIDs.PLANTED_FLOWER.id(), new BlockPlantedFlowerRender());

		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooCrate.blockID, new BambooCrateItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltar.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltarLightning.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltarHealing.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltarXP.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltarRepair.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.waspSword.itemID, new WaspSwordItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.umberFurnace.blockID, new ItemUmberFurnaceRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.waspDagger.itemID, new WaspDaggerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooTorch.blockID, new BambooTorchItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.wandOfAnimation.itemID, new WandOfAnimationItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.glowingJar.blockID, new ItemGlowingJarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.scorpionPincer.itemID, new ScorpionPincerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.umberGolemStatue.blockID, new ItemUmberGolemStatueRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.petrifiedWoodChest.blockID, new ItemPetrifiedWoodChestRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.blockBones.blockID, new ItemBoneBlockRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.webSlinger.itemID, new WebSlingerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooBridge.blockID, new BambooBridgeItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.extenderThingy.blockID, new ExtenderThingyItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooPole.blockID, new BambooPoleItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.jarOHoney.blockID, new ItemGlowingJarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.woodlouseBall.itemID, new WoodlouseBallItemRenderer());
	}

	@Override
	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		EntityFX fx = null;

		if (particleName.equals("repellent"))
			fx = new EntityRepellentFX(world, x, y, z, 0.0F, 0.0F, 0.0F);
		
		if (particleName.equals("sonic"))
			fx = new EntitySonicFX(world, x, y, z, vecX, vecY, vecZ);
		
		if (particleName.equals("portal"))
			fx = new EntityPortalFX(world, x, y, z, vecX, vecY, vecZ);
		
		if (particleName.equals("slime"))
			fx = new EntityBreakingFX(world, x, y, z, vecX, vecY, vecZ, Item.slimeBall, 0); 

		if (fx != null)
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}
}