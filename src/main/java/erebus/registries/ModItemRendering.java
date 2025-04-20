package erebus.registries;

import erebus.Erebus;
import erebus.client.render.item.model.PortalActivatorModel;
import erebus.client.render.item.model.WandOfAnimationItemModel;
import erebus.client.render.item.renderer.PortalActivatorRenderer;
import erebus.client.render.item.renderer.WandOfAnimationItemRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import javax.annotation.Nonnull;

public class ModItemRendering {
	
	public static final ModelLayerLocation WAND_OF_ANIMATION = new ModelLayerLocation(Erebus.prefix("wand_of_animation"), "main");
	public static final ModelLayerLocation PORTAL_ACTIVATOR = new ModelLayerLocation(Erebus.prefix("portal_activator"), "main");

	public static void registerItemRender(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			@Nonnull
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new WandOfAnimationItemRenderer(null, null);
			}
		}, ModItems.WAND_OF_ANIMATION.get());

		event.registerItem(new IClientItemExtensions() {
			@Nonnull
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new PortalActivatorRenderer(null, null);
			}
		}, ModItems.PORTAL_ACTIVATOR.get());
	}

    public static void registerItemLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WAND_OF_ANIMATION, WandOfAnimationItemModel::createBodyLayer);
		event.registerLayerDefinition(PORTAL_ACTIVATOR, PortalActivatorModel::createBodyLayer);
    }
}
