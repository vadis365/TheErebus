package erebus.registries.client;

import erebus.Erebus;
import erebus.block.fluid.BasicFluidType;
import erebus.client.render.block.renderer.stack.*;
import erebus.client.render.item.model.*;
import erebus.client.render.item.renderer.*;
import erebus.registries.ModFluids;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.AmberBlocks;
import erebus.registries.blocks.providers.ChestBlocks;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.ChestBlock;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

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
        event.registerLayerDefinition(RHINO_HELM, RhinoHeadModel::createBodyLayer);
        event.registerLayerDefinition(MUSHROOM_HELM, MushroomHelmModel::createBodyLayer);
	}

	public static void registerItemRender(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WandOfAnimationItemRenderer(null, null);
            }
        }, ModItems.WAND_OF_ANIMATION.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WandOfPreservationRenderer(null, null);
            }
        }, ModItems.WAND_OF_PRESERVATION.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new PortalActivatorRenderer(null, null);
            }
        }, ModItems.PORTAL_ACTIVATOR.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WaspSwordRenderer(null, null);
            }
        }, ModItems.WASP_SWORD.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WaspDaggerRenderer(null, null);
            }
        }, ModItems.WASP_DAGGER.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new QuakeHammerRenderer(null, null);
            }
        }, ModItems.QUAKE_HAMMER.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ScorpionPincerRenderer(null, null);
            }
        }, ModItems.ENHANCED_SCORPION_PINCER.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WebSlingerRenderer(null, null, false);
            }
        }, ModItems.WEB_SLINGER.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WebSlingerRenderer(null, null, true);
            }
        }, ModItems.WEB_SLINGER_WITHER.get());

        event.registerItem(new IClientItemExtensions() {
                               @Override
                               public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                                   return new ErebusShieldPartsRenderer(null, null);
                               }
                           },
				ModItems.BAMBOO_SHIELD.get(),
				ModItems.JADE_SHIELD.get(),
				ModItems.EXOSKELETON_SHIELD.get(),
				ModItems.REIN_EXOSKELETON_SHIELD.get(),
                ModItems.RHINO_EXOSKELETON_SHIELD.get());
        
        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new OfferingAltarStackItemRenderer(null, null);
            }
        }, OtherBlocks.OFFERING_ALTAR.get().asItem());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new BlockOfBonesStackItemRenderer(null, null);
            }
        }, OtherBlocks.BLOCK_OF_BONES.get().asItem());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new BlenderStackItemRenderer(null, null);
            }
        }, OtherBlocks.BLENDER.get().asItem());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new FluidJarStackItemRenderer(null, null);
            }
        }, AmberBlocks.FLUID_JAR.get().asItem());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new LiquifierStackItemRenderer(null, null);
            }
        }, OtherBlocks.LIQUIFIER.get().asItem());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new GlowingJarStackItemRenderer(null, null);
            }
        }, AmberBlocks.GLOWING_JAR.get().asItem());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new BambooBridgeItemRenderer(null, null);
            }
        }, OtherBlocks.BAMBOO_BRIDGE.get().asItem());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new BambooExtenderItemRenderer(null, null);
            }
        }, OtherBlocks.BAMBOO_EXTENDER.get().asItem());

        registerChestItem(event, ChestBlocks.CHEST_ASPER, "asper_chest");
        registerChestItem(event, ChestBlocks.CHEST_BAOBAB, "baobab_chest");
        registerChestItem(event, ChestBlocks.CHEST_BAMBOO, "bamboo_chest");
        registerChestItem(event, ChestBlocks.CHEST_BALSAM, "balsam_chest");
        registerChestItem(event, ChestBlocks.CHEST_CYPRESS, "cypress_chest");
        registerChestItem(event, ChestBlocks.CHEST_EUCALYPTUS, "eucalyptus_chest");
        registerChestItem(event, ChestBlocks.CHEST_MAHOGANY, "mahogany_chest");
        registerChestItem(event, ChestBlocks.CHEST_MARSHWOOD, "marshwood_chest");
        registerChestItem(event, ChestBlocks.CHEST_MOSSBARK, "mossbark_chest");
        registerChestItem(event, ChestBlocks.CHEST_PETRIFIED, "petrified_chest");
        registerChestItem(event, ChestBlocks.CHEST_ROTTEN, "rotten_chest");
        registerChestItem(event, ChestBlocks.CHEST_SCORCHED, "scorched_chest");
        registerChestItem(event, ChestBlocks.CHEST_VARNISHED, "varnished_chest");
        registerChestItem(event, ChestBlocks.CHEST_WHITE, "white_chest");

        //Fluids
        event.registerFluidType(new BasicFluidType("beetle_juice"), ModFluids.BEETLE_JUICE_TYPE.get());
        event.registerFluidType(new BasicFluidType("honey"), ModFluids.HONEY_TYPE.get());
        event.registerFluidType(new BasicFluidType("anti_venom"), ModFluids.ANTI_VENOM_TYPE.get());
        event.registerFluidType(new BasicFluidType("formic_acid"), ModFluids.FORMIC_ACID_TYPE.get());
	}

	public static void registerItemColors(final RegisterColorHandlersEvent.Item event) {
		event.register((stack, tint) -> {
			var fluid = ((BucketItem) stack.getItem()).content;
			return tint == 1 ? IClientFluidTypeExtensions.of(fluid).getTintColor(new FluidStack(fluid, FluidType.BUCKET_VOLUME)) : -1;
		}, ModItems.FORMIC_ACID_BUCKET, ModItems.HONEY_BUCKET, ModItems.BEETLE_JUICE_BUCKET, ModItems.ANTI_VENOM_BUCKET);
	}

    private static void registerChestItem(RegisterClientExtensionsEvent event, Supplier<ChestBlock> chest, String texture) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ErebusChestStackItemRenderer(null, null, texture);
            }
        }, chest.get().asItem());
    }
}
