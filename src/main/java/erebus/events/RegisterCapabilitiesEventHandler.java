package erebus.events;

import erebus.Erebus;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.item.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.fluid.BucketResourceHandler;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

@EventBusSubscriber(modid = Erebus.MODID)
public class RegisterCapabilitiesEventHandler {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.FLUID_JAR.get(),
                (tile, _) -> tile.getTank()
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.BAMBOO_PIPE.get(),
                (tile, _) -> tile.getTank()
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.BAMBOO_PIPE_EXTRACT.get(),
                (tile, _) -> tile.getTank()
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.LIQUIFIER.get(),
                (tile, _) -> tile.getTank()
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.BLENDER.get(),
                (tile, _) -> tile.tanks
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.UMBERFURNACE.get(),
                (tile, _) -> tile.getTank()
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.UMBERFURNACE.get(),
                WorldlyContainerWrapper::new
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.BLENDER.get(),
                WorldlyContainerWrapper::new
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.OFFERING_ALTAR.get(),
                WorldlyContainerWrapper::new
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.LIQUIFIER.get(),
                WorldlyContainerWrapper::new
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.HONEY_COMB.get(),
                WorldlyContainerWrapper::new
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.BAMBOO_EXTENDER.get(),
                WorldlyContainerWrapper::new
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.BAMBOO_CRATE.get(),
                WorldlyContainerWrapper::new
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.COMPOSTER.get(),
                WorldlyContainerWrapper::new
        );

        event.registerBlockEntity(
                Capabilities.Item.BLOCK,
                ModBlockEntities.SILO_TANK.get(),
                WorldlyContainerWrapper::new
        );

        event.registerItem(
                Capabilities.Fluid.ITEM,
                (_, access) -> new BucketResourceHandler(access),
                ModItems.BAMBUCKET.asItem(),
                ModItems.FORMIC_ACID_BUCKET.asItem(),
                ModItems.HONEY_BUCKET.asItem(),
                ModItems.ANTI_VENOM_BUCKET.asItem(),
                ModItems.BEETLE_JUICE_BUCKET.asItem()
        );
    }
}
