package erebus.events;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class PlantDyeComponents {
    @SubscribeEvent
    public static void modify(ModifyDefaultComponentsEvent event) {
        // Dye tags select ingredients; modern dye recipes also require the color component.
        dye(event, ModBlocks.PETAL_BLACK, DyeColor.BLACK);
        dye(event, ModBlocks.PETAL_RED, DyeColor.RED);
        dye(event, ModBlocks.PETAL_BROWN, DyeColor.BROWN);
        dye(event, ModBlocks.PETAL_BLUE, DyeColor.BLUE);
        dye(event, ModBlocks.PETAL_PURPLE, DyeColor.PURPLE);
        dye(event, ModBlocks.PETAL_CYAN, DyeColor.CYAN);
        dye(event, ModBlocks.PETAL_LIGHT_GRAY, DyeColor.LIGHT_GRAY);
        dye(event, ModBlocks.PETAL_GRAY, DyeColor.GRAY);
        dye(event, ModBlocks.PETAL_PINK, DyeColor.PINK);
        dye(event, ModBlocks.PETAL_YELLOW, DyeColor.YELLOW);
        dye(event, ModBlocks.PETAL_LIGHT_BLUE, DyeColor.LIGHT_BLUE);
        dye(event, ModBlocks.PETAL_MAGENTA, DyeColor.MAGENTA);
        dye(event, ModBlocks.PETAL_ORANGE, DyeColor.ORANGE);
        dye(event, ModBlocks.PETAL_WHITE, DyeColor.WHITE);
        dye(event, ModBlocks.STEM, DyeColor.GREEN);
        dye(event, ModBlocks.GIANT_LILY_PAD, DyeColor.GREEN);
    }

    private static void dye(ModifyDefaultComponentsEvent event, ItemLike item, DyeColor color) {
        event.modify(item, (components, context, key) -> components.set(DataComponents.DYE, color));
    }
}
