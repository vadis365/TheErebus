package erebus.item;

import erebus.Erebus;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.network.Filterable;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.WritableBookItem;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SmoothieBookItem extends WritableBookItem {

    public SmoothieBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
        List<Filterable<Component>> pages = new ArrayList<>();

        for(int c = 0; c < 17; c++) {
            pages.add(new Filterable<>(
                    Component.translatable("%s.book.smoothie.%d".formatted(Erebus.MODID, c)),
                    Optional.empty()
            ));
        }

        WrittenBookContent content = new WrittenBookContent(
                new Filterable<>("Smoothie-matic 2000", Optional.empty()),
                "ErebusCo.",
                0,
                pages,
                true
        );

        book.set(DataComponents.WRITTEN_BOOK_CONTENT, content);
        player.openItemGui(book, hand);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(book, level.isClientSide());
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
