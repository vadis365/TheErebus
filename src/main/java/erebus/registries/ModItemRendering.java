package erebus.registries;

import erebus.Erebus;
import erebus.client.render.item.model.*;
import erebus.client.render.item.renderer.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
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
		event.registerLayerDefinition(PORTAL_ACTIVATOR, PortalActivatorModel::createBodyLayer);
		event.registerLayerDefinition(EREBUS_SHIELD_PARTS, ErebusShieldPartsModel::createBodyLayer);
		event.registerLayerDefinition(SCORPION_PINCER, ScorpionPincerModel::createBodyLayer);
		event.registerLayerDefinition(WAND_OF_PRESERVATION, WandOfPreservationModel::createBodyLayer);
		event.registerLayerDefinition(WAR_HAMMER, WarHammerModel::createBodyLayer);
		event.registerLayerDefinition(WASP_DAGGER, WaspDaggerModel::createBodyLayer);
		event.registerLayerDefinition(WASP_SWORD, WaspSwordModel::createBodyLayer);
		event.registerLayerDefinition(WEB_SLINGER, WebSlingerModel::createBodyLayer);
	}

	public static void registerItemRender(RegisterClientExtensionsEvent event) {
		registerItemWithRenderer(event, new WandOfAnimationItemRenderer(), ModItems.WAND_OF_ANIMATION.get());
		registerItemWithRenderer(event, new WandOfPreservationRenderer(), ModItems.WAND_OF_PRESERVATION.get());
		registerItemWithRenderer(event, new PortalActivatorRenderer(), ModItems.PORTAL_ACTIVATOR.get());

		registerItemWithRenderer(event, new WaspSwordRenderer(), ModItems.WASP_SWORD.get());
		registerItemWithRenderer(event, new WaspDaggerRenderer(), ModItems.WASP_DAGGER.get());

		registerItemWithRenderer(event, new WarHammerRenderer(), ModItems.WAR_HAMMER.get());
		registerItemWithRenderer(event, new ScorpionPincerRenderer(), ModItems.ENHANCED_SCORPION_PINCER.get());
		registerItemWithRenderer(event, new WebSlingerRenderer(), ModItems.WEB_SLINGER.get(), ModItems.WEB_SLINGER_WITHER.get());

		registerItemWithRenderer(event, new ErebusShieldPartsRenderer(),
				ModItems.BAMBOO_SHIELD.get(),
				ModItems.JADE_SHIELD.get(),
				ModItems.EXOSKELETON_SHIELD.get(),
				ModItems.REIN_EXOSKELETON_SHIELD.get(),
				ModItems.RHINO_EXOSKELETON_SHIELD.get()
		);
	}

	private static <T extends BlockEntityWithoutLevelRenderer> void registerItemWithRenderer(RegisterClientExtensionsEvent event, T renderer, Item... item) {
		event.registerItem(new ErebusItemRenderer<>(renderer), item);
	}
}
