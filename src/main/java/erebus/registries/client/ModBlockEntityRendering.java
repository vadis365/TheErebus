package erebus.registries.client;

import erebus.Erebus;
import erebus.client.render.block.model.*;
import erebus.client.render.block.renderer.*;
import erebus.registries.ModBlockEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModBlockEntityRendering {
	public static final ModelLayerLocation OFFERING_ALTAR = new ModelLayerLocation(Erebus.prefix("offering_altar"), "main");
    public static final ModelLayerLocation BLOCK_OF_BONES = new ModelLayerLocation(Erebus.prefix("block_of_bones"), "main");
    public static final ModelLayerLocation ALTAR_HEALING = new ModelLayerLocation(Erebus.prefix("altar_healing"), "main");
    public static final ModelLayerLocation ALTAR_LIGHTNING = new ModelLayerLocation(Erebus.prefix("altar_lightning"), "main");
    public static final ModelLayerLocation ALTAR_REPAIR = new ModelLayerLocation(Erebus.prefix("altar_repair"), "main");
    public static final ModelLayerLocation ALTAR_EXPERIENCE = new ModelLayerLocation(Erebus.prefix("altar_experience"), "main");
    public static final ModelLayerLocation BLENDER = new ModelLayerLocation(Erebus.prefix("blender"), "main");
    
	public static void registerBlockEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(OFFERING_ALTAR, OfferingAltarModel::createBodyLayer);
        event.registerLayerDefinition(BLOCK_OF_BONES, BlockOfBonesModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_HEALING, HealingAltarModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_LIGHTNING, LightningAltarModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_REPAIR, RepairAltarModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_EXPERIENCE, ExperienceAltarModel::createBodyLayer);
        event.registerLayerDefinition(BLENDER, BlenderModel::createBodyLayer);
	}
	
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GAEAN_KEYSTONE.get(), GaeanKeystoneRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.OFFERING_ALTAR.get(), OfferingAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLOCK_OF_BONES.get(), BlockOfBonesRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_HEALING.get(), HealingAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_LIGHTNING.get(), LightningAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_REPAIR.get(), RepairAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_EXPERIENCE.get(), ExperienceAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLENDER.get(), BlenderRenderer::new);
    }
    
}
