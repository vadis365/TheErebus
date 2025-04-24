package erebus.registries.client;

import erebus.Erebus;
import erebus.client.render.block.renderer.OfferingAltarStackItemRenderer;
import erebus.client.render.item.model.ErebusShieldPartsModel;
import erebus.client.render.item.model.PortalActivatorModel;
import erebus.client.render.item.model.ScorpionPincerModel;
import erebus.client.render.item.model.WandOfAnimationItemModel;
import erebus.client.render.item.model.WandOfPreservationModel;
import erebus.client.render.item.model.WarHammerModel;
import erebus.client.render.item.model.WaspDaggerModel;
import erebus.client.render.item.model.WaspSwordModel;
import erebus.client.render.item.model.WebSlingerModel;
import erebus.client.render.item.renderer.ErebusShieldPartsRenderer;
import erebus.client.render.item.renderer.PortalActivatorRenderer;
import erebus.client.render.item.renderer.ScorpionPincerRenderer;
import erebus.client.render.item.renderer.WandOfAnimationItemRenderer;
import erebus.client.render.item.renderer.WandOfPreservationRenderer;
import erebus.client.render.item.renderer.WarHammerRenderer;
import erebus.client.render.item.renderer.WaspDaggerRenderer;
import erebus.client.render.item.renderer.WaspSwordRenderer;
import erebus.client.render.item.renderer.WebSlingerRenderer;
import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ModItemRendering {

	public static final ModelLayerLocation WAND_OF_ANIMATION = new ModelLayerLocation(Erebus.prefix("wand_of_animation"), "main");
	public static final ModelLayerLocation PORTAL_ACTIVATOR = new ModelLayerLocation(Erebus.prefix("portal_activator"), "main");
	public static final ModelLayerLocation EREBUS_SHIELD_PARTS = new ModelLayerLocation(Erebus.prefix("erebus_shield_parts"), "main");
	public static final ModelLayerLocation SCORPION_PINCER = new ModelLayerLocation(Erebus.prefix("scorpion_pincer"), "main");
	public static final ModelLayerLocation WAND_OF_PRESERVATION = new ModelLayerLocation(Erebus.prefix("wand_of_preservation"), "main");
	public static final ModelLayerLocation WAR_HAMMER = new ModelLayerLocation(Erebus.prefix("war_hammer"), "main");
	public static final ModelLayerLocation WASP_DAGGER = new ModelLayerLocation(Erebus.prefix("wasp_dagger"), "main");
	public static final ModelLayerLocation WASP_SWORD = new ModelLayerLocation(Erebus.prefix("wasp_sword"), "main");
	public static final ModelLayerLocation WEB_SLINGER = new ModelLayerLocation(Erebus.prefix("web_slinger"), "main");

	public static void registerItemLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WAND_OF_ANIMATION, WandOfAnimationItemModel::createBodyLayer);
        event.registerLayerDefinition(WAND_OF_PRESERVATION, WandOfPreservationModel::createBodyLayer);
		event.registerLayerDefinition(PORTAL_ACTIVATOR, PortalActivatorModel::createBodyLayer);
		event.registerLayerDefinition(EREBUS_SHIELD_PARTS, ErebusShieldPartsModel::createBodyLayer);
		event.registerLayerDefinition(SCORPION_PINCER, ScorpionPincerModel::createBodyLayer);
		event.registerLayerDefinition(WAR_HAMMER, WarHammerModel::createBodyLayer);
		event.registerLayerDefinition(WASP_DAGGER, WaspDaggerModel::createBodyLayer);
		event.registerLayerDefinition(WASP_SWORD, WaspSwordModel::createBodyLayer);
		event.registerLayerDefinition(WEB_SLINGER, WebSlingerModel::createBodyLayer);
	}

	public static void registerItemRender(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WandOfAnimationItemRenderer(null, null);
            }
        }, ModItems.WAND_OF_ANIMATION.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WandOfPreservationRenderer(null, null);
            }
        }, ModItems.WAND_OF_PRESERVATION.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new PortalActivatorRenderer(null, null);
            }
        }, ModItems.PORTAL_ACTIVATOR.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WaspSwordRenderer(null, null);
            }
        }, ModItems.WASP_SWORD.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WaspDaggerRenderer(null, null);
            }
        }, ModItems.WASP_DAGGER.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WarHammerRenderer(null, null);
            }
        }, ModItems.WAR_HAMMER.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ScorpionPincerRenderer(null, null);
            }
        }, ModItems.ENHANCED_SCORPION_PINCER.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WebSlingerRenderer(null, null, false);
            }
        }, ModItems.WEB_SLINGER.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WebSlingerRenderer(null, null, true);
            }
        }, ModItems.WEB_SLINGER_WITHER.get());

        event.registerItem(new IClientItemExtensions() {
                               @Override
                               public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                                   return new ErebusShieldPartsRenderer(null, null);
                               }
                           },
				ModItems.BAMBOO_SHIELD.get(),
				ModItems.JADE_SHIELD.get(),
				ModItems.EXOSKELETON_SHIELD.get(),
				ModItems.REIN_EXOSKELETON_SHIELD.get(),
                ModItems.RHINO_EXOSKELETON_SHIELD.get());
        
        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new OfferingAltarStackItemRenderer(null, null);
            }
        }, ModBlocks.OFFERING_ALTAR.get().asItem());
	}
}
