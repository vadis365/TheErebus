package erebus.registries.client;

import erebus.Erebus;
import erebus.client.render.block.model.*;
import erebus.client.render.block.model.altar.experience.ExperienceAltarBaseModel;
import erebus.client.render.block.model.altar.experience.ExperienceAltarGlassModel;
import erebus.client.render.block.model.altar.experience.ExperienceAltarMidModel;
import erebus.client.render.block.model.altar.healing.HealingAltarBaseModel;
import erebus.client.render.block.model.altar.healing.HealingAltarMidModel;
import erebus.client.render.block.model.altar.healing.HealingAltarRoseModel;
import erebus.client.render.block.model.altar.lightning.LightningAltarBaseModel;
import erebus.client.render.block.model.altar.lightning.LightningAltarElectrodeModel;
import erebus.client.render.block.model.altar.lightning.LightningAltarMidModel;
import erebus.client.render.block.model.altar.repair.RepairAltarAnvilModel;
import erebus.client.render.block.model.altar.repair.RepairAltarBaseModel;
import erebus.client.render.block.model.altar.repair.RepairAltarMidModel;
import erebus.client.render.block.renderer.*;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModBlockEntityRendering {
    public static final ModelLayerLocation OFFERING_ALTAR = register("offering_altar");
    public static final ModelLayerLocation BLOCK_OF_BONES = register("block_of_bones");
    public static final ModelLayerLocation ALTAR_HEALING_BASE = register("altar_healing", "base");
    public static final ModelLayerLocation ALTAR_HEALING_MID = register("altar_healing", "mid");
    public static final ModelLayerLocation ALTAR_HEALING_ROSE = register("altar_healing", "rose");
    public static final ModelLayerLocation ALTAR_LIGHTNING_BASE = register("altar_lightning", "base");
    public static final ModelLayerLocation ALTAR_LIGHTNING_MID = register("altar_lightning", "mid");
    public static final ModelLayerLocation ALTAR_LIGHTNING_ELECTRODE = register("altar_lightning", "electrode");
    public static final ModelLayerLocation ALTAR_REPAIR_BASE = register("altar_repair", "base");
    public static final ModelLayerLocation ALTAR_REPAIR_MID = register("altar_repair", "mid");
    public static final ModelLayerLocation ALTAR_REPAIR_ANVIL = register("altar_repair", "glass");
    public static final ModelLayerLocation ALTAR_EXPERIENCE_BASE = register("altar_experience", "base");
    public static final ModelLayerLocation ALTAR_EXPERIENCE_MID = register("altar_experience", "mid");
    public static final ModelLayerLocation ALTAR_EXPERIENCE_GLASS = register("altar_experience", "glass");
    public static final ModelLayerLocation LIQUIFIER = register("liquifier");
    public static final ModelLayerLocation GLOWING_JAR = register("glowing_jar");
    public static final ModelLayerLocation BAMBOO_BRIDGE = register("bamboo_bridge");
    public static final ModelLayerLocation BAMBOO_EXTENDER = register("bamboo_extender");

	@SuppressWarnings("DuplicatedCode")
    public static void registerBlockEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(OFFERING_ALTAR, OfferingAltarModel::createBodyLayer);
        event.registerLayerDefinition(BLOCK_OF_BONES, BlockOfBonesModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_HEALING_BASE, HealingAltarBaseModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_HEALING_ROSE, HealingAltarRoseModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_HEALING_MID, HealingAltarMidModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_LIGHTNING_BASE, LightningAltarBaseModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_LIGHTNING_MID, LightningAltarMidModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_LIGHTNING_ELECTRODE, LightningAltarElectrodeModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_REPAIR_BASE, RepairAltarBaseModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_REPAIR_MID, RepairAltarMidModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_REPAIR_ANVIL, RepairAltarAnvilModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_EXPERIENCE_BASE, ExperienceAltarBaseModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_EXPERIENCE_MID, ExperienceAltarMidModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_EXPERIENCE_GLASS, ExperienceAltarGlassModel::createBodyLayer);
        event.registerLayerDefinition(LIQUIFIER, LiquifierModel::createBodyLayer);
        event.registerLayerDefinition(GLOWING_JAR, GlowingJarModel::createBodyLayer);
        event.registerLayerDefinition(BAMBOO_BRIDGE, BambooBridgeModel::createBodyLayer);
        event.registerLayerDefinition(BAMBOO_EXTENDER, BambooExtenderModel::createBodyLayer);
	}
	
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GAEAN_KEYSTONE.get(), GaeanKeystoneRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.OFFERING_ALTAR.get(), OfferingAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLOCK_OF_BONES.get(), BlockOfBonesRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_HEALING.get(), HealingAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_LIGHTNING.get(), LightningAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_REPAIR.get(), RepairAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_EXPERIENCE.get(), ExperienceAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.FLUID_JAR.get(), FluidJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LIQUIFIER.get(), LiquifierRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GLOWING_JAR.get(), GlowingJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BAMBOO_BRIDGE.get(), BambooBridgeRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BAMBOO_EXTENDER.get(), BambooExtenderRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PRESERVED_BLOCK.get(), PreservedBlockRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.EREBUS_CHEST.get(), ErebusChestRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SPAWNER.get(), ErebusSpawnerRenderer::new);
    }
    
    private static ModelLayerLocation register(String name, String layer) {
        return new ModelLayerLocation(Erebus.prefix(name), layer);
    }
    
    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }
}
