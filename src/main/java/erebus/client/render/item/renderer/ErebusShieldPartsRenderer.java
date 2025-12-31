

package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.client.render.item.model.ErebusShieldPartsModel;
import erebus.registries.client.ModItemRendering;
import erebus.registries.client.ModShieldMaterials;
import erebus.registries.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ErebusShieldPartsRenderer extends BlockEntityWithoutLevelRenderer {

    private final ErebusShieldPartsModel model;

    @SuppressWarnings("DataFlowIssue")
    public ErebusShieldPartsRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
        super(renderer, modelSet);
        EntityModelSet models = Minecraft.getInstance().getEntityModels();
        model = new ErebusShieldPartsModel(models.bakeLayer(ModItemRendering.EREBUS_SHIELD_PARTS));
    }

    @Override
    public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext context, PoseStack pose, MultiBufferSource buffer, int light, int overlay) {
        pose.pushPose();
        pose.scale(1, -1, -1);
        Material material = ModShieldMaterials.LOCATION_BAMBOO_SHIELD;
        if(stack.is(ModItems.EXOSKELETON_SHIELD)) material = ModShieldMaterials.LOCATION_EXOSKELETON_SHIELD;
        if(stack.is(ModItems.JADE_SHIELD)) material = ModShieldMaterials.LOCATION_JADE_SHIELD;
        if(stack.is(ModItems.RHINO_EXOSKELETON_SHIELD)) material = ModShieldMaterials.LOCATION_RHINO_EXOSKELETON_SHIELD;
        if(stack.is(ModItems.REIN_EXOSKELETON_SHIELD)) material = ModShieldMaterials.LOCATION_REIN_EXOSKELETON_SHIELD;

        VertexConsumer consumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(buffer, model.renderType(material.atlasLocation()), true, stack.hasFoil()));
        model.renderToBuffer(pose, consumer, light, overlay);
        pose.popPose();
    }
}
