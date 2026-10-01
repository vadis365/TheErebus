package erebus.client;

import erebus.client.render.item.model.ArmorGliderModel;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jspecify.annotations.NonNull;

import java.util.IdentityHashMap;
import java.util.Map;

public final class GliderExtensions implements IClientItemExtensions {
    private final ModelLayerLocation layer;
    private final Map<HumanoidModel<?>, ArmorGliderModel> bodies = new IdentityHashMap<>();
    private EntityModelSet modelSet;
    private ArmorGliderModel wings;

    public GliderExtensions(boolean powered) {
        layer = powered ? ModItemRendering.ARMOR_GLIDER_POWERED : ModItemRendering.ARMOR_GLIDER;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public @NonNull Model getGenericArmorModel(@NonNull ItemStack stack, EquipmentClientInfo.@NonNull LayerType type, @NonNull Model original) {
        var current = Minecraft.getInstance().getEntityModels();
        if (modelSet != current) {
            modelSet = current;
            bodies.clear();
            wings = new ArmorGliderModel(modelSet.bakeLayer(layer), true, null);
        }
        if (type == EquipmentClientInfo.LayerType.WINGS) return wings;
        if (original instanceof HumanoidModel<?> humanoid)
            return bodies.computeIfAbsent(humanoid, model -> new ArmorGliderModel(modelSet.bakeLayer(layer), false, (HumanoidModel<HumanoidRenderState>) model));
        return original;
    }
}
