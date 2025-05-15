package erebus.block;

import com.mojang.serialization.MapCodec;

import erebus.inventory.server.PetrifiedCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PetrifiedCraftingTableBlock extends CraftingTableBlock {
    public static final MapCodec<PetrifiedCraftingTableBlock> CODEC = simpleCodec(PetrifiedCraftingTableBlock::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.petrified_crafting_table");

    public PetrifiedCraftingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull MapCodec<? extends CraftingTableBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((containerId, inv, player) -> new PetrifiedCraftingMenu(containerId, inv, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
    }
}
