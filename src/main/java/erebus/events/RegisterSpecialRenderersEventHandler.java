package erebus.events;

import erebus.Erebus;
import erebus.client.render.block.renderer.stack.*;
import erebus.client.render.item.renderer.*;
import erebus.registries.blocks.ModBlocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterSpecialBlockModelRendererEvent;
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
    }

    @SubscribeEvent
    public static void registerSpecialBlockRenderers(RegisterSpecialBlockModelRendererEvent event) {
        event.register(ModBlocks.CHEST_ASPER.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.ASPER_TEXTURE));
        event.register(ModBlocks.CHEST_BAMBOO.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.BAMBOO_TEXTURE));
        event.register(ModBlocks.CHEST_BALSAM.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.BALSAM_TEXTURE));
        event.register(ModBlocks.CHEST_BAOBAB.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.BAOBAB_TEXTURE));
        event.register(ModBlocks.CHEST_CYPRESS.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.CYPRESS_TEXTURE));
        event.register(ModBlocks.CHEST_EUCALYPTUS.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.EUCALYPTUS_TEXTURE));
        event.register(ModBlocks.CHEST_MAHOGANY.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.MAHOGANY_TEXTURE));
        event.register(ModBlocks.CHEST_MARSHWOOD.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.MARSHWOOD_TEXTURE));
        event.register(ModBlocks.CHEST_MOSSBARK.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.MOSSBARK_TEXTURE));
        event.register(ModBlocks.CHEST_PETRIFIED.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.PETRIFIED_TEXTURE));
        event.register(ModBlocks.CHEST_ROTTEN.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.ROTTEN_TEXTURE));
        event.register(ModBlocks.CHEST_SCORCHED.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.SCORCHED_TEXTURE));
        event.register(ModBlocks.CHEST_VARNISHED.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.VARNISHED_TEXTURE));
        event.register(ModBlocks.CHEST_WHITE.get(), new ErebusChestSpecialRenderer.Unbaked(ErebusChestSpecialRenderer.WHITE_TEXTURE));

        event.register(ModBlocks.BLOCK_OF_BONES.get(), new BlockOfBonesSpecialRenderer.Unbaked(Erebus.prefix("bone_block")));
        event.register(ModBlocks.ALTAR_EXPERIENCE.get(), new ExperienceAltarSpecialRenderer.Unbaked(Erebus.prefix("altar_experience")));
        event.register(ModBlocks.ALTAR_HEALING.get(), new HealingAltarSpecialRenderer.Unbaked(Erebus.prefix("altar_healing")));
        event.register(ModBlocks.ALTAR_LIGHTNING.get(), new LightningAltarSpecialRenderer.Unbaked(Erebus.prefix("altar_lightning")));
        event.register(ModBlocks.ALTAR_REPAIR.get(), new RepairAltarSpecialRenderer.Unbaked(Erebus.prefix("altar_repair")));
        event.register(ModBlocks.OFFERING_ALTAR.get(), new OfferingAltarSpecialRenderer.Unbaked(Erebus.prefix("offering_altar")));
    }
}
