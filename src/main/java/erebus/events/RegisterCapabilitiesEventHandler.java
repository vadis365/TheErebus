package erebus.events;

import erebus.Erebus;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.item.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.BucketResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

@EventBusSubscriber(modid = Erebus.MODID)
public class RegisterCapabilitiesEventHandler {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.FLUID_JAR.get(),
                (_, _) -> new FluidStacksResourceHandler(1, FluidType.BUCKET_VOLUME * 32)
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.BAMBOO_PIPE.get(),
                (_, _) -> new FluidStacksResourceHandler(1, 100)
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.BAMBOO_PIPE_EXTRACT.get(),
                (_, _) -> new FluidStacksResourceHandler(1, 100)
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.LIQUIFIER.get(),
                (_, _) -> new FluidStacksResourceHandler(1, FluidType.BUCKET_VOLUME * 8)
        );

        event.registerBlockEntity(
                Capabilities.Fluid.BLOCK,
                ModBlockEntities.BLENDER.get(),
                (_, _) -> new FluidStacksResourceHandler(4, FluidType.BUCKET_VOLUME * 8)
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
