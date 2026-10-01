package erebus.item;

import erebus.Erebus;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.item.context.UseOnContext;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SmoothieBookItem extends WrittenBookItem {
    public SmoothieBookItem() {
        super(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("smoothie_book")))
                .component(DataComponents.WRITTEN_BOOK_CONTENT, createContent()));
    }

    private static WrittenBookContent createContent() {
        List<Filterable<Component>> pages = new ArrayList<>();
        for (int page = 0; page < 17; page++)
            pages.add(new Filterable<>(Component.translatable("erebus.book.smoothie." + page), Optional.empty()));
        return new WrittenBookContent(new Filterable<>("Smoothie-matic 2000", Optional.empty()), "ErebusCo.", 0, pages, true);
    }

    @Override
    public @NonNull InteractionResult onItemUseFirst(@NonNull ItemStack stack, UseOnContext context) {
        var player = context.getPlayer();
        return player == null ? InteractionResult.PASS : use(context.getLevel(), player, context.getHand());
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) {
        return true;
    }
}
