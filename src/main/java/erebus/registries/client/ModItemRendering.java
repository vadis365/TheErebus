package erebus.registries.client;

import erebus.Erebus;
import erebus.block.fluid.BasicFluidType;
import erebus.client.render.item.model.*;
import erebus.registries.ModFluids;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ModItemRendering {

    public static final ModelLayerLocation WAND_OF_ANIMATION = new ModelLayerLocation(Erebus.prefix("wand_of_animation"), "main");
    public static final ModelLayerLocation PORTAL_ACTIVATOR = new ModelLayerLocation(Erebus.prefix("portal_activator"), "main");
    public static final ModelLayerLocation EREBUS_SHIELD_PARTS = new ModelLayerLocation(Erebus.prefix("erebus_shield_parts"), "main");
    public static final ModelLayerLocation SCORPION_PINCER = new ModelLayerLocation(Erebus.prefix("scorpion_pincer"), "main");
    public static final ModelLayerLocation WAND_OF_PRESERVATION = new ModelLayerLocation(Erebus.prefix("wand_of_preservation"), "main");
    public static final ModelLayerLocation QUAKE_HAMMER = new ModelLayerLocation(Erebus.prefix("quake_hammer"), "main");
    public static final ModelLayerLocation WASP_DAGGER = new ModelLayerLocation(Erebus.prefix("wasp_dagger"), "main");
    public static final ModelLayerLocation WASP_SWORD = new ModelLayerLocation(Erebus.prefix("wasp_sword"), "main");
    public static final ModelLayerLocation WEB_SLINGER = new ModelLayerLocation(Erebus.prefix("web_slinger"), "main");
    public static final ModelLayerLocation FLUID_JAR = new ModelLayerLocation(Erebus.prefix("fluid_jar"), "main");
    public static final ModelLayerLocation LIQUIFIER = new ModelLayerLocation(Erebus.prefix("liquifier"), "main");
    public static final ModelLayerLocation BAMBOO_BRIDGE = new ModelLayerLocation(Erebus.prefix("bamboo_bridge"), "main");
    public static final ModelLayerLocation BAMBOO_EXTENDER = new ModelLayerLocation(Erebus.prefix("bamboo_extender"), "main");
    public static final ModelLayerLocation ARMOR_GLIDER_POWERED = new ModelLayerLocation(Erebus.prefix("armor_glider_powered"), "main");
    public static final ModelLayerLocation ARMOR_GLIDER = new ModelLayerLocation(Erebus.prefix("armor_glider"), "main");
    public static final ModelLayerLocation RHINO_HELM = new ModelLayerLocation(Erebus.prefix("rhino_helm"), "main");
    public static final ModelLayerLocation MUSHROOM_HELM = new ModelLayerLocation(Erebus.prefix("mushroom_helm"), "main");

    public static void registerItemLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WAND_OF_ANIMATION, WandOfAnimationItemModel::createBodyLayer);
        event.registerLayerDefinition(WAND_OF_PRESERVATION, WandOfPreservationModel::createBodyLayer);
        event.registerLayerDefinition(PORTAL_ACTIVATOR, PortalActivatorModel::createBodyLayer);
        event.registerLayerDefinition(EREBUS_SHIELD_PARTS, ErebusShieldPartsModel::createBodyLayer);
        event.registerLayerDefinition(SCORPION_PINCER, ScorpionPincerModel::createBodyLayer);
        event.registerLayerDefinition(QUAKE_HAMMER, QuakeHammerModel::createBodyLayer);
        event.registerLayerDefinition(WASP_DAGGER, WaspDaggerModel::createBodyLayer);
        event.registerLayerDefinition(WASP_SWORD, WaspSwordModel::createBodyLayer);
        event.registerLayerDefinition(WEB_SLINGER, WebSlingerModel::createBodyLayer);
        event.registerLayerDefinition(FLUID_JAR, EmptyModel::createBodyLayer);
        event.registerLayerDefinition(LIQUIFIER, EmptyModel::createBodyLayer);
        event.registerLayerDefinition(BAMBOO_BRIDGE, EmptyModel::createBodyLayer);
        event.registerLayerDefinition(BAMBOO_EXTENDER, EmptyModel::createBodyLayer);
        event.registerLayerDefinition(ARMOR_GLIDER, ArmorGliderModel::createBodyLayer);
        event.registerLayerDefinition(ARMOR_GLIDER_POWERED, ArmorGliderModel::createPoweredBodyLayer);
        event.registerLayerDefinition(RHINO_HELM, RhinoHeadModel::createBodyLayer);
        event.registerLayerDefinition(MUSHROOM_HELM, MushroomHelmModel::createBodyLayer);
    }

    public static void registerItemRender(RegisterClientExtensionsEvent event) {
        //Fluids
        event.registerFluidType(new BasicFluidType(), ModFluids.BEETLE_JUICE_TYPE.get());
        event.registerFluidType(new BasicFluidType(), ModFluids.HONEY_TYPE.get());
        event.registerFluidType(new BasicFluidType(), ModFluids.ANTI_VENOM_TYPE.get());
        event.registerFluidType(new BasicFluidType(), ModFluids.FORMIC_ACID_TYPE.get());
    }
}
