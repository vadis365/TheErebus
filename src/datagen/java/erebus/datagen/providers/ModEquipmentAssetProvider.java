package erebus.datagen.providers;

import erebus.Erebus;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.function.BiConsumer;

public final class ModEquipmentAssetProvider extends EquipmentAssetProvider {
    public ModEquipmentAssetProvider(PackOutput output) {
        super(output);
    }

    private static ResourceKey<EquipmentAsset> key(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Erebus.prefix(name));
    }

    @Override
    protected void registerModels(@NonNull BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        for (String name : new String[]{"glider", "powered_glider"})
            output.accept(key(name), EquipmentClientInfo.builder().addMainHumanoidLayer(Erebus.prefix("glider"), false)
                    .addLayers(EquipmentClientInfo.LayerType.WINGS, new EquipmentClientInfo.Layer(Erebus.prefix("glider"),
                            Optional.of(new EquipmentClientInfo.Dyeable(Optional.of(0xFFFFFF))), false)).build());
        for (String name : new String[]{"jade", "exoskeleton", "reinforced_exoskeleton", "rhino", "bamboo"})
            output.accept(key(name), EquipmentClientInfo.builder().addHumanoidLayers(Erebus.prefix(name)).build());
        for (String name : new String[]{"compound_goggles", "reinforced_compound_goggles", "mushroom_helm", "spider_t_shirt", "water_striders", "jump_boots"})
            output.accept(key(name), EquipmentClientInfo.builder().addMainHumanoidLayer(Erebus.prefix(name), false).build());
        for (int frame = 0; frame < 3; frame++) {
            String name = "sprint_leggings" + (frame == 0 ? "" : "_" + frame);
            output.accept(key(name), EquipmentClientInfo.builder()
                    .addLayers(EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS, new EquipmentClientInfo.Layer(Erebus.prefix(name)))
                    .addLayers(EquipmentClientInfo.LayerType.HUMANOID_BABY, new EquipmentClientInfo.Layer(Erebus.prefix(name))).build());
        }
    }
}
