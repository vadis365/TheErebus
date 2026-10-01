package erebus.events;

import erebus.Erebus;
import erebus.registries.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ExtractLevelRenderStateEvent;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public final class GogglesLightmapHandler {
    private GogglesLightmapHandler() {
    }

    @SubscribeEvent
    public static void extract(ExtractLevelRenderStateEvent event) {
        var minecraft = Minecraft.getInstance();
        var player = minecraft.player;
        if (player == null) return;
        var head = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!head.is(ModItems.COMPOUND_GOGGLES) && !head.is(ModItems.REIN_COMPOUND_GOGGLES)) return;
        // Vanilla has just extracted the lightmap. Override only this frame's render data;
        // actual potion effects, their remaining duration and subsequent removal are untouched.
        var lightmap = minecraft.gameRenderer.getGameRenderState().lightmapRenderState;
        if (lightmap.needsUpdate) lightmap.nightVisionEffectIntensity = 1.0F;
    }

}
