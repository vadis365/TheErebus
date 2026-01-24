package erebus.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.item.model.ArmorGliderModel;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class GliderLayer<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    private static final Identifier  WINGS = Erebus.prefix("textures/models/armor/glider_layer_1.png");
    private final ArmorGliderModel armorModel;
    private final EquipmentLayerRenderer equipmentRenderer;

    public GliderLayer(RenderLayerParent<S, M> renderer, EntityModelSet modelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderer);
        armorModel = new ArmorGliderModel(modelSet.bakeLayer(ModItemRendering.ARMOR_GLIDER));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void submit(@NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        ItemStack itemStack = state.chestEquipment;
        Equippable equippable = itemStack.get(DataComponents.EQUIPPABLE);
        if (equippable != null && equippable.assetId().isPresent()) {
            poseStack.pushPose();
            poseStack.translate(0.0F, 0.0F, 0.125F);
            this.equipmentRenderer
                    .renderLayers(
                            EquipmentClientInfo.LayerType.WINGS,
                            equippable.assetId().get(),
                            armorModel,
                            state,
                            itemStack,
                            poseStack,
                            submitNodeCollector,
                            lightCoords,
                            getPlayerWingTexture(state),
                            state.outlineColor,
                            0
                    );
            poseStack.popPose();
        }
    }

    @Nullable
    public static Identifier getPlayerWingTexture(HumanoidRenderState state) {
        if(state instanceof AvatarRenderState playerState) {
            PlayerSkin skin = playerState.skin;
            if(skin.cape() != null && playerState.showCape) {
                return skin.cape().texturePath();
            }
        }

        return WINGS;
    }
}
