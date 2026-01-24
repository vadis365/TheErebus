package erebus.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.item.model.MushroomHelmModel;
import erebus.client.render.item.model.RhinoHeadModel;
import erebus.registries.client.ModItemRendering;
import erebus.registries.item.ModItems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import org.jspecify.annotations.NonNull;

public class CustomHelmLayer<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    private static final Identifier RHINO_HELM = Erebus.prefix("textures/models/armor/rhino_helm.png");
    private static final Identifier MUSHROOM_HELM = Erebus.prefix("textures/models/armor/mushroom_helm_layer_1.png");

    private final RhinoHeadModel rhinoHeadModel;
    private final MushroomHelmModel mushroomHelmModel;
    private final EquipmentLayerRenderer equipmentRenderer;

    public CustomHelmLayer(RenderLayerParent<S, M> renderer, EntityModelSet modelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderer);
        rhinoHeadModel = new RhinoHeadModel(modelSet.bakeLayer(ModItemRendering.RHINO_HELM));
        mushroomHelmModel = new MushroomHelmModel(modelSet.bakeLayer(ModItemRendering.MUSHROOM_HELM));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        ItemStack stack = state.headEquipment;
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        if(equippable != null && !equippable.assetId().isEmpty()) {
            pose.pushPose();
            pose.scale(1.2F, 1.0F, 1.2F);
            if(stack.is(ModItems.RHINO_EXOSKELETON_HELMET)) {
                equipmentRenderer.renderLayers(
                        EquipmentClientInfo.LayerType.HUMANOID,
                        equippable.assetId().get(),
                        rhinoHeadModel,
                        state,
                        stack,
                        pose,
                        submitNodeCollector,
                        lightCoords,
                        RHINO_HELM,
                        state.outlineColor,
                        0
                );
            } else if(stack.is(ModItems.MUSHROOM_HELMET)) {
                equipmentRenderer.renderLayers(
                        EquipmentClientInfo.LayerType.HUMANOID,
                        equippable.assetId().get(),
                        mushroomHelmModel,
                        state,
                        stack,
                        pose,
                        submitNodeCollector,
                        lightCoords,
                        MUSHROOM_HELM,
                        state.outlineColor,
                        0
                );
            }
            pose.popPose();
        }
    }
}
