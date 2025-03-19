package erebus.registries;

import javax.annotation.Nonnull;

import erebus.Erebus;
import erebus.client.render.item.model.WandOfAnimationItemModel;
import erebus.client.render.item.renderer.WandOfAnimationItemRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ModItemRendering {
	
	public static final ModelLayerLocation WAND_OF_ANIMATION = new ModelLayerLocation(Erebus.prefix("wand_of_animation"), "main");
	
	public static void registerItemRender(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			@Nonnull
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new WandOfAnimationItemRenderer(null, null);
			}
		}, ModItems.WAND_OF_ANIMATION.get());
	}

    public static void registerItemLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WAND_OF_ANIMATION, WandOfAnimationItemModel::createBodyLayer);
    }

}
