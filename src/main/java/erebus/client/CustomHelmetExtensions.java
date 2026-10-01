package erebus.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jspecify.annotations.NonNull;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public final class CustomHelmetExtensions implements IClientItemExtensions {
    private final ModelLayerLocation layer;
    private final Identifier texture;
    private final Map<HumanoidModel<?>, HelmetModel> models = new IdentityHashMap<>();
    private EntityModelSet modelSet;

    public CustomHelmetExtensions(ModelLayerLocation layer, Identifier texture) {
        this.layer = layer;
        this.texture = texture;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public @NonNull Model getGenericArmorModel(@NonNull ItemStack stack, EquipmentClientInfo.@NonNull LayerType type, @NonNull Model original) {
        if (!(original instanceof HumanoidModel<?> humanoid)) return original;
        var currentModels = Minecraft.getInstance().getEntityModels();
        if (currentModels != modelSet) {
            modelSet = currentModels;
            models.clear();
        }
        return models.computeIfAbsent(humanoid, model -> new HelmetModel(modelSet.bakeLayer(layer), (HumanoidModel<HumanoidRenderState>) model));
    }

    @Override
    public Identifier getArmorTexture(@NonNull ItemStack stack, EquipmentClientInfo.@NonNull LayerType type, EquipmentClientInfo.@NonNull Layer layer, @NonNull Identifier fallback) {
        return texture;
    }

    private static final class HelmetModel extends EntityModel<HumanoidRenderState> {
        private final HumanoidModel<HumanoidRenderState> original;
        private final ModelPart helmet;

        private HelmetModel(ModelPart helmet, HumanoidModel<HumanoidRenderState> original) {
            super(new ModelPart(List.of(), Map.of("head", helmet)));
            this.helmet = helmet;
            this.original = original;
        }

        private static void copyPose(ModelPart from, ModelPart to) {
            to.loadPose(from.storePose());
            to.xScale = from.xScale;
            to.yScale = from.yScale;
            to.zScale = from.zScale;
        }

        @Override
        public void setupAnim(@NonNull HumanoidRenderState state) {
            super.setupAnim(state);
            original.setupAnim(state);
            copyPose(original.root(), root());
            copyPose(original.head, helmet);
            helmet.xScale *= 1.2F;
            helmet.zScale *= 1.2F;
        }
    }
}
