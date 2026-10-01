package erebus.events;

import erebus.Erebus;
import erebus.client.CustomHelmetExtensions;
import erebus.client.GliderExtensions;
import erebus.client.MaxSpeedBowExtensions;
import erebus.registries.ModFluids;
import erebus.registries.client.ModItemRendering;
import erebus.registries.item.ModItems;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public class RegisterClientExtensionsEventHandler {

    @SubscribeEvent
    public static void registerFluidModels(RegisterFluidModelsEvent event) {
        registerFluidModel(event, "honey", ModFluids.HONEY_STILL, ModFluids.HONEY_FLOW);
        registerFluidModel(event, "anti_venom", ModFluids.ANTI_VENOM_STILL, ModFluids.ANTI_VENOM_FLOW);
        registerFluidModel(event, "beetle_juice", ModFluids.BEETLE_JUICE_STILL, ModFluids.BEETLE_JUICE_FLOW);
        registerFluidModel(event, "formic_acid", ModFluids.FORMIC_ACID_STILL, ModFluids.FORMIC_ACID_FLOW);
    }

    private static void registerFluidModel(RegisterFluidModelsEvent event, String name,
                                           Supplier<? extends Fluid> still, Supplier<? extends Fluid> flowing) {
        event.register(new FluidModel.Unbaked(
                new Material(Erebus.prefix("block/" + name + "_still")),
                new Material(Erebus.prefix("block/" + name + "_flowing")),
                null, null, null), still, flowing);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new MaxSpeedBowExtensions(), ModItems.MAX_SPEED_BOW);
        event.registerItem(new GliderExtensions(false), ModItems.GLIDER_CHESTPLATE);
        event.registerItem(new GliderExtensions(true), ModItems.GLIDER_CHESTPLATE_POWERED);
        event.registerItem(new CustomHelmetExtensions(ModItemRendering.RHINO_HELM, Erebus.prefix("textures/models/armor/rhino_helm.png")), ModItems.RHINO_EXOSKELETON_HELMET);
        event.registerItem(new CustomHelmetExtensions(ModItemRendering.MUSHROOM_HELM, Erebus.prefix("textures/models/armor/mushroom_helm_layer_1.png")), ModItems.MUSHROOM_HELMET);
    }
}
