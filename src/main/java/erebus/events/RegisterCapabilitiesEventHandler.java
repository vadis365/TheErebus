package erebus.events;

import de.cech12.bucketlib.api.BucketLibApi;
import erebus.Erebus;
import erebus.block.entity.*;
import erebus.registries.ModItems;
import erebus.registries.blocks.ModBlockEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class RegisterCapabilitiesEventHandler {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.FLUID_JAR.get(), FluidJarBlockEntity::getTank);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.BAMBOO_PIPE.get(), BambooPipeBlockEntity::getTank);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.BAMBOO_PIPE_EXTRACT.get(), BambooPipeExtractBlockEntity::getTank);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.LIQUIFIER.get(), LiquifierBlockEntity::getTank);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.BLENDER.get(), BlenderBlockEntity::getTank0);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.BLENDER.get(), BlenderBlockEntity::getTank1);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.BLENDER.get(), BlenderBlockEntity::getTank2);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.BLENDER.get(), BlenderBlockEntity::getTank3);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.LIQUIFIER.get(), (liquifier, side) -> new InvWrapper(liquifier));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.HONEY_COMB.get(), (honey_comb, side) -> new InvWrapper(honey_comb));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.BAMBOO_EXTENDER.get(), (extender, side) -> new InvWrapper(extender));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.BAMBOO_CRATE.get(), (crate, side) -> new InvWrapper(crate));
        event.registerItem(Capabilities.FluidHandler.ITEM, (stack, ctx) -> new FluidBucketWrapper(stack), ModItems.BEETLE_JUICE_BUCKET.get());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.COMPOSTER.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.SILO_TANK.get(), SidedInvWrapper::new);

        BucketLibApi.registerBucket(event, ModItems.BAMBUCKET.getId());
    }
}
