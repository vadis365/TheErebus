package erebus.events;

import erebus.Erebus;
import erebus.client.render.block.renderer.stack.*;
import erebus.client.render.item.renderer.*;
import erebus.registries.blocks.ModBlocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public class RegisterSpecialRenderersEventHandler {

    @SubscribeEvent
    public static void registerSpecialRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(Erebus.prefix("shield_special"), ErebusShieldSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("portal_activator_special"), PortalActivatorSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("quake_hammer_special"), QuakeHammerSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("scorpion_pincer_special"), ScorpionPincerSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("wand_of_animation_special"), WandOfAnimationItemSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("wand_of_preservation_special"), WandOfPreservationSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("wasp_dagger_special"), WaspDaggerSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("wasp_sword_special"), WaspSwordSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("web_slinger_special"), WebSlingerSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("chest_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);

        event.register(Erebus.prefix("block_of_bones_special"), BlockOfBonesSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("altar_experience_special"), ExperienceAltarSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("altar_healing_special"), HealingAltarSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("altar_lightning_special"), LightningAltarSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("altar_repair_special"), RepairAltarSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("offering_altar_special"), OfferingAltarSpecialRenderer.Unbaked.MAP_CODEC);

        event.register(ModBlocks.CHEST_ASPER.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_BAMBOO.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_BALSAM.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_BAOBAB.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_CYPRESS.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_EUCALYPTUS.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_MAHOGANY.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_MARSHWOOD.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_MOSSBARK.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_PETRIFIED.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_ROTTEN.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_SCORCHED.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_VARNISHED.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.CHEST_WHITE.getId().withSuffix("_special"), ErebusChestSpecialRenderer.Unbaked.MAP_CODEC);

        event.register(ModBlocks.BLOCK_OF_BONES.getId().withSuffix("_special"), BlockOfBonesSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.ALTAR_EXPERIENCE.getId().withSuffix("_special"), ExperienceAltarSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.ALTAR_HEALING.getId().withSuffix("_special"), HealingAltarSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.ALTAR_LIGHTNING.getId().withSuffix("_special"), LightningAltarSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.ALTAR_REPAIR.getId().withSuffix("_special"), RepairAltarSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(ModBlocks.OFFERING_ALTAR.getId().withSuffix("_special"), OfferingAltarSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
