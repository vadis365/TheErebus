package erebus.client;

import com.google.common.reflect.TypeToken;
import erebus.Erebus;
import erebus.registries.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;

import java.util.Optional;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public final class SprintLeggingsRenderHandler {
    private SprintLeggingsRenderHandler() {
    }

    @SubscribeEvent
    public static void register(RegisterRenderStateModifiersEvent event) {
        event.registerEntityModifier(new TypeToken<EntityRenderer<Entity, EntityRenderState>>() {
        }, (entity, state) -> {
            if (!(state instanceof HumanoidRenderState humanoid) || !humanoid.legsEquipment.is(ModItems.SPRINT_LEGGINGS)) return;
            var equipped = humanoid.legsEquipment.get(DataComponents.EQUIPPABLE);
            if (equipped == null || equipped.assetId().isEmpty()) return;
            int tick = entity.isSprinting() ? 0 : entity.tickCount % 61;
            int frame = tick <= 20 ? 0 : tick <= 40 ? 1 : 2;
            var original = equipped.assetId().orElseThrow();
            var asset = ResourceKey.create(original.registryKey(), Erebus.prefix("sprint_leggings" + (frame == 0 ? "" : "_" + frame)));
            humanoid.legsEquipment = humanoid.legsEquipment.copy();
            humanoid.legsEquipment.set(DataComponents.EQUIPPABLE, new Equippable(equipped.slot(), equipped.equipSound(), Optional.of(asset),
                    equipped.cameraOverlay(), equipped.allowedEntities(), equipped.dispensable(), equipped.swappable(), equipped.damageOnHurt(),
                    equipped.equipOnInteract(), equipped.canBeSheared(), equipped.shearingSound()));
        });
    }
}
